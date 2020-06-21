package magma_monsters;

import magma_monsters.configs.Config;
import magma_monsters.entities.EntityMagmaMonster;
import magma_monsters.entities.EntityMagmaMonsterGrunt;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	public static EntityType<EntityMagmaMonster> MAGMA_MONSTER;
	public static EntityType<EntityMagmaMonsterGrunt> MAGMA_MONSTER_GRUNT;

	public static void init() {

		MAGMA_MONSTER = EntityType.Builder.create(EntityMagmaMonster::new, EntityClassification.MONSTER).size(0.90F, 1.75F).build(getEntityResource("magma_monster").toString());
		EntitySpawnPlacementRegistry.register(MAGMA_MONSTER, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMagmaMonster::canSpawnHere);
		
		MAGMA_MONSTER_GRUNT = EntityType.Builder.create(EntityMagmaMonsterGrunt::new, EntityClassification.MONSTER).size(0.5F, 0.9F).build(getEntityResource("magma_monster_grunt").toString());
		EntitySpawnPlacementRegistry.register(MAGMA_MONSTER_GRUNT, PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityMagmaMonsterGrunt::canSpawnHere);

// egg colour 0xFF0000, 0x06B900;
	}
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> e) {
		final IForgeRegistry<EntityType<?>> registry = e.getRegistry();
		registry.register(MAGMA_MONSTER.setRegistryName(Reference.MOD_ID, "magma_monster"));
		registry.register(MAGMA_MONSTER_GRUNT.setRegistryName(Reference.MOD_ID, "magma_monster_grunt"));
	}

	@SubscribeEvent
	public static void registerSpawnEggs(final RegistryEvent.Register<Item> event) {
		init();
		event.getRegistry().registerAll(
				new SpawnEggItem(MAGMA_MONSTER, 0xFF0000, 0x06B900, new Item.Properties().group(MagmaMonsters.TAB)).setRegistryName(Reference.MOD_ID, "magma_monster_spawn_egg"),
				new SpawnEggItem(MAGMA_MONSTER_GRUNT, 0xFF0000, 0x06B900, new Item.Properties().group(MagmaMonsters.TAB)).setRegistryName(Reference.MOD_ID, "magma_monster_grunt_spawn_egg"));
	}

	public static void registerEntitySpawns() {
		for (Biome allBiomes : ForgeRegistries.BIOMES.getValues()) {
			if (!BiomeDictionary.hasType(allBiomes, Type.NETHER) && !BiomeDictionary.hasType(allBiomes, Type.END)) {
				if(Config.MAGMA_MONSTER_OW_SPAWN.get())
					if(Config.MAGMA_MONSTER_OW_SPAWN_PROBABILITY.get() > 0 && Config.MAGMA_MONSTER_OW_MIN_SPAWN_SIZE.get() > 0 && Config.MAGMA_MONSTER_OW_MAX_SPAWN_SIZE.get() > 0)
						allBiomes.getSpawns(EntityClassification.MONSTER).add(new SpawnListEntry(MAGMA_MONSTER, Config.MAGMA_MONSTER_OW_SPAWN_PROBABILITY.get(), Config.MAGMA_MONSTER_OW_MIN_SPAWN_SIZE.get(), Config.MAGMA_MONSTER_OW_MAX_SPAWN_SIZE.get()));
				if(Config.MAGMA_MONSTER_GRUNT_OW_SPAWN.get())
					if(Config.MAGMA_MONSTER_GRUNT_OW_SPAWN_PROBABILITY.get() > 0 && Config.MAGMA_MONSTER_GRUNT_OW_MIN_SPAWN_SIZE.get() > 0 && Config.MAGMA_MONSTER_GRUNT_OW_MAX_SPAWN_SIZE.get() > 0)
						allBiomes.getSpawns(EntityClassification.MONSTER).add(new SpawnListEntry(MAGMA_MONSTER_GRUNT, Config.MAGMA_MONSTER_GRUNT_OW_SPAWN_PROBABILITY.get(), Config.MAGMA_MONSTER_GRUNT_OW_MIN_SPAWN_SIZE.get(), Config.MAGMA_MONSTER_GRUNT_OW_MAX_SPAWN_SIZE.get()));
			}
			if (BiomeDictionary.hasType(allBiomes, Type.NETHER)) {
				if(Config.MAGMA_MONSTER_HELL_SPAWN.get())
					if(Config.MAGMA_MONSTER_HELL_SPAWN_PROBABILITY.get() > 0 && Config.MAGMA_MONSTER_HELL_MIN_SPAWN_SIZE.get() > 0 && Config.MAGMA_MONSTER_HELL_MAX_SPAWN_SIZE.get() > 0)
						allBiomes.getSpawns(EntityClassification.MONSTER).add(new SpawnListEntry(MAGMA_MONSTER, Config.MAGMA_MONSTER_HELL_SPAWN_PROBABILITY.get(), Config.MAGMA_MONSTER_HELL_MIN_SPAWN_SIZE.get(), Config.MAGMA_MONSTER_HELL_MAX_SPAWN_SIZE.get()));
				if(Config.MAGMA_MONSTER_GRUNT_HELL_SPAWN.get())
					if(Config.MAGMA_MONSTER_GRUNT_HELL_SPAWN_PROBABILITY.get() > 0 && Config.MAGMA_MONSTER_GRUNT_HELL_MIN_SPAWN_SIZE.get() > 0 && Config.MAGMA_MONSTER_GRUNT_HELL_MAX_SPAWN_SIZE.get() > 0)
						allBiomes.getSpawns(EntityClassification.MONSTER).add(new SpawnListEntry(MAGMA_MONSTER_GRUNT, Config.MAGMA_MONSTER_GRUNT_HELL_SPAWN_PROBABILITY.get(), Config.MAGMA_MONSTER_GRUNT_HELL_MIN_SPAWN_SIZE.get(), Config.MAGMA_MONSTER_GRUNT_HELL_MAX_SPAWN_SIZE.get()));
			}
		}
	}

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation("magma_monsters", entityName);
	}
}
