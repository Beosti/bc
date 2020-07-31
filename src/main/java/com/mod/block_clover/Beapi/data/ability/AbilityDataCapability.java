package com.mod.block_clover.Beapi.data.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

public class AbilityDataCapability
{
    @CapabilityInject(IAbilityData.class)
    public static final Capability<IAbilityData> INSTANCE = null;
    public static IAbilityData get(final LivingEntity entity)
    {
        return  entity.getCapability(INSTANCE, null).orElse(new AbilityDataBase());
    }
}
