package magma_monsters;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
	private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Reference.MOD_ID);
	public static final RegistryObject<SoundEvent> MAGMA_MONSTER_LIVING = getSounds().register("magma_monster_living", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, "magma_monster_living")));
	public static final RegistryObject<SoundEvent> MAGMA_MONSTER_HURT = getSounds().register("magma_monster_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, "magma_monster_hurt")));
	public static final RegistryObject<SoundEvent> MAGMA_MONSTER_DEATH = getSounds().register("magma_monster_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Reference.MOD_ID, "magma_monster_death")));

	public static DeferredRegister<SoundEvent> getSounds() {
		return SOUNDS;
	}
}
