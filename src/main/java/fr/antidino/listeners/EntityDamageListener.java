package fr.antidino.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.Plugin;

import fr.antidino.utils.PlayerStatues;

public class EntityDamageListener implements Listener {
    Plugin plugin;

    public EntityDamageListener(Plugin pl) {
        this.plugin = pl;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause() == DamageCause.FALL && event.getEntityType() == EntityType.PLAYER) {
            if (PlayerStatues.getStatues(this.plugin, event.getEntity().getUniqueId(), "PVP")) {

                if (!PlayerStatues.getStatues(plugin, event.getEntity().getUniqueId(), "PVP")) {
                    event.setCancelled(true);
                }

            }
        }

    }
}