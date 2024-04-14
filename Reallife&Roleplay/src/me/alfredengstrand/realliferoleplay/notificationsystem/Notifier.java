package me.alfredengstrand.realliferoleplay.notificationsystem;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Notifier {
	
	public void notifyAll(String message) {
		Bukkit.broadcastMessage(message);
	}
	
	public void notifyPlayer(Player player, String message) {
		player.sendMessage(message);
	}

}
