package fr.antidino.listeners;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.Location;
import org.bukkit.Material;
import net.md_5.bungee.api.ChatColor;

import fr.antidino.utils.HealthDisplay;
import fr.antidino.utils.Utils;

public class PlayerListener implements Listener {
    public Map<UUID, String> stat = new HashMap<>();
    Plugin plugin;

    public PlayerListener(Plugin pl) {
        this.plugin = pl;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        stat.put(player.getUniqueId(), "null");
        player.setGameMode(GameMode.ADVENTURE);
        player.teleport(new Location(player.getWorld(), 10.5, 5, 8.5));
        Utils.giveCompass(player);
    }

    @EventHandler
    public void onDamageEntity(EntityDamageByEntityEvent event) {

        if (event.getEntityType() == EntityType.PLAYER && event.getDamager().getType() == EntityType.PLAYER) {
            Player player = ((Player) event.getEntity());
            Player damager = ((Player) event.getDamager());
            if (stat.get(player.getUniqueId()) == "null") {
                event.setCancelled(true);
                damager.sendMessage(ChatColor.DARK_RED + "Vous ne pouvez pas pvp ici");
            } else {
                HealthDisplay.showHealthBar(damager, player, event.getFinalDamage());
            }

        }

    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        stat.put(player.getUniqueId(), "null");
        player.setHealth(player.getMaxHealth());
        Bukkit.getLogger().info(player.getName() + " est reapparue");
        Utils.giveCompass(player);

    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void OnPickupItem(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        if (!player.isOp()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        try {
            HumanEntity player = event.getWhoClicked();
            try {
                if (event.getClickedInventory().contains(Material.GOLD_INGOT)) {
                    Location loc = Utils.choseSpawn(plugin, player.getWorld());
                    player.teleport(loc, TeleportCause.NETHER_PORTAL);
                    event.setCancelled(true);
                    player.setHealth(player.getMaxHealth());
                    Utils.giveStuff(((Player) player));
                    stat.put(player.getUniqueId(), "pvp");

                }
            } catch (Exception e) {
                Bukkit.getLogger().info("An error is " + e);
            }

        } catch (Exception e) {
            Bukkit.getLogger().warning("error : " + e);
        }

    }
}