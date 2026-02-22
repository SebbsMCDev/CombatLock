package me.magicsebs.combatLock;

import me.magicsebs.combatLock.combat.CombatManager;
import me.magicsebs.combatLock.config.ConfigManager;
import me.magicsebs.combatLock.config.MessageManager;
import me.magicsebs.combatLock.listener.CommandListener;
import me.magicsebs.combatLock.listener.DamageListener;
import me.magicsebs.combatLock.listener.DeathListener;
import me.magicsebs.combatLock.listener.QuitListener;
import me.magicsebs.combatLock.task.CombatTask;
import org.bukkit.plugin.java.JavaPlugin;

public class CombatLock extends JavaPlugin {

    // Core managers
    private static CombatLock instance;
    private CombatManager combatManager;
    private ConfigManager configManager;
    private MessageManager messageManager;

    public static CombatLock getInstance() {
        return instance;
    }

    public CombatManager getCombatManager() {
        return combatManager;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public MessageManager getMessageManager() {
        return messageManager;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Load configuration
        saveDefaultConfig(); // Saves config.yml if it doesn't exist
        saveResource("messages.yml", false); // Saves messages.yml if it doesn't exist

        configManager = new ConfigManager(this);
        messageManager = new MessageManager(this);

        // Initialize combat manager
        combatManager = new CombatManager(this);

        // Register listeners
        getServer().getPluginManager().registerEvents(new DamageListener(this), this);
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);

        // Start combat timer task
        new CombatTask(this).runTaskTimer(this, 20L, 20L); // Runs every second

        getLogger().info("CombatLock has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("CombatLock has been disabled!");
    }
}