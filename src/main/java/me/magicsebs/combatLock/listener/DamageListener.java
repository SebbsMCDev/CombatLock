package me.magicsebs.combatLock.listener;

import me.magicsebs.combatLock.CombatLock;
import me.magicsebs.combatLock.combat.CombatManager;
import me.magicsebs.combatLock.combat.CombatSession;
import me.magicsebs.combatLock.util.ActionBarUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener {

    private final CombatLock plugin;
    private final CombatManager combatManager;

    public DamageListener(CombatLock plugin) {
        this.plugin = plugin;
        this.combatManager = plugin.getCombatManager();
    }

    @EventHandler
    public void onPlayerHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player damager)) return;
        if (!(event.getEntity() instanceof Player victim)) return;

        // Start or reset combat for both players
        if (combatManager.isInCombat(damager)) {
            combatManager.resetCombat(damager);
        } else {
            combatManager.startCombat(damager, victim);
        }

        if (combatManager.isInCombat(victim)) {
            combatManager.resetCombat(victim);
        } else {
            combatManager.startCombat(victim, damager);
        }

        // Send action bar message for both
        CombatSession damagerSession = combatManager.getSession(damager);
        CombatSession victimSession = combatManager.getSession(victim);

        ActionBarUtil.sendActionBar(damager, plugin.getMessageManager().getActionBarMessage(damagerSession.getRemainingSeconds()));
        ActionBarUtil.sendActionBar(victim, plugin.getMessageManager().getActionBarMessage(victimSession.getRemainingSeconds()));
    }
}