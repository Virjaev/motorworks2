package de.motorworks.block.entity;

import de.motorworks.block.LampBlock;
import de.motorworks.energy.SimpleEnergy;
import de.motorworks.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class LampBlockEntity extends BlockEntity {
    public static final int USE_PER_TICK = 4;
    public final SimpleEnergy energy = new SimpleEnergy(100, 100, 0);

    public LampBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.LAMP.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, LampBlockEntity be) {
        boolean on = be.energy.getEnergyStored() >= USE_PER_TICK;
        if (on) be.energy.drain(USE_PER_TICK);
        if (state.getValue(LampBlock.LIT) != on) {
            level.setBlock(pos, state.setValue(LampBlock.LIT, on), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.put("energy", energy.serializeNBT(registries));
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        if (tag.contains("energy")) energy.deserializeNBT(registries, tag.get("energy"));
    }
}
