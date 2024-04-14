package me.alfredengstrand.realliferoleplay.tutorialstoryline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import me.alfredengstrand.realliferoleplay.Main;
import me.alfredengstrand.realliferoleplay.economy.Account;
import me.alfredengstrand.realliferoleplay.economy.Economy;

public class Storyline implements Listener {

	Main plugin;

	public Storyline(Main plugin) {
		this.plugin = plugin;
	}

	public static Map<Player, List<Villager>> storyline = new HashMap<Player, List<Villager>>();
	private Map<UUID, Long> cooldowns = new HashMap<>();

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		startStoryline(player);
		if (player.hasPlayedBefore()) {
			Economy.accounts.add(new Account(player.getUniqueId(), 0));
			return;
		}
		if (Economy.getAccount(player) == null) {
			Economy.accounts.add(new Account(player.getUniqueId(), 0));
		}
	}

	public static void startStoryline(Player player) {
		storyline.put(player, new ArrayList<Villager>());
	}

	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		Player player = event.getPlayer();
		String villagerName = event.getRightClicked().getCustomName();

		if (villagerName != null) {
			villagerName = villagerName.toLowerCase();
			switch (villagerName) {
			case "luis":
				handleLuis(player);
				break;
			case "pat":
				handlePat(player);
				break;
			case "jeff":
				handleJeff(player);
				break;
			case "nikita":
				handleNikita(player);
				break;
			}
		}
	}

	private void handleLuis(Player player) {
		if (!checkCooldown(player, "Luis", 2)) {
			return;
		}
		sendMessage(player, ChatColor.RED + "Luis: " + ChatColor.GRAY + "Hey, you!", 2);
		sendMessage(player, ChatColor.RED + "Luis: " + ChatColor.GRAY + "I see you're here now too!", 2);
		sendMessage(player, ChatColor.RED + "Luis: " + ChatColor.GRAY + "Finally we can get started!", 3);
		sendMessage(player, ChatColor.RED + "Luis: " + ChatColor.GRAY
				+ "Welcome to a new chapter in your life. You're probably wondering what kind of chapter?", 6);
		sendMessage(player, ChatColor.RED + "Luis: " + ChatColor.GRAY
				+ "Well, in this section you live your life as you see fit. You choose your life.", 6);
		sendMessage(player, ChatColor.RED + "Luis: " + ChatColor.GRAY
				+ "What does that mean? Well, everything! Go to Pat and he will tell you more.", 8);
	}

	private void handlePat(Player player) {
		if (!checkCooldown(player, "Pat", 2)) {
			return;
		}
		sendMessage(player, ChatColor.RED + "Pat: " + ChatColor.GRAY + "Ah! The newcomer, greetings.", 2);
		sendMessage(player, ChatColor.RED + "Pat: " + ChatColor.GRAY
				+ "You will definitely want to find out more. So open your ears!", 4);
		sendMessage(player, ChatColor.RED + "Pat: " + ChatColor.GRAY
				+ "Where do I start...  Here you can live your life the way you want. Do you want to be a criminal? or would you rather save people? Do you want to be a hero or a simple miner?",
				10);
		sendMessage(player, ChatColor.RED + "Pat: " + ChatColor.GRAY
				+ "It's in your hands. With us, there are various ways in which you can build your future.", 5);
		sendMessage(player, ChatColor.RED + "Pat: " + ChatColor.GRAY
				+ "We have so-called 'factions', no, not the faction from other servers. The Reallife & Roleplay Faction. I'll explain what that means.",
				10);
		sendMessage(player, ChatColor.RED + "Pat: " + ChatColor.GRAY
				+ "A distinction is made between state and crime factions. I think the name says it all.", 5);
		sendMessage(player, ChatColor.RED + "Pat: " + ChatColor.GRAY
				+ "The special thing about factions is that each faction has special abilities. Which of course I can't reveal. You'll have to find out for yourself. Go on to Jeff to find out more about how to earn money.",
				20);
	}

	private void handleJeff(Player player) {
		if (!checkCooldown(player, "Pat", 2)) {
			return;
		}
		sendMessage(player, ChatColor.RED + "Jeff: " + ChatColor.GRAY
				+ "What's up, you want to know how to make money, I'll tell you.", 2);
		sendMessage(player, ChatColor.RED + "Jeff: " + ChatColor.GRAY
				+ "There are basically 3 different ways to make money. 1. join a faction. You get paid for every hour you play. 2. do sidejobs and earn money and level up in the sidejobs or the last point. Buy a company and work with your friends to become millionaires.",
				7);
		sendMessage(player, ChatColor.RED + "Jeff: " + ChatColor.GRAY + "But then what do I do with the money?\n"
				+ "Well, it's up to you, do you want a villa? Weapons? Body armor? Illegal things? Houses? It's all up to you! Decide for yourself.",
				15);
		sendMessage(player, ChatColor.RED + "Jeff: " + ChatColor.GRAY + "Continue to Nikita to complete the tutorial.",
				20);

	}

	private void handleNikita(Player player) {

		sendMessage(player, ChatColor.RED + "Nikita: " + ChatColor.GRAY
				+ "Yo yo, it's me nikita. I just wanted to give you a few starting tips. For you, I would recommend focusing on the basics. By that I mean, build up some money and make sure you have food first.  During this time you will automatically level up and can become a permanent part of a faction. Become a police chief, or take part in events like raids or airdrops. There are so many possibilities. ",
				2);
		sendMessage(player, ChatColor.RED + "Nikita: " + ChatColor.GRAY
				+ "I would like to remind you that this is a reallfie & roleplay server. We play soft roleplay here. That means don't say things you will never do in life. Of course it's a little toned down here. But to understand it completely, please go to our Discord and read the rules.",
				5);
		sendMessage(player,
				ChatColor.RED + "Nikita: " + ChatColor.GRAY
						+ "If you have any questions you can ask a team member ingame.\n"
						+ "With /support you can get in contact with a team member.",
				8);
		sendMessage(player, ChatColor.RED + "Nikita: " + ChatColor.GRAY
				+ "Be free and live your life, Nikita and Alfred wish you well.", 10);

		this.complete(player);
	}

	@SuppressWarnings("deprecation")
	public void complete(Player player) {

		plugin.getServer().getScheduler().runTaskLater(plugin, () -> {

			int particleCount = 100; // Number of particles to spawn
			double offsetX = 1.0; // Offset on the x-axis
			double offsetY = 1.0; // Offset on the y-axis
			double offsetZ = 1.0; // Offset on the z-axis
			double speed = 5; // Speed of particles

			// Spawn particles around the player
			player.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, // Particle type
					player.getLocation(), // Location to spawn particles
					particleCount, // Number of particles
					offsetX, offsetY, offsetZ, // Offsets
					speed // Speed of particles
			);
			Economy.getAccount(player).addBalance(500);

			player.sendTitle(" ", ChatColor.GREEN + "+500$");
			String header = ChatColor.YELLOW + "====================" + ChatColor.RESET;
			String title = ChatColor.GOLD + "  Tutorial Complete!";
			String message = ChatColor.GRAY + "Congratulations, " + ChatColor.YELLOW + player.getName() + ChatColor.GRAY
					+ "!";
			String completionMessage = ChatColor.GREEN + "You have successfully completed the tutorial.";

			player.sendMessage(header);
			player.sendMessage(title);
			player.sendMessage(header);
			player.sendMessage(message);
			player.sendMessage(completionMessage);
		}, 200); // 20 ticks delay (1 second)
	}

	private boolean checkCooldown(Player player, String villagerName, int cooldownSeconds) {
		UUID playerId = player.getUniqueId();
		if (cooldowns.containsKey(playerId)) {
			long lastInteractionTime = cooldowns.get(playerId);
			long currentTime = System.currentTimeMillis();
			long cooldownTime = cooldownSeconds * 1000; // Convert seconds to milliseconds
			if (currentTime - lastInteractionTime < cooldownTime) {
				// Cooldown not expired yet
				player.sendMessage("You must wait before talking to " + villagerName + " again.");
				return false;
			}
		}
		cooldowns.put(playerId, System.currentTimeMillis());
		return true;
	}

	private void sendMessage(Player player, String message, int delaySeconds) {
		new BukkitRunnable() {
			@Override
			public void run() {
				player.sendMessage(message);
			}
		}.runTaskLater(plugin, delaySeconds * 20); // Convert seconds to ticks
	}
}
