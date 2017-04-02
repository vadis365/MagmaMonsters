package magma_monsters;

import magma_monsters.configs.ConfigHandler;
import magma_monsters.entities.EntityMagmaMonster;
import magma_monsters.entities.EntityMagmaMonsterGrunt;
import magma_monsters.network.QuenchMessage;
import magma_monsters.network.QuenchPacketHandler;
import magma_monsters.proxy.CommonProxy;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = "magma_monsters", name = "magma_monsters", version = "0.1.14", guiFactory = "magma_monsters.configs.ConfigGuiFactory")

public class MagmaMonsters {

	static int startEntityId = 1;

	@Instance("magma_monsters")
	public static MagmaMonsters INSTANCE;

	@SidedProxy(clientSide = "magma_monsters.proxy.ClientProxy", serverSide = "magma_monsters.proxy.CommonProxy")
	public static CommonProxy PROXY;
	public static SoundEvent MAGMA_MONSTER_LIVING, MAGMA_MONSTER_HURT, MAGMA_MONSTER_DEATH;
	public static SimpleNetworkWrapper NETWORK_WRAPPER;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.INSTANCE.loadConfig(event);
		EntityRegistry.registerModEntity(EntityMagmaMonster.class, "MagmaMonster", 1, this, 120, 1, true);
		EntitySpawnPlacementRegistry.setPlacementType(EntityMagmaMonster.class, SpawnPlacementType.IN_WATER);
		registerEntityEgg(EntityMagmaMonster.class, 0xFF0000, 0x06B900);
		
		EntityRegistry.registerModEntity(EntityMagmaMonsterGrunt.class, "MagmaMonsterGrunt", 2, this, 120, 1, true);
		EntitySpawnPlacementRegistry.setPlacementType(EntityMagmaMonsterGrunt.class, SpawnPlacementType.IN_WATER);
		registerEntityEgg(EntityMagmaMonsterGrunt.class, 0xFF0000, 0x06B900);

		PROXY.registerRenderers();

		for (Biome allBiomes : ForgeRegistries.BIOMES.getValues()) {
			if (!BiomeDictionary.isBiomeOfType(allBiomes, Type.NETHER) && !BiomeDictionary.isBiomeOfType(allBiomes, Type.END)) {
				if(ConfigHandler.MAGMA_OW_SPAWN)
					EntityRegistry.addSpawn(EntityMagmaMonster.class, ConfigHandler.MAGMA_OW_SPAWN_PROBABILITY, ConfigHandler.MAGMA_OW_MIN_SPAWN_SIZE, ConfigHandler.MAGMA_OW_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
				if(ConfigHandler.MAGMA_GRUNT_OW_SPAWN)
					EntityRegistry.addSpawn(EntityMagmaMonsterGrunt.class, ConfigHandler.MAGMA_GRUNT_OW_SPAWN_PROBABILITY, ConfigHandler.MAGMA_GRUNT_OW_MIN_SPAWN_SIZE, ConfigHandler.MAGMA_GRUNT_OW_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
			}
			if (BiomeDictionary.isBiomeOfType(allBiomes, Type.NETHER)) {
				if(ConfigHandler.MAGMA_HELL_SPAWN)
					EntityRegistry.addSpawn(EntityMagmaMonster.class, ConfigHandler.MAGMA_HELL_SPAWN_PROBABILITY, ConfigHandler.MAGMA_HELL_MIN_SPAWN_SIZE, ConfigHandler.MAGMA_HELL_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
				if(ConfigHandler.MAGMA_GRUNT_HELL_SPAWN)
					EntityRegistry.addSpawn(EntityMagmaMonsterGrunt.class, ConfigHandler.MAGMA_GRUNT_HELL_SPAWN_PROBABILITY, ConfigHandler.MAGMA_GRUNT_HELL_MIN_SPAWN_SIZE, ConfigHandler.MAGMA_GRUNT_HELL_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
			}
		}
	}

	public static int getUniqueEntityId() {
		do
			startEntityId++;
		while (EntityList.getClassFromID(startEntityId) != null);
		return startEntityId;
	}

	public static void registerEntityEgg(Class<? extends Entity> entity, int primaryColor, int secondaryColor) {
		int id = getUniqueEntityId();
		EntityList.addMapping(entity, entity.getName(), id);
		EntityList.ENTITY_EGGS.put(entity.getName(), new EntityList.EntityEggInfo(entity.getName(), primaryColor, secondaryColor));
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MAGMA_MONSTER_LIVING = new SoundEvent(new ResourceLocation("magma_monsters", "magma_monster_living")).setRegistryName("magma_monsters", "magma_monster_living");
		GameRegistry.register(MAGMA_MONSTER_LIVING);
		MAGMA_MONSTER_HURT = new SoundEvent(new ResourceLocation("magma_monsters", "magma_monster_hurt")).setRegistryName("magma_monsters", "magma_monster_hurt");
		GameRegistry.register(MAGMA_MONSTER_HURT);
		MAGMA_MONSTER_DEATH = new SoundEvent(new ResourceLocation("magma_monsters", "magma_monster_death")).setRegistryName("magma_monsters", "magma_monster_death");
		GameRegistry.register(MAGMA_MONSTER_DEATH);
		MinecraftForge.EVENT_BUS.register(ConfigHandler.INSTANCE);
		NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel("magma_monsters");
		NETWORK_WRAPPER.registerMessage(QuenchPacketHandler.class, QuenchMessage.class, 0, Side.CLIENT);
	}
}