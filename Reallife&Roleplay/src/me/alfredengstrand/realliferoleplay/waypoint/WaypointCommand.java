package me.alfredengstrand.realliferoleplay.waypoint;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WaypointCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Only players can use this command!");
			return true;
		}

		Player player = (Player) sender;

		if (!label.equalsIgnoreCase("waypoint")) {
			return false;
		}

		if (!player.isOp()) {
			player.sendMessage(ChatColor.RED + "You do not have permission to do that!");
			return true;
		}

		if (args.length != 2) {
			player.sendMessage(ChatColor.RED + "/waypoint <set/remove> <name>");
			return true;
		}

		if (args[0].equalsIgnoreCase("set")) {
			new Waypoint(player.getLocation(), args[1]);
			player.sendMessage(ChatColor.GREEN + "You created a new waypoint named " + ChatColor.GREEN + args[1] + ChatColor.GREEN + "!");
			return true;
		} else if (args[0].equalsIgnoreCase("remove")) {
			if (WaypointManager.getWaypoint(args[1]) == null) {
				player.sendMessage(
						ChatColor.RED + "No waypoint named " + ChatColor.RED + args[1] + ChatColor.RED + " was found!");
				return true;
			}
			WaypointManager.remove(WaypointManager.getWaypoint(args[1]));
			player.sendMessage(
					ChatColor.GREEN + "Waypoint " + ChatColor.GREEN + args[1] + ChatColor.GREEN + " was removed!");
			return true;
		} else {
			player.sendMessage(ChatColor.RED + "/waypoint <set/remove> <name>");
			return true;
		}
	}
}