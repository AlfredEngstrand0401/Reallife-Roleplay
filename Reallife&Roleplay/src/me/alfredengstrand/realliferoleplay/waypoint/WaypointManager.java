package me.alfredengstrand.realliferoleplay.waypoint;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class WaypointManager {

	private static List<Waypoint> waypoints = new ArrayList<Waypoint>();

	public static Waypoint getWaypoint(String name) {
		for (Waypoint waypoint : waypoints) {
			if (waypoint.getName().equalsIgnoreCase(name)) {
				return waypoint;
			}
		}
		return null;
	}
	
	public static void register(Waypoint waypoint) {
		waypoints.add(waypoint);
	}
	
	public static void remove(Waypoint waypoint) {
		waypoints.remove(waypoint);
	}
	
	public static void saveWaypoints() {
		List<String> points = new ArrayList<String>();
		for(Waypoint point : waypoints) {
			points.add(point.getName());
		}
		WaypointDataFile.get().set("waypoints", points);
		for(Waypoint waypoint : waypoints) {
			WaypointDataFile.get().set(waypoint.getName(), waypoint.getLocation());
		}
		WaypointDataFile.save();
	}
	
	public static void loadWaypoints() {
		@SuppressWarnings("unchecked")
		List<String> points = (List<String>) WaypointDataFile.get().getList("waypoints");
		for(String point : points) {
			Location location = WaypointDataFile.get().getLocation(point);
			waypoints.add(new Waypoint(location, point));
		}
	}
}
