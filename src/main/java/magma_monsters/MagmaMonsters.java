package magma_monsters;

import java.nio.file.Path;

import magma_monsters.client.render.RenderMagmaMonster;
import magma_monsters.client.render.RenderMagmaMonsterGrunt;
import magma_monsters.configs.Config;
import magma_monsters.network.QuenchMessage;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(Reference.MOD_ID)
public class MagmaMonsters {
	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel NETWORK_WRAPPER = NetworkRegistry.newSimpleChannel(new ResourceLocation(Reference.MOD_ID, "magma_net"),
		    () -> PROTOCOL_VERSION,
		    PROTOCOL_VERSION::equals,
		    PROTOCOL_VERSION::equals
		);

	public MagmaMonsters () {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
		MinecraftForge.EVENT_BUS.register(this);

		FMLJavaModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		FMLJavaModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
		Path path = FMLPaths.CONFIGDIR.get().resolve("magma_monsters-common.toml");
		Config.loadConfig(Config.COMMON_CONFIG, path);
	}

	public static CreativeModeTab TAB = new CreativeModeTab(Reference.MOD_ID) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack (Items.MAGMA_CREAM);
		}
	};

	private void setup(final FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new ModSpawns());
		ModEntities.registerEntityAttributes();
		NETWORK_WRAPPER.registerMessage(0, QuenchMessage.class, QuenchMessage::encode, QuenchMessage::new, QuenchMessage.Handler::handle);
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		EntityRenderers.register(ModEntities.MAGMA_MONSTER, RenderMagmaMonster::new);
		EntityRenderers.register(ModEntities.MAGMA_MONSTER_GRUNT, RenderMagmaMonsterGrunt::new);
	}
}