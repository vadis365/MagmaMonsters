package magma_monsters;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModSounds {
	public static SoundEvent MAGMA_MONSTER_LIVING, MAGMA_MONSTER_HURT, MAGMA_MONSTER_DEATH;

	public static void init() {
		MAGMA_MONSTER_LIVING = new SoundEvent(new ResourceLocation("magma_monsters", "magma_monster_living")).setRegistryName("magma_monsters", "magma_monster_living");
		MAGMA_MONSTER_HURT = new SoundEvent(new ResourceLocation("magma_monsters", "magma_monster_hurt")).setRegistryName("magma_monsters", "magma_monster_hurt");
		MAGMA_MONSTER_DEATH = new SoundEvent(new ResourceLocation("magma_monsters", "magma_monster_death")).setRegistryName("magma_monsters", "magma_monster_death");
	}

	@Mod.EventBusSubscriber(modid = "magma_monsters")
	public static class RegistrationHandlerSounds {
		@SubscribeEvent
		public static void registerSoundEvents(final RegistryEvent.Register<SoundEvent> event) {
			event.getRegistry().registerAll(
					MAGMA_MONSTER_LIVING,
					MAGMA_MONSTER_HURT,
					MAGMA_MONSTER_DEATH
					);
		}
	}
}
