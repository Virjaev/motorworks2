package de.motorworks.block;

import de.motorworks.block.entity.*;
import de.motorworks.registry.*;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import java.util.EnumMap;
import java.util.Map;

public class CableBlock extends Block implements EntityBlock {
    public static final Map<Direction, BooleanProperty> PROPS = new EnumMap<>(Direction.class);
    static {
        PROPS.put(Direction.NORTH, BlockStateProperties.NORTH);
        PROPS.put(Direction.EAST, BlockStateProperties.EAST);
        PROPS.put(Direction.SOUTH, BlockStateProperties.SOUTH);
        PROPS.put(Direction.WEST, BlockStateProperties.WEST);
        PROPS.put(Direction.UP, BlockStateProperties.UP);
        PROPS.put(Direction.DOWN, BlockStateProperties.DOWN);
    }

    private static final VoxelShape CORE = Block.box(6, 6, 6, 10, 10, 10);
    private static final Map<Direction, VoxelShape> ARMS = new EnumMap<>(Direction.class);
    static {
        ARMS.put(Direction.NORTH, Block.box(6, 6, 0, 10, 10, 6));
        ARMS.put(Direction.SOUTH, Block.box(6, 6, 10, 10, 10, 16));
        ARMS.put(Direction.WEST, Block.box(0, 6, 6, 6, 10, 10));
        ARMS.put(Direction.EAST, Block.box(10, 6, 6, 16, 10, 10));
        ARMS.put(Direction.DOWN, Block.box(6, 0, 6, 10, 6, 10));
        ARMS.put(Direction.UP, Block.box(6, 10, 6, 10, 16, 10));
    }

    public CableBlock(Properties props) {
        super(props);
        BlockState s = stateDefinition.any();
        for (BooleanProperty p : PROPS.values()) s = s.setValue(p, false);
        registerDefaultState(s);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        PROPS.values().forEach(builder::add);
    }

    private BlockState withConnections(Level level, BlockPos pos, BlockState state) {
        for (Direction d : Direction.values()) {
            state = state.setValue(PROPS.get(d), canConnect(level, pos, d));
        }
        return state;
    }

    private boolean canConnect(Level level, BlockPos pos, Direction d) {
        BlockPos np = pos.relative(d);
        Block b = level.getBlockState(np).getBlock();
        if (b instanceof CableBlock || b instanceof WheelBlock) return true;
        return de.motorworks.energy.EnergyUtil.get(level, np, d.getOpposite()) != null;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return withConnections(ctx.getLevel(), ctx.getClickedPos(), defaultBlockState());
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean moved) {
        if (level.isClientSide) return;
        BlockState updated = withConnections(level, pos, state);
        if (updated != state) level.setBlock(pos, updated, 3);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        VoxelShape shape = CORE;
        for (Direction d : Direction.values()) {
            if (state.getValue(PROPS.get(d))) shape = Shapes.or(shape, ARMS.get(d));
        }
        return shape;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CableBlockEntity(pos, state);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide || type != ModBlockEntities.CABLE.get()) return null;
        return (l, p, s, be) -> CableBlockEntity.tick(l, p, s, (CableBlockEntity) be);
    }
}
