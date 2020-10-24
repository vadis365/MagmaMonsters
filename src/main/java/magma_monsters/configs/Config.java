package magma_monsters.configs;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import magma_monsters.Reference;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    public static final String MAGMA_MONSTER = "Magma Monster Settings";
    public static final String MAGMA_MONSTER_GRUNT = "Magma Monster Grunt Settings";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

	public static ConfigValue<List<? extends Integer>> MAGMA_MONSTER_BLACKLISTED_DIMS;
	public static ConfigValue<List<? extends Integer>> MAGMA_MONSTER_GRUNT_BLACKLISTED_DIMS;

	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_OW_SPAWN;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_OW_MIN_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_OW_MAX_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_OW_SPAWN_PROBABILITY;

	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_HELL_SPAWN;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_HELL_MIN_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_HELL_MAX_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_HELL_SPAWN_PROBABILITY;

	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_SPAWN_Y_HEIGHT;
	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_BLOCK_FIRE;

	public static ForgeConfigSpec.DoubleValue MAGMA_MONSTER_HEALTH;
	public static ForgeConfigSpec.DoubleValue MAGMA_MONSTER_ATTACK_DAMAGE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_SLOWNESS_EFFECT_DURATION;

	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_GRUNT_OW_SPAWN;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_OW_MIN_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_OW_MAX_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_OW_SPAWN_PROBABILITY;

	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_GRUNT_HELL_SPAWN;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_HELL_MIN_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_HELL_MAX_SPAWN_SIZE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_HELL_SPAWN_PROBABILITY;

	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_SPAWN_Y_HEIGHT;
	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_GRUNT_BLOCK_FIRE;

	public static ForgeConfigSpec.DoubleValue MAGMA_MONSTER_GRUNT_HEALTH;
	public static ForgeConfigSpec.DoubleValue MAGMA_MONSTER_GRUNT_ATTACK_DAMAGE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_SLOWNESS_EFFECT_DURATION;

	static List<Integer> blacklistedDimsMagmaMonster = new ArrayList<>();
	static List<Integer> blacklistedDimsMagmaMonsterGrunt = new ArrayList<>();
	
    static {
        COMMON_BUILDER.comment("Magma Monster Settings").push(MAGMA_MONSTER);
        
		MAGMA_MONSTER_OW_SPAWN = COMMON_BUILDER.comment("Spawn Over World Magma Monsters").define("magma_monster_ow_spawns", true);
		MAGMA_MONSTER_OW_MIN_SPAWN_SIZE = COMMON_BUILDER.comment("Over World Magma Monster Group Minimum Size").defineInRange("magma_monster_min_spawn_size", 1, 1, Integer.MAX_VALUE);
		MAGMA_MONSTER_OW_MAX_SPAWN_SIZE = COMMON_BUILDER.comment("Over World Magma Monster Group Maximum Size").defineInRange("magma_monster_max_spawn_size", 3, 1, Integer.MAX_VALUE);
		MAGMA_MONSTER_OW_SPAWN_PROBABILITY = COMMON_BUILDER.comment("Over World Magma Monster Chance Probability").defineInRange("magma_monster_chance", 20, 1, Integer.MAX_VALUE);

		MAGMA_MONSTER_HELL_SPAWN = COMMON_BUILDER.comment("Spawn Nether Magma Monsters").define("magma_monster_nether_spawns", true);
		MAGMA_MONSTER_HELL_MIN_SPAWN_SIZE = COMMON_BUILDER.comment("Nether Magma Monster Group Minimum Size").defineInRange("magma_monster_min_spawn_size", 1, 1, Integer.MAX_VALUE);
		MAGMA_MONSTER_HELL_MAX_SPAWN_SIZE = COMMON_BUILDER.comment("Nether Magma Monster Group Maximum Size").defineInRange("magma_monster_max_spawn_size", 3, 1, Integer.MAX_VALUE);
		MAGMA_MONSTER_HELL_SPAWN_PROBABILITY = COMMON_BUILDER.comment("Nether Magma Monster Chance Probability").defineInRange("magma_monster_chance", 20, 1, Integer.MAX_VALUE);

		MAGMA_MONSTER_SPAWN_Y_HEIGHT = COMMON_BUILDER.comment("Magma Monster Max Y Spawn Height").defineInRange("magma_monster_max_y", 63, 1, 256);
		MAGMA_MONSTER_BLOCK_FIRE = COMMON_BUILDER.comment("Spawn Fire Whilst On Flamable Blocks").define("magma_monster_burns_blocks", true);

		MAGMA_MONSTER_HEALTH = COMMON_BUILDER.comment("Magma Monster Health").defineInRange("magma_monster_health", 25D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_ATTACK_DAMAGE = COMMON_BUILDER.comment("Magma Monster Attack Damage").defineInRange("magma_monster_attack_damage", 4D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_SLOWNESS_EFFECT_DURATION = COMMON_BUILDER.comment("Obsidian Monster Slowness Effect Duration (Seconds)").defineInRange("magma_monster_slowness_effect", 5, 1, Integer.MAX_VALUE);

		MAGMA_MONSTER_BLACKLISTED_DIMS = COMMON_BUILDER.comment("Magma Monster Dimension Blacklist").defineList("magma_monster_dimensions", blacklistedDimsMagmaMonster, p-> isSafeInteger((int) p));

		COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Magma Monster Grunt Settings").push(MAGMA_MONSTER_GRUNT);
		MAGMA_MONSTER_GRUNT_OW_SPAWN = COMMON_BUILDER.comment("Spawn Over World Magma Monster Grunts").define("magma_monster_grunt_ow_spawns", true);
		MAGMA_MONSTER_GRUNT_OW_MIN_SPAWN_SIZE = COMMON_BUILDER.comment("Over World Magma Monster Grunt Group Minimum Size").defineInRange("magma_monster_grunt_min_spawn_size", 1, 1, Integer.MAX_VALUE);
		MAGMA_MONSTER_GRUNT_OW_MAX_SPAWN_SIZE = COMMON_BUILDER.comment("Over World Magma Monster Grunt Group Maximum Size").defineInRange("magma_monster_grunt_max_spawn_size", 3, 1, Integer.MAX_VALUE);
		MAGMA_MONSTER_GRUNT_OW_SPAWN_PROBABILITY = COMMON_BUILDER.comment("Over World Magma Monster Grunt Chance Probability").defineInRange("magma_monster_grunt_chance", 20, 1, Integer.MAX_VALUE);

		MAGMA_MONSTER_GRUNT_HELL_SPAWN = COMMON_BUILDER.comment("Spawn Nether Magma Monster Grunts").define("magma_monster_grunt_nether_spawns", true);
		MAGMA_MONSTER_GRUNT_HELL_MIN_SPAWN_SIZE = COMMON_BUILDER.comment("Nether Magma Monster Grunt Group Minimum Size").defineInRange("magma_monster_grunt_min_spawn_size", 1, 1, Integer.MAX_VALUE);
		MAGMA_MONSTER_GRUNT_HELL_MAX_SPAWN_SIZE = COMMON_BUILDER.comment("Nether Magma Monster Grunt Group Maximum Size").defineInRange("magma_monster_grunt_max_spawn_size", 3, 1, Integer.MAX_VALUE);
		MAGMA_MONSTER_GRUNT_HELL_SPAWN_PROBABILITY = COMMON_BUILDER.comment("Nether Magma Monster Grunt Chance Probability").defineInRange("magma_monster_grunt_chance", 20, 1, Integer.MAX_VALUE);

		MAGMA_MONSTER_GRUNT_SPAWN_Y_HEIGHT = COMMON_BUILDER.comment("Magma Monster Grunt Max Y Spawn Height").defineInRange("magma_monster_grunt_max_y", 63, 1, 256);
		MAGMA_MONSTER_GRUNT_BLOCK_FIRE = COMMON_BUILDER.comment("Spawn Fire Whilst Grunt On Flamable Blocks").define("magma_monster_grunt_burns_blocks", true);

		MAGMA_MONSTER_GRUNT_HEALTH = COMMON_BUILDER.comment("Magma Monster Grunt Health").defineInRange("magma_monster_grunt_health", 15D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_GRUNT_ATTACK_DAMAGE = COMMON_BUILDER.comment("Magma Monster Grunt Attack Damage").defineInRange("magma_monster_grunt_attack_damage", 2D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_GRUNT_SLOWNESS_EFFECT_DURATION = COMMON_BUILDER.comment("Obsidian Grunt Slowness Effect Duration (Seconds)").defineInRange("magma_monster_grunt_slowness_effect", 5, 1, Integer.MAX_VALUE);

		MAGMA_MONSTER_GRUNT_BLACKLISTED_DIMS = COMMON_BUILDER.comment("Magma Monster Grunt Dimension Blacklist").defineList("magma_monster_grunt_dimensions", blacklistedDimsMagmaMonsterGrunt, p-> isSafeInteger((int) p));

        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

	public static boolean isSafeInteger(int checkNum) {
		return checkNum >= Integer.MIN_VALUE && checkNum <= Integer.MAX_VALUE;
	}

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
  
        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfig.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfig.Reloading configEvent) {
    }
}

