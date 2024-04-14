package me.alfredengstrand.realliferoleplay.bukkitEvents;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.alfredengstrand.realliferoleplay.user.UserManager;

public class PlayerMove implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		UserManager.getUser(e.getPlayer().getUniqueId()).resetIdleTime();
		if(UserManager.getUser(e.getPlayer().getUniqueId()).isAfk()) {
			UserManager.getUser(e.getPlayer().getUniqueId()).setAfk(false);
			e.getPlayer().sendMessage(ChatColor.GRAY + "You are no longer afk!");
		}
		UserManager.getUser(e.getPlayer().getUniqueId()).resetIdleTime();
	}

}
