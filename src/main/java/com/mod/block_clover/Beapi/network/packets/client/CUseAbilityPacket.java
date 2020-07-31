package com.mod.block_clover.Beapi.network.packets.client;

import com.mod.block_clover.Beapi.abilities.Spell;
import com.mod.block_clover.Beapi.data.ability.AbilityDataCapability;
import com.mod.block_clover.Beapi.data.ability.IAbilityData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CUseAbilityPacket
{
    private int abilitySlot;

    public CUseAbilityPacket() {}

    public CUseAbilityPacket(int abilitySlot)
    {
        this.abilitySlot = abilitySlot;
    }

    public void encode(PacketBuffer buffer)
    {
        buffer.writeInt(this.abilitySlot);
    }

    public static CUseAbilityPacket decode(PacketBuffer buffer)
    {
        CUseAbilityPacket msg = new CUseAbilityPacket();
        msg.abilitySlot = buffer.readInt();
        return msg;
    }

    public static void handle(CUseAbilityPacket message, final Supplier<NetworkEvent.Context> ctx)
    {
        //Minecraft.getInstance().profiler.startSection("ability_use");

        if(ctx.get().getDirection() == NetworkDirection.PLAY_TO_SERVER)
        {
            ctx.get().enqueueWork(() ->
            {
                PlayerEntity player = ctx.get().getSender();
                IAbilityData abilityDataProps = AbilityDataCapability.get(player);


                Spell currentAbility = abilityDataProps.getEquippedAbility(message.abilitySlot);

                if (currentAbility != null)
                {
                        currentAbility.use(player);
                }
            });
        }

        ctx.get().setPacketHandled(true);
    }
}
//TODO i'm not sure if it works, would have to figure out how tf i make it so it's the slot that gets used