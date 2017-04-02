package magma_monsters.client.render;

import magma_monsters.client.model.entity.ModelMagmaMonster;
import magma_monsters.entities.EntityMagmaMonster;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMagmaMonster extends RenderLiving<EntityMagmaMonster> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("magma_monsters:textures/entity/magma_monster.png");

	public RenderMagmaMonster(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelMagmaMonster(), 0.75F);
        addLayer(new LayerMagmaMonster(this));
    }

	@Override
	protected void preRenderCallback(EntityMagmaMonster entity, float partialTickTime) {
		GlStateManager.scale(1.25F, 1.25F, 1.25F);
		GlStateManager.translate(0F, 0F, -0.06F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMagmaMonster entity) {
		return TEXTURE;
	}
}
