package magma_monsters.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;

import magma_monsters.client.model.entity.ModelMagmaMonster;
import magma_monsters.entities.EntityMagmaMonster;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMagmaMonster extends LayerRenderer<EntityMagmaMonster, ModelMagmaMonster<EntityMagmaMonster>> {
    private static final ResourceLocation LIGHTING_TEXTURE = new ResourceLocation("magma_monsters:textures/entity/magma_monster_flow.png");
    private final RenderMagmaMonster monsterRenderer;
    private final ModelMagmaMonster<EntityMagmaMonster> monsterModel = new ModelMagmaMonster<>();

    public LayerMagmaMonster(IEntityRenderer <EntityMagmaMonster, ModelMagmaMonster<EntityMagmaMonster>> entity) {
    	super(entity);
        this.monsterRenderer = (RenderMagmaMonster) entity;
    }

	@Override
	public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight, EntityMagmaMonster entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		GlStateManager.pushMatrix();
		//GlStateManager.enableBlend();
		//GlStateManager.enableAlphaTest();
		//GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA.param, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA.param);
		//GlStateManager.depthMask(!entity.isInvisible());
		//this.monsterRenderer.bindTexture(LIGHTING_TEXTURE);
		//GlStateManager.matrixMode(5890);
		//GlStateManager.loadIdentity();
		float f = (float) entity.ticksExisted + partialTicks;
		//GlStateManager.translatef(0F, -f * 0.002F, 0.0F);
	//	GlStateManager.matrixMode(5888);
	//	GlStateManager.blendColor(1F, 1F, 1F, (float)entity.getMoltenTimer() * 0.02F);
	//	GlStateManager.disableLighting();
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
		//monsterModel.copyModelAttributesTo(this.monsterRenderer.getEntityModel());
		monsterModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
		monsterModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		monsterModel.render(matrix, buffer.getBuffer(getEnergySwirl(LIGHTING_TEXTURE, 0, -f * 0.002F, 1F - (float)entity.getMoltenTimer() * 0.02F)/*RenderType.getEntitySolid(LIGHTING_TEXTURE)*/), packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
		//monsterModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		//GlStateManager.matrixMode(5890);
		//GlStateManager.loadIdentity();
		//GlStateManager.matrixMode(5888);
		//GlStateManager.enableLighting();
		//GlStateManager.depthMask(true);
		//GlStateManager.disableBlend();
		GlStateManager.popMatrix();
	}

	public static RenderType getEnergySwirl(ResourceLocation locationIn, float uIn, float vIn, float alpha) {
		return RenderType.makeType("energy_swirl", DefaultVertexFormats.ENTITY, 7, 256, false, true, RenderType.State.getBuilder()
		    		  .texture(new RenderState.TextureState(locationIn, false, false))
		    		  .texturing(new RenderState.OffsetTexturingState(uIn, vIn))
		    		  .alpha(new RenderState.AlphaState(alpha)).build(true));
	}
}