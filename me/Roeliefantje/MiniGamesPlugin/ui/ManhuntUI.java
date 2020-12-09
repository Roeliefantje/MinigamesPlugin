package me.Roeliefantje.MiniGamesPlugin.ui;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Roeliefantje.MiniGamesPlugin.Minigames.Manhunt;
import me.Roeliefantje.MiniGamesPlugin.utils.Utils;

public class ManhuntUI {
	public static Inventory inv;
	public static String inventoryName;
	public static int invBoxes = 9;
	public static int invRows = 4;
	public static int totalSize = invBoxes * invRows;
	
	public static void initialize() {
		inventoryName = Utils.chat("&4&bPLAYER TO TRACK");
		
		inv = Bukkit.createInventory(null, totalSize);
	}
	
	public static Inventory GUI (Player p) {
		Inventory toReturn = Bukkit.createInventory(null, totalSize, inventoryName);
		
		Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		
		int place = 0;
		for(Player onlinePlayer : onlinePlayers) {
			Utils.createItemHead(inv, place, onlinePlayer);
			place++;
		}
		
		
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
	
	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		String username = clicked.getItemMeta().getDisplayName();
		Bukkit.broadcastMessage(Utils.chat("&eSelected player: " + username));
		
		
		Player target = null;
		Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();
		
		for(Player onlinePlayer : onlinePlayers) {
			if (onlinePlayer.getName().equals(username)){
				target = onlinePlayer;
				break;
			}
		}
		
		if (target != null) {
			new Manhunt(target);
		} else {
			p.sendMessage(Utils.chat("&4Something went wrong, try again!"));
		}
		
		p.closeInventory();
	}
}
