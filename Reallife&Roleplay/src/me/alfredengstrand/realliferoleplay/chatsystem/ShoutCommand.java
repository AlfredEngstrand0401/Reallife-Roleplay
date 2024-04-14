package me.alfredengstrand.realliferoleplay.chatsystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ShoutCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("shout")) {
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "/shout <message>");
				return true;
			}
			MessageManager.sendMessageByArgumentsToNearbyPlayers(player, args, 22);
		}
		return true;

	}

}
