package me.alfredengstrand.realliferoleplay.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import me.alfredengstrand.realliferoleplay.Main;
import me.alfredengstrand.realliferoleplay.afk.AfkHandler;
import me.alfredengstrand.realliferoleplay.sidejobs.Job;
import me.alfredengstrand.realliferoleplay.sidejobs.Jobs;
import me.alfredengstrand.realliferoleplay.sidejobs.XPLog;
import me.alfredengstrand.realliferoleplay.user.id.ID;
import me.alfredengstrand.realliferoleplay.user.license.License;
import me.alfredengstrand.realliferoleplay.waypoint.Waypoint;

public class User {

	private Map<String, Float> xp = new HashMap<String, Float>();

	Main plugin;

	private UUID uuid;
	private ID id;
	private List<License> licenses;
	private BukkitTask task;
	private boolean afk;
	private int idleTime;
	private AfkHandler afkHandler;
	private int level;
	private Job sidejob;
	private int playtime;
	private Waypoint selectedWaypoint;

	public User(UUID uuid, Main plugin) {
		this.uuid = uuid;
		this.plugin = plugin;
		this.startTimer();
		this.setIdleTime(0);
		this.setAfkHandler(new AfkHandler(this, plugin));
		this.setLevel(1);
		this.afkHandler.scheduleAFKCheckTask();
		this.initXP();
	}

	public void setSideJob(Job job) {
		this.sidejob = job;
		Jobs.registerEmployee(Bukkit.getPlayer(uuid), sidejob);
	}

	public UUID getUuid() {
		return uuid;
	}

	public ID getId() {
		return id;
	}

	public List<License> getLicenses() {
		return licenses;
	}

	public BukkitTask getTask() {
		return task;
	}

	public boolean isAfk() {
		return afk;
	}

	public void setAfk(boolean afk) {
		this.afk = afk;
	}

	public void startTimer() {

		task = new BukkitRunnable() {
			@Override
			public void run() {
				if (!isAfk()) {
					updateLevel();
				}
			}
		}.runTaskTimer(plugin, 0L, 20 * 60 * 60);
	}

	public int getIdleTime() {
		return idleTime;
	}

	public void setIdleTime(int idleTime) {
		this.idleTime = idleTime;
	}

	public void resetIdleTime() {
		this.setIdleTime(0);
	}

	public AfkHandler getAfkHandler() {
		return afkHandler;
	}

	public void setAfkHandler(AfkHandler afkHandler) {
		this.afkHandler = afkHandler;
	}

	public Job getSideJob() {
		return Jobs.workers.get(uuid);
	}

	public void addJobXP(String sidejob, float amount) {
		float current = xp.get(sidejob);
		current += amount;
		xp.put(sidejob, current);
	}

	private void initXP() {
		xp.put(XPLog.GET_FARMING_XP, (float) 0);
		xp.put(XPLog.GET_MINING_XP, (float) 0);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getPlaytime() {
		return playtime;
	}

	public void updateLevel() {
		this.playtime++;
		if (this.playtime >= 7200 && this.level < 2) {
			this.setLevel(2);
		}
		if (this.playtime >= 18000 && this.level < 3) {
			this.setLevel(3);
		}
	}

	public Waypoint getSelectedWaypoint() {
		return selectedWaypoint;
	}

	public void setSelectedWaypoint(Waypoint selectedWaypoint) {
		this.selectedWaypoint = selectedWaypoint;
	}
}
