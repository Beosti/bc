package com.mod.block_clover.Beapi.data.ability;

import com.mod.block_clover.Beapi.SpellCategory;
import com.mod.block_clover.Beapi.abilities.Spell;

import java.util.List;

public interface IAbilityData
{
    boolean addUnlockAbility(Spell spl);
    boolean setUnlockedAbility(int slot, Spell abl);
    boolean removeUnlockedAbility(Spell abl);
    boolean hasUnlockedAbility(Spell abl);
    <T extends Spell> T getUnlockedAbility(T abl);
    <T extends Spell> T getUnlockedAbility(int slot);
    List<Spell> getUnlockedAbilities(Spell category);
    void clearUnlockedAbilities(Spell category);
    void clearUnlockedAbilityFromList(Spell category, List<Spell> list);
    int countUnlockedAbilities(SpellCategory category);

    boolean addEquippedAbility(Spell abl);
    boolean setEquippedAbility(int slot, Spell abl);
    boolean removeEquippedAbility(Spell abl);
    boolean hasEquippedAbility(Spell abl);
    <T extends Spell> T getEquippedAbility(T abl);
    <T extends Spell> T getEquippedAbility(int slot);
    <T extends Spell> T[] getEquippedAbilities();
    <T extends Spell> T[] getEquippedAbilities(Spell category);
    void clearEquippedAbilities(Spell category);
    void clearEquippedAbilityFromList(Spell category, List<Spell> list);
    int countEquippedAbilities(SpellCategory category);

    <T extends Spell> T getPreviouslyUsedAbility();
    void setPreviouslyUsedAbility(Spell abl);

}
