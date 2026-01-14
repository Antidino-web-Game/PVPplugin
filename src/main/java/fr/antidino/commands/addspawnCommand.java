package fr.antidino.commands;

import fr.antidino.utils.*;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class addspawnCommand implements CommandExecutor {
    Plugin plugin;

    public addspawnCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by players");
            return true;
        }
        Player player = (Player) sender;

        try {
            File file = Utils.openfile(plugin, "spawn");
            YamlConfiguration spawns = YamlConfiguration.loadConfiguration(file);
            int x = player.getLocation().getBlockX();
            int y = player.getLocation().getBlockY();
            int z = player.getLocation().getBlockZ();
            player.sendMessage("§e==============debug===========");
            spawns.set("spawns." + args[0] + ".x", x);
            spawns.set("spawns." + args[0] + ".y", y);
            spawns.set("spawns." + args[0] + ".z", z);
            spawns.save(file);
            if (spawns.contains("spawns." + args[0])) {
                player.sendMessage("" + args[0] + " trouvé");
            } else {
                player.sendMessage("" + args[0] + " non trouvé");
            }

        } catch (Exception e) {
            player.sendMessage("§r une erreur interne a lieu " + e);
        }

        return true;
    }
}