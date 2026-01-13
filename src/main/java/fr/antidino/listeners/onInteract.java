package fr.antidino.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class onInteract implements Listener {
    protected Inventory createInventory(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9, "Allez au combat");
        // Ajouter des éléments à l'inventaire:
        ItemStack item = new ItemStack(Material.GOLD_INGOT);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Allez au combat");
        item.setItemMeta(meta);
        inv.setItem(4, item);
        return inv;
    }

    // Capturer l'interaction avec un item:
    @EventHandler
    public void onInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == Material.COMPASS) {
            player.openInventory(createInventory(player));
        } else {
            if (player.getItemInHand().getType() == Material.APPLE) {
                player.setMaxHealth(20);
            }
        }

    }

}