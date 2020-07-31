package com.mod.block_clover.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;

import java.awt.*;

public class CurseEffect extends Effect
{

    @Override
    public void performEffect(LivingEntity entity, int amplifier)
    {
        int duration = entity.getActivePotionEffect(ModEffects.CURSE).getDuration();



        if(amplifier >= 1)
        {
            entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 60,  0, false, false));

        }
        if(duration==1)
            entity.setHealth(0);
    }

    @Override
    public boolean isReady(int duration, int amplifier)
    {
        if(amplifier >= 1 && duration>20)
            return duration%20==0;


        return duration == 1;
    }

    @Override
    public boolean shouldRender(EffectInstance effect)
    {
        return effect.getAmplifier()<2;
    }
    @Override
    public boolean shouldRenderHUD(EffectInstance effect)
    {
        return effect.getAmplifier()<2;
    }

    public CurseEffect()
    {
        super(EffectType.HARMFUL, Color.RED.getRGB());
    }
}
