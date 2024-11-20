package grisesiacombat.grisesiacombat.Events;

import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;
import grisesiacombat.grisesiacombat.CountdownBar;
import grisesiacombat.grisesiacombat.GrisesiaCombat;
import grisesiacombat.grisesiacombat.Files.PVPManager;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerDamageListener implements Listener {
   private GrisesiaCombat main;

   public PlayerDamageListener(GrisesiaCombat gCombat) {
      this.main = gCombat;
   }

   @EventHandler
   public void PlayerAttack(EntityDamageByEntityEvent event) {
      Entity damager = event.getDamager();
      Entity damaged = event.getEntity();
      if (damager instanceof Player) {
         LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer((Player)damager);
         RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
         RegionQuery query = container.createQuery();
         ApplicableRegionSet set = query.getApplicableRegions(localPlayer.getLocation());
         Iterator var8 = set.iterator();

         while(true) {
            while(true) {
               ProtectedRegion region;
               do {
                  do {
                     if (!var8.hasNext()) {
                        return;
                     }

                     region = (ProtectedRegion)var8.next();
                  } while(region.getFlags().containsValue(Flags.PVP));
               } while(!(damaged instanceof Mob) && !(damaged instanceof Player));

               Player player = (Player)damager;
               PVPManager.ChangePlayerState(player, true);
               CountdownBar countdownBar;
               if (PVPManager.GetPlayer(player)) {
                  Iterator it = Bukkit.getBossBars();

                  while(it.hasNext()) {
                     KeyedBossBar bossBar = (KeyedBossBar)it.next();
                     if (bossBar.getKey().equals(new NamespacedKey(this.main, player.getUniqueId().toString()))) {
                        bossBar.removePlayer(player);
                        bossBar.setVisible(false);
                     }
                  }

                  countdownBar = new CountdownBar(this.main);
                  countdownBar.StartTimer(player);
               } else {
                  countdownBar = new CountdownBar(this.main);
                  countdownBar.StartTimer(player);
               }
            }
         }
      }
   }
}