package com.mod.block_clover.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;

import java.awt.*;


public class NomEffect extends Effect
{
    @Override
    public void performEffect(LivingEntity entity, int amplifier)
    {
        int duration = entity.getActivePotionEffect(ModEffects.NOMOTATOES).getDuration();
        if(entity instanceof PlayerEntity)
            if (((PlayerEntity) entity).getFoodStats().getFoodLevel() >= 18)
                ((PlayerEntity) entity).getFoodStats().setFoodLevel(17);

    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        if(amplifier >= 1 && duration>20)
            return duration%20==0;
        return true;
    }

    @Override
    public boolean shouldRender(EffectInstance effect)
    {
        return false;
    }
    @Override
    public boolean shouldRenderHUD(EffectInstance effect)
    {
        return false;
    }
    public NomEffect(){super(EffectType.NEUTRAL, Color.green.getRGB());}
}
