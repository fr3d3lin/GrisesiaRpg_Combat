package ca.fredelin.grisesiarpg_combat.Events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.onarandombox.MultiversePortals.event.MVPortalEvent;

import ca.fredelin.grisesiarpg_combat.Files.ConfigManager;
import ca.fredelin.grisesiarpg_combat.Files.PVPManager;

public class MVTeleportEventListener implements Listener {
	private static String color(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	@EventHandler
	public void TeleportEvent(MVPortalEvent event) {
		Player player = event.getTeleportee();
		if (PVPManager.GetPlayer(player)) {
			player.sendMessage(color(ConfigManager.GetCooldownMessage()));
			event.setCancelled(true);
		}
	}
}
	
	