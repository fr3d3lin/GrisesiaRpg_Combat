package grisesiacombat.grisesiacombat;

import com.onarandombox.MultiverseCore.MultiverseCore;
import com.onarandombox.MultiversePortals.MultiversePortals;
import grisesiacombat.grisesiacombat.Events.EnterRegionListener;
import grisesiacombat.grisesiacombat.Events.MVTPortalEventListener;
import grisesiacombat.grisesiacombat.Events.MVTeleportEventListener;
import grisesiacombat.grisesiacombat.Events.PlayerDamageListener;
import grisesiacombat.grisesiacombat.Events.PlayerJoinListener;
import grisesiacombat.grisesiacombat.Files.ConfigManager;
import grisesiacombat.grisesiacombat.Files.PVPManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class GrisesiaCombat extends JavaPlugin {
   public static GrisesiaCombat instance;
   public static MultiversePortals portalInstance;
   public static MultiverseCore coreInstance;

   public void onEnable() {
      instance = this;
      ConfigManager.setupConfig(this);
      PVPManager.setupFile(this);
      this.getServer().getPluginManager().registerEvents(new EnterRegionListener(this), this);
      this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
      this.getServer().getPluginManager().registerEvents(new PlayerDamageListener(this), this);
      this.getServer().getPluginManager().registerEvents(new MVTPortalEventListener(), this);
      this.getServer().getPluginManager().registerEvents(new MVTeleportEventListener(), this);
      portalInstance = (MultiversePortals)Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Portals");
      coreInstance = (MultiverseCore)Bukkit.getServer().getPluginManager().getPlugin("Multiverse-Core");
   }

   public void onDisable() {
   }
}

