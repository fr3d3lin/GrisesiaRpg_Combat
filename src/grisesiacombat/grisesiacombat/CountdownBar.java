package grisesiacombat.grisesiacombat;

import grisesiacombat.grisesiacombat.Files.ConfigManager;
import grisesiacombat.grisesiacombat.Files.PVPManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class CountdownBar {
   private GrisesiaCombat main;
   private BukkitTask task;
   private double count;

   public CountdownBar(GrisesiaCombat gCombat) {
      this.main = gCombat;
   }

   private static String color(String msg) {
      return ChatColor.translateAlternateColorCodes('&', msg);
   }

   public void StartTimer(Player player) {
      this.count = ConfigManager.GetCooldownTime();
      BossBar bossBar = Bukkit.createBossBar(new NamespacedKey(this.main, player.getUniqueId().toString()), color("&c&lCOMBAT COOLDOWN: " + this.count), BarColor.RED, BarStyle.SOLID, new BarFlag[0]);
      bossBar.addPlayer(player);
      this.task = Bukkit.getScheduler().runTaskTimer(this.main, () -> {
         bossBar.setTitle(color("&c&lCOMBAT COOLDOWN: " + this.count));
         bossBar.setProgress(this.count / ConfigManager.GetCooldownTime());
         if (this.count == 0.0D) {
            bossBar.removePlayer(player);
            PVPManager.ChangePlayerState(player, false);
            this.task.cancel();
         }

         --this.count;
      }, 0L, 20L);
   }
}

