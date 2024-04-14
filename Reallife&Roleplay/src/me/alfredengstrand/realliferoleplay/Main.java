package me.alfredengstrand.realliferoleplay;

import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.alfredengstrand.realliferoleplay.bukkitEvents.PlayerJoin;
import me.alfredengstrand.realliferoleplay.bukkitEvents.PlayerLeave;
import me.alfredengstrand.realliferoleplay.bukkitEvents.PlayerMove;
import me.alfredengstrand.realliferoleplay.bukkitEvents.PlayerPickupXP;
import me.alfredengstrand.realliferoleplay.chatsystem.PlayerChat;
import me.alfredengstrand.realliferoleplay.chatsystem.ShoutCommand;
import me.alfredengstrand.realliferoleplay.chatsystem.WhisperCommand;
import me.alfredengstrand.realliferoleplay.economy.EconomyCommand;
import me.alfredengstrand.realliferoleplay.interactionsystem.InterfaceCommand;
import me.alfredengstrand.realliferoleplay.interactionsystem.UserInterface;
import me.alfredengstrand.realliferoleplay.mysql.MySQL;
import me.alfredengstrand.realliferoleplay.safemode.SafeMode;
import me.alfredengstrand.realliferoleplay.sidejobs.JobbCommand;
import me.alfredengstrand.realliferoleplay.sidejobs.farmer.events.PlayerFarming;
import me.alfredengstrand.realliferoleplay.sidejobs.lumberjack.events.PlayerWoodcutting;
import me.alfredengstrand.realliferoleplay.tutorialstoryline.Storyline;
import me.alfredengstrand.realliferoleplay.tutorialstoryline.StorylineSetup;
import me.alfredengstrand.realliferoleplay.waypoint.WaypointCommand;
import me.alfredengstrand.realliferoleplay.waypoint.WaypointCompass;
import me.alfredengstrand.realliferoleplay.waypoint.WaypointDataFile;
import me.alfredengstrand.realliferoleplay.waypoint.WaypointManager;
public class Main extends JavaPlugin {
	
	private MySQL sql;
	
	public void onEnable() {
		this.sql = new MySQL();
		this.registerCommand();
		this.registerEvent();
		this.loadConfig();
//		WaypointManager.loadWaypoints();
		try {
			this.sql.connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			this.startInSafemode();
		} catch (SQLException e) {
			e.printStackTrace();
			this.startInSafemode();
		}
	}
	
	public void onDisable() {
		this.beforeShutdown();
		WaypointManager.saveWaypoints();
		this.sql.disconnect();
	}
	
	private void beforeShutdown() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.kickPlayer("The server is reloading!");
		}
	}
	
	private void startInSafemode() {
		SafeMode.setActive(true);
	}
	
	private void registerCommand() {
		getCommand("storyline").setExecutor(new StorylineSetup());
		getCommand("eco").setExecutor(new EconomyCommand());
		getCommand("shout").setExecutor(new ShoutCommand());
		getCommand("whisper").setExecutor(new WhisperCommand());
		getCommand("interface").setExecutor(new InterfaceCommand());
		getCommand("farmer").setExecutor(new JobbCommand());
		getCommand("waypoint").setExecutor(new WaypointCommand());
	}
	
	private void registerEvent() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Storyline(this), this);
		pm.registerEvents(new PlayerChat(), this);
		pm.registerEvents(new PlayerJoin(this), this);
		pm.registerEvents(new PlayerLeave(this), this);
		pm.registerEvents(new UserInterface(), this);
		pm.registerEvents(new PlayerPickupXP(), this);
		pm.registerEvents(new PlayerMove(), this);
		pm.registerEvents(new PlayerFarming(), this);
		pm.registerEvents(new WaypointCompass(), this);
		pm.registerEvents(new PlayerWoodcutting(), this);
	}
	
	public MySQL getMySQL() {
		return sql;
	}
	
	private void loadConfig() {
	//	WaypointDataFile.setup();
	//	WaypointDataFile.save();
	}

}
