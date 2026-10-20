package de.motorworks.block.entity;

import de.motorworks.block.MotorBlock;
import de.motorworks.block.WheelBlock;
import de.motorworks.energy.EnergyUtil;
import de.motorworks.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class WheelBlockEntity extends BlockEntity {
    public static final int GENERATION = 40; // FE pro Tick

    public WheelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.WHEEL.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, WheelBlockEntity be) {
        boolean driven = false;
        for (Direction d : Direction.values()) {
            BlockState n = level.getBlockState(pos.relative(d));
            if (n.getBlock() instanceof MotorBlock && n.getValue(MotorBlock.LIT)) {
                driven = true;
                break;
            }
        }
        if (state.getValue(WheelBlock.LIT) != driven) {
            level.setBlock(pos, state.setValue(WheelBlock.LIT, driven), 3);
        }
        if (!driven) return;

        int remaining = GENERATION;
        for (Direction d : Direction.values()) {
            if (remaining <= 0) break;
            IEnergyStorage target = EnergyUtil.get(level, pos.relative(d), d.getOpposite());
            if (target != null && target.canReceive()) {
                remaining -= target.receiveEnergy(remaining, false);
            }
        }
    }
}
