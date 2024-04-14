package me.alfredengstrand.realliferoleplay.economy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Economy {

	public static List<Account> accounts = new ArrayList<Account>();

	public static Account getAccount(Player player) {
		Account account = null;
		for (Account currentAccount : accounts) {
			if (currentAccount.getUuid() == player.getUniqueId()) {
				account = currentAccount;
			}
		}
		return account;
	}

	@SuppressWarnings("deprecation")
	public static void withdraw(Player player, int amount) {
		Account account = getAccount(player);
		account.removeBalance(amount);
		player.sendTitle(ChatColor.RED + "$" + ChatColor.RED + amount, " ");
	}

	@SuppressWarnings("deprecation")
	public static void deposit(Player player, int amount) {
		Account account = getAccount(player);
		account.addBalance(amount);
		player.sendTitle(ChatColor.GREEN + "$" + ChatColor.GREEN + amount, " ");
	}

	public static void tax(Player player) {
		Account account = getAccount(player);
		float tax = account.getBalance() * 0.2f;
		account.removeBalance(tax);
	}

}
