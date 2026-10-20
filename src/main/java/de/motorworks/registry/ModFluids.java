package de.motorworks.registry;

import de.motorworks.Motorworks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Motorworks.MODID);

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> GASOLINE =
            FLUIDS.register("gasoline", () -> new BaseFlowingFluid.Source(ModFluids.PROPS));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> GASOLINE_FLOWING =
            FLUIDS.register("flowing_gasoline", () -> new BaseFlowingFluid.Flowing(ModFluids.PROPS));

    public static final BaseFlowingFluid.Properties PROPS =
            new BaseFlowingFluid.Properties(ModFluidTypes.GASOLINE_TYPE, GASOLINE, GASOLINE_FLOWING)
                    .bucket(ModItems.GASOLINE_BUCKET)
                    .block(ModBlocks.GASOLINE_BLOCK);
}
