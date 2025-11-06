package magma_monsters.configs;

import java.nio.file.Path;

import org.jetbrains.annotations.Nullable;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.neoforged.fml.config.IConfigSpec.ILoadedConfig;
import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    public static final String MAGMA_MONSTER = "Magma Monster Settings";
    public static final String MAGMA_MONSTER_GRUNT = "Magma Monster Grunt Settings";

    private static final ModConfigSpec.Builder SERVER_BUILDER = new ModConfigSpec.Builder();

    public static ModConfigSpec SERVER;

	public static ModConfigSpec.BooleanValue MAGMA_MONSTER_OW_SPAWN;
	public static ModConfigSpec.BooleanValue MAGMA_MONSTER_HELL_SPAWN;

	public static ModConfigSpec.IntValue MAGMA_MONSTER_SPAWN_MIN_Y_HEIGHT;
	public static ModConfigSpec.IntValue MAGMA_MONSTER_SPAWN_MAX_Y_HEIGHT;
	public static ModConfigSpec.BooleanValue MAGMA_MONSTER_BLOCK_FIRE;

	public static ModConfigSpec.DoubleValue MAGMA_MONSTER_HEALTH;
	public static ModConfigSpec.DoubleValue MAGMA_MONSTER_ATTACK_DAMAGE;
	public static ModConfigSpec.IntValue MAGMA_MONSTER_SLOWNESS_EFFECT_DURATION;

	public static ModConfigSpec.BooleanValue MAGMA_MONSTER_GRUNT_OW_SPAWN;
	public static ModConfigSpec.BooleanValue MAGMA_MONSTER_GRUNT_HELL_SPAWN;

	public static ModConfigSpec.IntValue MAGMA_MONSTER_GRUNT_SPAWN_MIN_Y_HEIGHT;
	public static ModConfigSpec.IntValue MAGMA_MONSTER_GRUNT_SPAWN_MAX_Y_HEIGHT;
	public static ModConfigSpec.BooleanValue MAGMA_MONSTER_GRUNT_BLOCK_FIRE;

	public static ModConfigSpec.DoubleValue MAGMA_MONSTER_GRUNT_HEALTH;
	public static ModConfigSpec.DoubleValue MAGMA_MONSTER_GRUNT_ATTACK_DAMAGE;
	public static ModConfigSpec.IntValue MAGMA_MONSTER_GRUNT_SLOWNESS_EFFECT_DURATION;

	
    static {
        SERVER_BUILDER.comment("Magma Monster Settings").push(MAGMA_MONSTER);
		MAGMA_MONSTER_OW_SPAWN = SERVER_BUILDER.comment("Spawn Over World Magma Monsters").define("magma_monster_ow_spawns", true);
		MAGMA_MONSTER_HELL_SPAWN = SERVER_BUILDER.comment("Spawn Nether Magma Monsters").define("magma_monster_nether_spawns", true);

		MAGMA_MONSTER_SPAWN_MIN_Y_HEIGHT = SERVER_BUILDER.comment("Magma Monster Min Y Spawn Height").defineInRange("magma_monster_min_y", -64, -2032, 2032);
		MAGMA_MONSTER_SPAWN_MAX_Y_HEIGHT = SERVER_BUILDER.comment("Magma Monster Max Y Spawn Height").defineInRange("magma_monster_max_y", 319, -2032, 2032);
		MAGMA_MONSTER_BLOCK_FIRE = SERVER_BUILDER.comment("Spawn Fire Whilst On Flamable Blocks").define("magma_monster_burns_blocks", true);

		MAGMA_MONSTER_HEALTH = SERVER_BUILDER.comment("Magma Monster Health").defineInRange("magma_monster_health", 25D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_ATTACK_DAMAGE = SERVER_BUILDER.comment("Magma Monster Attack Damage").defineInRange("magma_monster_attack_damage", 4D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_SLOWNESS_EFFECT_DURATION = SERVER_BUILDER.comment("Obsidian Monster Slowness Effect Duration (Seconds)").defineInRange("magma_monster_slowness_effect", 5, 1, Integer.MAX_VALUE);
		SERVER_BUILDER.pop();

        SERVER_BUILDER.comment("Magma Monster Grunt Settings").push(MAGMA_MONSTER_GRUNT);
		MAGMA_MONSTER_GRUNT_OW_SPAWN = SERVER_BUILDER.comment("Spawn Over World Magma Monster Grunts").define("magma_monster_grunt_ow_spawns", true);
		MAGMA_MONSTER_GRUNT_HELL_SPAWN = SERVER_BUILDER.comment("Spawn Nether Magma Monster Grunts").define("magma_monster_grunt_nether_spawns", true);

		MAGMA_MONSTER_GRUNT_SPAWN_MIN_Y_HEIGHT = SERVER_BUILDER.comment("Magma Monster Grunt Min Y Spawn Height").defineInRange("magma_monster_grunt_min_y", -64, -2032, 2032);
		MAGMA_MONSTER_GRUNT_SPAWN_MAX_Y_HEIGHT = SERVER_BUILDER.comment("Magma Monster Grunt Max Y Spawn Height").defineInRange("magma_monster_grunt_max_y", 319, -2032, 2032);
		MAGMA_MONSTER_GRUNT_BLOCK_FIRE = SERVER_BUILDER.comment("Spawn Fire Whilst Grunt On Flamable Blocks").define("magma_monster_grunt_burns_blocks", true);

		MAGMA_MONSTER_GRUNT_HEALTH = SERVER_BUILDER.comment("Magma Monster Grunt Health").defineInRange("magma_monster_grunt_health", 15D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_GRUNT_ATTACK_DAMAGE = SERVER_BUILDER.comment("Magma Monster Grunt Attack Damage").defineInRange("magma_monster_grunt_attack_damage", 2D, 1D, Double.MAX_VALUE);
		MAGMA_MONSTER_GRUNT_SLOWNESS_EFFECT_DURATION = SERVER_BUILDER.comment("Obsidian Grunt Slowness Effect Duration (Seconds)").defineInRange("magma_monster_grunt_slowness_effect", 5, 1, Integer.MAX_VALUE);
        SERVER_BUILDER.pop();

        SERVER = SERVER_BUILDER.build();
    }

    public static void loadConfig(ModConfigSpec spec, Path path) {
  
        final @Nullable CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        configData.load();
       // spec.acceptConfig(configData);
    }
}

