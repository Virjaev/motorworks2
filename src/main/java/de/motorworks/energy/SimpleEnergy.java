package de.motorworks.energy;

import net.neoforged.neoforge.energy.EnergyStorage;

/** EnergyStorage mit internem Verbrauch (drain). */
public class SimpleEnergy extends EnergyStorage {
    public SimpleEnergy(int capacity, int maxReceive, int maxExtract) {
        super(capacity, maxReceive, maxExtract);
    }

    public void drain(int amount) {
        this.energy = Math.max(0, this.energy - amount);
    }
}
