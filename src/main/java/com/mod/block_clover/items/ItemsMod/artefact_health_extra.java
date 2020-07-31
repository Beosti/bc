package com.mod.block_clover.items.ItemsMod;

import com.mod.block_clover.Block_Clover;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SUpdateHealthPacket;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class artefact_health_extra extends Item
{
    public artefact_health_extra(){super(new Properties().group(Block_Clover.Black).maxStackSize(1));}

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        player.setActiveHand(hand);
        if(!world.isRemote)
        {
            float a = player.getMaxHealth();
            IAttributeInstance maxHpAttribute = player.getAttribute(SharedMonsterAttributes.MAX_HEALTH);
            maxHpAttribute.setBaseValue(a + 4);
            ((ServerPlayerEntity) player).connection.sendPacket(new SUpdateHealthPacket(player.getHealth(), player.getFoodStats().getFoodLevel(), player.getFoodStats().getSaturationLevel()));
        }
        player.inventory.getCurrentItem().shrink(1);
        return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));

    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity)
    {
        itemStack.shrink(1);
        return itemStack;
    }
}
