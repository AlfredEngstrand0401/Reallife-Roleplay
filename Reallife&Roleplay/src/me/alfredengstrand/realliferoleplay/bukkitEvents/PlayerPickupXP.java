package me.alfredengstrand.realliferoleplay.bukkitEvents;

import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerPickupXP implements Listener {
	
	@EventHandler
	public void onPlayerPickupXP(ExperienceOrb e) {
		e.setExperience(0);
		e.setTicksLived(0);
	}

}
