package magma_monsters;

import magma_monsters.client.model.entity.ModelMagmaMonster;
import magma_monsters.client.model.entity.ModelMagmaMonsterGrunt;
import magma_monsters.client.render.LayerMagmaMonster;
import magma_monsters.client.render.LayerMagmaMonsterGrunt;
import magma_monsters.client.render.RenderMagmaMonster;
import magma_monsters.client.render.RenderMagmaMonsterGrunt;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModRendering {

    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RenderMagmaMonster.LAYER_LOCATION, ()-> ModelMagmaMonster.createBodyLayer());
        event.registerLayerDefinition(RenderMagmaMonsterGrunt.LAYER_LOCATION, ()-> ModelMagmaMonsterGrunt.createBodyLayer());
        
        event.registerLayerDefinition(LayerMagmaMonster.LAYER_LOCATION, ()-> ModelMagmaMonster.createBodyLayer());
        event.registerLayerDefinition(LayerMagmaMonsterGrunt.LAYER_LOCATION, ()-> ModelMagmaMonsterGrunt.createBodyLayer());
    }

    public static void registerEntityRender(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.MAGMA_MONSTER.get(), RenderMagmaMonster::new);
        event.registerEntityRenderer(ModEntities.MAGMA_MONSTER_GRUNT.get(), RenderMagmaMonsterGrunt::new);
    }
}