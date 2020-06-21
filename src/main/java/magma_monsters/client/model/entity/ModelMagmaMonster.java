package magma_monsters.client.model.entity;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import magma_monsters.entities.EntityMagmaMonster;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelMagmaMonster<T extends EntityMagmaMonster> extends EntityModel<T> {
	public ModelRenderer torsoBottom;
	public ModelRenderer leftLegThigh;
    public ModelRenderer rightLegThigh;
    public ModelRenderer torsoTop;
    public ModelRenderer backLumpMid;
    public ModelRenderer backLumpBot;
    public ModelRenderer crotch;
    public ModelRenderer headMain;
    public ModelRenderer leftLegShin;
    public ModelRenderer leftLegLavaOuter;
    public ModelRenderer leftLegLavaInner;
    public ModelRenderer leftLegLavaFront;
    public ModelRenderer leftLegLavaBack;
    public ModelRenderer rightLegShin;
    public ModelRenderer rightLegLavaBack;
    public ModelRenderer rightLegLavaInner;
    public ModelRenderer rightLegLavaOuter;
    public ModelRenderer rightLegLavaFront;
    public ModelRenderer backLumpTop;
    public ModelRenderer leftArmTop;
    public ModelRenderer rightArmTop;
    public ModelRenderer leftArmBottom;
    public ModelRenderer leftArmLavaOutFront;
    public ModelRenderer leftArmLavaInner;
    public ModelRenderer leftArmLavaOutBack;
    public ModelRenderer rightArmBottom;
    public ModelRenderer rightArmLavaInner;
    public ModelRenderer rightArmLavaOutFront;
    public ModelRenderer rightArmLavaOutBack;
    public ModelRenderer headTop;
    public ModelRenderer headCrest;
    public ModelRenderer faceBrow;
    public ModelRenderer faceBottom;
    public ModelRenderer faceLeft;
    public ModelRenderer faceRight;
    public ModelRenderer nose;
    public ModelRenderer rightTuskStart;
    public ModelRenderer rightTuskEnd;
    public ModelRenderer leftTuskStart;
    public ModelRenderer leftTuskEnd;
    public ModelRenderer jaw;
    public ModelRenderer eyes;
    public ModelRenderer ltooth;
    public ModelRenderer rtooth;

    public ModelMagmaMonster() {
        textureWidth = 64;
        textureHeight = 64;
        torsoTop = new ModelRenderer(this, 12, 22);
        torsoTop.setRotationPoint(0.0F, -7.0F, 2.5F);
        torsoTop.addBox(-7.0F, -5.9F, -5.6F, 14, 6, 6, 0.0F);
        setRotateAngle(torsoTop, 0.5235987755982988F, 0.0F, 0.0F);
        nose = new ModelRenderer(this, 29, 3);
        nose.setRotationPoint(0.0F, 0.0F, 0.0F);
        nose.addBox(-0.5F, 0.7F, -6.9F, 1, 4, 2, 0.0F);
        setRotateAngle(nose, -0.3490658503988659F, 0.0F, 0.0F);
        faceBottom = new ModelRenderer(this, 41, 6);
        faceBottom.setRotationPoint(0.0F, 0.0F, 0.0F);
        faceBottom.addBox(-2.0F, 0.5F, -7.0F, 4, 4, 1, 0.0F);
        headTop = new ModelRenderer(this, 0, 0);
        headTop.setRotationPoint(0.0F, 0.0F, 0.0F);
        headTop.addBox(-2.0F, -3.3F, -5.0F, 4, 1, 4, 0.0F);
        setRotateAngle(headTop, 0.296705972839036F, -0.0F, 0.0F);
        rightArmBottom = new ModelRenderer(this, 52, 6);
        rightArmBottom.setRotationPoint(-1.5F, 5.0F, 0.0F);
        rightArmBottom.addBox(-1.1F, 0.0F, -1.2F, 3, 8, 3, 0.0F);
        setRotateAngle(rightArmBottom, -0.9599310885968813F, -0.0F, -0.6981317007977318F);
        backLumpMid = new ModelRenderer(this, 0, 35);
        backLumpMid.setRotationPoint(0.0F, 0.0F, 0.0F);
        backLumpMid.addBox(-1.0F, -5.0F, 2.7F, 2, 3, 2, 0.0F);
        setRotateAngle(backLumpMid, 0.3490658503988659F, 0.0F, 0.0F);
        leftLegThigh = new ModelRenderer(this, 0, 43);
        leftLegThigh.mirror = true;
        leftLegThigh.setRotationPoint(2.0F, 0.0F, 0.0F);
        leftLegThigh.addBox(-1.5F, -1.0F, -6.0F, 4, 3, 7, 0.0F);
        setRotateAngle(leftLegThigh, 0.0F, -0.3490658503988659F, 0.2617993877991494F);
        torsoBottom = new ModelRenderer(this, 19, 35);
        torsoBottom.setRotationPoint(0.0F, 13.0F, 7.0F);
        torsoBottom.addBox(-4.0F, -7.0F, -2.2F, 8, 9, 5, 0.0F);
        setRotateAngle(torsoBottom, 0.6981317007977318F, 0.0F, 0.0F);
        rightLegLavaInner = new ModelRenderer(this, 20, 55);
        rightLegLavaInner.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightLegLavaInner.addBox(-1.1F, 0.5F, -1.0F, 2, 8, 1, 0.0F);
        setRotateAngle(rightLegLavaInner, 0.0F, 0.0F, -0.2617993877991494F);
        leftLegLavaBack = new ModelRenderer(this, 13, 54);
        leftLegLavaBack.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftLegLavaBack.addBox(0.0F, 0.5F, -1.0F, 1, 8, 2, 0.0F);
        setRotateAngle(leftLegLavaBack, 0.2617993877991494F, 0.0F, 0.0F);
        rightLegLavaBack = new ModelRenderer(this, 13, 54);
        rightLegLavaBack.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightLegLavaBack.addBox(-1.0F, 0.5F, -1.0F, 1, 8, 2, 0.0F);
        setRotateAngle(rightLegLavaBack, 0.2617993877991494F, 0.0F, 0.0F);
        faceBrow = new ModelRenderer(this, 25, 0);
        faceBrow.setRotationPoint(0.0F, 0.0F, 0.0F);
        faceBrow.addBox(-3.0F, -2.0F, -7.0F, 6, 1, 1, 0.0F);
        rightArmLavaInner = new ModelRenderer(this, 27, 56);
        rightArmLavaInner.setRotationPoint(0.9F, 6.5F, 0.5F);
        rightArmLavaInner.addBox(-0.5F, -0.5F, -1.5F, 1, 5, 3, 0.0F);
        setRotateAngle(rightArmLavaInner, 0.9599310885968813F, 0.0F, 0.0F);
        headMain = new ModelRenderer(this, 20, 10);
        headMain.setRotationPoint(0.0F, -11.3F, -1.0F);
        headMain.addBox(-3.0F, -2.0F, -6.0F, 6, 5, 6, 0.0F);
        setRotateAngle(headMain, -0.5235987755982988F, 0.0F, 0.0F);
        rightLegShin = new ModelRenderer(this, 52, 53);
        rightLegShin.setRotationPoint(0.0F, 0.7F, -4.8F);
        rightLegShin.addBox(-2.0F, -0.6F, -2.0F, 3, 8, 3, 0.0F);
        setRotateAngle(rightLegShin, -0.6806784082777886F, 0.0F, 0.0F);
        leftArmLavaOutBack = new ModelRenderer(this, 36, 56);
        leftArmLavaOutBack.setRotationPoint(0.1F, 3.5F, 0.5F);
        leftArmLavaOutBack.addBox(-0.5F, -0.5F, -1.0F, 1, 6, 2, 0.0F);
        setRotateAngle(leftArmLavaOutBack, 0.9564404300928925F, -0.0F, 0.0F);
        rightLegLavaFront = new ModelRenderer(this, 13, 54);
        rightLegLavaFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightLegLavaFront.addBox(-1.0F, 0.5F, -1.9F, 1, 8, 2, 0.0F);
        setRotateAngle(rightLegLavaFront, -0.2617993877991494F, 0.0F, 0.0F);
        faceLeft = new ModelRenderer(this, 24, 3);
        faceLeft.setRotationPoint(0.0F, 0.0F, 0.0F);
        faceLeft.addBox(2.0F, -1.0F, -7.0F, 1, 2, 1, 0.0F);
        crotch = new ModelRenderer(this, 24, 50);
        crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
        crotch.addBox(-2.5F, 0.3F, -2.6F, 5, 2, 2, 0.0F);
        setRotateAngle(crotch, -0.6981317007977318F, 0.0F, 0.0F);
        leftLegLavaOuter = new ModelRenderer(this, 20, 55);
        leftLegLavaOuter.mirror = true;
        leftLegLavaOuter.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftLegLavaOuter.addBox(-0.1F, 0.5F, -1.0F, 2, 8, 1, 0.0F);
        setRotateAngle(leftLegLavaOuter, 0.0F, 0.0F, -0.2617993877991494F);
        backLumpBot = new ModelRenderer(this, 9, 35);
        backLumpBot.setRotationPoint(0.0F, 0.0F, 0.0F);
        backLumpBot.addBox(-1.0F, -1.0F, 1.3F, 2, 3, 2, 0.0F);
        setRotateAngle(backLumpBot, 0.3490658503988659F, -0.0F, 0.0F);
        leftArmLavaOutFront = new ModelRenderer(this, 43, 56);
        leftArmLavaOutFront.setRotationPoint(0.1F, 6.5F, 0.5F);
        leftArmLavaOutFront.addBox(-0.5F, -0.5F, -0.5F, 1, 7, 1, 0.0F);
        setRotateAngle(leftArmLavaOutFront, 0.9599310885968813F, -0.0F, 0.0F);
        leftTuskStart = new ModelRenderer(this, 48, 34);
        leftTuskStart.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftTuskStart.addBox(-1.0F, -0.5F, -8.5F, 2, 2, 6, 0.0F);
        setRotateAngle(leftTuskStart, 0.22689280275926282F, -0.3490658503988659F, -0.17453292519943295F);
        leftLegLavaInner = new ModelRenderer(this, 20, 55);
        leftLegLavaInner.mirror = true;
        leftLegLavaInner.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftLegLavaInner.addBox(-0.9F, 0.5F, -1.0F, 2, 8, 1, 0.0F);
        setRotateAngle(leftLegLavaInner, 0.0F, 0.0F, 0.2617993877991494F);
        rightTuskEnd = new ModelRenderer(this, 56, 29);
        rightTuskEnd.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightTuskEnd.addBox(-0.5F, 4.2F, -9.8F, 1, 1, 3, 0.0F);
        setRotateAngle(rightTuskEnd, -0.28797932657906433F, 0.3490658503988659F, 0.17453292519943295F);
        backLumpTop = new ModelRenderer(this, 0, 28);
        backLumpTop.setRotationPoint(0.0F, 0.0F, 0.0F);
        backLumpTop.addBox(-1.0F, -4.8F, -0.1F, 2, 4, 2, 0.0F);
        setRotateAngle(backLumpTop, 0.3490658503988659F, 0.0F, 0.0F);
        leftLegShin = new ModelRenderer(this, 0, 53);
        leftLegShin.mirror = true;
        leftLegShin.setRotationPoint(0.0F, 0.7F, -4.8F);
        leftLegShin.addBox(-1.0F, -0.6F, -2.0F, 3, 8, 3, 0.0F);
        setRotateAngle(leftLegShin, -0.6806784082777886F, 0.0F, 0.0F);
        leftArmTop = new ModelRenderer(this, 0, 18);
        leftArmTop.setRotationPoint(6.0F, -3.5F, -3.5F);
        leftArmTop.addBox(0.0F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
        setRotateAngle(leftArmTop, -1.1344640137963142F, -0.6981317007977318F, -0.17453292519943295F);
        rightLegLavaOuter = new ModelRenderer(this, 20, 55);
        rightLegLavaOuter.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightLegLavaOuter.addBox(-1.9F, 0.5F, -1.0F, 2, 8, 1, 0.0F);
        setRotateAngle(rightLegLavaOuter, 0.0F, 0.0F, 0.2617993877991494F);
        leftLegLavaFront = new ModelRenderer(this, 13, 54);
        leftLegLavaFront.mirror = true;
        leftLegLavaFront.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftLegLavaFront.addBox(0.0F, 0.5F, -1.9F, 1, 8, 2, 0.0F);
        setRotateAngle(leftLegLavaFront, -0.2617993877991494F, 0.0F, 0.0F);
        rightArmLavaOutFront = new ModelRenderer(this, 43, 56);
        rightArmLavaOutFront.setRotationPoint(-0.1F, 6.5F, 0.5F);
        rightArmLavaOutFront.addBox(-0.5F, -0.5F, -0.5F, 1, 7, 1, 0.0F);
        setRotateAngle(rightArmLavaOutFront, 0.9599310885968813F, -0.0F, 0.0F);
        leftArmLavaInner = new ModelRenderer(this, 27, 56);
        leftArmLavaInner.setRotationPoint(-0.9F, 6.5F, 0.5F);
        leftArmLavaInner.addBox(-0.5F, -0.5F, -1.5F, 1, 5, 3, 0.0F);
        setRotateAngle(leftArmLavaInner, 0.9599310885968813F, -0.0F, 0.0F);
        rightArmTop = new ModelRenderer(this, 52, 18);
        rightArmTop.setRotationPoint(-6.0F, -3.5F, -3.5F);
        rightArmTop.addBox(-3.0F, -1.0F, -1.5F, 3, 6, 3, 0.0F);
        setRotateAngle(rightArmTop, -1.1344640137963142F, 0.6981317007977318F, 0.17453292519943295F);
        leftArmBottom = new ModelRenderer(this, 0, 6);
        leftArmBottom.setRotationPoint(1.5F, 5.0F, 0.0F);
        leftArmBottom.addBox(-1.9F, 0.0F, -1.2F, 3, 8, 3, 0.0F);
        setRotateAngle(leftArmBottom, -0.9599310885968813F, 0.0F, 0.6981317007977318F);
        rightArmLavaOutBack = new ModelRenderer(this, 36, 56);
        rightArmLavaOutBack.setRotationPoint(-0.1F, 3.5F, 0.5F);
        rightArmLavaOutBack.addBox(-0.5F, -0.5F, -1.0F, 1, 6, 2, 0.0F);
        setRotateAngle(rightArmLavaOutBack, 0.9599310885968813F, 0.0F, 0.0F);
        jaw = new ModelRenderer(this, 44, 0);
        jaw.setRotationPoint(0.0F, 3.5F, -2.6F);
        jaw.addBox(-3.0F, -0.5F, -3.4F, 6, 1, 4, 0.0F);
        setRotateAngle(jaw, 0.5235987755982988F, 0.0F, 0.0F);
        rightLegThigh = new ModelRenderer(this, 42, 43);
        rightLegThigh.setRotationPoint(-2.0F, 0.0F, 0.0F);
        rightLegThigh.addBox(-2.5F, -1.0F, -6.0F, 4, 3, 7, 0.0F);
        setRotateAngle(rightLegThigh, 0.0F, 0.3490658503988659F, -0.2617993877991494F);
        leftTuskEnd = new ModelRenderer(this, 56, 29);
        leftTuskEnd.setRotationPoint(0.0F, 0.0F, 0.0F);
        leftTuskEnd.addBox(-0.5F, 4.2F, -9.8F, 1, 1, 3, 0.0F);
        setRotateAngle(leftTuskEnd, -0.28797932657906433F, -0.3490658503988659F, -0.17453292519943295F);
        rightTuskStart = new ModelRenderer(this, 48, 34);
        rightTuskStart.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightTuskStart.addBox(-1.0F, -0.5F, -8.5F, 2, 2, 6, 0.0F);
        setRotateAngle(rightTuskStart, 0.22689280275926282F, 0.3490658503988659F, 0.17453292519943295F);
        headCrest = new ModelRenderer(this, 12, 1);
        headCrest.setRotationPoint(0.0F, 0.0F, 0.0F);
        headCrest.addBox(-0.5F, -3.6F, -5.8F, 1, 2, 6, 0.0F);
        setRotateAngle(headCrest, 0.296705972839036F, -0.0F, 0.0F);
        faceRight = new ModelRenderer(this, 36, 3);
        faceRight.setRotationPoint(0.0F, 0.0F, 0.0F);
        faceRight.addBox(-3.0F, -1.0F, -7.0F, 1, 2, 1, 0.0F);
        eyes = new ModelRenderer(this, 13, 12);
        eyes.setRotationPoint(0.0F, 0.0F, 0.0F);
        eyes.addBox(-2.0F, -1.25F, -6.1F, 4, 2, 1, 0.0F);
        rtooth = new ModelRenderer(this, 14, 19);
        rtooth.setRotationPoint(0.0F, 0.0F, 0.0F);
        rtooth.addBox(-3.0F, -1.5F, -3.5F, 1, 1, 1, 0.0F);
        ltooth = new ModelRenderer(this, 14, 16);
        ltooth.setRotationPoint(0.0F, 0.0F, 0.0F);
        ltooth.addBox(2.0F, -1.5F, -3.5F, 1, 1, 1, 0.0F);

        torsoBottom.addChild(torsoTop);
        headMain.addChild(nose);
        headMain.addChild(faceBottom);
        headMain.addChild(headTop);
        headMain.addChild(eyes);
        rightArmTop.addChild(rightArmBottom);
        torsoBottom.addChild(backLumpMid);
        torsoBottom.addChild(leftLegThigh);
        rightLegShin.addChild(rightLegLavaInner);
        leftLegShin.addChild(leftLegLavaBack);
        rightLegShin.addChild(rightLegLavaBack);
        headMain.addChild(faceBrow);
        rightArmBottom.addChild(rightArmLavaInner);
        torsoBottom.addChild(headMain);
        rightLegThigh.addChild(rightLegShin);
        leftArmBottom.addChild(leftArmLavaOutBack);
        rightLegShin.addChild(rightLegLavaFront);
        headMain.addChild(faceLeft);
        torsoBottom.addChild(crotch);
        leftLegShin.addChild(leftLegLavaOuter);
        torsoBottom.addChild(backLumpBot);
        leftArmBottom.addChild(leftArmLavaOutFront);
        headMain.addChild(leftTuskStart);
        leftLegShin.addChild(leftLegLavaInner);
        headMain.addChild(rightTuskEnd);
        torsoTop.addChild(backLumpTop);
        leftLegThigh.addChild(leftLegShin);
        torsoTop.addChild(leftArmTop);
        rightLegShin.addChild(rightLegLavaOuter);
        leftLegShin.addChild(leftLegLavaFront);
        rightArmBottom.addChild(rightArmLavaOutFront);
        leftArmBottom.addChild(leftArmLavaInner);
        torsoTop.addChild(rightArmTop);
        leftArmTop.addChild(leftArmBottom);
        rightArmBottom.addChild(rightArmLavaOutBack);
        headMain.addChild(jaw);
        torsoBottom.addChild(rightLegThigh);
        headMain.addChild(leftTuskEnd);
        headMain.addChild(rightTuskStart);
        headMain.addChild(headCrest);
        headMain.addChild(faceRight);
        jaw.addChild(rtooth);
        jaw.addChild(ltooth);
    }

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.torsoBottom).forEach((p_228279_8_) -> {
            p_228279_8_.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);});
	}

	@Override
	 public void setRotationAngles(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		EntityMagmaMonster magma_monster = (EntityMagmaMonster) entity;
		float flap = MathHelper.sin((magma_monster.ticksExisted) * 0.3F) * 0.8F;
		headMain.rotateAngleY = MathHelper.sin((netHeadYaw / (180F / (float) Math.PI)) * 0.5F);
		headMain.rotateAngleX = -0.5235987755982988F + MathHelper.sin((headPitch / (180F / (float) Math.PI)) * 0.5F) + flap * 0.025F;
	}

	@Override
	public void setLivingAnimations(T entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		EntityMagmaMonster magma_monster = (EntityMagmaMonster) entity;
		float swing = MathHelper.sin(limbSwing * 0.7F) * 1.2F * limbSwingAngle;
		float flap = MathHelper.sin((magma_monster.ticksExisted) * 0.3F) * 0.8F;

		leftArmTop.rotateAngleX = -1.1344640137963142F + swing;
		rightArmTop.rotateAngleX = -1.1344640137963142F - swing;

		leftArmLavaInner.rotateAngleX = 0.9599310885968813F - swing;
		leftArmLavaOutFront.rotateAngleX = 0.9599310885968813F - swing;
		leftArmLavaOutBack.rotateAngleX = 0.9599310885968813F - swing;

	    rightArmLavaInner.rotateAngleX = 0.9599310885968813F + swing;
	    rightArmLavaOutFront.rotateAngleX = 0.9599310885968813F + swing;
	    rightArmLavaOutBack.rotateAngleX = 0.9599310885968813F + swing;

		leftArmTop.rotateAngleY = -0.6981317007977318F + flap * 0.03125F;
		rightArmTop.rotateAngleY = 0.6981317007977318F - flap * 0.03125F;

		rightLegThigh.rotateAngleX = -swing * 0.125F - swing;
		leftLegThigh.rotateAngleX = swing * 0.125F + swing;

		rightLegShin.rotateAngleX = -0.6806784082777886F + swing * 0.5F;
		leftLegShin.rotateAngleX = -0.6806784082777886F - swing * 0.5F;

		headMain.rotateAngleX = -0.5235987755982988F + flap * 0.025F;
		torsoTop.rotateAngleX = 0.5235987755982988F - flap * 0.025F;
		torsoBottom.rotateAngleX = 0.6981317007977318F - swing * 0.125F;
		torsoBottom.rotateAngleZ = 0 - swing * 0.125F;
		jaw.rotateAngleX = 0.5235987755982988F + flap * 0.2F;
	}

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
