package magma_monsters.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;

import magma_monsters.client.model.entity.ModelMagmaMonster;
import magma_monsters.entities.EntityMagmaMonster;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMagmaMonster extends LayerRenderer<EntityMagmaMonster, ModelMagmaMonster<EntityMagmaMonster>> {
    private static final ResourceLocation LIGHTING_TEXTURE = new ResourceLocation("magma_monsters:textures/entity/magma_monster_flow.png");
    private final RenderMagmaMonster monsterRenderer;
    private final ModelMagmaMonster monsterModel = new ModelMagmaMonster();

    public LayerMagmaMonster(IEntityRenderer <EntityMagmaMonster, ModelMagmaMonster<EntityMagmaMonster>> entity) {
    	super(entity);
        this.monsterRenderer = monsterRenderer;
    }

	@Override
	public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight, EntityMagmaMonster entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		GlStateManager.pushMatrix();
		GlStateManager.enableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		GlStateManager.depthMask(!entity.isInvisible());
		this.monsterRenderer.bindTexture(LIGHTING_TEXTURE);
		GlStateManager.matrixMode(5890);
		GlStateManager.loadIdentity();
		float f = (float) entity.ticksExisted + partialTicks;
		GlStateManager.translate(0F, -f * 0.002F, 0.0F);
		GlStateManager.matrixMode(5888);
		GlStateManager.color(1F, 1F, 1F, (float)entity.getMoltenTimer() * 0.02F);
		GlStateManager.disableLighting();
		monsterModel.eyes.showModel = false;
		monsterModel.ltooth.showModel = false;
		monsterModel.rtooth.showModel = false;
		monsterModel.backLumpMid.showModel = false;
		monsterModel.backLumpBot.showModel = false;
		monsterModel.backLumpTop.showModel = false;
		monsterModel.headTop.showModel = false;
		monsterModel.headCrest.showModel = false;
		monsterModel.rightTuskStart.showModel = false;
		monsterModel.rightTuskEnd.showModel = false;
		monsterModel.leftTuskStart.showModel = false;
		monsterModel.leftTuskEnd.showModel = false;
		monsterModel.setModelAttributes(this.monsterRenderer.getMainModel());
		monsterModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
		monsterModel.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		monsterModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		GlStateManager.matrixMode(5890);
		GlStateManager.loadIdentity();
		GlStateManager.matrixMode(5888);
		GlStateManager.enableLighting();
		GlStateManager.depthMask(true);
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();
		
	}
}