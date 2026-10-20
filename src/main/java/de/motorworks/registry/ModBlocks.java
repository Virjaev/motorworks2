package de.motorworks.registry;

import de.motorworks.Motorworks;
import de.motorworks.block.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Motorworks.MODID);

    public static final DeferredBlock<MotorBlock> MOTOR = BLOCKS.registerBlock("motor", MotorBlock::new,
            BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.METAL)
                    .lightLevel(s -> s.getValue(MotorBlock.LIT) ? 7 : 0));

    public static final DeferredBlock<WheelBlock> WHEEL = BLOCKS.registerBlock("wheel", WheelBlock::new,
            BlockBehaviour.Properties.of().strength(2.5f).sound(SoundType.METAL));

    public static final DeferredBlock<BatteryBlock> BATTERY = BLOCKS.registerBlock("battery", BatteryBlock::new,
            BlockBehaviour.Properties.of().strength(3.0f).sound(SoundType.METAL));

    public static final DeferredBlock<CableBlock> CABLE = BLOCKS.registerBlock("cable", CableBlock::new,
            BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.WOOL).noOcclusion());

    public static final DeferredBlock<LampBlock> LAMP = BLOCKS.registerBlock("lamp", LampBlock::new,
            BlockBehaviour.Properties.of().strength(0.5f).sound(SoundType.GLASS)
                    .lightLevel(s -> s.getValue(LampBlock.LIT) ? 15 : 0));

    public static final DeferredBlock<LiquidBlock> GASOLINE_BLOCK = BLOCKS.register("gasoline_block",
            () -> new LiquidBlock(ModFluids.GASOLINE.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER)));
}
