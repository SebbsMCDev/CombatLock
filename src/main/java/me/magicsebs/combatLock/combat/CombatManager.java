package me.magicsebs.combatLock.combat;

import me.magicsebs.combatLock.CombatLock;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CombatManager {

    private final CombatLock plugin;
    private final CombatSettings settings;

    private final Map<UUID, CombatSession> activeCombat = new HashMap<>();

    public CombatManager(CombatLock plugin) {
        this.plugin = plugin;
        this.settings = new CombatSettings(plugin);
    }

    public void startCombat(Player player, Player opponent) {
        int duration = settings.getCombatDuration();

        // Start or reset combat for both players
        activeCombat.put(player.getUniqueId(), new CombatSession(player, opponent, duration));
        activeCombat.put(opponent.getUniqueId(), new CombatSession(opponent, player, duration));
    }

    public void resetCombat(Player player) {
        CombatSession session = activeCombat.get(player.getUniqueId());
        if (session != null) {
            session.setRemainingSeconds(settings.getCombatDuration());
        }
    }

    public boolean isInCombat(Player player) {
        return activeCombat.containsKey(player.getUniqueId());
    }

    public CombatSession getSession(Player player) {
        return activeCombat.get(player.getUniqueId());
    }

    public void removeCombat(Player player) {
        activeCombat.remove(player.getUniqueId());
    }

    public Map<UUID, CombatSession> getActiveCombat() {
        return activeCombat;
    }
}