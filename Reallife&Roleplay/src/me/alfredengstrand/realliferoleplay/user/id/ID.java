package me.alfredengstrand.realliferoleplay.user.id;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.alfredengstrand.realliferoleplay.Main;

public class ID {

	Main plugin;

	private UUID uuid;
	private String country;
	private int age;
	private String gender;

	public ID(Main plugin, UUID uuid) {
		this.plugin = plugin;
		this.uuid = uuid;
		this.country = "Unknown";
		this.setAge(0);
		this.setGender("Unknown");
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}
	
	public UUID getUUID() {
		return uuid;
	}

	public String getCountry() {
		return country;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
