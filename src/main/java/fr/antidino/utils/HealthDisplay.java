package fr.antidino.utils;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class HealthDisplay {

    public static void showHealthBar(Player player, Player target, double damage) {
        double health = target.getHealth() - damage;
        if (health < 0)
            health = 0;

        int fullHearts = (int) (health / 2);
        boolean hasHalfHeart = (health % 2 >= 1);

        StringBuilder heartBar = new StringBuilder("§e" + target.getName() + " §7: ");

        for (int i = 0; i < fullHearts; i++) {
            heartBar.append("§4❤");
        }

        if (hasHalfHeart) {
            heartBar.append("§c❤");
        }

        int emptyHearts = 10 - fullHearts - (hasHalfHeart ? 1 : 0);
        for (int i = 0; i < emptyHearts; i++) {
            heartBar.append("§8❤");
        }

        heartBar.append(" §f(" + String.format("%.1f", health) + " HP)");

        // NMS - SOLUTION ULTIME
        sendActionBar(player, heartBar.toString());
    }

    private static void sendActionBar(Player player, String message) {
        // On s'assure que le message est au format JSON valide
        String json = "{\"text\":\"" + message + "\"}";
        IChatBaseComponent component = IChatBaseComponent.ChatSerializer.a(json);

        // Position 2 = Action Bar
        PacketPlayOutChat packet = new PacketPlayOutChat(component, (byte) 2);
        // Envoi du paquet via la connexion du joueur
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
    }
}