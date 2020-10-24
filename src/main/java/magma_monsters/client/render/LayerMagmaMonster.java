package magma_monsters.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import magma_monsters.client.model.entity.ModelMagmaMonster;
import magma_monsters.entities.EntityMagmaMonster;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderState.TransparencyState;
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
    private final ModelMagmaMonster<EntityMagmaMonster> monsterModel = new ModelMagmaMonster<>();

    public LayerMagmaMonster(IEntityRenderer<EntityMagmaMonster, ModelMagmaMonster<EntityMagmaMonster>> entity) {
    	super(entity);
    }

	@Override
	public void render(MatrixStack matrix, IRenderTypeBuffer buffer, int packedLight, EntityMagmaMonster entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = (float) entity.ticksExisted + partialTicks;
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
		monsterModel.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
		monsterModel.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		monsterModel.render(matrix, buffer.getBuffer(getLavaOverlay(LIGHTING_TEXTURE, 0, -f * 0.002F)), packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, (float)entity.getMoltenTimer() * 0.02F);
	}

	public static RenderType getLavaOverlay(ResourceLocation locationIn, float uIn, float vIn) {
		RenderType.State renderTypeState = RenderType.State.getBuilder().texture(new RenderState.TextureState(locationIn, false, false)).texturing(new RenderState.OffsetTexturingState(uIn, vIn)).transparency(new TransparencyState("translucent_transparency", () -> {
		      RenderSystem.enableBlend();
		      RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		   }, () -> {
		      RenderSystem.disableBlend();
		      RenderSystem.defaultBlendFunc();
		   })).diffuseLighting(new RenderState.DiffuseLightingState(true)).cull(new RenderState.CullState(false)).lightmap(new RenderState.LightmapState(false)).overlay(new RenderState.OverlayState(true)).build(true);
		
		return RenderType.makeType("lava_overlay", DefaultVertexFormats.ENTITY, 7, 256, false, true, renderTypeState);
	}
}