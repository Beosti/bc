package com.mod.block_clover;

import com.mod.block_clover.blocks.ModBlocks;
import com.mod.block_clover.effects.ModEffects;
import com.mod.block_clover.init.KeyBindingList;
import com.mod.block_clover.init.ModKeybindings;
import com.mod.block_clover.init.ModStructures;
import com.mod.block_clover.items.ItemsMod.*;
import com.mod.block_clover.items.ModItems;
import com.mod.block_clover.lists.Armourlist;
import com.mod.block_clover.lists.Toollist;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.mod.block_clover.items.ModItems.cloack;
import static com.mod.block_clover.items.ModItems.nomotatoes;


@Mod(Block_Clover.MODID)
public class Block_Clover
{
    private static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "block_clover";

    public static final ItemGroup Black = new BlackGroup();


    public Block_Clover()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModEffects.EFFECTS.register(modEventBus);
    }


    private void clientSetup(FMLClientSetupEvent event)
    {
    }
    private void setup(FMLCommonSetupEvent Event)
    {
        KeyBindingList.register();
        MinecraftForge.EVENT_BUS.register(new ModKeybindings());
    }
    private void commonSetup(FMLCommonSetupEvent event) {
        ForgeRegistries.BIOMES.getValues().stream().forEach((biome -> {
            if (biome.getCategory() == Biome.Category.FOREST || biome.getCategory() == Biome.Category.PLAINS) {
                biome.addStructure(ModStructures.HOUSE, IFeatureConfig.NO_FEATURE_CONFIG);
                biome.addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, Biome.createDecoratedFeature(ModStructures.HOUSE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.NOPE, IPlacementConfig.NO_PLACEMENT_CONFIG));
            }
        }));
    }
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll
                    (
                            ModBlocks.firstblock = new Block(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f, 3.0f).lightValue(5).sound(SoundType.METAL)).setRegistryName("firstblock")
                    );
        }


        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            event.getRegistry().registerAll
                    (
                            ModItems.firstblock = new BlockItem(ModBlocks.firstblock, new Item.Properties().group(Black)).setRegistryName("firstblock"),
                            ModItems.Yami = new Yami(Toollist.Yami, 1, -2.7f, new Item.Properties().group(Black)).setRegistryName("yami"),
                            cloack = new cloack(Armourlist.cloak, EquipmentSlotType.CHEST, new Item.Properties().group(Black)).setRegistryName("cloack"),
                            ModItems.refined_iron = new Item(new Item.Properties().group(Black)).setRegistryName("refined_iron"),
                            ModItems.Fire_bat = new Fire_bat(Toollist.Fire_Bat, 0, -3f, new Item.Properties().group(Black)).setRegistryName("fire_bat"),
                            ModItems.demon_dweller = new demon_dweller(Toollist.demon_dweller, 0, 0f, new Item.Properties().group(Black)).setRegistryName("demon_dweller"),
                            ModItems.five_leaf_grimuaru = new five_leaf(new Item.Properties().group(Black)).setRegistryName("five_leaf"),
                            nomotatoes = new Item(new Item.Properties().group(Black).food(new Food.Builder().saturation(0).hunger(2).effect(new EffectInstance(ModEffects.NOMOTATOES,1, 1, false, false), 100).build())).setRegistryName("nomotatoes"),
                            ModItems.coocked_nomotatoes = new Item(new Item.Properties().group(Black).food(new Food.Builder().saturation(5).hunger(5).build())).setRegistryName("coocked_nomotatoes"),
                            ModItems.artefact_health_regen = new artefact_health_regen().setRegistryName("artefact_healing"),
                            ModItems.artefact_health_extra = new artefact_health_extra().setRegistryName("artefact_health_extra")
            );
        }
    }
}
