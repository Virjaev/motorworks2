package de.motorworks.block.entity;

import de.motorworks.block.MotorBlock;
import de.motorworks.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MotorBlockEntity extends BlockEntity {
    public static final int MAX_FUEL = 24000;      // Ticks (20 Ticks = 1 Sekunde)
    public static final int FUEL_PER_BUCKET = 6000; // 5 Minuten pro Eimer

    private int fuel = 0;

    public MotorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MOTOR.get(), pos, state);
    }

    public int getFuel() { return fuel; }

    public boolean addFuel(int amount) {
        if (fuel + amount > MAX_FUEL) return false;
        fuel += amount;
        setChanged();
        return true;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, MotorBlockEntity be) {
        boolean running = be.fuel > 0;
        if (running) {
            be.fuel--;
            if (be.fuel % 40 == 0) be.setChanged();
            if (level instanceof ServerLevel sl && level.getGameTime() % 10 == 0) {
                sl.sendParticles(ParticleTypes.SMOKE, pos.getX() + 0.5, pos.getY() + 1.05, pos.getZ() + 0.5, 1, 0.1, 0.0, 0.1, 0.01);
            }
        }
        if (state.getValue(MotorBlock.LIT) != running) {
            level.setBlock(pos, state.setValue(MotorBlock.LIT, running), 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putInt("fuel", fuel);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        fuel = tag.getInt("fuel");
    }
}
