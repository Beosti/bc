package com.mod.block_clover.Beapi.abilities;


import com.mod.block_clover.Beapi.network.Network;
import com.mod.block_clover.Beapi.data.ability.AbilityDataCapability;
import com.mod.block_clover.Beapi.data.ability.IAbilityData;
import com.mod.block_clover.Beapi.SpellCategory;
import com.mod.block_clover.Beapi.network.packets.server.SSyncAbilityDataPacket;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.registries.ForgeRegistryEntry;



import java.io.Serializable;

import net.minecraft.entity.player.PlayerEntity;

public class Spell extends ForgeRegistryEntry<Spell>
{
    private String name = "";
    private String desc = "";
    protected double cooldown;
    protected double maxCooldown;
    private SpellCategory category = SpellCategory.ALL;
    private State state = State.STANDBY;
    protected IOnUse onUseEvent;
    protected IDuringCooldown duringCooldownEvent;
    protected IOnEndCooldown onEndCooldown;

    // Setting the defaults so that no crash occurs and so they will be null safe.
    public Spell(String name, SpellCategory category) {
        this.category = SpellCategory.ALL;
        this.state = Spell.State.STANDBY;
        this.onUseEvent = (player) -> {
            return true;
        };
        this.duringCooldownEvent = (player, cooldown) -> {
        };
        this.onEndCooldown = (player) -> {
        };
        this.name = name;
        this.category = category;
    }

    public static enum State
    {
        STANDBY,
        DISABLED,
        COOLDOWN,
        PASSIVE,
        CHARGING
    }

    public boolean isOnCooldown()
    {
        return this.state == State.COOLDOWN;
    }
    public double getCooldown()
    {
        return this.cooldown;
    }
    public boolean isOnStandby()
    {
        return this.state == State.STANDBY;
    }
    public void startCooldown()
    {
        this.state = State.COOLDOWN;
    }
    public boolean isPassiveActive()
    {
        return this.state == State.PASSIVE;
    }
    public String setDescription(String desc){return this.desc;}
    public void setMaxCooldown(double cooldown)
    {
        this.maxCooldown = cooldown * 20.0D;
        this.cooldown = this.maxCooldown;
    }
    public void stopCooldown(PlayerEntity player)
    {
        if(player.world.isRemote)
            return;
        this.cooldown = this.maxCooldown;
        this.state = State.STANDBY;
        this.onEndCooldown.onEndCooldown(player);
        IAbilityData props = AbilityDataCapability.get(player);
        Network.sendTo(new SSyncAbilityDataPacket(props), (ServerPlayerEntity)player);
    }
    public void cooldown(PlayerEntity player)
    {
        if(this.isOnCooldown() && this.cooldown > 0)
        {
            --this.cooldown;
            if (!player.world.isRemote)
            {
                this.duringCooldownEvent.duringCooldown(player, (int) this.cooldown);
            }
        }
        else if(this.isOnCooldown() && this.cooldown <= 0.0D)
        {
            this.stopCooldown(player);
        }
    }
    public boolean isCharging()
    {
        return this.state == State.CHARGING;
    }
    public boolean isDisabled()
    {
        return this.state == State.DISABLED;
    }
    public void setState(State state)
    {
        this.state = state;
    }
    public State getState()
    {
        return this.state;
    }




    public void use(PlayerEntity player)
    {
        if (player.world.isRemote)
            return;
        if(this.isOnCooldown() && this.getCooldown() <= 10)
            this.stopCooldown(player);
        if(!this.isOnStandby())
            return;
        if(this.onUseEvent.onUse(player))
        {
            this.startCooldown();
            IAbilityData props = AbilityDataCapability.get(player);
            Network.sendTo(new SSyncAbilityDataPacket(props), (ServerPlayerEntity)player);
        }
    }
    public SpellCategory getCategory()
    {
        return this.category;
    }


    
    public interface IOnUse extends Serializable
    {
        boolean onUse(PlayerEntity player);
    }
    public interface IOnEndCooldown extends Serializable
    {
        void onEndCooldown(PlayerEntity player);
    }
    public interface IDuringCooldown extends Serializable
    {
        void duringCooldown(PlayerEntity player, int cooldown);
    }
}
