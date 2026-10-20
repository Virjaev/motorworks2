package de.motorworks.registry;

import de.motorworks.Motorworks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Motorworks.MODID);

    public static final net.neoforged.neoforge.registries.DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN =
            TABS.register("main", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.motorworks"))
                    .icon(() -> new ItemStack(ModItems.MOTOR.get()))
                    .displayItems((params, out) -> {
                        out.accept(ModItems.MOTOR.get());
                        out.accept(ModItems.GASOLINE_BUCKET.get());
                        out.accept(ModItems.WHEEL.get());
                        out.accept(ModItems.BATTERY.get());
                        out.accept(ModItems.CABLE.get());
                        out.accept(ModItems.LAMP.get());
                    })
                    .build());
}
