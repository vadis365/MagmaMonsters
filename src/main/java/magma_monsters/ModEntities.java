package magma_monsters;
import magma_monsters.entities.EntityMagmaMonster;
import magma_monsters.entities.EntityMagmaMonsterGrunt;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Reference.MOD_ID);
	private static final DeferredRegister<CreativeModeTab> TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

	public static final RegistryObject<EntityType<EntityMagmaMonster>> MAGMA_MONSTER = getEntityTypes().register("magma_monster", () -> EntityType.Builder.of(EntityMagmaMonster::new, MobCategory.MONSTER).fireImmune().sized(0.90F, 1.75F).build(getEntityResource("magma_monster").toString()));
	public static final RegistryObject<EntityType<EntityMagmaMonsterGrunt>> MAGMA_MONSTER_GRUNT = getEntityTypes().register("magma_monster_grunt", () -> EntityType.Builder.of(EntityMagmaMonsterGrunt::new, MobCategory.MONSTER).fireImmune().sized(0.5F, 0.9F).build(getEntityResource("magma_monster_grunt").toString()));
	public static final RegistryObject<Item> MAGMA_MONSTER_SPAWN_EGG = getItems().register("magma_monster_spawn_egg",  () -> new ForgeSpawnEggItem(()-> MAGMA_MONSTER.get(), 0xFF0000, 0x06B900, new Item.Properties()));
	public static final RegistryObject<Item> MAGMA_MONSTER_GRUNT_SPAWN_EGG = getItems().register("magma_monster_grunt_spawn_egg",  () -> new ForgeSpawnEggItem(()-> MAGMA_MONSTER_GRUNT.get(), 0xFF0000, 0x06B900, new Item.Properties()));
	public static final RegistryObject<CreativeModeTab> UPPERS_TAB = TAB.register(Reference.MOD_ID, () -> CreativeModeTab.builder().title(Component.translatable("itemGroup.magma_monsters")).icon(Items.FIRE_CHARGE::getDefaultInstance).displayItems((params, output) -> {
				output.accept(MAGMA_MONSTER_SPAWN_EGG.get());
				output.accept(MAGMA_MONSTER_GRUNT_SPAWN_EGG.get());
			})
			.build());

	@SuppressWarnings("deprecation")
	public static void init() {
		SpawnPlacements.register(MAGMA_MONSTER.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMagmaMonster::canSpawnHere);
		SpawnPlacements.register(MAGMA_MONSTER_GRUNT.get(), SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMagmaMonsterGrunt::canSpawnHere);
	}

	@SubscribeEvent
    public static void entityAttributeCreationEvent(final EntityAttributeCreationEvent event) {
		init();
    	event.put(ModEntities.MAGMA_MONSTER.get(), EntityMagmaMonster.createAttributes().build());
    	event.put(ModEntities.MAGMA_MONSTER_GRUNT.get(), EntityMagmaMonsterGrunt.createAttributes().build());
    }

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation(Reference.MOD_ID, entityName);
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
