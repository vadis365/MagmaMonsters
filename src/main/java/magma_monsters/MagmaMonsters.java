package magma_monsters;

import java.nio.file.Path;

import magma_monsters.client.render.RenderMagmaMonster;
import magma_monsters.client.render.RenderMagmaMonsterGrunt;
import magma_monsters.configs.Config;
import magma_monsters.network.QuenchMessage;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

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

		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);
		Path path = FMLPaths.CONFIGDIR.get().resolve("magma_monsters-common.toml");
		Config.loadConfig(Config.COMMON_CONFIG, path);
	}

	public static ItemGroup TAB = new ItemGroup(Reference.MOD_ID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack (Items.MAGMA_CREAM);
		}
	};

	private void setup(final FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(new ModSpawns());
		ModEntities.registerEntityAttributes();
		//MinecraftForge.EVENT_BUS.register(new ModEvents());
		NETWORK_WRAPPER.registerMessage(0, QuenchMessage.class, QuenchMessage::encode, QuenchMessage::new, QuenchMessage.Handler::handle);
	}

	private void doClientStuff(final FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.MAGMA_MONSTER, RenderMagmaMonster::new);
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.MAGMA_MONSTER_GRUNT, RenderMagmaMonsterGrunt::new);
	}
}