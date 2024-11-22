package ca.fredelin.grisesiarpg_combat.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ca.fredelin.grisesiarpg_combat.CountdownBar;
import ca.fredelin.grisesiarpg_combat.main;
import ca.fredelin.grisesiarpg_combat.Files.PVPManager;

public class PlayerJoinListener implements Listener {
	private main main;
	
	public PlayerJoinListener(main grisesiaCombat) {
		this.main = grisesiaCombat;
	}
	
	@EventHandler
	public void OnJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (!player.hasPlayedBefore()) {
			PVPManager.AddPlayer(player);
		}
		
		if (PVPManager.GetPlayer(player)) {
			CountdownBar countdownbar = new CountdownBar(this.main);
			countdownbar.StartTimer(player);
		}
	}

}
