package ca.fredelin.grisesiarpg_combat.Events;

import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import ca.fredelin.grisesiarpg_combat.main;
import ca.fredelin.grisesiarpg_combat.Files.ConfigManager;
import ca.fredelin.grisesiarpg_combat.Files.PVPManager;
import net.raidstone.wgevents.events.RegionEnteredEvent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class EnterRegionListener implements Listener {
   private main main;

   public EnterRegionListener(main grisesiaCombat) {
      this.main = grisesiaCombat;
   }

   private static String color(String msg) {
      return ChatColor.translateAlternateColorCodes('&', msg);
   }

   @EventHandler
   public void onRegionEntered(RegionEnteredEvent event) {
	   
	   Player player = event.getPlayer();
	   ProtectedRegion region = event.getRegion();
	   if (player != null && PVPManager.GetPlayer(player) && !region.getFlags().containsValue(Flags.PVP)) {
		   player.sendMessage(color(ConfigManager.GetCooldownMessage()));
		   event.setCancelled(true);
      }

   }
}