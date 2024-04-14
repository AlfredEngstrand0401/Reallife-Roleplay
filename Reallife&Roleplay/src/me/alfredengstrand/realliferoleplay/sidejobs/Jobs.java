package me.alfredengstrand.realliferoleplay.sidejobs;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Jobs {

	public static Map<UUID, Job> workers = new HashMap<UUID, Job>();

	public static void registerEmployee(Player player, Job job) {
		if (player.getUniqueId() == null) {
			System.out.println("The player uuid is null!");
		}
		if (workers.containsKey(player.getUniqueId())) {
			workers.remove(player.getUniqueId());
		}
		workers.put(player.getUniqueId(), job);
	}

	public static void unregisterEmployee(Player player) {
		if (!workers.containsKey(player.getUniqueId())) {
			return;
		}
		workers.get(player.getUniqueId()).complete();
		workers.remove(player.getUniqueId());
	}

}
