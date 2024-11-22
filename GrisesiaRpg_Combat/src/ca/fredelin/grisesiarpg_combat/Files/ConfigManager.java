package ca.fredelin.grisesiarpg_combat.Files;

import org.bukkit.configuration.file.FileConfiguration;
import ca.fredelin.grisesiarpg_combat.main;

public class ConfigManager {
	private static FileConfiguration config;
	private static main main;
	
	public static void setupConfig(main gCombat) {
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
