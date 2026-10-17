package net.mcreator.alien_craft;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/**
 * Rebuilt Forge 1.20.1 entry point. Legacy MCreator classes targeted 1.12.2 APIs and
 * cannot be loaded by modern Forge; this port registers the recoverable catalog and assets.
 */
@Mod(AlienCraft.MOD_ID)
public final class AlienCraft {
    public static final String MOD_ID = "alien_craft";
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    private static RegistryObject<Block> block(String id, float hardness) {
        RegistryObject<Block> result = BLOCKS.register(id, () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(hardness)));
        ITEMS.register(id, () -> new BlockItem(result.get(), new Item.Properties()));
        return result;
    }
    private static RegistryObject<Item> item(String id) { return ITEMS.register(id, () -> new Item(new Item.Properties())); }

    public static final RegistryObject<Block> ALIENCRATE = block("aliencrate", 2.5f);
    public static final RegistryObject<Block> ALIENFLOOR = block("alienfloor", 2.0f);
    public static final RegistryObject<Block> ALIENFLOORSTAIRS = BLOCKS.register("alienfloorstairs", () -> new StairBlock(() -> ALIENFLOOR.get().defaultBlockState(), BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_PURPLE).strength(2.0f)));
    public static final RegistryObject<Block> ALIENFLOWER = BLOCKS.register("alienflower", () -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).noCollission().instabreak()));
    public static final RegistryObject<Block> ALIENLEAVES = block("alienleaves", 0.2f);
    public static final RegistryObject<Block> ANCIENTALIENTEXT = block("ancientalientext", 3.0f);
    public static final RegistryObject<Block> ASTEROIDBLOCK = block("asteroidblock", 3.0f);
    public static final RegistryObject<Block> BLOCKOFSTEEL = block("blockofsteel", 5.0f);
    public static final RegistryObject<Block> PLATINUMORE = block("platinumore", 3.0f);
    public static final RegistryObject<Block> TUBE = block("tube", 2.0f);
    public static final RegistryObject<Block> ALIENPLANETSELECTOR = block("alienplanetselector", 3.0f);
    public static final RegistryObject<Block> DOOR = block("door", 2.0f);
    public static final RegistryObject<Item> ALIENFLOORSTAIRS_ITEM = ITEMS.register("alienfloorstairs", () -> new BlockItem(ALIENFLOORSTAIRS.get(), new Item.Properties()));
    public static final RegistryObject<Item> ALIENFLOWER_ITEM = ITEMS.register("alienflower", () -> new BlockItem(ALIENFLOWER.get(), new Item.Properties()));

    public static final RegistryObject<Item> DARKMATTER = item("darkmatter");
    public static final RegistryObject<Item> IRONROD = item("ironrod");
    public static final RegistryObject<Item> PLATINUMINGOT = item("platinumingot");
    public static final RegistryObject<Item> PLATINUMPLATE = item("platinumplate");
    public static final RegistryObject<Item> UFOICON = item("ufoicon");
    public static final RegistryObject<Item> WOW = item("wow");
    public static final RegistryObject<Item> ALIENSWORD = ITEMS.register("aliensword", () -> new SwordItem(Tiers.DIAMOND, 3, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> LIGHTNINGSWORD = ITEMS.register("lightningsword", () -> new SwordItem(Tiers.DIAMOND, 4, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> SPACESWORD2 = ITEMS.register("spacesword2", () -> new SwordItem(Tiers.NETHERITE, 5, -2.4f, new Item.Properties()));
    public static final RegistryObject<Item> ALIEN = item("alien");
    public static final RegistryObject<Item> ALIENCIVIL = item("aliencivil");
    public static final RegistryObject<Item> LONGALIEN = item("longalien");
    public static final RegistryObject<Item> LONGLAIENGUARD = item("longlaienguard");
    public static final RegistryObject<Item> ZOMBIEALIEN = item("zombiealien");
    public static final RegistryObject<Item> UFO = item("ufo");

    public static final RegistryObject<CreativeModeTab> MAIN_TAB = TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.alien_craft"))
            .icon(() -> new ItemStack(PLATINUMINGOT.get()))
            .displayItems((parameters, output) -> ITEMS.getEntries().forEach(entry -> output.accept(entry.get())))
            .build());

    public AlienCraft() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modBus);
        ITEMS.register(modBus);
        TABS.register(modBus);
    }
}
