package ca.fredelin.grisesiarpg_combat.Events;

import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import ca.fredelin.grisesiarpg_combat.CountdownBar;
import ca.fredelin.grisesiarpg_combat.Files.PVPManager;
import ca.fredelin.grisesiarpg_combat.main;

public class PlayerVictimListener implements Listener {
	private main main;
	
	public PlayerVictimListener(main gcombat) {
		this.main = gcombat;
	}
	
	
	@EventHandler
	public void onEntityDamageEvent(EntityDamageByEntityEvent e) {
		
		Entity victim = e.getEntity();
	       
        if (e.getEntityType() == EntityType.PLAYER) {
        	
			if (victim instanceof Player) {
				
				LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer((Player)victim);
				RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
				RegionQuery query = container.createQuery();
				ApplicableRegionSet set = query.getApplicableRegions(localPlayer.getLocation());
				

				if (set.testState(localPlayer, Flags.PVP)) {
					//do the code
					
					
					
					
					
					
					Player player = (Player)victim;
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



