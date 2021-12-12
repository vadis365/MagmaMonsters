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
		torsoBottom = root.getChild("root").getChild("torsoBottom");
		leftLegThigh = torsoBottom.getChild("leftLegThigh");
		leftLegShin = leftLegThigh.getChild("leftLegShin");
		leftLegLavaOuter = leftLegShin.getChild("leftLegLavaOuter");
		leftLegLavaInner = leftLegShin.getChild("leftLegLavaInner");
		leftLegLavaFront = leftLegShin.getChild("leftLegLavaFront");
		leftLegLavaBack = leftLegShin.getChild("leftLegLavaBack");
		rightLegThigh = torsoBottom.getChild("rightLegThigh");
		rightLegShin = rightLegThigh.getChild("rightLegShin");
		rightLegLavaBack = rightLegShin.getChild("rightLegLavaBack");
		rightLegLavaInner = rightLegShin.getChild("rightLegLavaInner");
		rightLegLavaOuter = rightLegShin.getChild("rightLegLavaOuter");
		rightLegLavaFront = rightLegShin.getChild("rightLegLavaFront");
		torsoTop = torsoBottom.getChild("torsoTop");
		backLumpTop = torsoTop.getChild("backLumpTop");
		leftArmTop = torsoTop.getChild("leftArmTop");
		leftArmBottom = leftArmTop.getChild("leftArmBottom");
		leftArmLavaOutFront = leftArmBottom.getChild("leftArmLavaOutFront");
		leftArmLavaInner = leftArmBottom.getChild("leftArmLavaInner");
		leftArmLavaOutBack = leftArmBottom.getChild("leftArmLavaOutBack");
		rightArmTop = torsoTop.getChild("rightArmTop");
		rightArmBottom = rightArmTop.getChild("rightArmBottom");
		rightArmLavaInner = rightArmBottom.getChild("rightArmLavaInner");
		rightArmLavaOutFront = rightArmBottom.getChild("rightArmLavaOutFront");
		rightArmLavaOutBack = rightArmBottom.getChild("rightArmLavaOutBack");
		backLumpMid = torsoBottom.getChild("backLumpMid");
		backLumpBot = torsoBottom.getChild("backLumpBot");
		crotch = torsoBottom.getChild("crotch");
		headMain = torsoBottom.getChild("headMain");
		headTop = headMain.getChild("headTop");
		headCrest = headMain.getChild("headCrest");
		faceBrow = headMain.getChild("faceBrow");
		faceBottom = headMain.getChild("faceBottom");
		faceLeft = headMain.getChild("faceLeft");
		faceRight = headMain.getChild("faceRight");
		nose = headMain.getChild("nose");
		rightTuskStart = headMain.getChild("rightTuskStart");
		rightTuskEnd = headMain.getChild("rightTuskEnd");
		leftTuskStart = headMain.getChild("leftTuskStart");
		leftTuskEnd = headMain.getChild("leftTuskEnd");
		jaw = headMain.getChild("jaw");
		ltooth = jaw.getChild("ltooth");
		rtooth = jaw.getChild("rtooth");
		eyes = headMain.getChild("eyes");
	}
	
	@SuppressWarnings("unused")
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
		headMain.yRot = (float) Math.sin((netHeadYaw / (180F / (float) Math.PI)) * 0.5F);
		headMain.xRot = (float) (-0.5235987755982988F + Math.sin((headPitch / (180F / (float) Math.PI)) * 0.5F) + flap * 0.025F);
	}

	@Override
	public void prepareMobModel(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		EntityMagmaMonsterGrunt magma_monster = (EntityMagmaMonsterGrunt) entity;
		float swing = (float) (Math.sin(limbSwing * 0.7F) * 1.2F * limbSwingAngle);
		float swingCos = (float) (Math.cos(limbSwing * 0.7F) * 1.2F * limbSwingAngle);
		float flap = (float) (Math.sin((magma_monster.tickCount) * 0.3F) * 0.8F);

		leftArmTop.xRot = -1.1345F  -swing;
		rightArmTop.xRot = -1.1345F + swing;

		leftArmLavaInner.xRot = 0.9599310885968813F - swing;
		leftArmLavaOutFront.xRot = 0.9599310885968813F - swing;
		leftArmLavaOutBack.xRot = 0.9599310885968813F - swing;

		rightArmLavaInner.xRot = 0.9599310885968813F + swing;
		rightArmLavaOutFront.xRot = 0.9599310885968813F + swing;
		rightArmLavaOutBack.xRot = 0.9599310885968813F + swing;

		leftArmTop.yRot = 0.6981F + flap * 0.03125F;
		rightArmTop.yRot = -0.6981F - flap * 0.03125F;

		rightLegThigh.xRot = -swing * 0.5F;
		leftLegThigh.xRot = swing * 0.5F;

		rightLegShin.xRot = -0.6806784082777886F + swingCos * 0.75F;
		leftLegShin.xRot = -0.6806784082777886F - swingCos * 0.75F;

		headMain.xRot = -0.5235987755982988F + flap * 0.025F;
		torsoTop.xRot = 0.5235987755982988F - flap * 0.025F;
		torsoBottom.xRot = 0.6981317007977318F - swing * 0.0625F;
		torsoBottom.zRot = 0 - swing * 0.125F;
		jaw.xRot = 0.5235987755982988F + flap * 0.2F;
	}

	@Override
	public ModelPart root() {
		return root;
	}
}
