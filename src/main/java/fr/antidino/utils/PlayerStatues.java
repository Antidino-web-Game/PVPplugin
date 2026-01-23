package fr.antidino.utils;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import fr.antidino.CustomClass.Variable;

public class PlayerStatues {
    private static int getNumberKill(YamlConfiguration file, UUID uuid) {
        return file.getInt(uuid.toString() + ".kill");
    }

    public static int killCounter(Plugin plugin, UUID uuid) {
        int killNumber = 0;
        File file = Utils.openfile(plugin, "player");
        YamlConfiguration killFile = YamlConfiguration.loadConfiguration(file);
        if (killFile.contains("" + uuid)) {
            killNumber = getNumberKill(killFile, uuid);
            killFile.set(uuid.toString() + ".kill", killNumber + 1);
            killNumber++;
            try {
            } catch (Exception e) {
                Bukkit.getLogger().warning("failed to save file " + e);
            }

        } else {

            killFile.set(uuid.toString(), 1);
            killNumber = killFile.getInt(uuid.toString());
        }
        try {
            killFile.save(file);
        } catch (Exception e) {
            Bukkit.getLogger().warning("error when you save file " + e.toString());
        }
        return killNumber;

    }

    public static void setStatues(Plugin plugin, UUID uuid, String stat, Boolean statues) {
        File file = Utils.openfile(plugin, "player");
        Variable variable = new Variable(stat, statues);
        YamlConfiguration playerStatues = YamlConfiguration.loadConfiguration(file);
        playerStatues.set(uuid.toString() + "." + variable.getName(), variable.getStat());
        try {
            playerStatues.save(file);
        } catch (Exception e) {
            Bukkit.getLogger().warning("error when save file " + e);
        }
    }

    public static boolean getStatues(Plugin plugin, UUID uuid, String name) {
        File file = Utils.openfile(plugin, "player");
        YamlConfiguration playerStatues = YamlConfiguration.loadConfiguration(file);
        return playerStatues.getBoolean(uuid + "." + name);

    }

}