package com.mod.block_clover;

import com.mod.block_clover.items.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BlackGroup extends ItemGroup {
    public BlackGroup()
    {
        super("tutorial");
    }
    @Override
    public ItemStack createIcon()
    {
        //here we say that the icon of the block is the icon of the fire bat
        return new ItemStack(ModItems.Yami);
    }
}
