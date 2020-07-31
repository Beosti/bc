package com.mod.block_clover.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

import java.awt.*;

public class HealthEffect extends Effect
{
    @Override
    public void performEffect(LivingEntity entity, int amplifier)
    {
        int duration = entity.getActivePotionEffect(ModEffects.HEALTH).getDuration();
        if(entity instanceof PlayerEntity)
        {
            if(amplifier==1) //over small regen
            {
                entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 5, 3, false, false));
            }
            else if (amplifier==2) //overall ok regen
            {
                entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 5, 5, false, false));
            }
            else if(amplifier==3) //used for the artefact of healing
            {
                entity.addPotionEffect(new EffectInstance(Effects.REGENERATION, 15, 10, false, false));
            }

        }
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
    public HealthEffect(){super(EffectType.BENEFICIAL, Color.green.getRGB());}
}
