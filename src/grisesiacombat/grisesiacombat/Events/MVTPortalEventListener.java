package grisesiacombat.grisesiacombat.Events;

import com.onarandombox.MultiverseCore.event.MVTeleportEvent;
import grisesiacombat.grisesiacombat.Files.ConfigManager;
import grisesiacombat.grisesiacombat.Files.PVPManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MVTPortalEventListener implements Listener {
   private static String color(String msg) {
      return ChatColor.translateAlternateColorCodes('&', msg);
   }

   @EventHandler
   public void TeleportEvent(MVTeleportEvent event) {
      Player player = event.getTeleportee();
      if (PVPManager.GetPlayer(player)) {
         player.sendMessage(color(ConfigManager.GetCooldownMessage()));
         event.setCancelled(true);
      }

   }
}

