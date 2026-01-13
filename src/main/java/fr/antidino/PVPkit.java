package fr.antidino;

import java.lang.reflect.Array;
import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import fr.antidino.managers.ListenerManager;
import fr.antidino.managers.PluginManager;

public class PVPkit extends JavaPlugin {
    ArrayList<Array> eventplayers = new ArrayList<>();

    @Override
    public void onEnable() {

        // Initialize managers
        PluginManager.getInstance().initialize();
        ListenerManager.initialize(this);

        // Register listeners

        getLogger().info("PVPkit has been enabled!");

    }

    @Override
    public void onDisable() {
        getLogger().info("PVPkit has been disabled!");

    }

}