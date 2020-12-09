package me.Roeliefantje.MiniGamesPlugin.Minigames;

import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;


import me.Roeliefantje.MiniGamesPlugin.Main;
import me.Roeliefantje.MiniGamesPlugin.listeners.BlockShuffleListener;
import me.Roeliefantje.MiniGamesPlugin.ui.UI;
import me.Roeliefantje.MiniGamesPlugin.utils.Utils;

public class BlockShuffle {
	public Player target;
	private Main plugin;
	
	public HashMap<Player, Material> blockToFind;
//	The Runnables that handle the cooldown and the blockcheck.
	public int[] cooldowns;
	public int checkBlocks;
	
	public BlockShuffle(){
		plugin = UI.pluginMain;
		blockToFind = new HashMap<Player, Material>();
		
		startRound();
	}
	
	public void startRound() {
		blockToFind.clear();
		
		Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		for (Player p : onlinePlayers) {
			Material mat = null;
			Random random = new Random();
			while(mat == null) {
				mat = Material.values()[random.nextInt(Material.values().length)];
				if(mat.isSolid() == false || mat.isBlock() == false || mat.isItem() == false) {
					mat = null;
				}
			}
			blockToFind.put(p, mat);
			p.sendMessage(Utils.chat("&eYour block is: " + mat.toString().replace("_", " ").toLowerCase() + "!, Good luck..."));
		}
		
		cooldowns = Utils.startCooldown(10, 300);
		checkBlocks = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			public void run() { checkPlayers();}
		}, 300 * 20, 300 * 20);
		
		new BlockShuffleListener(this);
		
	}
	
	private void checkPlayers() {
		Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		
		for(Player p : onlinePlayers) {
			if (blockToFind.containsKey(p)) {
				Material mat = blockToFind.get(p);
				Material standingOn = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
				if(mat != standingOn) {
					p.damage(4000);
				}
			}
		}
	}
}