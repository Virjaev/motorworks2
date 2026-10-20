package de.motorworks.registry;

import de.motorworks.Motorworks;
import de.motorworks.block.entity.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Motorworks.MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<MotorBlockEntity>> MOTOR =
            BLOCK_ENTITIES.register("motor", () -> BlockEntityType.Builder.of(MotorBlockEntity::new, ModBlocks.MOTOR.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<WheelBlockEntity>> WHEEL =
            BLOCK_ENTITIES.register("wheel", () -> BlockEntityType.Builder.of(WheelBlockEntity::new, ModBlocks.WHEEL.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BatteryBlockEntity>> BATTERY =
            BLOCK_ENTITIES.register("battery", () -> BlockEntityType.Builder.of(BatteryBlockEntity::new, ModBlocks.BATTERY.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CableBlockEntity>> CABLE =
            BLOCK_ENTITIES.register("cable", () -> BlockEntityType.Builder.of(CableBlockEntity::new, ModBlocks.CABLE.get()).build(null));
    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<LampBlockEntity>> LAMP =
            BLOCK_ENTITIES.register("lamp", () -> BlockEntityType.Builder.of(LampBlockEntity::new, ModBlocks.LAMP.get()).build(null));
}
