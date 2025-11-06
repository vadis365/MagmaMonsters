package magma_monsters;

import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
	private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(Registries.SOUND_EVENT, Reference.MOD_ID);
	public static final DeferredHolder<SoundEvent, SoundEvent> MAGMA_MONSTER_LIVING = getSounds().register("magma_monster_living", () -> SoundEvent.createVariableRangeEvent(MagmaMonsters.prefix("magma_monster_living")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MAGMA_MONSTER_HURT = getSounds().register("magma_monster_hurt", () -> SoundEvent.createVariableRangeEvent(MagmaMonsters.prefix("magma_monster_hurt")));
	public static final DeferredHolder<SoundEvent, SoundEvent> MAGMA_MONSTER_DEATH = getSounds().register("magma_monster_death", () -> SoundEvent.createVariableRangeEvent(MagmaMonsters.prefix("magma_monster_death")));

	public static DeferredRegister<SoundEvent> getSounds() {
		return SOUNDS;
	}
}
