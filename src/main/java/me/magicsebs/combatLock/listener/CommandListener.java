package me.magicsebs.combatLock.listener;

import me.magicsebs.combatLock.CombatLock;
import me.magicsebs.combatLock.combat.CombatManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class CommandListener implements Listener {

    private final CombatLock plugin;
    private final CombatManager combatManager;

    public CommandListener(CombatLock plugin) {
        this.plugin = plugin;
        this.combatManager = plugin.getCombatManager();
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();

        if (!combatManager.isInCombat(player)) return;

        // Check if command is allowed
        List<String> allowed = plugin.getConfigManager().getAllowedCommands();
        String cmd = event.getMessage().split(" ")[0].substring(1); // Remove "/"

        if (allowed.contains(cmd.toLowerCase())) return;

        event.setCancelled(true);
        player.sendMessage(plugin.getMessageManager().getNoCommandsMessage());
    }
}