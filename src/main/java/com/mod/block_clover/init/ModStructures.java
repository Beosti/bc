package com.mod.block_clover.init;

import com.mod.block_clover.Block_Clover;
import com.mod.block_clover.structures.HousePiece;
import com.mod.block_clover.structures.HouseStructure;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraftforge.registries.IForgeRegistry;


public class ModStructures {

    public static final Structure<NoFeatureConfig> HOUSE = new HouseStructure(NoFeatureConfig::deserialize);

    public static final Feature<?> HOUSEFEATURE  = HOUSE.setRegistryName(Block_Clover.MODID, "house");
    public static IStructurePieceType HOUSE_PIECE_TYPE;


    public static void registerStructure(IForgeRegistry<Feature<?>> registry) {
        registry.register(HOUSEFEATURE);
        HOUSE_PIECE_TYPE = IStructurePieceType.register(HousePiece::new, "HP");
    }

}
