package com.mod.block_clover.effects;

import com.mod.block_clover.Block_Clover;
import net.minecraft.item.Items;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Block_Clover.MODID)
public class EffectEvents {
    @SubscribeEvent
    public static void onFoodAte(LivingEntityUseItemEvent.Finish event)
    {
        if(event.getItem().getItem() == Items.GOLDEN_APPLE)
            event.getEntityLiving().curePotionEffects(event.getItem());
    }
    @SubscribeEvent
    public static void onDrinkMilk(PotionEvent.PotionRemoveEvent event)
    {
        boolean isMilkBucket = event.getEntityLiving().getHeldItem(event.getEntityLiving().getActiveHand()).getItem() == Items.MILK_BUCKET;
        if(event.getPotion() == ModEffects.CURSE && isMilkBucket)
            event.setCanceled(true);
    }
}
