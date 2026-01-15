package fr.antidino.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEventListener implements Listener {

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent event) {
        if (!event.getPlayer().isOp()) {
            event.getPlayer().sendMessage(ChatColor.DARK_RED + "Le chat est bloqué");
            event.setCancelled(true);
        }

    }
}