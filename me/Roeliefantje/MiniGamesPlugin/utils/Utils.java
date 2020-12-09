package me.Roeliefantje.MiniGamesPlugin.utils;

import java.util.*;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Utils {
	public static String chat(String s) {
		return ChatColor.translateAlternateColorCodes('&', s);
	}
	
	public static void createItem(Inventory inv, Material mat, int amount, int invSlot, String displayName, String... loreString) {
		ItemStack item;
		List<String> lore = new ArrayList<String>();
		
		item = new ItemStack(mat, amount);
		
		ItemMeta meta = item.getItemMeta();
		for (String s : loreString) {
			lore.add(Utils.chat(s));
		}
		
		meta.setLore(lore);
		meta.setDisplayName(displayName);
		
		item.setItemMeta(meta);
		inv.setItem(invSlot, item);

		return;
	}
	
	public static void createItemHead(Inventory inv, int invSlot, Player p, String... loreString) {
		ItemStack item;
		List<String> lore = new ArrayList<String>();
		
		item = new ItemStack(Material.PLAYER_HEAD, 1);
		
		SkullMeta meta = (SkullMeta) item.getItemMeta();
		for (String s : loreString) {
			lore.add(Utils.chat(s));
		}
		
		meta.setLore(lore);
		meta.setOwningPlayer(p);
		String playerName = p.getName();
		meta.setDisplayName(playerName);
		
		item.setItemMeta(meta);
		inv.setItem(invSlot, item);

		return;
	}
	
	
}
