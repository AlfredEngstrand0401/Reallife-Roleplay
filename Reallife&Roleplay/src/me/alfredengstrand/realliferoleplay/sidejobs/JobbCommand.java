package me.alfredengstrand.realliferoleplay.sidejobs;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.alfredengstrand.realliferoleplay.sidejobs.farmer.Farmer;
import me.alfredengstrand.realliferoleplay.user.User;
import me.alfredengstrand.realliferoleplay.user.UserManager;

public class JobbCommand implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if(label.equalsIgnoreCase("farmer")) {
			User user = UserManager.getUser(player.getUniqueId());
			user.setSideJob(new Farmer(player.getUniqueId()));
			player.sendMessage(ChatColor.GREEN + "You are now a farmer!");
			player.setItemInHand(new ItemStack(Material.GOLDEN_HOE));
		}
		return true;
	}

}
