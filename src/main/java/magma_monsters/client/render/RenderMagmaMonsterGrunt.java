package magma_monsters.client.render;

import magma_monsters.client.model.entity.ModelMagmaMonsterGrunt;
import magma_monsters.entities.EntityMagmaMonsterGrunt;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderMagmaMonsterGrunt extends RenderLiving<EntityMagmaMonsterGrunt> {
	public static final ResourceLocation TEXTURE = new ResourceLocation("magma_monsters:textures/entity/magma_monster_cobble.png");

	public RenderMagmaMonsterGrunt(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelMagmaMonsterGrunt(), 0.4F);
        addLayer(new LayerMagmaMonsterGrunt(this));
    }

	@Override
	protected void preRenderCallback(EntityMagmaMonsterGrunt entity, float partialTickTime) {
		GlStateManager.scale(0.7F, 0.7F, 0.7F);
		GlStateManager.translate(0F, 0F, -0.06F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMagmaMonsterGrunt entity) {
		return TEXTURE;
	}
}
