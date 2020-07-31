package com.mod.block_clover.structures;

import com.mod.block_clover.Block_Clover;
import com.mod.block_clover.init.ModStructures;
import java.util.List;
import java.util.Random;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;



public class HousePiece extends TemplateStructurePiece {
    private static final BlockPos STRUCTURE_OFFSET = new BlockPos(4, 0, 15);
    private final Rotation rotation;
    private final ResourceLocation resource;
    public ChunkPos chunkpos;

    public HousePiece(TemplateManager temp, IStructurePieceType p_i51339_1_, CompoundNBT p_i51339_2_) {
        super(p_i51339_1_, p_i51339_2_);
        this.boundingBox = new MutableBoundingBox();
        this.rotation = Rotation.CLOCKWISE_90;
        this.resource = new ResourceLocation(Block_Clover.MODID, "house");
        this.func_204754_a(temp);
    }

    public HousePiece(TemplateManager temp, CompoundNBT nbt) {
        super(ModStructures.HOUSE_PIECE_TYPE, nbt);
        this.rotation = Rotation.CLOCKWISE_90;
        this.resource = new ResourceLocation(Block_Clover.MODID, "house");
        this.func_204754_a(temp);
    }

    public HousePiece(TemplateManager temp, IStructurePieceType type, int value) {
        super(type, value);
        this.boundingBox = new MutableBoundingBox();
        this.rotation = Rotation.CLOCKWISE_90;
        this.resource = new ResourceLocation(Block_Clover.MODID, "house");
        this.func_204754_a(temp);
    }

    @Override
    protected void readAdditional(CompoundNBT tagCompound) {
        tagCompound.putInt("TPX", this.templatePosition.getX());
        tagCompound.putInt("TPY", this.templatePosition.getY());
        tagCompound.putInt("TPZ", this.templatePosition.getZ());
    }

    private void func_204754_a(TemplateManager p_204754_1_) {
        Template template = p_204754_1_.getTemplateDefaulted(this.resource);
        PlacementSettings placementsettings = (new PlacementSettings())
                .setRotation(this.rotation)
                .setMirror(Mirror.NONE)
                .setCenterOffset(HousePiece.STRUCTURE_OFFSET)
                .addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
        this.setup(template, this.templatePosition, placementsettings);
    }

    public static void addPieces(TemplateManager temp, List<StructurePiece> values, CompoundNBT nbt, Random rand) {
        if (rand.nextInt(500) > 498)
                        values.add(new HousePiece(temp, ModStructures.HOUSE_PIECE_TYPE, nbt));


    }


    @Override
    protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {

    }
}
