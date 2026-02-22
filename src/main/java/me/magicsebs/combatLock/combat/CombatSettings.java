package me.magicsebs.combatLock.combat;

import me.magicsebs.combatLock.CombatLock;

public class CombatSettings {

    private final int combatDuration;

    public CombatSettings(CombatLock plugin) {
        this.combatDuration = plugin.getConfigManager().getCombatDuration();
    }

    public int getCombatDuration() {
        return combatDuration;
    }
}