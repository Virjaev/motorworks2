package de.motorworks.registry;

import de.motorworks.Motorworks;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Motorworks.MODID);

    public static final DeferredItem<BlockItem> MOTOR = ITEMS.registerSimpleBlockItem(ModBlocks.MOTOR);
    public static final DeferredItem<BlockItem> WHEEL = ITEMS.registerSimpleBlockItem(ModBlocks.WHEEL);
    public static final DeferredItem<BlockItem> BATTERY = ITEMS.registerSimpleBlockItem(ModBlocks.BATTERY);
    public static final DeferredItem<BlockItem> CABLE = ITEMS.registerSimpleBlockItem(ModBlocks.CABLE);
    public static final DeferredItem<BlockItem> LAMP = ITEMS.registerSimpleBlockItem(ModBlocks.LAMP);

    public static final DeferredItem<BucketItem> GASOLINE_BUCKET = ITEMS.register("gasoline_bucket",
            () -> new BucketItem(ModFluids.GASOLINE.get(),
                    new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
}
