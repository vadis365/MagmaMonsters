package magma_monsters.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;

import magma_monsters.Reference;
import magma_monsters.client.model.entity.ModelMagmaMonster;
import magma_monsters.entities.EntityMagmaMonster;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderStateShard.CullStateShard;
import net.minecraft.client.renderer.RenderStateShard.LightmapStateShard;
import net.minecraft.client.renderer.RenderStateShard.OverlayStateShard;
import net.minecraft.client.renderer.RenderStateShard.TransparencyStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LayerMagmaMonster extends RenderLayer<EntityMagmaMonster, ModelMagmaMonster<EntityMagmaMonster>> {
    private static final ResourceLocation LIGHTING_TEXTURE = new ResourceLocation("magma_monsters:textures/entity/magma_monster_flow.png");
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Reference.MOD_ID, "magma_monster"), "flow");
    private final ModelMagmaMonster<EntityMagmaMonster> monsterModel;

    public LayerMagmaMonster(RenderLayerParent<EntityMagmaMonster, ModelMagmaMonster<EntityMagmaMonster>> entity, EntityModelSet modelSet) {
    	super(entity);
    	this.monsterModel = new ModelMagmaMonster<>(modelSet.bakeLayer(LAYER_LOCATION));
    }

    @Override
	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, EntityMagmaMonster entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = (float) entity.tickCount + partialTicks;
		monsterModel.eyes.visible = false;
		monsterModel.ltooth.visible = false;
		monsterModel.rtooth.visible = false;
		monsterModel.backLumpMid.visible = false;
		monsterModel.backLumpBot.visible = false;
		monsterModel.backLumpTop.visible = false;
		monsterModel.headTop.visible = false;
		monsterModel.headCrest.visible = false;
		monsterModel.rightTuskStart.visible = false;
		monsterModel.rightTuskEnd.visible = false;
		monsterModel.leftTuskStart.visible = false;
		monsterModel.leftTuskEnd.visible = false;
		monsterModel.prepareMobModel(entity, limbSwing, limbSwingAmount, partialTicks);
		monsterModel.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		monsterModel.renderToBuffer(matrix, buffer.getBuffer(getLavaOverlay(LIGHTING_TEXTURE, 0, -f * 0.004F)), packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, (float)entity.getMoltenTimer() * 0.02F);
	}

	public static RenderType getLavaOverlay(ResourceLocation locationIn, float uIn, float vIn) {
		RenderType.CompositeState renderTypeState = RenderType.CompositeState.builder().setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeEnergySwirlShader)).setTextureState(new RenderStateShard.TextureStateShard(locationIn, false, false)).setTexturingState(new RenderStateShard.OffsetTexturingStateShard(uIn, vIn)).setTransparencyState(new TransparencyStateShard("translucent_transparency", () -> {
		      RenderSystem.enableBlend();
		      RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		   }, () -> {
		      RenderSystem.disableBlend();
		      RenderSystem.defaultBlendFunc();
		   })).setLightmapState(new LightmapStateShard(true)).setCullState(new CullStateShard(false)).setLightmapState(new LightmapStateShard(false)).setOverlayState(new OverlayStateShard(true)).createCompositeState(false);
		
		return RenderType.create("lava_overlay", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, renderTypeState);
	}

}