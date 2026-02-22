package me.magicsebs.combatLock.util;

import org.bukkit.ChatColor;

public class TextUtil {

    // Converts & codes to Minecraft color codes
    public static String color(String message) {
        if (message == null) return "";
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    // Replace multiple placeholders in one call
    public static String replacePlaceholders(String message, String placeholder, String value) {
        if (message == null || placeholder == null || value == null) return message;
        return message.replace(placeholder, value);
    }
}