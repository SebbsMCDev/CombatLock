package me.magicsebs.combatLock.task;

import me.magicsebs.combatLock.CombatLock;
import me.magicsebs.combatLock.combat.CombatManager;
import me.magicsebs.combatLock.combat.CombatSession;
import me.magicsebs.combatLock.util.ActionBarUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Map;
import java.util.UUID;

public class CombatTask extends BukkitRunnable {

    private final CombatLock plugin;
    private final CombatManager combatManager;

    public CombatTask(CombatLock plugin) {
        this.plugin = plugin;
        this.combatManager = plugin.getCombatManager();
    }

    @Override
    public void run() {
        // Loop through all active combat sessions
        for (Map.Entry<UUID, CombatSession> entry : combatManager.getActiveCombat().entrySet()) {
            UUID uuid = entry.getKey();
            CombatSession session = entry.getValue();
            Player player = Bukkit.getPlayer(uuid);

            if (player == null || !player.isOnline()) continue;

            // Decrement remaining seconds
            session.decrement();

            // Send action bar
            ActionBarUtil.sendActionBar(player,
                    plugin.getMessageManager().getActionBarMessage(session.getRemainingSeconds()));

            // Remove combat if time is up
            if (session.getRemainingSeconds() <= 0) {
                combatManager.removeCombat(player);
                player.sendMessage(plugin.getMessageManager().getCombatEndMessage());
            }
        }
    }
}