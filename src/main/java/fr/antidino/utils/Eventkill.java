package fr.antidino.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.md_5.bungee.api.ChatColor;

public class Eventkill {
    public static void storm(Player player) {
        for (int x = -3; x < 3; x++) {
            for (int z = -3; z < 3; z++) {
                Location strick = player.getLocation();

                strick.setX(player.getLocation().getX() + x);
                strick.setZ(player.getLocation().getZ() + z);
                player.getWorld().strikeLightning(strick);
            }
        }

        player.sendMessage(
                ChatColor.DARK_PURPLE + "La coleur de dieu s'est t'abbatue sur vous");
    }

    public static void Regen(Player player) {
        PotionEffect regen = new PotionEffect(PotionEffectType.REGENERATION, 10, 20);
        player.addPotionEffect(regen);
        player.sendMessage("§aVous avez gagnez de la " + ChatColor.RED + "Régénération");
    }

    public static void veng(Player player) {
        player.damage(25, player);
        player.sendMessage(ChatColor.DARK_RED + player.getName() + " c'est vengé !!!");
    }

    public static void sword(Player player) {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordItemMeta = sword.getItemMeta();
        swordItemMeta.addEnchant(Enchantment.DAMAGE_ALL, 3, false);
        swordItemMeta.setDisplayName("épée temp");
        sword.setItemMeta(swordItemMeta);
        Short dura = 1551;
        sword.setDurability(dura);
        player.sendMessage(ChatColor.AQUA + "Vous avez récu un épée temporaire");
        player.getInventory().setItem(player.getInventory().firstEmpty(), sword);
    }

    public static void health(Player player) {
        PotionEffect abso = new PotionEffect(PotionEffectType.ABSORPTION, 9999, 2);
        player.addPotionEffect(abso);
        player.sendMessage("§aVous avez avez gagnez des coeur §ntemporaire");
    }
}
