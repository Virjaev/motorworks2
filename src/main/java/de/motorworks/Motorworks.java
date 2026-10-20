package de.motorworks;

import de.motorworks.block.entity.*;
import de.motorworks.registry.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@Mod(Motorworks.MODID)
public class Motorworks {
    public static final String MODID = "motorworks";

    public Motorworks(IEventBus bus) {
        ModFluidTypes.FLUID_TYPES.register(bus);
        ModFluids.FLUIDS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModCreativeTabs.TABS.register(bus);
        bus.addListener(this::registerCapabilities);
    }

    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.CABLE.get(), (be, side) -> be.energy);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.LAMP.get(), (be, side) -> be.energy);
        event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, ModBlockEntities.BATTERY.get(), (be, side) -> be.getPort(side));
    }
}
