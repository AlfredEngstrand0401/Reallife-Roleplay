package me.alfredengstrand.realliferoleplay.afk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import me.alfredengstrand.realliferoleplay.Main;
import me.alfredengstrand.realliferoleplay.user.User;
import me.alfredengstrand.realliferoleplay.user.UserManager;

public class AfkHandler implements Listener {

	private final static int AFK_TIME_THRESHOLD = 240;

	public Main plugin;
	public User user;
	public Player player;
	public boolean isWalking;

	public AfkHandler(User user, Main plugin) {
		this.user = user;
		this.plugin = plugin;
		this.player = Bukkit.getPlayer(user.getUuid());
	}

	public void scheduleAFKCheckTask() {
		Bukkit.getScheduler().runTaskTimer(plugin, () -> {
			if (isStill(player)) {
				int idleTime = UserManager.getUser(player.getUniqueId()).getIdleTime();
				UserManager.getUser(player.getUniqueId()).setIdleTime(idleTime + 1);
				if (idleTime == AFK_TIME_THRESHOLD) {
					UserManager.getUser(player.getUniqueId()).setAfk(true);
					player.sendMessage(ChatColor.GRAY + "You are now afk!");
				}
			}
		}, 0L, 20L);

	}

	private boolean isStill(Player player) {
		return !player.isSneaking() && !player.isFlying() && !player.isInsideVehicle();
	}

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		UserManager.getUser(player.getUniqueId()).resetIdleTime();
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player player = e.getPlayer();
		UserManager.getUser(player.getUniqueId()).resetIdleTime();
	}

}
