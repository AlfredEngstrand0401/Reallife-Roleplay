package me.alfredengstrand.realliferoleplay.bukkitEvents;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import me.alfredengstrand.realliferoleplay.interactionsystem.UserInterface;

public class PlayerInteract implements Listener {
	
	@EventHandler
	public void onPlayerRightClickPlayer(PlayerInteractEntityEvent e) {
		Player player = e.getPlayer();
		Entity targetEntity = e.getRightClicked();
		if(!(targetEntity instanceof Player)) {
			return;
		}
		Player target = (Player) targetEntity;
		UserInterface.openInfoMenu(player, target);
	}

}
