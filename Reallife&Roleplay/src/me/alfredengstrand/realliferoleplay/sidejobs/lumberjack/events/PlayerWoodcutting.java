package me.alfredengstrand.realliferoleplay.sidejobs.lumberjack.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.alfredengstrand.realliferoleplay.sidejobs.Jobs;
import me.alfredengstrand.realliferoleplay.sidejobs.WorkTitles;
import me.alfredengstrand.realliferoleplay.sidejobs.lumberjack.Lumberjack;
import me.alfredengstrand.realliferoleplay.user.User;
import me.alfredengstrand.realliferoleplay.user.UserManager;

public class PlayerWoodcutting implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player player = e.getPlayer();
		Block block = e.getBlock();
		User user = UserManager.getUser(player.getUniqueId());
		if (Jobs.workers.containsKey(player.getUniqueId())) {
			if (!Jobs.workers.get(player.getUniqueId()).getTitle().equals(WorkTitles.LUMBERJACK)
					&& e.getBlock().getType().toString().endsWith("LOG")) {
				player.sendMessage(ChatColor.RED + "# " + ChatColor.GRAY + "You are not a lumberjack!");
				e.setCancelled(true);
				return;
			}
			if (block.getType().toString().endsWith("LOG") && player.getItemInHand().getType() == Material.GOLDEN_AXE) {

				user.getSideJob().update();
				user.getSideJob().addXP(0.01f);
				Location location = e.getBlock().getLocation();
				player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location.getX(), location.getY(),
						location.getZ(), 10, 0.5, 0.5, 0.5, 0);
			}
		}
	}

	@EventHandler
	private void completeJob(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		User user = UserManager.getUser(player.getUniqueId());

		// Check if the right-clicked entity is a Farmer
		if (!e.getRightClicked().getName().equals("Lumberjack")) {
			return;
		}

		// Check if the player already has a side job
		if (user.getSideJob() == null) {
			user.setSideJob(new Lumberjack(user.getUuid()));
			player.sendMessage(ChatColor.GREEN + "You are now a lumberjack!");
			return;
		}

		// Check if the player has a different side job in progress
		if (user.getSideJob() == null || !user.getSideJob().getTitle().equals(WorkTitles.LUMBERJACK)) {
			player.sendMessage(ChatColor.RED + "Please complete your current job first!");
			return;
		}

		// Check if the player has earned farming XP
		if (user.getSideJob().getXP() == 0) {
			player.sendMessage(ChatColor.RED + "You need to earn woodcutting xp!");
			return;
		}

		// If all conditions are met, reward the player with farming XP
		player.sendMessage(ChatColor.GREEN + "You have successfully completed your job!");

		// Unregister the player from their job
		Jobs.unregisterEmployee(player);
	}
}
