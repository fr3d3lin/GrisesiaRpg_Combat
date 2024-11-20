package grisesiacombat.grisesiacombat.Events;

import grisesiacombat.grisesiacombat.CountdownBar;
import grisesiacombat.grisesiacombat.GrisesiaCombat;
import grisesiacombat.grisesiacombat.Files.PVPManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {
   private GrisesiaCombat main;

   public PlayerJoinListener(GrisesiaCombat grisesiaCombat) {
      this.main = grisesiaCombat;
   }

   @EventHandler
   public void OnJoin(PlayerJoinEvent event) {
      Player player = event.getPlayer();
      if (!player.hasPlayedBefore()) {
         PVPManager.AddPlayer(player);
      }

      if (PVPManager.GetPlayer(player)) {
         CountdownBar countdownBar = new CountdownBar(this.main);
         countdownBar.StartTimer(player);
      }

   }
}
