package me.Roeliefantje.MiniGamesPlugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.Roeliefantje.MiniGamesPlugin.Main;
import me.Roeliefantje.MiniGamesPlugin.ui.UI;
import me.Roeliefantje.MiniGamesPlugin.Minigames.BlockShuffle;

public class BlockShuffleListener implements Listener {

	private Main plugin;
	private BlockShuffle bs;
	
	public BlockShuffleListener(BlockShuffle bs) {
		this.plugin = UI.pluginMain;
		this.bs = bs;
		
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		
		if(bs.blockToFind.containsKey(p)) {
			Material matToMatch = bs.blockToFind.get(p);
			
			Material mat = p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType();
			
			if (mat == matToMatch) {
				bs.blockToFind.remove(p);
			}
			
			if (bs.blockToFind.isEmpty()) {
				for(int i = 0; i < bs.cooldowns.length; i++) {
					Bukkit.getScheduler().cancelTask(bs.cooldowns[i]);
				}
				Bukkit.getScheduler().cancelTask(bs.checkBlocks);
				
				bs.startRound();
			}
		}
		
	}
}
