package me.Roeliefantje.MiniGamesPlugin.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.Roeliefantje.MiniGamesPlugin.Main;
import me.Roeliefantje.MiniGamesPlugin.ui.ManhuntUI;
import me.Roeliefantje.MiniGamesPlugin.ui.UI;
import me.Roeliefantje.MiniGamesPlugin.utils.Utils;

public class InventoryClickListener implements Listener {
	
	private Main plugin;
	
	public InventoryClickListener(Main plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, this.plugin);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		String title = e.getView().getTitle();
		if (title.equals(Utils.chat(UI.inventoryName))) {
			e.setCancelled(true);
			if(e.getCurrentItem() == null) {
				return;
			}
			UI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
		}
		if (title.equals(Utils.chat(ManhuntUI.inventoryName))) {
			e.setCancelled(true);
			if(e.getCurrentItem() == null) {
				return;
			}
			ManhuntUI.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
		}
	}

}
