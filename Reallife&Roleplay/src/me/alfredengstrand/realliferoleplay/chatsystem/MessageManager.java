package me.alfredengstrand.realliferoleplay.chatsystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class MessageManager {

	public static void sendMessageByArgumentsToNearbyPlayers(Player player, String[] args, int radius) {
		String message = "";
		for (String arg : args) {
			message += " " + arg;
		}

		for (Player p : Bukkit.getOnlinePlayers()) {
			double distance = player.getLocation().distance(p.getLocation());

			if (distance <= radius) {
				ChatColor color = calculateColor(distance);
				p.sendMessage(color + message);
			}
		}
	}

	public static void sendMessageToNearbyPlayers(Player player, String message, int radius) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			double distance = player.getLocation().distance(p.getLocation());

			if (distance <= radius) {
				ChatColor color = calculateColor(distance);
				p.sendMessage(color + message);
			}
		}
	}

	private static ChatColor calculateColor(double distance) {
		double maxDistance = 22;
		double distanceRatio = distance / maxDistance;
		int grayValue = (int) (255 * (1 - distanceRatio));
		grayValue = Math.max(0, Math.min(255, grayValue));

		ChatColor color = ChatColor.getByChar(Integer.toHexString(grayValue));
		return color;
	}

}
