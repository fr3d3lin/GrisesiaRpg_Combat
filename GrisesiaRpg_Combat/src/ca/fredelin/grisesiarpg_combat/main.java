
package ca.fredelin.grisesiarpg_combat;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiversePortals.MultiversePortals;
import ca.fredelin.grisesiarpg_combat.Events.EnterRegionListener;
import ca.fredelin.grisesiarpg_combat.Events.MVTPortalEventListener;
import ca.fredelin.grisesiarpg_combat.Events.MVTeleportEventListener;
import ca.fredelin.grisesiarpg_combat.Events.PlayerDamageListener;
import ca.fredelin.grisesiarpg_combat.Events.PlayerJoinListener;
import ca.fredelin.grisesiarpg_combat.Events.PlayerVictimListener;
import ca.fredelin.grisesiarpg_combat.Files.ConfigManager;
import ca.fredelin.grisesiarpg_combat.Files.PVPManager;

public final class main extends JavaPlugin {
	public static main instance;
	public static MultiversePortals PortalInstance;
	public static MultiverseCore CoreInstance;
		
		
		@Override
		public void onEnable() {
			
			Bukkit.getServer().getConsoleSender().sendMessage("");
		    Bukkit.getServer().getConsoleSender().sendMessage("--------------------------------------------------------");
		    Bukkit.getServer().getConsoleSender().sendMessage("§aGrisesiaRPG - §b§nCombat§r §a- Le plugin viens de s'allumer");
		    Bukkit.getServer().getConsoleSender().sendMessage("--------------------------------------------------------");
		    Bukkit.getServer().getConsoleSender().sendMessage("");
		    
		    instance = this;
		    ConfigManager.setupConfig(this);
		    PVPManager.setupFile(this);
		    
		    this.getServer().getPluginManager().registerEvents(new EnterRegionListener(this), this);
		    this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
		    this.getServer().getPluginManager().registerEvents(new PlayerDamageListener(this), this);
		    this.getServer().getPluginManager().registerEvents(new PlayerVictimListener(this), this);
		    this.getServer().getPluginManager().registerEvents(new MVTPortalEventListener(), this);
		    this.getServer().getPluginManager().registerEvents(new MVTeleportEventListener(), this);
		    
		    PortalInstance = (MultiversePortals)Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Portals");
		    CoreInstance = (MultiverseCore)Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
		    
		}
		
		
		
		@Override
		public void onDisable() {
			
			Bukkit.getServer().getConsoleSender().sendMessage("");
		    Bukkit.getServer().getConsoleSender().sendMessage("---------------------------------------------------------");
		    Bukkit.getServer().getConsoleSender().sendMessage("§aGrisesiaRPG - §b§nCombat§r §a- Le plugin viens de s'eteindre");
		    Bukkit.getServer().getConsoleSender().sendMessage("---------------------------------------------------------");
		    Bukkit.getServer().getConsoleSender().sendMessage("");
			
			
		}

}
