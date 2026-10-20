package de.motorworks.block.entity;

import de.motorworks.block.BatteryBlock;
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
import net.neoforged.neoforge.energy.IEnergyStorage;

public class BatteryBlockEntity extends BlockEntity {
    public static final int CAPACITY = 50000;
    public static final int MAX_IO = 200;

    public final SimpleEnergy energy = new SimpleEnergy(CAPACITY, MAX_IO, MAX_IO);
    private final Port inPort = new Port(false);
    private final Port outPort = new Port(true);

    public BatteryBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BATTERY.get(), pos, state);
    }

    /** Front (orange) = Ausgang, alle anderen Seiten = Eingang. */
    public IEnergyStorage getPort(Direction side) {
        if (side != null && side == getBlockState().getValue(BatteryBlock.FACING)) return outPort;
        return inPort;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, BatteryBlockEntity be) {
        if (be.energy.getEnergyStored() <= 0) return;
        Direction out = state.getValue(BatteryBlock.FACING);
        if (EnergyUtil.push(level, pos, out, be.outPort, MAX_IO, false) > 0) be.setChanged();
    }

    private class Port implements IEnergyStorage {
        private final boolean output;
        Port(boolean output) { this.output = output; }
        @Override public int receiveEnergy(int a, boolean sim) { return output ? 0 : energy.receiveEnergy(a, sim); }
        @Override public int extractEnergy(int a, boolean sim) { return output ? energy.extractEnergy(a, sim) : 0; }
        @Override public int getEnergyStored() { return energy.getEnergyStored(); }
        @Override public int getMaxEnergyStored() { return energy.getMaxEnergyStored(); }
        @Override public boolean canExtract() { return output; }
        @Override public boolean canReceive() { return !output; }
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
