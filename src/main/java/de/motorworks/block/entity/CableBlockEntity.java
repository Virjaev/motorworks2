package de.motorworks.block.entity;

import de.motorworks.energy.EnergyUtil;
import de.motorworks.energy.SimpleEnergy;
import de.motorworks.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CableBlockEntity extends BlockEntity {
    public final SimpleEnergy energy = new SimpleEnergy(500, 500, 500);

    public CableBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CABLE.get(), pos, state);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CableBlockEntity be) {
        if (be.energy.getEnergyStored() <= 0) return;
        for (Direction d : Direction.values()) {
            EnergyUtil.push(level, pos, d, be.energy, 250, true);
        }
        be.setChanged();
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
