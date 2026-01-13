// package fr.antidino.listeners;

// import java.util.HashMap;
// import java.util.Map;
// import java.util.UUID;

// import org.bukkit.Bukkit;
// import org.bukkit.entity.EntityType;
// import org.bukkit.entity.Player;
// import org.bukkit.event.EventHandler;
// import org.bukkit.event.Listener;
// import org.bukkit.event.entity.EntityDamageByEntityEvent;
// import org.bukkit.plugin.Plugin;
// import org.bukkit.scoreboard.ScoreboardManager;

// public class HealthScordboardListener implements Listener {

// Map<Double, UUID> Scoreboardhealth = new HashMap<>();
// Map<UUID, UUID> ScoreboardPlayers = new HashMap<>();

// Plugin plugin;

// public HealthScordboardListener(Plugin pl) {
// plugin = pl;
// }

// @EventHandler
// public void onEntityDamage(EntityDamageByEntityEvent event) {
// if (event.getEntityType() == EntityType.PLAYER &&
// event.getDamager().getType() == EntityType.PLAYER) {
// Player player = ((Player) event.getEntity());
// Player player2 = ((Player) event.getDamager());
// if (isAlreadyPvp(player)) {
// ScoreboardPlayers.remove(player.getUniqueId());
// }
// if (isAlreadyPvp(player2)) {
// ScoreboardPlayers.remove(player2.getUniqueId());
// }
// ScoreboardPlayers.put(player.getUniqueId(), player2.getUniqueId());

// }

// }

// private void createScoreboard(Player player, Player player2) {
// Runnable run = new Runnable() {
// @Override
// public void run() {
// updateHeath();
// }
// };
// ScoreboardManager manager;
// player.setScoreboard();
// Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(plugin, run,
// 20L, 20L);
// }

// private boolean isAlreadyPvp(Player player) {
// if (ScoreboardPlayers.containsKey(player.getUniqueId())
// || ScoreboardPlayers.containsValue(player.getUniqueId())) {
// return true;
// } else {
// return false;
// }
// }

// private void updateHeath() {
// for (UUID uuid : Scoreboardhealth.values()) {
// Player player = Bukkit.getPlayer(uuid);
// Scoreboardhealth.put(player.getHealth(), uuid);
// }
// }
// }