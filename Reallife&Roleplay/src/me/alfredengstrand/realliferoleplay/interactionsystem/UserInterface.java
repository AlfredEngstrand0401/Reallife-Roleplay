package me.alfredengstrand.realliferoleplay.interactionsystem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class UserInterface implements Listener {

	public static void openInfoMenu(Player player, Player target) {
		Inventory menu = Bukkit.createInventory(null, 27, target.getName() + "' Info");
		fill(menu);
		ItemStack id = newItem(Material.PAPER, ChatColor.GOLD + "" + ChatColor.BOLD + "Show ID", true,
				"Left Click To Show ", target.getName() + "'s ID");
		ItemStack licenses = newItem(Material.BOOK, ChatColor.GOLD + "" + ChatColor.BOLD + "Show Licences", true,
				"Left Click To Show ", target.getName() + "'s", "Valid Licences");
		ItemStack inventory = newItem(Material.CHEST, ChatColor.GOLD + "" + ChatColor.BOLD + "Show Inventory", true,
				"Left Click To Show ", target.getName() + "'s Inventory");
		ItemStack interaction = newItem(Material.END_CRYSTAL, ChatColor.GOLD + "" + ChatColor.BOLD + "Show Interactions", true,
				"Left Click To Show ",target.getName() + "'s Interactions");
		menu.setItem(10, id);
		menu.setItem(12, licenses);
		menu.setItem(14, inventory);
		menu.setItem(16, interaction);
		player.openInventory(menu);
	}

	private static ItemStack newItem(Material material, String displayname, boolean enchanted, String... lores) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayname);
		List<String> loreData = new ArrayList<String>();
		for (String lore : lores) {
			lore = ChatColor.RESET + "" + ChatColor.GRAY + lore;
			loreData.add(lore);
		}
		meta.setLore(loreData);
		item.setItemMeta(meta);
		if (enchanted) {
			meta.addEnchant(Enchantment.DURABILITY, 3, true);
		}
		return item;
	}

	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (e.getView().getTitle().endsWith("Info")) {
			e.setCancelled(true);
		}
		if (e.getCurrentItem() == null || e.getCurrentItem().getItemMeta() == null) {
			return;
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName()
				.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Show ID")) {
			this.openIDInterface(player);

		}
		if (e.getCurrentItem().getItemMeta().getDisplayName()
				.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Show Licences")) {
			this.openLicencesInterface(player);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName()
				.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Show Inventory")) {
			this.openInventoryInterface(player);
		}
		if (e.getCurrentItem().getItemMeta().getDisplayName()
				.equalsIgnoreCase(ChatColor.GOLD + "" + ChatColor.BOLD + "Show Interactions")) {
			this.openInteractionsInterface(player);
		}
	}
	
	private void openIDInterface(Player player) {
		Inventory menu = Bukkit.createInventory(null, 9, "ID");
		player.openInventory(menu);
	}
	
	private void openLicencesInterface(Player player) {
		Inventory menu = Bukkit.createInventory(null, 9, "Licences");
		player.openInventory(menu);
	}
	
	private void openInventoryInterface(Player player) {
		Inventory menu = Bukkit.createInventory(null, 9, "Inventory");
		player.openInventory(menu);
	}
	
	private void openInteractionsInterface(Player player) {
		Inventory menu = Bukkit.createInventory(null, 9, "Interactions");
		player.openInventory(menu);
	}
	
	private static void fill(Inventory menu) {
		for(int i = 0; i < menu.getSize(); i++) {
			menu.setItem(i, newItem(Material.BLACK_STAINED_GLASS_PANE, " ", true));
		}
	}
	
}
