package magma_monsters;

import magma_monsters.configs.ConfigHandler;
import magma_monsters.entities.EntityMagmaMonster;
import magma_monsters.entities.EntityMagmaMonsterGrunt;
import magma_monsters.network.QuenchMessage;
import magma_monsters.network.QuenchPacketHandler;
import magma_monsters.proxy.CommonProxy;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
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
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = "magma_monsters", name = "magma_monsters", version = "0.3.0", guiFactory = "magma_monsters.configs.ConfigGuiFactory")

public class MagmaMonsters {

	@Instance("magma_monsters")
	public static MagmaMonsters INSTANCE;

	@SidedProxy(clientSide = "magma_monsters.proxy.ClientProxy", serverSide = "magma_monsters.proxy.CommonProxy")
	public static CommonProxy PROXY;
	public static SimpleNetworkWrapper NETWORK_WRAPPER;
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.INSTANCE.loadConfig(event);
		EntityRegistry.registerModEntity(getEntityResource("magma_monster"), EntityMagmaMonster.class, "magma_monster", 1, this, 120, 1, true, 0xFF0000, 0x06B900);
		EntitySpawnPlacementRegistry.setPlacementType(EntityMagmaMonster.class, SpawnPlacementType.ON_GROUND);
		
		EntityRegistry.registerModEntity(getEntityResource("magma_monster_grunt"), EntityMagmaMonsterGrunt.class, "magma_monster_grunt", 2, this, 120, 1, true, 0xFF0000, 0x06B900);
		EntitySpawnPlacementRegistry.setPlacementType(EntityMagmaMonsterGrunt.class, SpawnPlacementType.ON_GROUND);

		PROXY.registerRenderers();

		for (Biome allBiomes : ForgeRegistries.BIOMES.getValues()) {
			if (!BiomeDictionary.hasType(allBiomes, Type.NETHER) && !BiomeDictionary.hasType(allBiomes, Type.END)) {
				if(ConfigHandler.MAGMA_OW_SPAWN)
					EntityRegistry.addSpawn(EntityMagmaMonster.class, ConfigHandler.MAGMA_OW_SPAWN_PROBABILITY, ConfigHandler.MAGMA_OW_MIN_SPAWN_SIZE, ConfigHandler.MAGMA_OW_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
				if(ConfigHandler.MAGMA_GRUNT_OW_SPAWN)
					EntityRegistry.addSpawn(EntityMagmaMonsterGrunt.class, ConfigHandler.MAGMA_GRUNT_OW_SPAWN_PROBABILITY, ConfigHandler.MAGMA_GRUNT_OW_MIN_SPAWN_SIZE, ConfigHandler.MAGMA_GRUNT_OW_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
			}
			if (BiomeDictionary.hasType(allBiomes, Type.NETHER)) {
				if(ConfigHandler.MAGMA_HELL_SPAWN)
					EntityRegistry.addSpawn(EntityMagmaMonster.class, ConfigHandler.MAGMA_HELL_SPAWN_PROBABILITY, ConfigHandler.MAGMA_HELL_MIN_SPAWN_SIZE, ConfigHandler.MAGMA_HELL_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
				if(ConfigHandler.MAGMA_GRUNT_HELL_SPAWN)
					EntityRegistry.addSpawn(EntityMagmaMonsterGrunt.class, ConfigHandler.MAGMA_GRUNT_HELL_SPAWN_PROBABILITY, ConfigHandler.MAGMA_GRUNT_HELL_MIN_SPAWN_SIZE, ConfigHandler.MAGMA_GRUNT_HELL_MAX_SPAWN_SIZE, EnumCreatureType.MONSTER, allBiomes);
			}
		}
	}

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation("magma_monsters", entityName);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(ConfigHandler.INSTANCE);
		NETWORK_WRAPPER = NetworkRegistry.INSTANCE.newSimpleChannel("magma_monsters");
		NETWORK_WRAPPER.registerMessage(QuenchPacketHandler.class, QuenchMessage.class, 0, Side.CLIENT);
	}
}