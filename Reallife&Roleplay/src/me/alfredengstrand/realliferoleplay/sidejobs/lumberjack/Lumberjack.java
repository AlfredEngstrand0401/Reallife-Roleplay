package me.alfredengstrand.realliferoleplay.sidejobs.lumberjack;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.alfredengstrand.realliferoleplay.sidejobs.Job;
import me.alfredengstrand.realliferoleplay.sidejobs.WorkTitles;

public class Lumberjack implements Job {
	
	private UUID uuid;
	private BossBar bossbar;
	private float xp;
	private int level;
	private float xpDrop;
	private ItemStack[] inventory;

	public Lumberjack(UUID uuid) {
		this.setUuid(uuid);
		this.setXpDrop(0.1f);
		this.setLevel(1);
		this.setupBossbar();
		this.inventory = Bukkit.getPlayer(uuid).getInventory().getContents();
		Bukkit.getPlayer(uuid).getInventory().clear();
		Bukkit.getPlayer(uuid).getInventory().addItem(new ItemStack(Material.GOLDEN_AXE));
	}

	@Override
	public int getSalary() {
		return 100;
	}

	@Override
	public String getTitle() {
		return WorkTitles.LUMBERJACK;
	}

	@Override
	public void setupBossbar() {
		if (this.getPlayer() != null && this.getPlayer().isOnline()) {
			setBossbar(Bukkit.createBossBar("Lumberjack Level | " + level, BarColor.YELLOW, BarStyle.SOLID));
			this.bossbar.setProgress(0.0);
		}
	}

	@Override
	public void removeBossbar() {
		bossbar.removeAll();
	}

	@Override
	public float getXP() {
		return xp;
	}

	@Override
	public void addXP(float amount) {
		this.xp += amount;
		double progress = bossbar.getProgress();
		progress += amount;
		bossbar.setProgress(progress);
	}

	@Override
	public void removeXP(float amount) {
		this.xp -= amount;
	}

	@Override
	public void setXP(float amount) {
		this.xp = amount;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}

	public BossBar getBossbar() {
		return bossbar;
	}

	public void setBossbar(BossBar bossbar) {
		this.bossbar = bossbar;
		this.bossbar.addPlayer(getPlayer());
	}

	public float getXp() {
		return xp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void update() {
		if (xp >= 1.0) {
			this.xp = 0;
			this.level += 1;
			this.bossbar.setTitle("Lumberjack Level | " + level);
			this.bossbar.setProgress(xp);
			Bukkit.getPlayer(uuid).sendTitle(ChatColor.GOLD + "Level Up!",
					ChatColor.GREEN + "You Have Reached Level " + ChatColor.GREEN + level + ChatColor.GREEN + "!");
		}
	}

	public float getXpDrop() {
		return xpDrop;
	}

	public void setXpDrop(float xpDrop) {
		this.xpDrop = xpDrop;
	}

	@Override
	public void complete() {
		bossbar.removePlayer(getPlayer());
	//	UserManager.getUser(uuid).addJobXP(XPLog.GET_LUMBERJACK_XP, xp);
		Bukkit.getPlayer(uuid).getInventory().setContents(inventory);
	}

	public ItemStack[] getInventory() {
		return inventory;
	}

}
