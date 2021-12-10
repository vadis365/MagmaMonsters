package magma_monsters.client.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;

import magma_monsters.client.model.entity.ModelMagmaMonster;
import magma_monsters.entities.EntityMagmaMonster;
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
    private final ModelMagmaMonster<EntityMagmaMonster> monsterModel = new ModelMagmaMonster<>();

    public LayerMagmaMonster(RenderLayerParent<EntityMagmaMonster, ModelMagmaMonster<EntityMagmaMonster>> entity) {
    	super(entity);
    }

	@Override
	public void render(PoseStack matrix, MultiBufferSource buffer, int packedLight, EntityMagmaMonster entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		float f = (float) entity.tickCount + partialTicks;
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
		monsterModel.render(matrix, buffer.getBuffer(getLavaOverlay(LIGHTING_TEXTURE, 0, -f * 0.004F)), packedLight, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, (float)entity.getMoltenTimer() * 0.02F);
	}

	public static RenderType getLavaOverlay(ResourceLocation locationIn, float uIn, float vIn) {
		RenderType.CompositeState renderTypeState = RenderType.CompositeState.builder().setTextureState(new RenderStateShard.TextureStateShard(locationIn, false, false)).setTexturingState(new RenderStateShard.OffsetTexturingStateShard(uIn, vIn)).setTransparencyState(new TransparencyStateShard("translucent_transparency", () -> {
		      RenderSystem.enableBlend();
		      RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		   }, () -> {
		      RenderSystem.disableBlend();
		      RenderSystem.defaultBlendFunc();
		   })).setLightmapState(new LightmapStateShard(true)).setCullState(new CullStateShard(false)).setLightmapState(new LightmapStateShard(false)).setOverlayState(new OverlayStateShard(true)).createCompositeState(false);
		
		return RenderType.create("lava_overlay", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, renderTypeState);
	}
}