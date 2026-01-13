package fr.antidino.utils;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class Utils {

    public static String colorize(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static File openfile(Plugin plugin, String name) {
        File file = new File(plugin.getDataFolder(), name + ".yml");
        plugin.saveResource(name + ".yml", false);
        return file;
    }

    public static void giveStuff(Player player) {
        Inventory inv = player.getInventory();
        inv.clear();
        Bukkit.getLogger().info("the invetory of " + player.getName() + " cleared");
        // set tool and weapon itemstack
        ItemStack[] tool = new ItemStack[5];
        tool[0] = new ItemStack(Material.IRON_SWORD);
        tool[1] = new ItemStack(Material.FISHING_ROD);
        tool[2] = new ItemStack(Material.BOW);
        tool[3] = new ItemStack(Material.ARROW, 16);
        tool[4] = new ItemStack(Material.COOKED_BEEF, 16);

        // set armor itemstack
        ItemStack[] armors = new ItemStack[4];
        armors[0] = new ItemStack(Material.IRON_HELMET);
        armors[1] = new ItemStack(Material.IRON_CHESTPLATE);
        armors[2] = new ItemStack(Material.IRON_LEGGINGS);
        armors[3] = new ItemStack(Material.IRON_BOOTS);
        ItemMeta Meta = null;
        for (int i = 0; i < armors.length; i++) {
            Meta = armors[i].getItemMeta();
            Meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
            armors[i].setItemMeta(Meta);
        }
        Meta = tool[0].getItemMeta();
        Meta.addEnchant(Enchantment.DAMAGE_ALL, 2, false);
        tool[0].setItemMeta(Meta);

        Meta = tool[2].getItemMeta();
        Meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
        tool[2].setItemMeta(Meta);
        // give items
        inv.setItem(0, tool[0]);
        inv.setItem(1, tool[1]);
        inv.setItem(2, tool[2]);
        inv.setItem(8, tool[3]);
        inv.setItem(3, tool[4]);
        inv.setItem(39, armors[0]);
        inv.setItem(38, armors[1]);
        inv.setItem(37, armors[2]);
        inv.setItem(36, armors[3]);
        player.updateInventory();
        Bukkit.getLogger().info("the invetory of " + player.getName() + " got an armor and weapon");
    }

    public static void giveCompass(Player player) {
        player.getInventory().clear();
        ItemStack play = new ItemStack(Material.COMPASS);
        ItemMeta playMeta = play.getItemMeta();
        playMeta.setDisplayName("Allez au combat");
        play.setItemMeta(playMeta);
        Inventory inv = player.getInventory();
        inv.setItem(4, play);
        for (int i = 35; i < 40; i++) {
            inv.setItem(i, new ItemStack(Material.AIR));
        }

    }

    public static Location choseSpawn(Plugin plugin, World world) {
        File file = Utils.openfile(plugin, "spawn");
        YamlConfiguration spawns = YamlConfiguration.loadConfiguration(file);
        java.util.Set<String> listspawn = spawns.getConfigurationSection("spawns").getKeys(false);
        int spawn = new Random().nextInt(listspawn.size());
        double x = spawns.getDouble("spawns." + listspawn.toArray()[spawn] + ".x");
        double y = spawns.getDouble("spawns." + listspawn.toArray()[spawn] + ".y");
        double z = spawns.getDouble("spawns." + listspawn.toArray()[spawn] + ".z");
        return new Location(world, x, y, z);
    }

}