package me.alfredengstrand.realliferoleplay.bukkitEvents;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.alfredengstrand.realliferoleplay.Main;
import me.alfredengstrand.realliferoleplay.economy.Economy;
import me.alfredengstrand.realliferoleplay.user.UserManager;

public class PlayerLeave implements Listener {
	
	Main plugin;
	
	public PlayerLeave(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e) {
		Player player = e.getPlayer();
		if(!plugin.getMySQL().exists(player.getUniqueId())) {
			plugin.getMySQL().createPlayer(player);
		}
		plugin.getMySQL().updateBalance(player.getUniqueId(), (int) Economy.getAccount(player).getBalance());
		UserManager.getUser(player.getUniqueId()).setAfk(false);
	}

}
