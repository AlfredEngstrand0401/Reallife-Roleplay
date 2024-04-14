package me.alfredengstrand.realliferoleplay.chatsystem;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.alfredengstrand.realliferoleplay.user.User;
import me.alfredengstrand.realliferoleplay.user.UserManager;

public class PlayerChat implements Listener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		User user = UserManager.getUser(player.getUniqueId());
		String message = ChatColor.GRAY + "[" + ChatColor.RED + user.getLevel() + ChatColor.GRAY + "] " + ChatColor.WHITE + player.getName() + ": " + e.getMessage();
		MessageManager.sendMessageToNearbyPlayers(player, message, 12);
		e.setCancelled(true);
	}
}
