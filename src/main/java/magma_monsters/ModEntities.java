package magma_monsters;
import java.util.function.Supplier;

import magma_monsters.configs.Config;
import magma_monsters.entities.EntityMagmaMonster;
import magma_monsters.entities.EntityMagmaMonsterGrunt;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeModificationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEntities {
	private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Reference.MOD_ID);
	private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Reference.MOD_ID);
	private static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

	public static final Supplier<EntityType<EntityMagmaMonster>> MAGMA_MONSTER = getEntityTypes().register("magma_monster", () -> EntityType.Builder.of(EntityMagmaMonster::new, MobCategory.MONSTER).fireImmune().sized(0.90F, 1.75F).build(prefix("magma_monster")));
	public static final Supplier<EntityType<EntityMagmaMonsterGrunt>> MAGMA_MONSTER_GRUNT = getEntityTypes().register("magma_monster_grunt", () -> EntityType.Builder.of(EntityMagmaMonsterGrunt::new, MobCategory.MONSTER).fireImmune().sized(0.5F, 0.9F).build(prefix("magma_monster_grunt")));
	public static final Supplier<Item> MAGMA_MONSTER_SPAWN_EGG = getItems().register("magma_monster_spawn_egg",  () -> new DeferredSpawnEggItem(()-> MAGMA_MONSTER.get(), 0xFF0000, 0x06B900, new Item.Properties()));
	public static final Supplier<Item> MAGMA_MONSTER_GRUNT_SPAWN_EGG = getItems().register("magma_monster_grunt_spawn_egg",  () -> new DeferredSpawnEggItem(()-> MAGMA_MONSTER_GRUNT.get(), 0xFF0000, 0x06B900, new Item.Properties()));
	public static final Supplier<CreativeModeTab> MAGMA_MONSTER_TAB = TAB.register(Reference.MOD_ID, () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.magma_monsters")).icon(Items.FIRE_CHARGE::getDefaultInstance).displayItems((params, output) -> {
				output.accept(MAGMA_MONSTER_SPAWN_EGG.get());
				output.accept(MAGMA_MONSTER_GRUNT_SPAWN_EGG.get());
			})
			.build());

	public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
		event.register(MAGMA_MONSTER.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMagmaMonster::canSpawnHere, null);
		event.register(MAGMA_MONSTER_GRUNT.get(), SpawnPlacementTypes.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMagmaMonsterGrunt::canSpawnHere, null);
	}

	public static void initializeAttributes(EntityAttributeCreationEvent event) {
    	event.put(ModEntities.MAGMA_MONSTER.get(), EntityMagmaMonster.createAttributes().build());
    	event.put(ModEntities.MAGMA_MONSTER_GRUNT.get(), EntityMagmaMonsterGrunt.createAttributes().build());
    }
	
	public static void changeAttributes(EntityAttributeModificationEvent event) {
		event.add(ModEntities.MAGMA_MONSTER.get(), Attributes.MAX_HEALTH, Config.MAGMA_MONSTER_HEALTH.get());
		event.add(ModEntities.MAGMA_MONSTER.get(), Attributes.ATTACK_DAMAGE, Config.MAGMA_MONSTER_ATTACK_DAMAGE.get());
		event.add(ModEntities.MAGMA_MONSTER.get(), Attributes.MAX_HEALTH, Config.MAGMA_MONSTER_GRUNT_HEALTH.get());
		event.add(ModEntities.MAGMA_MONSTER.get(), Attributes.ATTACK_DAMAGE, Config.MAGMA_MONSTER_GRUNT_ATTACK_DAMAGE.get());
    }

	private static String prefix(String name) {
		return MagmaMonsters.prefix(name).toString();
	}

	public static DeferredRegister<EntityType<?>> getEntityTypes() {
		return ENTITY_TYPES;
	}

	public static DeferredRegister<Item> getItems() {
		return ITEMS;
	}

	public static DeferredRegister<CreativeModeTab> getTab() {
		return TAB;
	}
}
