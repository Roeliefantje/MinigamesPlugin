package me.Roeliefantje.MiniGamesPlugin.Minigames;

import java.util.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import me.Roeliefantje.MiniGamesPlugin.Main;
import me.Roeliefantje.MiniGamesPlugin.listeners.DeathSwapListener;
import me.Roeliefantje.MiniGamesPlugin.ui.UI;
import me.Roeliefantje.MiniGamesPlugin.utils.Utils;

public class DeathSwap {
	private Main plugin;
	public List<Player> alivePlayers;
	private List<Location> playerLocations;
	
	public DeathSwap() {
		plugin = UI.pluginMain;
		alivePlayers = new ArrayList<Player>();
		
		Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		for (Player p : onlinePlayers) {
			alivePlayers.add(p);
		}
		
		Utils.startCooldown(10, 300);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() { teleportPlayers();}
		}, 300 * 20, 300 * 20);
		
		new DeathSwapListener(this);
	}
	
	private void teleportPlayers() {
		playerLocations.clear();
		
		for(int i = 0; i < alivePlayers.size(); i++) {
			playerLocations.add(alivePlayers.get(i).getLocation());
		}
		
		for(int i = 0; i < alivePlayers.size(); i++) {
			Player p = alivePlayers.get(i);
			Location tpLoc = playerLocations.get(i);
			if (i + 1 == alivePlayers.size()) {
				tpLoc = playerLocations.get(i + 1);
			} else {
				tpLoc = playerLocations.get(0);
			}
			
			p.teleport(tpLoc);
		}
	}
}
