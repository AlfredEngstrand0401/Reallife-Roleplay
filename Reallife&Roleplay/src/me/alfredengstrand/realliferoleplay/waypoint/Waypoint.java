package me.alfredengstrand.realliferoleplay.waypoint;

import org.bukkit.Location;

public class Waypoint {
	
	private Location location;
	private String name;
	
	public Waypoint(Location location, String name) {
		this.setLocation(location);
		this.setName(name);
		this.save();
		WaypointManager.register(this);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private void save() {
		WaypointDataFile.get().set(name, location);
	}

}
