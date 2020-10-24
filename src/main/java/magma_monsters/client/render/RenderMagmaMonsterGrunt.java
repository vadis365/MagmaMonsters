package magma_monsters.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;

import magma_monsters.client.model.entity.ModelMagmaMonsterGrunt;
import magma_monsters.entities.EntityMagmaMonsterGrunt;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderMagmaMonsterGrunt extends MobRenderer<EntityMagmaMonsterGrunt, ModelMagmaMonsterGrunt<EntityMagmaMonsterGrunt>> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("magma_monsters:textures/entity/magma_monster_cobble.png");

	public RenderMagmaMonsterGrunt(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ModelMagmaMonsterGrunt<>(), 0.4F);
        addLayer(new LayerMagmaMonsterGrunt(this));
    }

	@Override
	protected void preRenderCallback(EntityMagmaMonsterGrunt entity, MatrixStack matrix, float partialTickTime) {
		matrix.scale(0.7F, 0.7F, 0.7F);
		matrix.translate(0F, 0F, -0.06F);
	}

	@Override
	public ResourceLocation getEntityTexture(EntityMagmaMonsterGrunt entity) {
		return TEXTURE;
	}
}
