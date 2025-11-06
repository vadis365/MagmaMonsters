package magma_monsters.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import magma_monsters.MagmaMonsters;
import magma_monsters.client.model.entity.ModelMagmaMonsterGrunt;
import magma_monsters.entities.EntityMagmaMonsterGrunt;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMagmaMonsterGrunt extends MobRenderer<EntityMagmaMonsterGrunt, ModelMagmaMonsterGrunt<EntityMagmaMonsterGrunt>> {
	public static final ResourceLocation TEXTURE = MagmaMonsters.prefix("textures/entity/magma_monster_cobble.png");
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(MagmaMonsters.prefix("modelmagmamonstergrunt"), "main");

	public RenderMagmaMonsterGrunt(EntityRendererProvider.Context renderContext) {
        super(renderContext, new ModelMagmaMonsterGrunt<>(renderContext.bakeLayer(LAYER_LOCATION)), 0.4F);
        addLayer(new LayerMagmaMonsterGrunt(this, renderContext.getModelSet()));
    }

	@Override
	protected void scale(EntityMagmaMonsterGrunt entity, PoseStack matrix, float partialTickTime) {
		matrix.scale(0.7F, 0.7F, 0.7F);
		matrix.translate(0F, 0F, -0.06F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityMagmaMonsterGrunt entity) {
		return TEXTURE;
	}
}
