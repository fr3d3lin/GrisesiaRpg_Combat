package ca.fredelin.grisesiarpg_combat.Files;

import ca.fredelin.grisesiarpg_combat.main;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class PVPManager {
	private static File pvpFile;
	private static YamlConfiguration modifyPVPFile;
	private static main main;
	
	public static void setupFile(main gCombat) {
		main = gCombat;
		
		try {
			initiateFiles();
		} catch (IOException var2) {
			var2.printStackTrace();
		}
	}
	
	public static YamlConfiguration getModifyPVPFile() {
		return modifyPVPFile;
	}
	
	public static File getPvpFile() {
		return pvpFile;
	}
	
	private static void initiateFiles() throws IOException {
		pvpFile = new File(Bukkit.getServer().getPluginManager().getPlugin(main.getDescription().getName()).getDataFolder(), "PVP.yml");
		if (!pvpFile.exists()) {
			pvpFile.createNewFile();
		}
		
		modifyPVPFile = YamlConfiguration.loadConfiguration(pvpFile);
		if (!getModifyPVPFile().contains("pvp-tags")) {
			getModifyPVPFile().createSection("pvp-tags");
		}
		
		try {
			getModifyPVPFile().save(getPvpFile());
		} catch (IOException var1) {
			var1.printStackTrace();
		}
		
	}
	
	public static List<UUID> GetPlayers() {
		List<String> string = new ArrayList();
		string.addAll(getModifyPVPFile().getConfigurationSection("pvp-tag").getKeys(false));
		List<UUID> uuids = new ArrayList();
		
		for(int i = 0; i < string.size(); ++i) {
			uuids.add(UUID.fromString((String)string.get(i)));
		}
		
		return uuids;
	}
	
	public static boolean GetPlayer(Player player) {
		return getModifyPVPFile().getBoolean("pvp-tag." + player.getUniqueId());
	}
	
	public static void AddPlayer(Player player) {
		getModifyPVPFile().set("pvp-tags." + player.getUniqueId(), false);
		saveFile();
	}
	
	public static void ChangePlayerState(Player player, boolean state) {
		getModifyPVPFile().set("pvp-tags." + player.getUniqueId(), state);
		saveFile();
	}
	
	public static void RemovePlayer(Player player) {
		getModifyPVPFile().set("pvp-tags." + player.getUniqueId(), (Object)null);
		saveFile();
	}
	
	private static void saveFile() {
		try {
			getModifyPVPFile().save(getPvpFile());
		} catch (IOException var1) {
			var1.printStackTrace();
		}
	}
	
}







