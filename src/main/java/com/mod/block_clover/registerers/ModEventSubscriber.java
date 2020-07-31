package com.mod.block_clover.registerers;


import com.mod.block_clover.Block_Clover;
import com.mod.block_clover.init.ModStructures;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Block_Clover.MODID, bus = EventBusSubscriber.Bus.MOD)
public final class ModEventSubscriber
{
    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(Block_Clover.MODID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }

    @SubscribeEvent
    public static void onFeatureRegistry(final RegistryEvent.Register<Feature<?>> event) {
        IForgeRegistry<Feature<?>> registry = event.getRegistry();

        ModStructures.registerStructure(registry);
    }


    public static void registerEntityWorldSpawn(EntityType<?> type, int weight, int maxGroup, Biome... biomes) {
        for(Biome biome : biomes) {
            if(biome != null) {
                biome.getSpawns(type.getClassification()).add(new Biome.SpawnListEntry(type, weight, 1, maxGroup));

            }
        }
    }
}
