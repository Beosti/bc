package com.mod.block_clover.Beapi.network;

import com.mod.block_clover.Beapi.APIConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.simple.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class Network
{
    private static int packet = 0;
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(APIConfig.PROJECT_ID, "main_channel"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );
    //NetworkRegistry.ChannelBuilder.named(new ResourceLocation(APIConfig.PROJECT_ID, "main_channel")).clientAcceptedVersions(PROTOCOL_VERSION::equals).serverAcceptedVersions(PROTOCOL_VERSION::equals).networkProtocolVersion(() -> PROTOCOL_VERSION).simpleChannel();

    public static <MSG> void registerPacket(Class<MSG> messageType, BiConsumer<MSG, PacketBuffer> encoder, Function<PacketBuffer, MSG> decoder, BiConsumer<MSG, Supplier<NetworkEvent.Context>> messageConsumer)
    {
        INSTANCE.registerMessage(packet++, messageType, encoder, decoder, messageConsumer);
    }

    public static <MSG> void sendToServer(SimpleChannel channel, MSG msg)
    {
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendTo(MSG msg, ServerPlayerEntity player)
    {
        if (!(player instanceof ServerPlayerEntity))
            return;

        INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) player), msg);
    }

    public static <MSG> void sendToAll(SimpleChannel channel, MSG msg)
    {
        channel.send(PacketDistributor.ALL.noArg(), msg);
    }

    public static <MSG> void sendToAllAround(SimpleChannel channel, MSG msg, LivingEntity sender)
    {
        try
        {
            channel.send(PacketDistributor.NEAR.with(() -> new PacketDistributor.TargetPoint(sender.posX, sender.posY, sender.posZ, 50, sender.dimension)), msg);
            if(sender instanceof ServerPlayerEntity)
                sendTo(msg, (ServerPlayerEntity) sender);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return;
        }
    }
}
