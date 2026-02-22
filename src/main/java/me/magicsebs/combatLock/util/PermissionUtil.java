package me.magicsebs.combatLock.util;

import org.bukkit.entity.Player;

public class PermissionUtil {

    // Checks if player has a permission or bypass permission
    public static boolean hasPermission(Player player, String permission, String bypassPermission) {
        if (player == null) return false;
        return player.hasPermission(permission) || player.hasPermission(bypassPermission);
    }

    // Simple check if player can bypass combat restrictions
    public static boolean canBypassCombat(Player player) {
        return hasPermission(player, "combatlock.bypass", "combatlock.admin");
    }
}