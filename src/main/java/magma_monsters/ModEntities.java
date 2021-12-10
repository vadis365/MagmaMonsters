package magma_monsters;

import magma_monsters.entities.EntityMagmaMonster;
import magma_monsters.entities.EntityMagmaMonsterGrunt;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	public static EntityType<EntityMagmaMonster> MAGMA_MONSTER;
	public static EntityType<EntityMagmaMonsterGrunt> MAGMA_MONSTER_GRUNT;

	public static void init() {

		MAGMA_MONSTER = EntityType.Builder.of(EntityMagmaMonster::new, MobCategory.MONSTER).fireImmune().sized(0.90F, 1.75F).build(getEntityResource("magma_monster").toString());
		SpawnPlacements.register(MAGMA_MONSTER, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMagmaMonster::canSpawnHere);
		
		MAGMA_MONSTER_GRUNT = EntityType.Builder.of(EntityMagmaMonsterGrunt::new, MobCategory.MONSTER).fireImmune().sized(0.5F, 0.9F).build(getEntityResource("magma_monster_grunt").toString());
		SpawnPlacements.register(MAGMA_MONSTER_GRUNT, SpawnPlacements.Type.IN_LAVA, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, EntityMagmaMonsterGrunt::canSpawnHere);
	}
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityType<?>> e) {
		final IForgeRegistry<EntityType<?>> registry = e.getRegistry();
		registry.register(MAGMA_MONSTER.setRegistryName(Reference.MOD_ID, "magma_monster"));
		registry.register(MAGMA_MONSTER_GRUNT.setRegistryName(Reference.MOD_ID, "magma_monster_grunt"));
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@SubscribeEvent
	public static void registerSpawnEggs(final RegistryEvent.Register<Item> event) {
		init();
		event.getRegistry().registerAll(
				new ForgeSpawnEggItem(()-> MAGMA_MONSTER, 0xFF0000, 0x06B900, new Item.Properties().tab(MagmaMonsters.TAB)).setRegistryName(Reference.MOD_ID, "magma_monster_spawn_egg"),
				new ForgeSpawnEggItem(()-> MAGMA_MONSTER_GRUNT, 0xFF0000, 0x06B900, new Item.Properties().tab(MagmaMonsters.TAB)).setRegistryName(Reference.MOD_ID, "magma_monster_grunt_spawn_egg"));
	}

    @SubscribeEvent
    public static void entityAttributeCreationEvent(EntityAttributeCreationEvent event) {
    	event.put(MAGMA_MONSTER, EntityMagmaMonster.createAttributes().build());
    	event.put(MAGMA_MONSTER_GRUNT, EntityMagmaMonsterGrunt.createAttributes().build());
    }
    
	public static void registerEntityAttributes() {
	//	GlobalEntityTypeAttributes.put(MAGMA_MONSTER, EntityMagmaMonster.createAttributes());
	//	GlobalEntityTypeAttributes.put(MAGMA_MONSTER_GRUNT, EntityMagmaMonsterGrunt.createAttributes());
	}

	private static ResourceLocation getEntityResource(String entityName) {
		return new ResourceLocation(Reference.MOD_ID, entityName);
	}
}
