package com.mod.block_clover.effects;

import com.mod.block_clover.Block_Clover;
import net.minecraft.potion.Effect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects
{
    public static final DeferredRegister<Effect>EFFECTS=new
            DeferredRegister<>(ForgeRegistries.POTIONS, Block_Clover.MODID);

    public static final Effect CURSE = new CurseEffect();
    public static final Effect NOMOTATOES = new NomEffect();
    public static final Effect HEALTH = new HealthEffect();
    static
    {
        EFFECTS.register("nom",()->NOMOTATOES);
        EFFECTS.register("curse",()->CURSE);
        EFFECTS.register("health",()->HEALTH);
    }
}
