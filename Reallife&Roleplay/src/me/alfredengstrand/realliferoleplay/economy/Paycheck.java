package me.alfredengstrand.realliferoleplay.economy;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public class Paycheck {

	private int amount;
	private List<String> infoboard;

	public Paycheck(int amount) {
		this.amount = amount;
		this.infoboard = createInfoboard();
	}

	public int getAmount() {
		return amount;
	}

	public List<String> getInfoboard() {
		return infoboard;
	}

	private List<String> createInfoboard() {
		this.infoboard = new ArrayList<String>();
		infoboard.add(ChatColor.BLUE + "========" + ChatColor.GOLD + " PayDay " + ChatColor.BLUE + "========");
		infoboard.add(ChatColor.BLUE + "Your Earnings" + ChatColor.GRAY + ": " + ChatColor.GOLD + amount + ChatColor.GOLD + "$");
		infoboard.add(ChatColor.GRAY + " >> " + ChatColor.BLUE + "Level Bonus" + ChatColor.GRAY + ": " + ChatColor.GREEN + "+1$");
		infoboard.add(ChatColor.GRAY + " >> " + ChatColor.BLUE + "Part-Time Job" + ChatColor.GRAY + ": " + ChatColor.RED + "-1$");
		infoboard.add(ChatColor.GRAY + " >> " + ChatColor.BLUE + "Faction Salary" + ChatColor.GRAY + ": " + ChatColor.GREEN + "+1$");
		infoboard.add(ChatColor.GRAY + " >> " + ChatColor.BLUE + "Event Bonus" + ChatColor.GRAY + ": " + ChatColor.GREEN + "+1$");
		infoboard.add(ChatColor.GRAY + " >> " + ChatColor.BLUE + "Health Insurance" + ChatColor.GRAY + ": " + ChatColor.RED + "-1$");
		infoboard.add(ChatColor.GRAY + " >> " + ChatColor.BLUE + "Corprate Tax" + ChatColor.GRAY + ": " + ChatColor.RED + "-1$");
		infoboard.add(ChatColor.GRAY + " >> " + ChatColor.BLUE + "Team Bonus" + ChatColor.GRAY + ": " + ChatColor.GREEN + "+1$");
		infoboard.add(ChatColor.BLUE + "========================");
		return infoboard;
	}

}
