package com.mod.block_clover;

import com.mod.block_clover.Beapi.network.Network;
import com.mod.block_clover.Beapi.network.packets.client.CAbilityDataSyncPacket;
import com.mod.block_clover.Beapi.network.packets.client.CUseAbilityPacket;
import com.mod.block_clover.Beapi.network.packets.server.SSyncAbilityDataPacket;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import javax.annotation.Resource;

public class ModNetwork
{
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    public static SimpleChannel channel = NetworkRegistry.ChannelBuilder.named(new ResourceLocation(Block_Clover.MODID, "main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

    public static void init()
    {
        int packet = 0;

        //client
        channel.registerMessage(packet++, CUseAbilityPacket.class, CUseAbilityPacket::encode, CUseAbilityPacket::decode, CUseAbilityPacket::handle);
        channel.registerMessage(packet++, CAbilityDataSyncPacket.class, CAbilityDataSyncPacket::encode, CAbilityDataSyncPacket::decode, CAbilityDataSyncPacket::handle);

        //server
        channel.registerMessage(packet++, SSyncAbilityDataPacket.class, SSyncAbilityDataPacket::encode, SSyncAbilityDataPacket::decode, SSyncAbilityDataPacket::handle);
    }

    public static <MSG> void sendToServer(MSG msg)
    {
        Network.sendToServer(channel, msg);
    }

    public static <MSG> void sendTo(MSG msg, ServerPlayerEntity player)
    {
        Network.sendTo(msg, player);
    }

    public static <MSG> void sendToAll(MSG msg)
    {
        Network.sendToAll(channel, msg);
    }

    public static <MSG> void sendToAllAround(MSG msg, LivingEntity sender)
    {
        Network.sendToAllAround(channel, msg, sender);
    }
}
