package magma_monsters.client.render;

import com.mojang.blaze3d.vertex.PoseStack;

import magma_monsters.client.model.entity.ModelMagmaMonster;
import magma_monsters.entities.EntityMagmaMonster;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMagmaMonster extends MobRenderer<EntityMagmaMonster, ModelMagmaMonster<EntityMagmaMonster>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("magma_monsters:textures/entity/magma_monster.png");

	public RenderMagmaMonster(EntityRendererProvider.Context renderContext) {
        super(renderContext, new ModelMagmaMonster<>(), 0.75F);
        //addLayer(new LayerMagmaMonster(this, renderContext.getModelSet()));
    }

	@Override
	protected void scale(EntityMagmaMonster entity, PoseStack matrix, float partialTickTime) {
		matrix.scale(1.25F, 1.25F, 1.25F);
		matrix.translate(0F, 0F, -0.06F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityMagmaMonster entity) {
		return TEXTURE;
	}
}
