package magma_monsters;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSounds {
	public static SoundEvent MAGMA_MONSTER_LIVING, MAGMA_MONSTER_HURT, MAGMA_MONSTER_DEATH;

	public static void init() {
		MAGMA_MONSTER_LIVING = new SoundEvent(new ResourceLocation("magma_monsters", "magma_monster_living")).setRegistryName("magma_monsters", "magma_monster_living");
		MAGMA_MONSTER_HURT = new SoundEvent(new ResourceLocation("magma_monsters", "magma_monster_hurt")).setRegistryName("magma_monsters", "magma_monster_hurt");
		MAGMA_MONSTER_DEATH = new SoundEvent(new ResourceLocation("magma_monsters", "magma_monster_death")).setRegistryName("magma_monsters", "magma_monster_death");
	}

	@SubscribeEvent
	public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
		init();
		event.getRegistry().registerAll(MAGMA_MONSTER_LIVING, MAGMA_MONSTER_HURT, MAGMA_MONSTER_DEATH);
	}
}
