package me.alfredengstrand.realliferoleplay.waypoint;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.alfredengstrand.realliferoleplay.user.User;
import me.alfredengstrand.realliferoleplay.user.UserManager;

public class WaypointCompass implements Listener {

	public static void givePlayerCompass(User user) {
		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemMeta compassMeta = compass.getItemMeta();
		user.setSelectedWaypoint(WaypointManager.getWaypoint("Farmer"));
		Waypoint waypoint = user.getSelectedWaypoint();
		if (waypoint == null) {
			compassMeta.setDisplayName(ChatColor.AQUA + "Ⓘ" + ChatColor.GRAY + "You have no selected waypoint!");
			return;
		}
		compassMeta.setDisplayName(ChatColor.AQUA + "Ⓘ" + ChatColor.GRAY + " You Are " + ChatColor.GRAY
				+ (int) Bukkit.getPlayer(user.getUuid()).getLocation().distance(waypoint.getLocation())
				+ " blocks from " + ChatColor.GRAY + waypoint.getName() + ChatColor.GRAY + "!");
		compass.setItemMeta(compassMeta);
		Bukkit.getPlayer(user.getUuid()).getInventory().setItem(8, compass);
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) {
			return;
		}
		if (e.getCurrentItem().getType() == Material.COMPASS && e.getCurrentItem().getItemMeta().getDisplayName()
				.startsWith(ChatColor.AQUA + "Ⓘ" + ChatColor.GRAY + " You Are ")) {
			e.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
	    User user = UserManager.getUser(e.getPlayer().getUniqueId());
	    if (user.getSelectedWaypoint() == null) {
	        return;
	    }
	    Waypoint waypoint = user.getSelectedWaypoint();
	    int distance = (int) e.getPlayer().getLocation().distance(waypoint.getLocation());
	    ItemStack compass = e.getPlayer().getInventory().getItem(8);
	    if (compass != null && compass.getType() == Material.COMPASS) {
	        ItemMeta compassMeta = compass.getItemMeta();
	        compassMeta.setDisplayName(ChatColor.AQUA + "Ⓘ" + ChatColor.GRAY + " You Are " + ChatColor.GRAY + distance
	                + " blocks from " + ChatColor.GRAY + waypoint.getName() + ChatColor.GRAY + "!");
	        compass.setItemMeta(compassMeta);
	    }
	}
}
