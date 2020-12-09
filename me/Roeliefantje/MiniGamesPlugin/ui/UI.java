package me.Roeliefantje.MiniGamesPlugin.ui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Roeliefantje.MiniGamesPlugin.Main;
import me.Roeliefantje.MiniGamesPlugin.Minigames.BlockShuffle;
import me.Roeliefantje.MiniGamesPlugin.utils.Utils;

public class UI {
	public static Inventory inv;
	public static String inventoryName;
	public static Main pluginMain;
	public static int invBoxes = 9;
	public static int invRows = 1;
	public static int totalSize = invBoxes * invRows;
	
	public static void initialize(Main plugin) {
		inventoryName = Utils.chat("&4SELECT YOUR MINIGAME");
		pluginMain = plugin;
		
		inv = Bukkit.createInventory(null, totalSize);
	}
	
	public static Inventory GUI (Player p) {
		Inventory toReturn = Bukkit.createInventory(null, totalSize, inventoryName);
		
		Utils.createItem(inv, Material.COMPASS, 1, 0, Utils.chat("&4MANHUNT"),
													  Utils.chat("&eOne player tries to complete the game"),
													  Utils.chat("&ewhile the others try to kill him."));
		
		Utils.createItem(inv, Material.EMERALD, 1, 2, Utils.chat("&bBLOCK SHUFFLE"),
													  Utils.chat("&eFind the given item before time is over!"));
		
		Utils.createItem(inv, Material.LAVA_BUCKET, 1, 4, Utils.chat("&5DEATH SWAP"),
				  									  	  Utils.chat("&eSwitch position with the other players"),
				  									  	  Utils.chat("&eMake a trap and survive theirs!"));
		
		Utils.createItem(inv, Material.POPPY, 1, 6, Utils.chat("&bRANDOM BLOCK DROPS"),
				  									Utils.chat("&eBlocks drop random blocks!"));
		
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
	
	public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {
		if (clicked.getItemMeta().getDisplayName().equals(Utils.chat("&4MANHUNT"))){
			p.sendMessage(Utils.chat("&bStarting Manhunt!"));
			p.closeInventory();
			UI.openManhunt(p);
		}
		if (clicked.getItemMeta().getDisplayName().equals(Utils.chat("&bBLOCK SHUFFLE"))){
			p.sendMessage(Utils.chat("&bStarting blockshuffle!"));
			p.closeInventory();
			new BlockShuffle();
		}
		if (clicked.getItemMeta().getDisplayName().equals(Utils.chat("&5DEATH SWAP"))){
			p.sendMessage(Utils.chat("&bStart Death Swap!"));
			p.closeInventory();
		}
		if (clicked.getItemMeta().getDisplayName().equals(Utils.chat("&bRANDOM BLOCK DROPS"))){
			p.sendMessage(Utils.chat("&bNot implemented yet!"));
			p.closeInventory();
		}
	}
	
	public static void openManhunt(Player p) {
		p.openInventory(ManhuntUI.GUI(p));
	}
}
