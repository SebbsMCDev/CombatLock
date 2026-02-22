package me.magicsebs.combatLock.util;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class ActionBarUtil {

    public static void sendActionBar(Player player, String message) {
        if (player == null || !player.isOnline() || message == null) return;
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
    }
}