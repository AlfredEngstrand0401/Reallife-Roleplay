package me.alfredengstrand.realliferoleplay.waypoint;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class WaypointDataFile {
	
	private static File file;
    private static FileConfiguration waypointFile;
 
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("ReallifeRoleplay").getDataFolder(), "waypoints.yml");
 
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
            	
            }
        }
        waypointFile = YamlConfiguration.loadConfiguration(file);
    }
 
    public static FileConfiguration get(){
        return waypointFile;
    }
 
    public static void save(){
        try{
        	waypointFile.save(file);
        }catch (IOException e){
            System.out.println("Couldn't save file");
        }
    }
 
    public static void reload(){
    	waypointFile = YamlConfiguration.loadConfiguration(file);
    }

}
