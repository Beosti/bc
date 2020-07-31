package com.mod.block_clover.init;


import com.mod.block_clover.Beapi.network.packets.client.CUseAbilityPacket;
import com.mod.block_clover.ModNetwork;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModKeybindings {
    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
        Minecraft minecraft = Minecraft.getInstance();
        PlayerEntity player = minecraft.player;
        ClientWorld world = minecraft.world;

        if (player == null)
            return;


        if (KeyBindingList.spellKeyBinds[0].isPressed())
            ModNetwork.sendToServer(new CUseAbilityPacket(1));

    }
}
//TODO the keybind doesn't get registered, fix it 