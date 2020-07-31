package com.mod.block_clover.init;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyBindingList
{
    public static KeyBinding[] spellKeyBinds;
    public static void register()
    {
        spellKeyBinds = new KeyBinding[2];
        spellKeyBinds[0] = new KeyBinding("key.block_clover.spell", 19, "key.block_clover.category");
        spellKeyBinds[1] = new KeyBinding("key.block_clover.spell1", 20, "key.block_clover.category");
        for (int i = 0; i < spellKeyBinds.length; ++i)
        {
            ClientRegistry.registerKeyBinding(spellKeyBinds[i]);
        }
    }
}
