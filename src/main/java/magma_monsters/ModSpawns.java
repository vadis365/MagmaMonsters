package magma_monsters;


import magma_monsters.configs.Config;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModSpawns {
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onBiomeLoading(BiomeLoadingEvent event) {
		if (event.getCategory() == Biome.BiomeCategory.NETHER) {
			if(Config.MAGMA_MONSTER_HELL_SPAWN.get())
				if(Config.MAGMA_MONSTER_HELL_SPAWN_PROBABILITY.get() > 0 && Config.MAGMA_MONSTER_HELL_MIN_SPAWN_SIZE.get() > 0 && Config.MAGMA_MONSTER_HELL_MAX_SPAWN_SIZE.get() > 0)
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntities.MAGMA_MONSTER, Config.MAGMA_MONSTER_HELL_SPAWN_PROBABILITY.get(), Config.MAGMA_MONSTER_HELL_MIN_SPAWN_SIZE.get(), Config.MAGMA_MONSTER_HELL_MAX_SPAWN_SIZE.get()));
			if(Config.MAGMA_MONSTER_GRUNT_HELL_SPAWN.get())
				if(Config.MAGMA_MONSTER_GRUNT_HELL_SPAWN_PROBABILITY.get() > 0 && Config.MAGMA_MONSTER_GRUNT_HELL_MIN_SPAWN_SIZE.get() > 0 && Config.MAGMA_MONSTER_GRUNT_HELL_MAX_SPAWN_SIZE.get() > 0)
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntities.MAGMA_MONSTER_GRUNT, Config.MAGMA_MONSTER_GRUNT_HELL_SPAWN_PROBABILITY.get(), Config.MAGMA_MONSTER_GRUNT_HELL_MIN_SPAWN_SIZE.get(), Config.MAGMA_MONSTER_GRUNT_HELL_MAX_SPAWN_SIZE.get()));
		}
		
		if (event.getCategory() != Biome.BiomeCategory.NETHER && event.getCategory() != Biome.BiomeCategory.THEEND) {
			if(Config.MAGMA_MONSTER_OW_SPAWN.get())
				if(Config.MAGMA_MONSTER_OW_SPAWN_PROBABILITY.get() > 0 && Config.MAGMA_MONSTER_OW_MIN_SPAWN_SIZE.get() > 0 && Config.MAGMA_MONSTER_OW_MAX_SPAWN_SIZE.get() > 0)
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntities.MAGMA_MONSTER, Config.MAGMA_MONSTER_OW_SPAWN_PROBABILITY.get(), Config.MAGMA_MONSTER_OW_MIN_SPAWN_SIZE.get(), Config.MAGMA_MONSTER_OW_MAX_SPAWN_SIZE.get()));
			if(Config.MAGMA_MONSTER_GRUNT_OW_SPAWN.get())
				if(Config.MAGMA_MONSTER_GRUNT_OW_SPAWN_PROBABILITY.get() > 0 && Config.MAGMA_MONSTER_GRUNT_OW_MIN_SPAWN_SIZE.get() > 0 && Config.MAGMA_MONSTER_GRUNT_OW_MAX_SPAWN_SIZE.get() > 0)
					event.getSpawns().getSpawner(MobCategory.MONSTER).add(new SpawnerData(ModEntities.MAGMA_MONSTER_GRUNT, Config.MAGMA_MONSTER_GRUNT_OW_SPAWN_PROBABILITY.get(), Config.MAGMA_MONSTER_GRUNT_OW_MIN_SPAWN_SIZE.get(), Config.MAGMA_MONSTER_GRUNT_OW_MAX_SPAWN_SIZE.get()));
		}
	}
}
