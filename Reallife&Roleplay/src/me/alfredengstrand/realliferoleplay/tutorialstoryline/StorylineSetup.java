package me.alfredengstrand.realliferoleplay.tutorialstoryline;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

public class StorylineSetup implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (label.equalsIgnoreCase("storyline")) {
			if (args.length == 0) {
				player.sendMessage(ChatColor.RED + "/storyline setvillager <name>");
				return true;
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("setvillager")) {
					if (!player.isOp()) {
						player.sendMessage(ChatColor.RED + "You are not allowed to do that!");
						return true;
					}
					String name = args[1];
					Location location = player.getLocation();
					Villager villager = (Villager) location.getWorld().spawnEntity(location, EntityType.VILLAGER);
					villager.setCustomName(name);
					villager.setCustomNameVisible(true);
					villager.setAI(false);
					villager.setInvulnerable(true);
					player.sendMessage(ChatColor.GREEN + "A new villager named " + ChatColor.DARK_GREEN + name
							+ ChatColor.GREEN + " has been created!");
				}
			}
		}
		return true;
	}

}
