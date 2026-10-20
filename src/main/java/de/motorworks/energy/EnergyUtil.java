package de.motorworks.energy;

import de.motorworks.block.CableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.energy.IEnergyStorage;

public class EnergyUtil {
    public static IEnergyStorage get(Level level, BlockPos pos, Direction side) {
        return level.getCapability(Capabilities.EnergyStorage.BLOCK, pos, side);
    }

    /**
     * Schiebt Energie von 'from' in Richtung 'dir'. Kabel geben an andere Kabel nur ab,
     * wenn dort weniger Energie liegt (Gefaelle), damit der Strom nicht hin und her pendelt.
     */
    public static int push(Level level, BlockPos pos, Direction dir, IEnergyStorage from, int max, boolean gradient) {
        BlockPos np = pos.relative(dir);
        IEnergyStorage to = get(level, np, dir.getOpposite());
        if (to == null || !to.canReceive()) return 0;
        if (gradient && level.getBlockState(np).getBlock() instanceof CableBlock
                && to.getEnergyStored() >= from.getEnergyStored()) return 0;
        int ext = from.extractEnergy(max, true);
        if (ext <= 0) return 0;
        int acc = to.receiveEnergy(ext, false);
        from.extractEnergy(acc, false);
        return acc;
    }
}
