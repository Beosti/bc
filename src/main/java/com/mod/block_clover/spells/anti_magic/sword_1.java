package com.mod.block_clover.spells.anti_magic;

import com.mod.block_clover.Beapi.SpellCategory;
import com.mod.block_clover.Beapi.abilities.Spell;
import com.mod.block_clover.items.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

//TODO check what slot the player is holding that's empty atm and THEN put the sword in that slot setInventorySlotContents(int, stack) where int is the slot from 0-9 and you also have a variable named currentItem


public class sword_1 extends Spell
{
    public static final Spell INSTANCE = new sword_1();

    public sword_1()
    {
        super("Sword", SpellCategory.ANTI_MAGIC);
        this.setMaxCooldown(5.0D);
        this.setDescription("The anti magic sword appears");
        this.onUseEvent = this::onUseEvent;
    }

    private boolean onUseEvent(PlayerEntity player)
    {
        ItemStack Demon = new ItemStack(ModItems.demon_dweller, 1);
        if (!Minecraft.getInstance().player.inventory.hasItemStack(new ItemStack(ModItems.demon_dweller)))
        {

            // you should get demo
            Minecraft.getInstance().player.inventory.addItemStackToInventory(Demon);
        }
        else
        {
            ItemStack check = Minecraft.getInstance().player.inventory.getCurrentItem();
            if (check.getItem() == ModItems.demon_dweller)
            {
                Minecraft.getInstance().player.inventory.getCurrentItem().shrink(1);
            }
            //you should loose demo

        }
        return true;
    }
}
