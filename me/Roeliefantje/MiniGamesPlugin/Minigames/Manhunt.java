package me.Roeliefantje.MiniGamesPlugin.Minigames;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Roeliefantje.MiniGamesPlugin.Main;
import me.Roeliefantje.MiniGamesPlugin.ui.UI;

public class Manhunt {
	public Player target;
	private Main plugin;
	
	public Manhunt(Player target){
		this.target = target;
		this.plugin = UI.pluginMain;
		
		giveCompass(this.target);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
			public void run() { pointToPlayer();}
		},20, 20);
	}
	
	private void pointToPlayer() {
		Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		
		for(Player p : onlinePlayers) {
			p.setCompassTarget(target.getLocation());
		}
	}
	
	private void giveCompass(Player target) {
		Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		
		for(Player p : onlinePlayers) {
			if(p != target) {
				p.getInventory().addItem(new ItemStack(Material.COMPASS, 1));
			}
		}
	}
}
