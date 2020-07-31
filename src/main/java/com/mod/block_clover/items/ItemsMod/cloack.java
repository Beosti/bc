package com.mod.block_clover.items.ItemsMod;

import net.minecraft.block.DispenserBlock;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;

public class cloack extends ArmorItem {

    public cloack(IArmorMaterial materialIn, EquipmentSlotType slot, Item.Properties builder) {
        super(materialIn, slot, builder);
        DispenserBlock.registerDispenseBehavior(this, DISPENSER_BEHAVIOR);
    }

}
