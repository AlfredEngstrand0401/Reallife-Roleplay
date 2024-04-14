package me.alfredengstrand.realliferoleplay.sidejobs.farmer.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.alfredengstrand.realliferoleplay.sidejobs.Jobs;
import me.alfredengstrand.realliferoleplay.sidejobs.WorkTitles;
import me.alfredengstrand.realliferoleplay.sidejobs.farmer.Farmer;
import me.alfredengstrand.realliferoleplay.user.User;
import me.alfredengstrand.realliferoleplay.user.UserManager;

public class PlayerFarming implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerFarming(BlockBreakEvent e) {
		Player player = e.getPlayer();
		User user = UserManager.getUser(player.getUniqueId());

		// Check if the broken block is wheat
		if (e.getBlock().getType().equals(Material.WHEAT)) {
			// Check if the player is assigned as a farmer
			if (Jobs.workers.containsKey(player.getUniqueId())) {
				if (!Jobs.workers.get(player.getUniqueId()).getTitle().equals(WorkTitles.FARMER)) {
					player.sendMessage(ChatColor.RED + "# " + ChatColor.GRAY + "You are not a farmer!");
					e.setCancelled(true);
					return;
				}

				// Check if the player is holding a golden hoe
				if (!player.getItemInHand().getType().equals(Material.GOLDEN_HOE)) {
					player.sendMessage(ChatColor.RED + "# " + ChatColor.GRAY + "You need a golden hoe!");
					e.setCancelled(true);
					return;
				}

				Ageable ageable = (Ageable) e.getBlock().getBlockData();
				// Check if the wheat crop is fully grown
				if (ageable.getAge() < ageable.getMaximumAge()) {
					player.sendMessage(ChatColor.YELLOW + "# " + ChatColor.GRAY + "The crop is not ready yet!");
					e.setCancelled(true);
					return;
				}

				e.setCancelled(true);

				// Replant wheat and give feedback to the player
				e.getBlock().setType(Material.WHEAT);

				// Spawn particle effects at the block location
				Location location = e.getBlock().getLocation();
				player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, location.getX(), location.getY(),
						location.getZ(), 10, 0.5, 0.5, 0.5, 0);

				// Update player's farming XP and side job
				user.getSideJob().update();
				user.getSideJob().addXP(0.01f);
			}
		}
	}

	@EventHandler
	private void completeJob(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		User user = UserManager.getUser(player.getUniqueId());

		// Check if the right-clicked entity is a Farmer
		if (!e.getRightClicked().getName().equals("Farmer")) {
			return;
		}

		// Check if the player already has a side job
		if (user.getSideJob() == null) {
			user.setSideJob(new Farmer(user.getUuid()));
			player.sendMessage(ChatColor.GREEN + "You are now a Farmer!");
			return;
		}

		// Check if the player has a different side job in progress
		if (user.getSideJob() == null || !user.getSideJob().getTitle().equals(WorkTitles.FARMER)) {
			player.sendMessage(ChatColor.RED + "Please complete your current job first!");
			return;
		}

		// Check if the player has earned farming XP
		if (user.getSideJob().getXP() == 0) {
			player.sendMessage(ChatColor.RED + "You need to earn farming xp!");
			return;
		}

		// If all conditions are met, reward the player with farming XP
		player.sendMessage(ChatColor.GREEN + "You have successfully completed your job!");

		// Unregister the player from their job
		Jobs.unregisterEmployee(player);
	}
}
