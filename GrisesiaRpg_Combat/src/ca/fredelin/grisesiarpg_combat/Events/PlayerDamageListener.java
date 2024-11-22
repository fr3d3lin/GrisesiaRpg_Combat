package ca.fredelin.grisesiarpg_combat.Events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.NamespacedKey;
import java.util.Iterator;
import org.bukkit.Bukkit;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Mob;
import ca.fredelin.grisesiarpg_combat.CountdownBar;
import ca.fredelin.grisesiarpg_combat.main;
import ca.fredelin.grisesiarpg_combat.Files.PVPManager;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;


public class PlayerDamageListener implements Listener {
	private main main;
	
	public PlayerDamageListener(main gcombat) {
		this.main = gcombat;
	}
	
	@EventHandler
	public void PlayerAttack(EntityDamageByEntityEvent event) {
		Entity attacker = event.getDamager();
		Entity victim = event.getEntity();
		
		if (attacker instanceof Player) {
			
			LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer((Player)attacker);
			RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
			RegionQuery query = container.createQuery();
			ApplicableRegionSet set = query.getApplicableRegions(localPlayer.getLocation());
			Iterator<?> var8 = set.iterator();
			
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
				} while(!(victim instanceof Mob) && !(victim instanceof Player));
				
				Player player = (Player)attacker;
				PVPManager.ChangePlayerState(player, true);
				CountdownBar countdownBar;
				if (PVPManager.GetPlayer(player)) {
					Iterator<?> it = Bukkit.getBossBars();
					
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
				
