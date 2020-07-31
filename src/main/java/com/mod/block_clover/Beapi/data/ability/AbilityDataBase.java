package com.mod.block_clover.Beapi.data.ability;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.mod.block_clover.Beapi.APIConfig;
import com.mod.block_clover.Beapi.SpellCategory;
import com.mod.block_clover.Beapi.abilities.Spell;

public class AbilityDataBase implements IAbilityData
{
    private List<Spell> unlockedAbilities = new ArrayList<Spell>();
    private Spell[] equippedAbilities = new Spell[APIConfig.MAX_SELECTED_SPELLS];


    private Spell previouslyUsedAbility;

    /*
     * Unlocked Abilities
     */

    public boolean addUnlockedAbility(Spell abl)
    {
        Spell ogAbl = this.getUnlockedAbility(abl);
        if (ogAbl == null)
        {
            this.unlockedAbilities.add(abl);
            return true;
        }
        return false;
    }

    @Override
    public boolean addUnlockAbility(Spell spl) {
        return false;
    }

    @Override
    public boolean setUnlockedAbility(int slot, Spell abl)
    {
        Spell ogAbl = this.getUnlockedAbility(abl);
        if (ogAbl == null)
        {
            if(this.unlockedAbilities.size() > slot)
                this.unlockedAbilities.set(slot, abl);
            else
                this.unlockedAbilities.add(slot, abl);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeUnlockedAbility(Spell abl)
    {
        Spell ogAbl = this.getUnlockedAbility(abl);
        if (ogAbl != null)
        {
            this.unlockedAbilities.remove(ogAbl);
            return true;
        }
        return false;
    }

    @Override
    public boolean hasUnlockedAbility(Spell abl)
    {
        this.unlockedAbilities.removeIf(ability -> ability == null);
        return this.unlockedAbilities.parallelStream().anyMatch(ability -> ability.equals(abl));
    }

    @Override
    public <T extends Spell> T getUnlockedAbility(T abl)
    {
        this.unlockedAbilities.removeIf(Spell -> Spell == null);
        return (T) this.unlockedAbilities.parallelStream().filter(Spell -> Spell.equals(abl)).findFirst().orElse(null);
    }

    @Override
    public <T extends Spell> T getUnlockedAbility(int slot)
    {
        this.unlockedAbilities.removeIf(Spell -> Spell == null);
        return this.unlockedAbilities.size() > slot ? (T) this.unlockedAbilities.get(slot) : null;
    }

    @Override
    public List<Spell> getUnlockedAbilities(Spell category) {
        return null;
    }

    @Override
    public void clearUnlockedAbilities(Spell category) {

    }

    @Override
    public void clearUnlockedAbilityFromList(Spell category, List<Spell> list) {

    }

    public List<Spell> getUnlockedAbilities(SpellCategory category)
    {
        this.unlockedAbilities.removeIf(Spell -> Spell == null);
        return this.unlockedAbilities.parallelStream().filter(ability -> ability.getCategory() == category || category == SpellCategory.ALL).collect(Collectors.toList());
    }

    public void clearUnlockedAbilities(SpellCategory category)
    {
        this.unlockedAbilities.removeIf(Spell -> Spell == null || Spell.getCategory() == category || category == SpellCategory.ALL);
    }

    public void clearUnlockedAbilityFromList(SpellCategory category, List<Spell> list)
    {
        this.unlockedAbilities.removeIf(Spell -> (Spell == null || Spell.getCategory() == category || category == SpellCategory.ALL) && list.contains(Spell));
    }

    @Override
    public int countUnlockedAbilities(SpellCategory category)
    {
        this.unlockedAbilities.removeIf(Spell -> Spell == null);
        return this.unlockedAbilities.parallelStream().filter(Spell -> Spell.getCategory() == category || category == SpellCategory.ALL).collect(Collectors.toList()).size();
    }

    /*
     * Equipped Abilities
     */

    @Override
    public boolean addEquippedAbility(Spell abl)
    {
        for(int i = 0; i < this.equippedAbilities.length; i++)
        {
            Spell ability = this.equippedAbilities[i];
            if(ability == null)
            {
                this.equippedAbilities[i] = abl;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setEquippedAbility(int slot, Spell abl)
    {
        Spell ogAbl = this.getEquippedAbility(abl);
        if (ogAbl == null && slot <= APIConfig.MAX_SELECTED_SPELLS)
        {
            this.equippedAbilities[slot] = abl;
            return true;
        }
        return false;
    }

    @Override
    public boolean removeEquippedAbility(Spell abl)
    {
        Spell ogAbl = this.getUnlockedAbility(abl);
        if (ogAbl != null)
        {
            for(int i = 0; i < this.equippedAbilities.length; i++)
            {
                Spell ability = this.equippedAbilities[i];
                if(ability != null)
                {
                    this.equippedAbilities[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean hasEquippedAbility(Spell abl)
    {
        return Arrays.stream(this.equippedAbilities)
                .parallel()
                .filter(ability -> ability != null)
                .anyMatch(ability -> ability.equals(abl));
    }

    @Override
    public <T extends Spell> T getEquippedAbility(T abl)
    {
        return (T) Arrays.stream(this.equippedAbilities)
                .parallel()
                .filter(Spell -> Spell != null)
                .filter(Spell -> Spell.equals(abl))
                .findFirst().orElse(null);
    }

    @Override
    public <T extends Spell> T getEquippedAbility(int slot)
    {
        return (T) this.equippedAbilities[slot];
    }

    @Override
    public<T extends Spell> T[] getEquippedAbilities()
    {
        return (T[]) this.equippedAbilities;
    }

    @Override
    public <T extends Spell> T[] getEquippedAbilities(Spell category) {
        return null;
    }

    @Override
    public void clearEquippedAbilities(Spell category) {

    }

    @Override
    public void clearEquippedAbilityFromList(Spell category, List<Spell> list) {

    }

    public <T extends Spell> T[] getEquippedAbilities(SpellCategory category)
    {
        return (T[]) this.equippedAbilities;
    }

    public void clearEquippedAbilities(SpellCategory category)
    {
        for(int i = 0; i < this.equippedAbilities.length; i++)
        {
            Spell ability = this.equippedAbilities[i];
            if((ability != null && ability.getCategory() != category) || category == SpellCategory.ALL)
            {
                this.equippedAbilities[i] = null;
            }
        }
    }

    public void clearEquippedAbilityFromList(SpellCategory category, List<Spell> list)
    {
        for(int i = 0; i < this.equippedAbilities.length; i++)
        {
            Spell ability = this.equippedAbilities[i];
            if((ability != null && ability.getCategory() != category && !list.contains(ability)) || category != SpellCategory.ALL)
            {
                this.equippedAbilities[i] = null;
            }
        }
    }

    @Override
    public int countEquippedAbilities(SpellCategory category)
    {
        return Arrays.stream(this.equippedAbilities)
                .parallel()
                .filter(ability -> ability != null)
                .filter(ability -> ability.getCategory() == category || category == SpellCategory.ALL)
                .collect(Collectors.toList())
                .size();
    }

    /*
     * Previously Used Ability
     */

    @Override
    public <T extends Spell> T getPreviouslyUsedAbility()
    {
        return (T) this.previouslyUsedAbility;
    }

    @Override
    public void setPreviouslyUsedAbility(Spell abl)
    {
        this.previouslyUsedAbility = abl;
    }
}
