package magma_monsters.configs;

import java.nio.file.Path;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import magma_monsters.Reference;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Config {

    public static final String MAGMA_MONSTER = "Magma Monster Settings";
    public static final String MAGMA_MONSTER_GRUNT = "Magma Monster Grunt Settings";

    private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_OW_SPAWN;
	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_HELL_SPAWN;

	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_SPAWN_MIN_Y_HEIGHT;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_SPAWN_MAX_Y_HEIGHT;
	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_BLOCK_FIRE;

	public static ForgeConfigSpec.DoubleValue MAGMA_MONSTER_HEALTH;
	public static ForgeConfigSpec.DoubleValue MAGMA_MONSTER_ATTACK_DAMAGE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_SLOWNESS_EFFECT_DURATION;

	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_GRUNT_OW_SPAWN;
	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_GRUNT_HELL_SPAWN;

	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_SPAWN_MIN_Y_HEIGHT;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_SPAWN_MAX_Y_HEIGHT;
	public static ForgeConfigSpec.BooleanValue MAGMA_MONSTER_GRUNT_BLOCK_FIRE;

	public static ForgeConfigSpec.DoubleValue MAGMA_MONSTER_GRUNT_HEALTH;
	public static ForgeConfigSpec.DoubleValue MAGMA_MONSTER_GRUNT_ATTACK_DAMAGE;
	public static ForgeConfigSpec.IntValue MAGMA_MONSTER_GRUNT_SLOWNESS_EFFECT_DURATION;

	
    static {
        COMMON_BUILDER.comment("Magma Monster Settings").push(MAGMA_MONSTER);
		MAGMA_MONSTER_OW_SPAWN = COMMON_BUILDER.comment("Spawn Over World Magma Monsters").define("magma_monster_ow_spawns", true);
		MAGMA_MONSTER_HELL_SPAWN = COMMON_BUILDER.comment("Spawn Nether Magma Monsters").define("magma_monster_nether_spawns", true);

		MAGMA_MONSTER_SPAWN_MIN_Y_HEIGHT = COMMON_BUILDER.comment("Magma Monster Min Y Spawn Height").defineInRange("magma_monster_min_y", -64, -2032, 2032);
		MAGMA_MONSTER_SPAWN_MAX_Y_HEIGHT = COMMON_BUILDER.comment("Magma Monster Max Y Spawn Height").defineInRange("magma_monster_max_y", 319, -2032, 2032);
		MAGMA_MONSTER_BLOCK_FIRE = COMMON_BUILDER.comment("Spawn Fire Whilst On Flamable Blocks").define("magma_monster_burns_blocks", true);

		MAGMA_MONSTER_HEALTH = COMMON_BUILDER.comment("Magma Monster Health").defineInRange("magma_monster_health", 25D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_ATTACK_DAMAGE = COMMON_BUILDER.comment("Magma Monster Attack Damage").defineInRange("magma_monster_attack_damage", 4D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_SLOWNESS_EFFECT_DURATION = COMMON_BUILDER.comment("Obsidian Monster Slowness Effect Duration (Seconds)").defineInRange("magma_monster_slowness_effect", 5, 1, Integer.MAX_VALUE);
		COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Magma Monster Grunt Settings").push(MAGMA_MONSTER_GRUNT);
		MAGMA_MONSTER_GRUNT_OW_SPAWN = COMMON_BUILDER.comment("Spawn Over World Magma Monster Grunts").define("magma_monster_grunt_ow_spawns", true);
		MAGMA_MONSTER_GRUNT_HELL_SPAWN = COMMON_BUILDER.comment("Spawn Nether Magma Monster Grunts").define("magma_monster_grunt_nether_spawns", true);

		MAGMA_MONSTER_GRUNT_SPAWN_MIN_Y_HEIGHT = COMMON_BUILDER.comment("Magma Monster Grunt Min Y Spawn Height").defineInRange("magma_monster_grunt_min_y", -64, -2032, 2032);
		MAGMA_MONSTER_GRUNT_SPAWN_MAX_Y_HEIGHT = COMMON_BUILDER.comment("Magma Monster Grunt Max Y Spawn Height").defineInRange("magma_monster_grunt_max_y", 319, -2032, 2032);
		MAGMA_MONSTER_GRUNT_BLOCK_FIRE = COMMON_BUILDER.comment("Spawn Fire Whilst Grunt On Flamable Blocks").define("magma_monster_grunt_burns_blocks", true);

		MAGMA_MONSTER_GRUNT_HEALTH = COMMON_BUILDER.comment("Magma Monster Grunt Health").defineInRange("magma_monster_grunt_health", 15D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_GRUNT_ATTACK_DAMAGE = COMMON_BUILDER.comment("Magma Monster Grunt Attack Damage").defineInRange("magma_monster_grunt_attack_damage", 2D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_GRUNT_SLOWNESS_EFFECT_DURATION = COMMON_BUILDER.comment("Obsidian Grunt Slowness Effect Duration (Seconds)").defineInRange("magma_monster_grunt_slowness_effect", 5, 1, Integer.MAX_VALUE);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
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
}

