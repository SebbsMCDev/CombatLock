package me.magicsebs.combatLock.config;

import me.magicsebs.combatLock.CombatLock;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Collections;
import java.util.List;

public class ConfigManager {

    private final CombatLock plugin;
    private final FileConfiguration config;

    public ConfigManager(CombatLock plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        loadDefaults();
    }

    private void loadDefaults() {
        plugin.saveDefaultConfig();
    }

    // Combat duration in seconds
    public int getCombatDuration() {
        return config.getInt("combat.duration", 20);
    }

    // Allowed commands while in combat
    public List<String> getAllowedCommands() {
        List<String> cmds = config.getStringList("commands.allowed_commands");
        return cmds != null ? cmds : Collections.emptyList();
    }

    // Logout punishment toggle
    public boolean isLogoutKillEnabled() {
        return config.getBoolean("logout.kill_on_logout", true);
    }

    // Check if command blocking is enabled
    public boolean isCommandBlockEnabled() {
        return config.getBoolean("commands.block_all", true);
    }

    // Other potential config getters can be added here
}