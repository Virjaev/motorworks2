package de.motorworks;

import de.motorworks.registry.ModFluidTypes;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = Motorworks.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
    @SubscribeEvent
    public static void onClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private final ResourceLocation still = ResourceLocation.fromNamespaceAndPath(Motorworks.MODID, "block/gasoline_still");
            private final ResourceLocation flow = ResourceLocation.fromNamespaceAndPath(Motorworks.MODID, "block/gasoline_flow");
            private final ResourceLocation overlay = ResourceLocation.withDefaultNamespace("block/water_overlay");

            @Override public ResourceLocation getStillTexture() { return still; }
            @Override public ResourceLocation getFlowingTexture() { return flow; }
            @Override public ResourceLocation getOverlayTexture() { return overlay; }
            @Override public int getTintColor() { return 0xFFFFFFFF; }
        }, ModFluidTypes.GASOLINE_TYPE.get());
    }
}
