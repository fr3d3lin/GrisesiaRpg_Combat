package ca.fredelin.grisesiarpg_combat;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.NamespacedKey;
import ca.fredelin.grisesiarpg_combat.Files.ConfigManager;
import ca.fredelin.grisesiarpg_combat.Files.PVPManager;
import net.md_5.bungee.api.ChatColor;

public class CountdownBar {
	private main main;
	private BukkitTask task;
	private double count;
	
	public CountdownBar(main gcombat) {
		this.main = gcombat;
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
