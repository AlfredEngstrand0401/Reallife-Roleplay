package me.alfredengstrand.realliferoleplay.interactionsystem;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InterfaceCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = (Player) sender;
		if(label.equalsIgnoreCase("interface")) {
			UserInterface.openInfoMenu(player, player);
		}
		return false;
	}

}
