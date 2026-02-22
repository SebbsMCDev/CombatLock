package me.magicsebs.combatLock.config;

import me.magicsebs.combatLock.CombatLock;
import me.magicsebs.combatLock.util.TextUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class MessageManager {

    private final CombatLock plugin;
    private FileConfiguration messagesConfig;

    public MessageManager(CombatLock plugin) {
        this.plugin = plugin;
        loadMessages();
    }

    private void loadMessages() {
        File file = new File(plugin.getDataFolder(), "messages.yml");
        if (!file.exists()) {
            plugin.saveResource("messages.yml", false);
        }
        messagesConfig = YamlConfiguration.loadConfiguration(file);
    }

    public String getCombatStartMessage() {
        return TextUtil.color(messagesConfig.getString("combat.start", "&cYou are now in combat!"));
    }

    public String getCombatEndMessage() {
        return TextUtil.color(messagesConfig.getString("combat.end", "&aYou are no longer in combat."));
    }

    public String getNoCommandsMessage() {
        return TextUtil.color(messagesConfig.getString("commands.blocked", "&cYou cannot use commands while in combat!"));
    }

    public String getActionBarMessage(int seconds) {
        String msg = messagesConfig.getString("combat.actionbar", "&cIn Combat: %time%s");
        return TextUtil.color(msg.replace("%time%", String.valueOf(seconds)));
    }

    public String getLogoutDeathMessage(String playerName) {
        String msg = messagesConfig.getString("logout.death", "&c%player% logged out while in combat and died!");
        return TextUtil.color(msg.replace("%player%", playerName));
    }
}