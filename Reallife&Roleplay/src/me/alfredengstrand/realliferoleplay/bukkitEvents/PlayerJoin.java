package me.alfredengstrand.realliferoleplay.bukkitEvents;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.alfredengstrand.realliferoleplay.Main;
import me.alfredengstrand.realliferoleplay.economy.Account;
import me.alfredengstrand.realliferoleplay.economy.Economy;
import me.alfredengstrand.realliferoleplay.safemode.SafeMode;
import me.alfredengstrand.realliferoleplay.tutorialstoryline.Storyline;
import me.alfredengstrand.realliferoleplay.user.User;
import me.alfredengstrand.realliferoleplay.user.UserManager;
import me.alfredengstrand.realliferoleplay.waypoint.WaypointCompass;

public class PlayerJoin implements Listener {

	Main plugin;

	public PlayerJoin(Main plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		Storyline.startStoryline(player);
		if (SafeMode.isActive()) {
			if (player.isOp()) {
				player.sendMessage(ChatColor.RED + "The server is in safemode!");
			}
		} else {
			if (!player.hasPlayedBefore()) {
				Economy.accounts.add(new Account(player.getUniqueId(), 0));
				return;
			}
			if (!plugin.getMySQL().exists(player.getUniqueId())) {
				Economy.accounts.add(new Account(player.getUniqueId(), 0));
				plugin.getMySQL().createPlayer(player);
				return;
			}
			User user = new User(player.getUniqueId(), plugin);
			UserManager.registerUser(user);
			Account account = new Account(player.getUniqueId(), 0);
			account.setBalance(plugin.getMySQL().getBalance(player.getUniqueId()));
			Economy.accounts.add(account);
			WaypointCompass.givePlayerCompass(user);
		}
	}
}
