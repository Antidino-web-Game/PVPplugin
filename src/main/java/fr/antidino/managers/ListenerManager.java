package fr.antidino.managers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import fr.antidino.commands.addspawnCommand;
import fr.antidino.commands.listspawnCommand;
import fr.antidino.listeners.OnFireListener;
import fr.antidino.listeners.OnkillListener;
import fr.antidino.listeners.PlayerListener;
import fr.antidino.listeners.onInteract;

public class ListenerManager {
    public static void initialize(Plugin plugin) {
        try {
            Bukkit.getServer().getPluginManager().registerEvents(new PlayerListener(plugin), plugin);
            Bukkit.getServer().getPluginManager().registerEvents(new onInteract(), plugin);
            Bukkit.getServer().getPluginManager().registerEvents(new OnkillListener(plugin), plugin);
            Bukkit.getServer().getPluginManager().registerEvents(new OnFireListener(), plugin);
            Bukkit.getLogger().info("[PVPkits] : Tout les listener ce sont initialise avec succes");
        } catch (Exception e) {
            Bukkit.getLogger().warning("[PVPkits] : Un des listenner ne c'est pas charger " + e);
            Bukkit.getLogger().warning("[PVPkits] : Désactivation du plugin");
            plugin.getPluginLoader().disablePlugin(plugin);
        }

        try {
            Bukkit.getPluginCommand("addspawn").setExecutor(new addspawnCommand(plugin));
            Bukkit.getPluginCommand("listspawn").setExecutor(new listspawnCommand(plugin));
            Bukkit.getLogger().info("[PVPkits] : Tout les commandes ce sont initialise avec succes");
        } catch (Exception e) {
            Bukkit.getLogger().warning("PVPkits : Une des commandes ne c'est pas charger " + e);
            Bukkit.getLogger().warning("PVPkits : Désactivation du plugin");
            plugin.getPluginLoader().disablePlugin(plugin);

        }
    }
}
