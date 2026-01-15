package fr.antidino.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.Plugin;

import fr.antidino.utils.Eventkill;
import fr.antidino.utils.PlayerStatues;

import java.util.Random;

public class OnkillListener implements Listener {
    Random r = new Random();
    Plugin plugin;

    public OnkillListener(Plugin pl) {
        plugin = pl;
    }

    @EventHandler
    public void onEntityDeath(PlayerDeathEvent event) {
        if (event.getEntity().getKiller().getType() != null) {
            if (event.getEntityType() == EntityType.PLAYER
                    && event.getEntity().getKiller().getType() == EntityType.PLAYER) {
                if (event.getEntity() != event.getEntity().getKiller()) {
                    Player player = ((Player) event.getEntity());
                    Player killer = player.getKiller();

                    Bukkit.broadcastMessage("§c" + player.getName() + " | " + killer.getName() + " a "
                            + PlayerStatues.killCounter(plugin, killer.getUniqueId()));
                    int number = r.nextInt(100);
                    // event
                    if (number < 20) {
                        Eventkill.health(killer);
                    } else {
                    }

                    if (number < 30) {
                        Eventkill.sword(killer);

                    } else {
                        if (number < 60) {
                            Eventkill.veng(killer);
                        } else {
                            if (number < 80) {
                                Eventkill.storm(killer);
                            } else {
                                Eventkill.Regen(killer);
                            }
                        }
                    }
                }
            }

        }

    }
}