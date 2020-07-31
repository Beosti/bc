package com.mod.block_clover.structures;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;


public class HouseStructure extends Structure<NoFeatureConfig> {
    public HouseStructure(Function<Dynamic<?>, ? extends NoFeatureConfig> p_i51470_1_) {
        super(p_i51470_1_);
    }

    public String getStructureName() {
        return "House";
    }

    public int getSize() {
        return 10;
    }

    public boolean hasStartAt(ChunkGenerator<?> chunkGen, Random rand, int chunkPosX, int chunkPosZ) {


        if(rand.nextInt(10) > 8) {
            return true;
        }
        return false;
    }

    public Structure.IStartFactory getStartFactory(){
        return HouseStructure.Start::new;
    }

    protected int getSeedModifier()
    {
        return 165745296;
    }

    public static class Start extends StructureStart {
        public Start(Structure<?> p_i50497_1_, int p_i50497_2_, int p_i50497_3_, Biome p_i50497_4_, MutableBoundingBox p_i50497_5_, int p_i50497_6_, long p_i50497_7_) {
            super(p_i50497_1_, p_i50497_2_, p_i50497_3_, p_i50497_4_, p_i50497_5_, p_i50497_6_, p_i50497_7_);
        }


        public void init(ChunkGenerator<?> generator, TemplateManager templateManagerIn, int chunkX, int chunkZ, Biome biomeIn) {
            BlockPos blockpos = new BlockPos(chunkX * 16, 90, chunkZ * 16);
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("TPX", blockpos.getX());
            nbt.putInt("TPY", blockpos.getY());
            nbt.putInt("TPZ", blockpos.getZ());

            HousePiece.addPieces(templateManagerIn, this.components, nbt, this.rand);
            this.recalculateStructureSize();
        }
    }
}
