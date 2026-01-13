package fr.antidino.commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import fr.antidino.utils.Utils;

public class listspawnCommand implements CommandExecutor {
    Plugin plugin;

    public listspawnCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players");
            return true;
        }

        Player player = (Player) sender;
        File file = Utils.openfile(plugin, "spawn");
        YamlConfiguration spawns = YamlConfiguration.loadConfiguration(file);
        player.sendMessage("§efile open");
        java.util.Set<String> listspawn = spawns.getConfigurationSection("spawns").getKeys(false);
        for (String spawn : listspawn) {
            player.sendMessage(spawn);
        }
        return true;
    }
}