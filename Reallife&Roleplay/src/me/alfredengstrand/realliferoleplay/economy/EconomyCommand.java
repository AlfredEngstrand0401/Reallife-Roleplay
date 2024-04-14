package me.alfredengstrand.realliferoleplay.economy;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EconomyCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("eco")) {
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "Usage: /eco help");
				return true;
			}
			if (args[0].equalsIgnoreCase("balance")) {
				player.sendMessage(ChatColor.GREEN + "Your balance is: $" + ChatColor.GREEN
						+ Economy.getAccount(player).getBalance());
				return true;
			}
			if (args[0].equalsIgnoreCase("deposit")) {
				
			}
			if (args[0].equalsIgnoreCase("withdraw")) {

			}
			if (args[0].equalsIgnoreCase("reset")) {

			}
		}
		return false;
	}

}
