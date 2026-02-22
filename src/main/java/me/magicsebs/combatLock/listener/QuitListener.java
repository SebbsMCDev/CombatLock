package me.magicsebs.combatLock.listener;

import me.magicsebs.combatLock.CombatLock;
import me.magicsebs.combatLock.combat.CombatManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final CombatLock plugin;
    private final CombatManager combatManager;

    public QuitListener(CombatLock plugin) {
        this.plugin = plugin;
        this.combatManager = plugin.getCombatManager();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (!combatManager.isInCombat(player)) return;

        if (plugin.getConfigManager().isLogoutKillEnabled()) {
            player.setHealth(0); // Kill player
            Bukkit.broadcastMessage(plugin.getMessageManager().getLogoutDeathMessage(player.getName()));
        }

        combatManager.removeCombat(player);
    }
}