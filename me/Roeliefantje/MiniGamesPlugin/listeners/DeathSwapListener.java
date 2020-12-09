package me.Roeliefantje.MiniGamesPlugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.Roeliefantje.MiniGamesPlugin.Main;
import me.Roeliefantje.MiniGamesPlugin.Minigames.DeathSwap;
import me.Roeliefantje.MiniGamesPlugin.ui.UI;
import me.Roeliefantje.MiniGamesPlugin.utils.Utils;

public class DeathSwapListener implements Listener{

	private Main plugin;
	private DeathSwap ds;
	
	public DeathSwapListener(DeathSwap ds) {
		this.plugin = UI.pluginMain;
		this.ds = ds;
		
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = (Player) e.getEntity();
		p.setGameMode(GameMode.SPECTATOR);
		ds.alivePlayers.remove(p);
		
		if (ds.alivePlayers.size() == 1) {
			Player winner = ds.alivePlayers.get(0);
			Bukkit.broadcastMessage(Utils.chat("&2" + winner.getName() + " won the Death Swap!"));
		}
	}
}
