package magma_monsters.client.model.entity;

import magma_monsters.entities.EntityMagmaMonsterGrunt;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelMagmaMonsterGrunt extends ModelMagmaMonster  {

    public ModelMagmaMonsterGrunt() {}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAngle, float entityTickTime, float rotationYaw, float rotationPitch, float unitPixel, Entity entity) {
		EntityMagmaMonsterGrunt magma_monster = (EntityMagmaMonsterGrunt) entity;
		float flap = MathHelper.sin((magma_monster.ticksExisted) * 0.3F) * 0.8F;
		headMain.rotateAngleY = MathHelper.sin((rotationYaw / (180F / (float) Math.PI)) * 0.5F);
		headMain.rotateAngleX = -0.5235987755982988F + MathHelper.sin((rotationPitch / (180F / (float) Math.PI)) * 0.5F) + flap * 0.025F;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAngle, float partialRenderTicks) {
		EntityMagmaMonsterGrunt magma_monster = (EntityMagmaMonsterGrunt) entity;
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
}
