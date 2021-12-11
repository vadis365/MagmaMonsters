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

	public ModelPart root;
	public ModelPart torsoBottom;
	public ModelPart leftLegThigh;
	public ModelPart leftLegShin;
	public ModelPart leftLegLavaOuter;
	public ModelPart leftLegLavaInner;
	public ModelPart leftLegLavaFront;
	public ModelPart leftLegLavaBack;
	public ModelPart rightLegThigh;
	public ModelPart rightLegShin;
	public ModelPart rightLegLavaBack;
	public ModelPart rightLegLavaInner;
	public ModelPart rightLegLavaOuter;
	public ModelPart rightLegLavaFront;
	public ModelPart torsoTop;
	public ModelPart backLumpTop;
	public ModelPart leftArmTop;
	public ModelPart leftArmBottom;
	public ModelPart leftArmLavaOutFront;
	public ModelPart leftArmLavaInner;
	public ModelPart leftArmLavaOutBack;
	public ModelPart rightArmTop;
	public ModelPart rightArmBottom;
	public ModelPart rightArmLavaInner;
	public ModelPart rightArmLavaOutFront;
	public ModelPart rightArmLavaOutBack;
	public ModelPart backLumpMid;
	public ModelPart backLumpBot;
	public ModelPart crotch;
	public ModelPart headMain;
	public ModelPart headTop;
	public ModelPart headCrest;
	public ModelPart faceBrow;
	public ModelPart faceBottom;
	public ModelPart faceLeft;
	public ModelPart faceRight;
	public ModelPart nose;
	public ModelPart rightTuskStart;
	public ModelPart rightTuskEnd;
	public ModelPart leftTuskStart;
	public ModelPart leftTuskEnd;
	public ModelPart jaw;
	public ModelPart ltooth;
	public ModelPart rtooth;
	public ModelPart eyes;

	public ModelMagmaMonsterGrunt(ModelPart root) {
		this.root = root;
		torsoBottom = root.getChild("torsoBottom");
		leftLegThigh = root.getChild("leftLegThigh");
		leftLegShin = root.getChild("leftLegShin");
		leftLegLavaOuter = root.getChild("leftLegLavaOuter");
		leftLegLavaInner = root.getChild("leftLegLavaInner");
		leftLegLavaFront = root.getChild("leftLegLavaFront");
		leftLegLavaBack = root.getChild("leftLegLavaBack");
		rightLegThigh = root.getChild("rightLegThigh");
		rightLegShin = root.getChild("rightLegShin");
		rightLegLavaBack = root.getChild("rightLegLavaBack");
		rightLegLavaInner = root.getChild("rightLegLavaInner");
		rightLegLavaOuter = root.getChild("rightLegLavaOuter");
		rightLegLavaFront = root.getChild("rightLegLavaFront");
		torsoTop = root.getChild("torsoTop");
		backLumpTop = root.getChild("backLumpTop");
		leftArmTop = root.getChild("leftArmTop");
		leftArmBottom = root.getChild("leftArmBottom");
		leftArmLavaOutFront = root.getChild("leftArmLavaOutFront");
		leftArmLavaInner = root.getChild("leftArmLavaInner");
		leftArmLavaOutBack = root.getChild("leftArmLavaOutBack");
		rightArmTop = root.getChild("rightArmTop");
		rightArmBottom = root.getChild("rightArmBottom");
		rightArmLavaInner = root.getChild("rightArmLavaInner");
		rightArmLavaOutFront = root.getChild("rightArmLavaOutFront");
		rightArmLavaOutBack = root.getChild("rightArmLavaOutBack");
		backLumpMid = root.getChild("backLumpMid");
		backLumpBot = root.getChild("backLumpBot");
		crotch = root.getChild("crotch");
		headMain = root.getChild("headMain");
		headTop = root.getChild("headTop");
		headCrest = root.getChild("headCrest");
		faceBrow = root.getChild("faceBrow");
		faceBottom = root.getChild("faceBottom");
		faceLeft = root.getChild("faceLeft");
		faceRight = root.getChild("faceRight");
		nose = root.getChild("nose");
		rightTuskStart = root.getChild("rightTuskStart");
		rightTuskEnd = root.getChild("rightTuskEnd");
		leftTuskStart = root.getChild("leftTuskStart");
		leftTuskEnd = root.getChild("leftTuskEnd");
		jaw = root.getChild("jaw");
		ltooth = root.getChild("ltooth");
		rtooth = root.getChild("rtooth");
		eyes = root.getChild("eyes");
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		//PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("torsoBottom", CubeListBuilder.create().texOffs(19, 35).addBox(-4.0F, -7.0F, -2.2F, 8.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 13.0F, 7.0F, 0.6981F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("leftLegThigh", CubeListBuilder.create().texOffs(0, 43).mirror().addBox(-2.5F, -1.0F, -6.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 0.0F, 0.0F, 0.0F, 0.3491F, -0.2618F));

		partdefinition.addOrReplaceChild("leftLegShin", CubeListBuilder.create().texOffs(0, 53).mirror().addBox(-2.0F, -0.6F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.7F, -4.8F, -0.6807F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("leftLegLavaOuter", CubeListBuilder.create().texOffs(20, 55).mirror().addBox(-1.9F, 0.5F, -1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		partdefinition.addOrReplaceChild("leftLegLavaInner", CubeListBuilder.create().texOffs(20, 55).mirror().addBox(-1.1F, 0.5F, -1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		partdefinition.addOrReplaceChild("leftLegLavaFront", CubeListBuilder.create().texOffs(13, 54).mirror().addBox(-1.0F, 0.5F, -1.9F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("leftLegLavaBack", CubeListBuilder.create().texOffs(13, 54).addBox(-1.0F, 0.5F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("rightLegThigh", CubeListBuilder.create().texOffs(42, 43).addBox(-1.5F, -1.0F, -6.0F, 4.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, 0.0F, 0.0F, -0.3491F, 0.2618F));

		partdefinition.addOrReplaceChild("rightLegShin", CubeListBuilder.create().texOffs(52, 53).addBox(-1.0F, -0.6F, -2.0F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.7F, -4.8F, -0.6807F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("rightLegLavaBack", CubeListBuilder.create().texOffs(13, 54).addBox(0.0F, 0.5F, -1.0F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("rightLegLavaInner", CubeListBuilder.create().texOffs(20, 55).addBox(-0.9F, 0.5F, -1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

		partdefinition.addOrReplaceChild("rightLegLavaOuter", CubeListBuilder.create().texOffs(20, 55).addBox(-0.1F, 0.5F, -1.0F, 2.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

		partdefinition.addOrReplaceChild("rightLegLavaFront", CubeListBuilder.create().texOffs(13, 54).addBox(0.0F, 0.5F, -1.9F, 1.0F, 8.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.2618F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("torsoTop", CubeListBuilder.create().texOffs(12, 22).addBox(-7.0F, -5.9F, -5.6F, 14.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, 2.5F, 0.5236F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("backLumpTop", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -4.8F, -0.1F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("leftArmTop", CubeListBuilder.create().texOffs(0, 18).addBox(-3.0F, -1.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -3.5F, -3.5F, -1.1345F, 0.6981F, 0.1745F));

		partdefinition.addOrReplaceChild("leftArmBottom", CubeListBuilder.create().texOffs(0, 6).addBox(-1.1F, 0.0F, -1.2F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 5.0F, 0.0F, -0.9599F, 0.0F, -0.6981F));

		partdefinition.addOrReplaceChild("leftArmLavaOutFront", CubeListBuilder.create().texOffs(43, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 6.5F, 0.5F, 0.9599F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("leftArmLavaInner", CubeListBuilder.create().texOffs(27, 56).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.9F, 6.5F, 0.5F, 0.9599F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("leftArmLavaOutBack", CubeListBuilder.create().texOffs(36, 56).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 3.5F, 0.5F, 0.9564F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("rightArmTop", CubeListBuilder.create().texOffs(52, 18).addBox(0.0F, -1.0F, -1.5F, 3.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -3.5F, -3.5F, -1.1345F, -0.6981F, -0.1745F));

		partdefinition.addOrReplaceChild("rightArmBottom", CubeListBuilder.create().texOffs(52, 6).addBox(-1.9F, 0.0F, -1.2F, 3.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 5.0F, 0.0F, -0.9599F, 0.0F, 0.6981F));

		partdefinition.addOrReplaceChild("rightArmLavaInner", CubeListBuilder.create().texOffs(27, 56).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9F, 6.5F, 0.5F, 0.9599F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("rightArmLavaOutFront", CubeListBuilder.create().texOffs(43, 56).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 6.5F, 0.5F, 0.9599F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("rightArmLavaOutBack", CubeListBuilder.create().texOffs(36, 56).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 3.5F, 0.5F, 0.9599F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("backLumpMid", CubeListBuilder.create().texOffs(0, 35).addBox(-1.0F, -5.0F, 2.7F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("backLumpBot", CubeListBuilder.create().texOffs(9, 35).addBox(-1.0F, -1.0F, 1.3F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3491F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("crotch", CubeListBuilder.create().texOffs(24, 50).addBox(-2.5F, 0.3F, -2.6F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.6981F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("headMain", CubeListBuilder.create().texOffs(20, 10).addBox(-3.0F, -2.0F, -6.0F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.3F, -1.0F, -0.5236F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("headTop", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.3F, -5.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2967F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("headCrest", CubeListBuilder.create().texOffs(12, 1).addBox(-0.5F, -3.6F, -5.8F, 1.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2967F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("faceBrow", CubeListBuilder.create().texOffs(25, 0).addBox(-3.0F, -2.0F, -7.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("faceBottom", CubeListBuilder.create().texOffs(41, 6).addBox(-2.0F, 0.5F, -7.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("faceLeft", CubeListBuilder.create().texOffs(24, 3).addBox(-3.0F, -1.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("faceRight", CubeListBuilder.create().texOffs(36, 3).addBox(2.0F, -1.0F, -7.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(29, 3).addBox(-0.5F, 0.7F, -6.9F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3491F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("rightTuskStart", CubeListBuilder.create().texOffs(48, 34).addBox(-1.0F, -0.5F, -8.5F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2269F, -0.3491F, 0.1745F));

		partdefinition.addOrReplaceChild("rightTuskEnd", CubeListBuilder.create().texOffs(56, 29).addBox(-0.5F, 4.2F, -9.8F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.288F, -0.3491F, 0.1745F));

		partdefinition.addOrReplaceChild("leftTuskStart", CubeListBuilder.create().texOffs(48, 34).addBox(-1.0F, -0.5F, -8.5F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2269F, 0.3491F, -0.1745F));

		partdefinition.addOrReplaceChild("leftTuskEnd", CubeListBuilder.create().texOffs(56, 29).addBox(-0.5F, 4.2F, -9.8F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.288F, 0.3491F, -0.1745F));

		partdefinition.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(44, 0).addBox(-3.0F, -0.5F, -3.4F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, -2.6F, 0.5236F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("ltooth", CubeListBuilder.create().texOffs(14, 16).addBox(-3.0F, -1.5F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("rtooth", CubeListBuilder.create().texOffs(14, 19).addBox(2.0F, -1.5F, -3.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		partdefinition.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(13, 12).addBox(-2.0F, -1.25F, -6.1F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

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
		headMain.yRot = (float) Math.sin((netHeadYaw / (180F / (float) Math.PI)) * 0.5F);
		headMain.xRot = (float) (-0.5235987755982988F + Math.sin((headPitch / (180F / (float) Math.PI)) * 0.5F) + flap * 0.025F);
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		EntityMagmaMonsterGrunt magma_monster = (EntityMagmaMonsterGrunt) entity;
		float swing = (float) (Math.sin(limbSwing * 0.7F) * 1.2F * limbSwingAngle);
		float flap = (float) (Math.sin((magma_monster.tickCount) * 0.3F) * 0.8F);

		leftArmTop.xRot = -1.1344640137963142F + swing;
		rightArmTop.xRot = -1.1344640137963142F - swing;

		leftArmLavaInner.xRot = 0.9599310885968813F - swing;
		leftArmLavaOutFront.xRot = 0.9599310885968813F - swing;
		leftArmLavaOutBack.xRot = 0.9599310885968813F - swing;

		rightArmLavaInner.xRot = 0.9599310885968813F + swing;
		rightArmLavaOutFront.xRot = 0.9599310885968813F + swing;
		rightArmLavaOutBack.xRot = 0.9599310885968813F + swing;

		leftArmTop.yRot = -0.6981317007977318F + flap * 0.03125F;
		rightArmTop.yRot = 0.6981317007977318F - flap * 0.03125F;

		rightLegThigh.xRot = -swing * 0.125F - swing;
		leftLegThigh.xRot = swing * 0.125F + swing;

		rightLegShin.xRot = -0.6806784082777886F + swing * 0.5F;
		leftLegShin.xRot = -0.6806784082777886F - swing * 0.5F;

		headMain.xRot = -0.5235987755982988F + flap * 0.025F;
		torsoTop.xRot = 0.5235987755982988F - flap * 0.025F;
		torsoBottom.xRot = 0.6981317007977318F - swing * 0.125F;
		torsoBottom.zRot = 0 - swing * 0.125F;
		jaw.xRot = 0.5235987755982988F + flap * 0.2F;
	}

	@Override
	public ModelPart root() {
		return root;
	}
}
