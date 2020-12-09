package me.Roeliefantje.MiniGamesPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.Roeliefantje.MiniGamesPlugin.listeners.InventoryClickListener;
import me.Roeliefantje.MiniGamesPlugin.ui.ManhuntUI;
import me.Roeliefantje.MiniGamesPlugin.ui.UI;
import me.Roeliefantje.MiniGamesPlugin.utils.Utils;

public class Main extends JavaPlugin{
	
	@Override
	public void onEnable() {
		//startup
		//reloads
		//plugin reloads
		new InventoryClickListener(this);
		UI.initialize(this);
		ManhuntUI.initialize();
	}
	
	public boolean onCommand(CommandSender usr, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("play")) {
			if (usr instanceof Player) {
				Player p = (Player) usr;
				p.sendMessage(Utils.chat("&eOpening GUI"));
				p.openInventory(UI.GUI(p));
				
			} else {
				usr.sendMessage("Consoles can't open GUI's!");
			}
		}
		return true;
	}
}
