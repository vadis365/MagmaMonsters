package magma_monsters.configs;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {

	public static final ConfigHandler INSTANCE = new ConfigHandler();
	public Configuration CONFIG;
	public static boolean MAGMA_OW_SPAWN;
	public static int MAGMA_OW_MIN_SPAWN_SIZE;
	public static int MAGMA_OW_MAX_SPAWN_SIZE;
	public static int MAGMA_OW_SPAWN_PROBABILITY;

	public static boolean MAGMA_HELL_SPAWN;
	public static int MAGMA_HELL_MIN_SPAWN_SIZE;
	public static int MAGMA_HELL_MAX_SPAWN_SIZE;
	public static int MAGMA_HELL_SPAWN_PROBABILITY;

	public static int MAGMA_SPAWN_Y_HEIGHT;
	public static boolean MAGMA_BLOCK_FIRE;

	public static float MAGMA_HEALTH;
	public static float MAGMA_ATTACK_DAMAGE;
	public static int SLOWNESS_EFFECT_DURATION;

	public static boolean MAGMA_GRUNT_OW_SPAWN;
	public static int MAGMA_GRUNT_OW_MIN_SPAWN_SIZE;
	public static int MAGMA_GRUNT_OW_MAX_SPAWN_SIZE;
	public static int MAGMA_GRUNT_OW_SPAWN_PROBABILITY;

	public static boolean MAGMA_GRUNT_HELL_SPAWN;
	public static int MAGMA_GRUNT_HELL_MIN_SPAWN_SIZE;
	public static int MAGMA_GRUNT_HELL_MAX_SPAWN_SIZE;
	public static int MAGMA_GRUNT_HELL_SPAWN_PROBABILITY;

	public static int MAGMA_GRUNT_SPAWN_Y_HEIGHT;
	public static boolean MAGMA_GRUNT_BLOCK_FIRE;

	public static float MAGMA_GRUNT_HEALTH;
	public static float MAGMA_GRUNT_ATTACK_DAMAGE;
	public static int MAGMA_GRUNT_SLOWNESS_EFFECT_DURATION;

	public final String[] usedCategories = { "Magma Monster Spawn Settings", "Magma Monster Grunt Spawn Settings" };

	public void loadConfig(FMLPreInitializationEvent event) {
		CONFIG = new Configuration(event.getSuggestedConfigurationFile());
		CONFIG.load();
		syncConfigs();
	}

	private void syncConfigs() {
		MAGMA_OW_SPAWN = CONFIG.get("Magma Monster Spawn Settings", "Spawn Over World Magma Monsters", true).getBoolean(true);
		MAGMA_OW_MIN_SPAWN_SIZE = CONFIG.get("Magma Monster Spawn Settings", "Over World Magma Monster Group Minimum Size", 1).getInt(1);
		MAGMA_OW_MAX_SPAWN_SIZE = CONFIG.get("Magma Monster Spawn Settings", "Over World Magma Monster Group Maximum Size", 3).getInt(3);
		MAGMA_OW_SPAWN_PROBABILITY = CONFIG.get("Magma Monster Spawn Settings", "Over World Magma Monster Chance Probability", 50).getInt(50);

		MAGMA_HELL_SPAWN = CONFIG.get("Magma Monster Spawn Settings", "Spawn Nether Magma Monsters", true).getBoolean(true);
		MAGMA_HELL_MIN_SPAWN_SIZE = CONFIG.get("Magma Monster Spawn Settings", "Nether Magma Monster Group Minimum Size", 1).getInt(1);
		MAGMA_HELL_MAX_SPAWN_SIZE = CONFIG.get("Magma Monster Spawn Settings", "Nether Magma Monster Group Maximum Size", 3).getInt(3);
		MAGMA_HELL_SPAWN_PROBABILITY = CONFIG.get("Magma Monster Spawn Settings", "Nether Magma Monster Chance Probability", 30).getInt(30);

		MAGMA_SPAWN_Y_HEIGHT = CONFIG.get("Magma Monster Spawn Settings", "Magma Monster Max Y Spawn Height", 63).getInt(63);
		MAGMA_BLOCK_FIRE = CONFIG.get("Magma Monster Spawn Settings", "Spawn Fire Whilst On Flamable Blocks", true).getBoolean(true);

		MAGMA_HEALTH = CONFIG.getFloat("Magma Monster Health","Magma Monster Spawn Settings", 25F, 1F, Float.MAX_VALUE, "");
		MAGMA_ATTACK_DAMAGE = CONFIG.getFloat("Magma Monster Attack Damage", "Magma Monster Spawn Settings", 4F, 1F, Float.MAX_VALUE, "");
		SLOWNESS_EFFECT_DURATION = CONFIG.get("Magma Monster Spawn Settings", "Obsidian Monster Slowness Effect Duration (Seconds)", 5).getInt(5);

		MAGMA_GRUNT_OW_SPAWN = CONFIG.get("Magma Monster Grunt Spawn Settings", "Spawn Over World Magma Monster Grunts", true).getBoolean(true);
		MAGMA_GRUNT_OW_MIN_SPAWN_SIZE = CONFIG.get("Magma Monster Grunt Spawn Settings", "Over World Magma Monster Grunt Group Minimum Size", 1).getInt(1);
		MAGMA_GRUNT_OW_MAX_SPAWN_SIZE = CONFIG.get("Magma Monster Grunt Spawn Settings", "Over World Magma Monster Grunt Group Maximum Size", 3).getInt(3);
		MAGMA_GRUNT_OW_SPAWN_PROBABILITY = CONFIG.get("Magma Monster Grunt Spawn Settings", "Over World Magma Monster Grunt Chance Probability", 50).getInt(50);

		MAGMA_GRUNT_HELL_SPAWN = CONFIG.get("Magma Monster Grunt Spawn Settings", "Spawn Nether Magma Monster Grunts", true).getBoolean(true);
		MAGMA_GRUNT_HELL_MIN_SPAWN_SIZE = CONFIG.get("Magma Monster Grunt Spawn Settings", "Nether Magma Monster Grunt Group Minimum Size", 1).getInt(1);
		MAGMA_GRUNT_HELL_MAX_SPAWN_SIZE = CONFIG.get("Magma Monster Grunt Spawn Settings", "Nether Magma Monster Grunt Group Maximum Size", 3).getInt(3);
		MAGMA_GRUNT_HELL_SPAWN_PROBABILITY = CONFIG.get("Magma Monster Grunt Spawn Settings", "Nether Magma Monster Grunt Chance Probability", 30).getInt(30);

		MAGMA_GRUNT_SPAWN_Y_HEIGHT = CONFIG.get("Magma Monster Grunt Spawn Settings", "Magma Monster Grunt Max Y Spawn Height", 63).getInt(63);
		MAGMA_GRUNT_BLOCK_FIRE = CONFIG.get("Magma Monster Grunt Spawn Settings", "Grunts Spawn Fire Whilst On Flamable Blocks", true).getBoolean(true);

		MAGMA_GRUNT_HEALTH = CONFIG.getFloat("Magma Monster Grunt Health","Magma Monster Grunt Spawn Settings", 15F, 1F, Float.MAX_VALUE, "");
		MAGMA_GRUNT_ATTACK_DAMAGE = CONFIG.getFloat("Magma Monster Grunt Attack Damage", "Magma Monster Grunt Spawn Settings", 2F, 1F, Float.MAX_VALUE, "");
		MAGMA_GRUNT_SLOWNESS_EFFECT_DURATION = CONFIG.get("Magma Monster Grunt Spawn Settings", "Cobble Grunt Slowness Effect Duration (Seconds)", 5).getInt(5);
		
		if (CONFIG.hasChanged())
			CONFIG.save();
	}

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals("magma_monsters"))
			syncConfigs();
	}
}