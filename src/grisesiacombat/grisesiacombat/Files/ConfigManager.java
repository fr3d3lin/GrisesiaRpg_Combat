package grisesiacombat.grisesiacombat.Files;

import grisesiacombat.grisesiacombat.GrisesiaCombat;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigManager {
   private static FileConfiguration config;
   private static GrisesiaCombat main;

   public static void setupConfig(GrisesiaCombat gCombat) {
      main = gCombat;
      gCombat.saveDefaultConfig();
      config = gCombat.getConfig();
   }

   public static double GetCooldownTime() {
      return config.getDouble("cooldown-time");
   }

   public static String GetCooldownMessage() {
      return config.getString("cooldown-message");
   }
}