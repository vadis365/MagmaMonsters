package magma_monsters;

import java.nio.file.Path;
import java.util.Locale;

import magma_monsters.configs.Config;
import magma_monsters.network.MagmaMonstersNetwork;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.fml.loading.FMLPaths;

@Mod(Reference.MOD_ID)
public class MagmaMonsters {

	public MagmaMonsters (IEventBus modBus) {
		ModLoadingContext.get().getActiveContainer().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
		Path path = FMLPaths.CONFIGDIR.get().resolve("magma_monsters-common.toml");
		Config.loadConfig(Config.COMMON_CONFIG, path);

		ModEntities.getEntityTypes().register(modBus);
		ModEntities.getItems().register(modBus);
		ModEntities.getTab().register(modBus);
		ModSounds.getSounds().register(modBus);
		modBus.addListener(ModEntities::registerSpawnPlacements);
		modBus.addListener(ModEntities::initializeAttributes);
		//modBus.addListener(ModEntities::changeAttributes);
		modBus.addListener(MagmaMonstersNetwork::register);
		if (FMLEnvironment.dist.isClient()) {
			modBus.addListener(ModRendering::registerEntityLayers);
			modBus.addListener(ModRendering::registerEntityRender);
		}
	}
	
	public static ResourceLocation prefix(String name) {
		return ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, name.toLowerCase(Locale.ROOT));
	}
}