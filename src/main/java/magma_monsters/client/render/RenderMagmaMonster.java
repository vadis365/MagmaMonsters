package magma_monsters.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import magma_monsters.client.model.entity.ModelMagmaMonster;
import magma_monsters.entities.EntityMagmaMonster;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMagmaMonster extends MobRenderer<EntityMagmaMonster, ModelMagmaMonster<EntityMagmaMonster>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("magma_monsters:textures/entity/magma_monster.png");
	public static final ResourceLocation LIGHTING_TEXTURE = new ResourceLocation("magma_monsters:textures/entity/magma_monster_flow.png"); // temp

	public RenderMagmaMonster(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelMagmaMonster<>(), 0.75F);
       // addLayer(new LayerMagmaMonster(this));
    }

	@Override
	protected void preRenderCallback(EntityMagmaMonster entity, MatrixStack matrix, float partialTickTime) {
		matrix.scale(1.25F, 1.25F, 1.25F);
		matrix.translate(0F, 0F, -0.06F);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityMagmaMonster entity) {
		return entity.getMolten() ? LIGHTING_TEXTURE : TEXTURE;
	}
}
