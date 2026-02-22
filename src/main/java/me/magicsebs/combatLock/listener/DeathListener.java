package me.magicsebs.combatLock.listener;

import me.magicsebs.combatLock.CombatLock;
import me.magicsebs.combatLock.combat.CombatManager;
import me.magicsebs.combatLock.combat.CombatSession;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private final CombatLock plugin;
    private final CombatManager combatManager;

    public DeathListener(CombatLock plugin) {
        this.plugin = plugin;
        this.combatManager = plugin.getCombatManager();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!combatManager.isInCombat(player)) return;

        // Get the combat session of the player who died
        CombatSession session = combatManager.getSession(player);
        if (session != null) {
            // Remove both players from combat
            Player opponent = plugin.getServer().getPlayer(session.getOpponentUUID());
            if (opponent != null) {
                combatManager.removeCombat(opponent);
            }
            combatManager.removeCombat(player);
        }
    }
}