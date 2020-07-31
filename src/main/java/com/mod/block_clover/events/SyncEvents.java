package com.mod.block_clover.events;

import com.mod.block_clover.Block_Clover;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Block_Clover.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SyncEvents
{
    @SubscribeEvent
    public static void onHealthArtefactUsed(LivingEntityUseItemEvent event)
    {

    }
}
