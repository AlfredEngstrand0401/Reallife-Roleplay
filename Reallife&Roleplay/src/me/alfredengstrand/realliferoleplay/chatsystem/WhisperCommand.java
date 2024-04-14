package me.alfredengstrand.realliferoleplay.chatsystem;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WhisperCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(label.equalsIgnoreCase("whisper")) {
			if(args.length == 0) {
				player.sendMessage(ChatColor.RED + "/whisper <message>");
				return true;
			}
			MessageManager.sendMessageByArgumentsToNearbyPlayers(player, args, 3);
		}
		return true;
	}

}
