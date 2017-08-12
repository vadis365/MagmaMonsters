package magma_monsters.entities;

import java.util.Iterator;
import java.util.Random;

import magma_monsters.MagmaMonsters;
import magma_monsters.ModSounds;
import magma_monsters.configs.ConfigHandler;
import magma_monsters.network.QuenchMessage;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityMagmaMonsterGrunt extends EntityMob {
	private static final DataParameter<Boolean> IS_MOLTEN = EntityDataManager.<Boolean>createKey(EntityMagmaMonster.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> MOLTEN_TIMER = EntityDataManager.<Integer>createKey(EntityMagmaMonster.class, DataSerializers.VARINT);

	protected EntityMagmaMonsterGrunt.AIFireballAttack aiFireballAttack;
	public EntityMagmaMonsterGrunt(World world) {
		super(world);
		setSize(0.5F, 0.9F);
		setPathPriority(PathNodeType.LAVA, 8.0F);
		setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
		setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
		isImmuneToFire = true;
		experienceValue = 10;
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(IS_MOLTEN, true);
		dataManager.register(MOLTEN_TIMER, 50);
	}

	public void setMolten(boolean molten) {
		dataManager.set(IS_MOLTEN, molten);
	}

	public boolean getMolten() {
		return dataManager.get(IS_MOLTEN).booleanValue();
	}

	public void setMoltenTimer(int moltenTimer) {
		dataManager.set(MOLTEN_TIMER, moltenTimer);
	}

	public int getMoltenTimer() {
		return dataManager.get(MOLTEN_TIMER).intValue();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
		nbt.setBoolean("molten", getMolten());
		nbt.setInteger("molten_timer", getMoltenTimer());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
		dataManager.set(IS_MOLTEN, nbt.getBoolean("molten"));
		dataManager.set(MOLTEN_TIMER, nbt.getInteger("molten_timer"));
	}
	
	@Override
	protected void initEntityAI() {
		aiFireballAttack = new EntityMagmaMonsterGrunt.AIFireballAttack(this);
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, aiFireballAttack);
		tasks.addTask(2, new EntityMagmaMonsterGrunt.AIMonsterAttack(this));
		tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(4, new EntityAIWander(this, 1.0D));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, true, new Class[0]));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue((double)ConfigHandler.MAGMA_GRUNT_ATTACK_DAMAGE);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0D);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue((double)ConfigHandler.MAGMA_GRUNT_HEALTH);
	}

	@Override
	public boolean getCanSpawnHere() {
		return getCanSpawnNearLava() && posY <= ConfigHandler.MAGMA_SPAWN_Y_HEIGHT;
	}

	public boolean getCanSpawnNearLava() {
		AxisAlignedBB axisalignedbb = getEntityBoundingBox().expand(5.0D, 5.0D, 5.0D);
		int n = MathHelper.floor(axisalignedbb.minX);
		int o = MathHelper.floor(axisalignedbb.maxX + 1.0D);
		int p = MathHelper.floor(axisalignedbb.minY);
		int q = MathHelper.floor(axisalignedbb.maxY + 1.0D);
		int n1 = MathHelper.floor(axisalignedbb.minZ);
		int o1 = MathHelper.floor(axisalignedbb.maxZ + 1.0D);
		for (int p1 = n; p1 < o; p1++)
			for (int q1 = p; q1 < q; q1++)
				for (int n2 = n1; n2 < o1; n2++) {
					IBlockState o2 = getEntityWorld().getBlockState(new BlockPos(p1, q1, n2));
					if (!this.getEntityWorld().isAirBlock(new BlockPos(p1, q1, n2)))
						if (o2.getMaterial() == Material.LAVA)
							return true;
				}
		return false;
	}

	@Override
    public boolean isNotColliding() {
        return getEntityWorld().checkNoEntityCollision(getEntityBoundingBox()) && getEntityWorld().getCollisionBoxes(this, getEntityBoundingBox()).isEmpty();
    }

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.MAGMA_MONSTER_LIVING;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return ModSounds.MAGMA_MONSTER_HURT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.MAGMA_MONSTER_DEATH;
	}

	@Override
	protected boolean isValidLightLevel() {
		return true;
	}

	@Override
	protected float getSoundPitch() {
		return 2F;
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int looting) {
		int randomAmount = 1 + rand.nextInt(2 + looting);
		for (int count = 0; count < randomAmount; ++count)
			if(getMolten())
				entityDropItem(new ItemStack(Items.MAGMA_CREAM), 0F);
			else
				entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.COBBLESTONE)), 0F);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();
		if (getEntityWorld().isRemote && getEntityWorld().getWorldTime() % 10 == 0 && getMolten())
			lavaParticles(getEntityWorld(), posX, posY + 0.75D, posZ, rand);

		if (!getEntityWorld().isRemote) {
			if (getMolten() && getMoltenTimer() < 50)
				setMoltenTimer(getMoltenTimer() + 1);
			
			if (!getMolten() && getMoltenTimer() > 0)
				setMoltenTimer(getMoltenTimer() - 1);
		}
	}

	@SideOnly(Side.CLIENT)
	public void lavaParticles(World world, double x, double y, double z, Random rand) {
		MagmaMonsters.PROXY.spawnCustomParticle("lava", getEntityWorld(), x, y, z, 0F, 0F, 0F);
	}

	public void changeParticles(Entity entity, float x, float y, float z, byte type) {
		Iterator<EntityPlayer> players = entity.getEntityWorld().playerEntities.iterator();
		while (players.hasNext()) {
			EntityPlayer playersNear = players.next();
			if ((playersNear).getDistanceSqToEntity(entity) < 1024.0D) {
				MagmaMonsters.NETWORK_WRAPPER.sendTo(new QuenchMessage(x, y, z, type), (EntityPlayerMP) playersNear);
			}
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!getEntityWorld().isRemote) {

			if (isWet() && !isInLava() && getMolten()) {
		        getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.HOSTILE, 1F, 2.6F + (getEntityWorld().rand.nextFloat() - getEntityWorld().rand.nextFloat()) * 0.8F);
		        changeParticles(this, (float)posX, (float)posY + 0.45F, (float)posZ, (byte) 0);
				setMolten(false);
				getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10D);
			}

			if (isInLava() && !getMolten()) {
		        getEntityWorld().playSound((EntityPlayer)null, getPosition(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.HOSTILE, 1F, 0.6F + (getEntityWorld().rand.nextFloat() - getEntityWorld().rand.nextFloat()) * 0.8F);
		        changeParticles(this, (float)posX, (float)posY + 0.45F, (float)posZ, (byte) 1);
				setMolten(true);
				getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0D);
			}

			if (rand.nextInt(40) == 0 && ConfigHandler.MAGMA_GRUNT_BLOCK_FIRE && getMolten()) {
				int i = MathHelper.floor(posX);
				int j = MathHelper.floor(posY);
				int k = MathHelper.floor(posZ);
				for (int l = 0; l < 4; ++l) {
					i = MathHelper.floor(posX + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
					j = MathHelper.floor(posY);
					k = MathHelper.floor(posZ + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
					BlockPos blockpos = new BlockPos(i, j, k);
					if (getEntityWorld().getBlockState(blockpos).getMaterial() == Material.AIR && Blocks.FIRE.canPlaceBlockAt(getEntityWorld(), blockpos))
						getEntityWorld().setBlockState(blockpos, Blocks.FIRE.getDefaultState());
				}
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			int modifier =  !getMolten() ? 2 : 1;
			boolean hasHitTarget = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue() * modifier));
			if (hasHitTarget) {
				if (entity instanceof EntityLivingBase && !getMolten()) {
					int duration = ConfigHandler.MAGMA_GRUNT_SLOWNESS_EFFECT_DURATION;
					if (duration > 0)
						((EntityLivingBase) entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, duration * 20, 0));
				}
			}
			return hasHitTarget;
		}
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source instanceof EntityDamageSourceIndirect && !getMolten()) {
			getEntityWorld().playSound((EntityPlayer) null, posX, posY, posZ, SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundCategory.HOSTILE, 2.5F, 3F);
			return false;
		}
		return super.attackEntityFrom(source, damage);
	}

	static class AIMonsterAttack extends EntityAIAttackMelee {

		public AIMonsterAttack(EntityMagmaMonsterGrunt magma_monster) {
			super(magma_monster, 1D, false);
		}

		@Override
		protected double getAttackReachSqr(EntityLivingBase attackTarget) {
			return (double) (4.0F + attackTarget.width);
		}
	}

	static class AIFireballAttack extends EntityAIBase {
		private final EntityMagmaMonsterGrunt magma_monster;
		private int attackStep;
		private int attackTime;

		public AIFireballAttack(EntityMagmaMonsterGrunt magma_monsterIn) {
			magma_monster = magma_monsterIn;
			setMutexBits(3);
		}

		@Override
		public boolean shouldExecute() {
			EntityLivingBase entitylivingbase = magma_monster.getAttackTarget();
			return entitylivingbase != null && entitylivingbase.isEntityAlive() && magma_monster.getMolten();
		}

		@Override
		public void startExecuting() {
			attackStep = 0;
		}

		@Override
		public void updateTask() {
			--attackTime;
			EntityLivingBase entitylivingbase = magma_monster.getAttackTarget();
			double d0 = magma_monster.getDistanceSqToEntity(entitylivingbase);
			if (d0 < 4.0D) {
				if (attackTime <= 0) {
					attackTime = 20;
					magma_monster.attackEntityAsMob(entitylivingbase);
				}
				magma_monster.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			} else if (d0 < 256.0D) {
				double d1 = entitylivingbase.posX - magma_monster.posX;
				double d2 = entitylivingbase.getEntityBoundingBox().minY + (double) (entitylivingbase.height / 2.0F) - (magma_monster.posY + (double) (magma_monster.height / 2.0F));
				double d3 = entitylivingbase.posZ - magma_monster.posZ;
				if (attackTime <= 0) {
					++attackStep;
					if (attackStep == 1)
						attackTime = 60;
					else if (attackStep <= 4) 
						attackTime = 6;
					else {
						attackTime = 100;
						attackStep = 0;
					}
					if (attackStep == 1) {
						float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
						magma_monster.getEntityWorld().playEvent((EntityPlayer) null, 1018, new BlockPos((int) magma_monster.posX, (int) magma_monster.posY, (int) magma_monster.posZ), 0);
						EntitySmallFireball entitysmallfireball = new EntitySmallFireball(magma_monster.getEntityWorld(), magma_monster, d1 + magma_monster.getRNG().nextGaussian() * (double) f, d2, d3 + magma_monster.getRNG().nextGaussian() * (double) f);
						entitysmallfireball.posY = magma_monster.posY + (double) (magma_monster.height / 2.0F) + 0.5D;
						magma_monster.getEntityWorld().spawnEntity(entitysmallfireball);
					}
				}
				magma_monster.getLookHelper().setLookPositionWithEntity(entitylivingbase, 10.0F, 10.0F);
				magma_monster.getNavigator().clearPathEntity();
				magma_monster.getMoveHelper().setMoveTo(entitylivingbase.posX, entitylivingbase.posY, entitylivingbase.posZ, 1.0D);
			}
			super.updateTask();
		}
	}
}