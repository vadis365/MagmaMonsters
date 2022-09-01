package magma_monsters;

import magma_monsters.client.model.entity.ModelMagmaMonster;
import magma_monsters.client.model.entity.ModelMagmaMonsterGrunt;
import magma_monsters.client.render.LayerMagmaMonster;
import magma_monsters.client.render.LayerMagmaMonsterGrunt;
import magma_monsters.client.render.RenderMagmaMonster;
import magma_monsters.client.render.RenderMagmaMonsterGrunt;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRendering {
    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RenderMagmaMonster.LAYER_LOCATION, ()-> ModelMagmaMonster.createBodyLayer());
        event.registerLayerDefinition(RenderMagmaMonsterGrunt.LAYER_LOCATION, ()-> ModelMagmaMonsterGrunt.createBodyLayer());
        
        event.registerLayerDefinition(LayerMagmaMonster.LAYER_LOCATION, ()-> ModelMagmaMonster.createBodyLayer());
        event.registerLayerDefinition(LayerMagmaMonsterGrunt.LAYER_LOCATION, ()-> ModelMagmaMonsterGrunt.createBodyLayer());
    }

    @SubscribeEvent
    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.MAGMA_MONSTER.get(), RenderMagmaMonster::new);
        event.registerEntityRenderer(ModEntities.MAGMA_MONSTER_GRUNT.get(), RenderMagmaMonsterGrunt::new);
    }
}