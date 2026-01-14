package fr.antidino.utils;

import java.io.File;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Killcounter {
    public static int killCounter(Plugin plugin, UUID uuid) {
        int killNumber = 0;
        File file = Utils.openfile(plugin, "kill");
        YamlConfiguration killFile = YamlConfiguration.loadConfiguration(file);
        if (killFile.contains("" + uuid)) {
            killNumber = killFile.getInt(uuid.toString());
            killFile.set(uuid.toString(), killNumber + 1);
            killNumber = killFile.getInt(uuid.toString());
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
}