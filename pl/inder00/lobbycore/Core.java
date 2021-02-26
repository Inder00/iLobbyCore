package pl.inder00.lobbycore;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import pl.inder00.lobbycore.baisc.GUI;
import pl.inder00.lobbycore.bungeecord.Channel;
import pl.inder00.lobbycore.commands.player.ConnectCommand;
import pl.inder00.lobbycore.listeners.*;
import pl.inder00.lobbycore.storage.Config;
import pl.inder00.lobbycore.tasks.BungeePlayersTask;
import pl.inder00.lobbycore.tasks.ScoreboardTask;

import java.io.File;

public final class Core extends JavaPlugin {

    private static Core instance;
    private static File mainDir;
    private static File cfgFile = new File(mainDir, "config.yml");
    private static Config config;
    private static Inventory serverGui;

    @Override
    public void onEnable() {

        //INSTANCE
        instance = this;

        //FILES
        mainDir = this.getDataFolder();
        if(!mainDir.exists()) mainDir.mkdir();
        if(!cfgFile.exists()) this.saveDefaultConfig();

        //CONFIG
        config = new Config();
        config.load();

        //SCOREBOARD
        new ScoreboardTask().runTaskTimerAsynchronously(this,0,20);
        new BungeePlayersTask().runTaskTimerAsynchronously(this,0,20*5);

        //REGISTER LISTENERS
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new BlockBreakListener(), this);
        pm.registerEvents(new BlockPlaceListener(), this);
        pm.registerEvents(new AsyncPlayerChatListener(), this);
        pm.registerEvents(new EntityDamageListener(), this);
        pm.registerEvents(new FoodLevelChangeListener(), this);
        pm.registerEvents(new InventoryClickListener(),this);
        pm.registerEvents(new PlayerInteractListener(), this);
        pm.registerEvents(new PlayerDropItemListener(),this);
        pm.registerEvents(new PlayerQuitListener(), this);

        //INVENTORY
        serverGui = GUI.init();

        //BUNGEECORD
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, Channel.PLUGIN_CHANNEL);
        this.getServer().getMessenger().registerIncomingPluginChannel(this, Channel.PLUGIN_CHANNEL, new Channel());

        //COMMANDS
        new ConnectCommand();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    //Get instance
    public static Core getInstance() {
        return instance;
    }

    //Get config
    public static Config getConfiguration() {
        return config;
    }

    //Get select server gui
    public static Inventory getServerGui() {
        return serverGui;
    }
}
