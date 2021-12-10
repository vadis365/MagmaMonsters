package magma_monsters.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import magma_monsters.entities.EntityMagmaMonsterGrunt;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelMagmaMonsterGrunt<T extends EntityMagmaMonsterGrunt> extends HierarchicalModel<T> {

	private final ModelPart root;

	public ModelMagmaMonsterGrunt(ModelPart root) {
		this.root = root;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torsoBottom = root.addOrReplaceChild("torsoBottom", CubeListBuilder.create().texOffs(19, 35).addBox(-4.0F, -7.0F, -2.2F, 8.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, 7.0F, 0.6981F, 0.0F, 0.0F));

		PartDefinition leftLegThigh = torsoBottom.addOrReplaceChild("leftLegThigh", CubeListBuilder.create().texOffs(0, 43).mirror().addBox(-2.5F, -1.0F, -6.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

		PartDefinition leftLegShin = leftLegThigh.addOrReplaceChild("leftLegShin", CubeListBuilder.create().texOffs(0, 53).mirror().addBox(-2.0F, -0.6F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.7F, -4.8F, -0.6807F, 0.0F, 0.0F));

		PartDefinition leftLegLavaOuter = leftLegShin.addOrReplaceChild("leftLegLavaOuter", CubeListBuilder.create().texOffs(20, 55).mirror().addBox(-1.9F, 0.5F, -1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition leftLegLavaInner = leftLegShin.addOrReplaceChild("leftLegLavaInner", CubeListBuilder.create().texOffs(20, 55).mirror().addBox(-1.1F, 0.5F, -1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition leftLegLavaFront = leftLegShin.addOrReplaceChild("leftLegLavaFront", CubeListBuilder.create().texOffs(13, 54).mirror().addBox(-1.0F, 0.5F, -1.9F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition leftLegLavaBack = leftLegShin.addOrReplaceChild("leftLegLavaBack", CubeListBuilder.create().texOffs(13, 54).addBox(-1.0F, 0.5F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition rightLegThigh = torsoBottom.addOrReplaceChild("rightLegThigh", CubeListBuilder.create().texOffs(42, 43).addBox(-1.5F, -1.0F, -6.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.2618F));

		PartDefinition rightLegShin = rightLegThigh.addOrReplaceChild("rightLegShin", CubeListBuilder.create().texOffs(52, 53).addBox(-1.0F, -0.6F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.7F, -4.8F, -0.6807F, 0.0F, 0.0F));

		PartDefinition rightLegLavaBack = rightLegShin.addOrReplaceChild("rightLegLavaBack", CubeListBuilder.create().texOffs(13, 54).addBox(0.0F, 0.5F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		PartDefinition rightLegLavaInner = rightLegShin.addOrReplaceChild("rightLegLavaInner", CubeListBuilder.create().texOffs(20, 55).addBox(-0.9F, 0.5F, -1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition rightLegLavaOuter = rightLegShin.addOrReplaceChild("rightLegLavaOuter", CubeListBuilder.create().texOffs(20, 55).addBox(-0.1F, 0.5F, -1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition rightLegLavaFront = rightLegShin.addOrReplaceChild("rightLegLavaFront", CubeListBuilder.create().texOffs(13, 54).addBox(0.0F, 0.5F, -1.9F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		PartDefinition torsoTop = torsoBottom.addOrReplaceChild("torsoTop", CubeListBuilder.create().texOffs(12, 22).addBox(-7.0F, -5.9F, -5.6F, 14.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 2.5F, 0.5236F, 0.0F, 0.0F));

		PartDefinition backLumpTop = torsoTop.addOrReplaceChild("backLumpTop", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -4.8F, -0.1F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition leftArmTop = torsoTop.addOrReplaceChild("leftArmTop", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -1.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -3.5F, -3.5F, -1.1345F, 0.6981F, 0.1745F));

		PartDefinition leftArmBottom = leftArmTop.addOrReplaceChild("leftArmBottom", CubeListBuilder.create().texOffs(0, 6).addBox(-1.1F, 0.0F, -1.2F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.0F, 0.0F, -0.9599F, 0.0F, -0.6981F));

		PartDefinition leftArmLavaOutFront = leftArmBottom.addOrReplaceChild("leftArmLavaOutFront", CubeListBuilder.create().texOffs(43, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 6.5F, 0.5F, 0.9599F, 0.0F, 0.0F));

		PartDefinition leftArmLavaInner = leftArmBottom.addOrReplaceChild("leftArmLavaInner", CubeListBuilder.create().texOffs(27, 56).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, 6.5F, 0.5F, 0.9599F, 0.0F, 0.0F));

		PartDefinition leftArmLavaOutBack = leftArmBottom.addOrReplaceChild("leftArmLavaOutBack", CubeListBuilder.create().texOffs(36, 56).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 3.5F, 0.5F, 0.9564F, 0.0F, 0.0F));

		PartDefinition rightArmTop = torsoTop.addOrReplaceChild("rightArmTop", CubeListBuilder.create().texOffs(52, 18).addBox(0.0F, -1.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -3.5F, -3.5F, -1.1345F, -0.6981F, -0.1745F));

		PartDefinition rightArmBottom = rightArmTop.addOrReplaceChild("rightArmBottom", CubeListBuilder.create().texOffs(52, 6).addBox(-1.9F, 0.0F, -1.2F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 5.0F, 0.0F, -0.9599F, 0.0F, 0.6981F));

		PartDefinition rightArmLavaInner = rightArmBottom.addOrReplaceChild("rightArmLavaInner", CubeListBuilder.create().texOffs(27, 56).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9F, 6.5F, 0.5F, 0.9599F, 0.0F, 0.0F));

		PartDefinition rightArmLavaOutFront = rightArmBottom.addOrReplaceChild("rightArmLavaOutFront", CubeListBuilder.create().texOffs(43, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 6.5F, 0.5F, 0.9599F, 0.0F, 0.0F));

		PartDefinition rightArmLavaOutBack = rightArmBottom.addOrReplaceChild("rightArmLavaOutBack", CubeListBuilder.create().texOffs(36, 56).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 3.5F, 0.5F, 0.9599F, 0.0F, 0.0F));

		PartDefinition backLumpMid = torsoBottom.addOrReplaceChild("backLumpMid", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, -5.0F, 2.7F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition backLumpBot = torsoBottom.addOrReplaceChild("backLumpBot", CubeListBuilder.create().texOffs(9, 35).addBox(-1.0F, -1.0F, 1.3F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition crotch = torsoBottom.addOrReplaceChild("crotch", CubeListBuilder.create().texOffs(24, 50).addBox(-2.5F, 0.3F, -2.6F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		PartDefinition headMain = torsoBottom.addOrReplaceChild("headMain", CubeListBuilder.create().texOffs(20, 10).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.3F, -1.0F, -0.5236F, 0.0F, 0.0F));

		PartDefinition headTop = headMain.addOrReplaceChild("headTop", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.3F, -5.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2967F, 0.0F, 0.0F));

		PartDefinition headCrest = headMain.addOrReplaceChild("headCrest", CubeListBuilder.create().texOffs(12, 1).addBox(-0.5F, -3.6F, -5.8F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2967F, 0.0F, 0.0F));

		PartDefinition faceBrow = headMain.addOrReplaceChild("faceBrow", CubeListBuilder.create().texOffs(25, 0).addBox(-3.0F, -2.0F, -7.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition faceBottom = headMain.addOrReplaceChild("faceBottom", CubeListBuilder.create().texOffs(41, 6).addBox(-2.0F, 0.5F, -7.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition faceLeft = headMain.addOrReplaceChild("faceLeft", CubeListBuilder.create().texOffs(24, 3).addBox(-3.0F, -1.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition faceRight = headMain.addOrReplaceChild("faceRight", CubeListBuilder.create().texOffs(36, 3).addBox(2.0F, -1.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition nose = headMain.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(29, 3).addBox(-0.5F, 0.7F, -6.9F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition rightTuskStart = headMain.addOrReplaceChild("rightTuskStart", CubeListBuilder.create().texOffs(48, 34).addBox(-1.0F, -0.5F, -8.5F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2269F, -0.3491F, 0.1745F));

		PartDefinition rightTuskEnd = headMain.addOrReplaceChild("rightTuskEnd", CubeListBuilder.create().texOffs(56, 29).addBox(-0.5F, 4.2F, -9.8F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.288F, -0.3491F, 0.1745F));

		PartDefinition leftTuskStart = headMain.addOrReplaceChild("leftTuskStart", CubeListBuilder.create().texOffs(48, 34).addBox(-1.0F, -0.5F, -8.5F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2269F, 0.3491F, -0.1745F));

		PartDefinition leftTuskEnd = headMain.addOrReplaceChild("leftTuskEnd", CubeListBuilder.create().texOffs(56, 29).addBox(-0.5F, 4.2F, -9.8F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.288F, 0.3491F, -0.1745F));

		PartDefinition jaw = headMain.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(44, 0).addBox(-3.0F, -0.5F, -3.4F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -2.6F, 0.5236F, 0.0F, 0.0F));

		PartDefinition ltooth = jaw.addOrReplaceChild("ltooth", CubeListBuilder.create().texOffs(14, 16).addBox(-3.0F, -1.5F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition rtooth = jaw.addOrReplaceChild("rtooth", CubeListBuilder.create().texOffs(14, 19).addBox(2.0F, -1.5F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eyes = headMain.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(13, 12).addBox(-2.0F, -1.25F, -6.1F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStackIn, VertexConsumer bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
      root().render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
	}

	@Override
	 public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		EntityMagmaMonsterGrunt magma_monster = (EntityMagmaMonsterGrunt) entity;
		float flap = (float) (Math.sin((magma_monster.tickCount) * 0.3F) * 0.8F);
		root().getChild("headMain").yRot = (float) Math.sin((netHeadYaw / (180F / (float) Math.PI)) * 0.5F);
		root().getChild("headMain").xRot = (float) (-0.5235987755982988F + Math.sin((headPitch / (180F / (float) Math.PI)) * 0.5F) + flap * 0.025F);
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		EntityMagmaMonsterGrunt magma_monster = (EntityMagmaMonsterGrunt) entity;
		float swing = (float) (Math.sin(limbSwing * 0.7F) * 1.2F * limbSwingAngle);
		float flap = (float) (Math.sin((magma_monster.tickCount) * 0.3F) * 0.8F);

		root().getChild("leftArmTop").xRot = -1.1344640137963142F + swing;
		root().getChild("rightArmTop").xRot = -1.1344640137963142F - swing;

		root().getChild("leftArmLavaInner").xRot = 0.9599310885968813F - swing;
		root().getChild("leftArmLavaOutFront").xRot = 0.9599310885968813F - swing;
		root().getChild("leftArmLavaOutBack").xRot = 0.9599310885968813F - swing;

		root().getChild("rightArmLavaInner").xRot = 0.9599310885968813F + swing;
		root().getChild("rightArmLavaOutFront").xRot = 0.9599310885968813F + swing;
		root().getChild("rightArmLavaOutBack").xRot = 0.9599310885968813F + swing;

		root().getChild("leftArmTop").yRot = -0.6981317007977318F + flap * 0.03125F;
		root().getChild("rightArmTop").yRot = 0.6981317007977318F - flap * 0.03125F;

		root().getChild("rightLegThigh").xRot = -swing * 0.125F - swing;
		root().getChild("leftLegThigh").xRot = swing * 0.125F + swing;

		root().getChild("rightLegShin").xRot = -0.6806784082777886F + swing * 0.5F;
		root().getChild("leftLegShin").xRot = -0.6806784082777886F - swing * 0.5F;

		root().getChild("headMain").xRot = -0.5235987755982988F + flap * 0.025F;
		root().getChild("torsoTop").xRot = 0.5235987755982988F - flap * 0.025F;
		root().getChild("torsoBottom").xRot = 0.6981317007977318F - swing * 0.125F;
		root().getChild("torsoBottom").zRot = 0 - swing * 0.125F;
		root().getChild("jaw").xRot = 0.5235987755982988F + flap * 0.2F;
	}

	@Override
	public ModelPart root() {
		return root;
	}
}
