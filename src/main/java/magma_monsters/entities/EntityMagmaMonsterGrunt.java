package magma_monsters.entities;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Random;

import magma_monsters.MagmaMonsters;
import magma_monsters.ModSounds;
import magma_monsters.configs.Config;
import magma_monsters.network.QuenchMessage;
import magma_monsters.particles.ClientParticles;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;

public class EntityMagmaMonsterGrunt extends MonsterEntity {
	private static final DataParameter<Boolean> IS_MOLTEN = EntityDataManager.<Boolean>createKey(EntityMagmaMonsterGrunt.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> MOLTEN_TIMER = EntityDataManager.<Integer>createKey(EntityMagmaMonsterGrunt.class, DataSerializers.VARINT);
	protected Goal aiFireballAttack;

	public EntityMagmaMonsterGrunt(EntityType<? extends EntityMagmaMonsterGrunt> type, World world) {
		super(type, world);
		setPathPriority(PathNodeType.LAVA, 8.0F);
		setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
		setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
		experienceValue = 10;
	}

	@Override
	protected void registerData() {
		super.registerData();
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
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("molten", getMolten());
		compound.putInt("molten_timer", getMoltenTimer());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		dataManager.set(IS_MOLTEN, compound.getBoolean("molten"));
		dataManager.set(MOLTEN_TIMER, compound.getInt("molten_timer"));
	}
	
	@Override
	protected void registerGoals() {
		aiFireballAttack = new EntityMagmaMonsterGrunt.AIFireballAttack(this);
		goalSelector.addGoal(0, new SwimGoal(this));
		goalSelector.addGoal(1, aiFireballAttack);
		goalSelector.addGoal(2, new EntityMagmaMonsterGrunt.AttackGoal(this));
		goalSelector.addGoal(4, new WaterAvoidingRandomWalkingGoal(this, 1D));
		goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		goalSelector.addGoal(6, new LookRandomlyGoal(this));
		targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setCallsForHelp(EntityMagmaMonster.class).setCallsForHelp(EntityMagmaMonsterGrunt.class));
		targetSelector.addGoal(1, new EntityMagmaMonsterGrunt.TargetGoal<>(this, PlayerEntity.class));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MonsterEntity.func_234295_eP_()
				.func_233815_a_(Attributes.field_233818_a_, Config.MAGMA_MONSTER_GRUNT_HEALTH.get()) //health
				.func_233815_a_(Attributes.field_233819_b_, 32D) //follow range
				.func_233815_a_(Attributes.field_233821_d_, 0.23000000417232513D) //move speed
				.func_233815_a_(Attributes.field_233823_f_, Config.MAGMA_MONSTER_GRUNT_ATTACK_DAMAGE.get()); //attack damage	
	}

	public static boolean canSpawnHere(EntityType<EntityMagmaMonsterGrunt> entity, IWorld world, SpawnReason spawn_reason, BlockPos pos, Random random) {
		 BlockPos.Mutable blockPosMutable = pos.func_239590_i_();
	      do {
	    	  blockPosMutable.move(Direction.UP);
	      } while(world.getFluidState(blockPosMutable).isTagged(FluidTags.LAVA));
		return world.getBlockState(blockPosMutable).isAir() && blockPosMutable.getY() <= Config.MAGMA_MONSTER_GRUNT_SPAWN_Y_HEIGHT.get();
	}

	@Override
    public boolean isNotColliding(IWorldReader world) {
        return world.checkNoEntityCollision(this);
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

	protected static boolean isValidLightLevel(IWorld world, BlockPos pos) {
		return true;
	}

	@Override
	protected float getSoundPitch() {
		return 2F;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		int randomAmount = 1 + rand.nextInt(2 + looting);
		for (int count = 0; count < randomAmount; ++count)
			if(getMolten())
				entityDropItem(new ItemStack(Items.MAGMA_CREAM), 0F);
			else
				entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.COBBLESTONE)), 0F);
	}

	@Override
	public void tick() {
		super.tick();
		if (getEntityWorld().isRemote && getEntityWorld().getGameTime() % 10 == 0 && getMolten())
			lavaParticles(getEntityWorld(), getPosX(), getPosY() + 0.75D, getPosZ(), rand);

		if (!getEntityWorld().isRemote) {
			if (getMolten() && getMoltenTimer() < 50)
				setMoltenTimer(getMoltenTimer() + 1);
			
			if (!getMolten() && getMoltenTimer() > 0)
				setMoltenTimer(getMoltenTimer() - 1);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void lavaParticles(World world, double x, double y, double z, Random rand) {
		ClientParticles.spawnCustomParticle("lava", getEntityWorld(), x, y, z, 0F, 0F, 0F);
	}

	public void changeParticles(Entity entity, float x, float y, float z, byte type) {
		Iterator<? extends PlayerEntity> players = entity.getEntityWorld().getPlayers().iterator();
		while (players.hasNext()) {
			PlayerEntity playersNear = players.next();
			if ((playersNear).getDistanceSq(entity) < 1024.0D) {
				MagmaMonsters.NETWORK_WRAPPER.sendTo(new QuenchMessage(x, y, z, type), ((ServerPlayerEntity) playersNear).connection.netManager, NetworkDirection.PLAY_TO_CLIENT);
			}
		}
	}

	@Override
	  public void livingTick() {
		super.livingTick();

		if (!getEntityWorld().isRemote) {

			if (isWet() && !isInLava() && getMolten()) {
		        getEntityWorld().playSound(null, getPosX(), getPosY(), getPosZ(), SoundEvents.BLOCK_LAVA_EXTINGUISH, SoundCategory.HOSTILE, 1F, 2.6F + (getEntityWorld().rand.nextFloat() - getEntityWorld().rand.nextFloat()) * 0.8F);
		        changeParticles(this, (float)getPosX(), (float)getPosY() + 0.45F, (float)getPosZ(), (byte) 0);
				setMolten(false);
				getAttribute(Attributes.field_233826_i_).setBaseValue(10D);
			}

			if (isInLava() && !getMolten()) {
		        getEntityWorld().playSound(null, getPosX(), getPosY(), getPosZ(), SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.HOSTILE, 1F, 0.6F + (getEntityWorld().rand.nextFloat() - getEntityWorld().rand.nextFloat()) * 0.8F);
		        changeParticles(this, (float)getPosX(), (float)getPosY() + 0.45F, (float)getPosZ(), (byte) 1);
				setMolten(true);
				getAttribute(Attributes.field_233826_i_).setBaseValue(0D);
			}

			if (rand.nextInt(40) == 0 && Config.MAGMA_MONSTER_GRUNT_BLOCK_FIRE.get() && getMolten()) {
				int i = MathHelper.floor(getPosX());
				int j = MathHelper.floor(getPosY());
				int k = MathHelper.floor(getPosZ());
				for (int l = 0; l < 4; ++l) {
					i = MathHelper.floor(getPosX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
					j = MathHelper.floor(getPosY());
					k = MathHelper.floor(getPosZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
					BlockPos blockpos = new BlockPos(i, j, k);
					BlockState blockstate = AbstractFireBlock.func_235326_a_(getEntityWorld(), blockpos);
					if (getEntityWorld().getBlockState(blockpos).getMaterial() == Material.AIR && blockstate.isValidPosition(getEntityWorld(), blockpos))
						getEntityWorld().setBlockState(blockpos, Blocks.FIRE.getDefaultState());
				}
			}
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (canEntityBeSeen(entity)) {
			int modifier =  !getMolten() ? 2 : 1;
			boolean hasHitTarget = entity.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getAttribute(Attributes.field_233823_f_).getBaseValue() * modifier));
			if (hasHitTarget) {
				if (entity instanceof LivingEntity && !getMolten()) {
					int duration = Config.MAGMA_MONSTER_GRUNT_SLOWNESS_EFFECT_DURATION.get();
					if (duration > 0)
						((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, duration * 20, 0));
				}
			}
			return hasHitTarget;
		}
		return false;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float damage) {
		if (source instanceof IndirectEntityDamageSource && !getMolten()) {
			getEntityWorld().playSound((PlayerEntity) null, getPosX(), getPosY(), getPosZ(), SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF, SoundCategory.HOSTILE, 2.5F, 3F);
			return false;
		}
		return super.attackEntityFrom(source, damage);
	}

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(EntityMagmaMonsterGrunt magma_monster, Class<T> classTarget) {
			super(magma_monster, classTarget, true);
		}
	}

	static class AttackGoal extends MeleeAttackGoal {

		public AttackGoal(EntityMagmaMonsterGrunt magma_monster) {
			super(magma_monster, 1D, false);
		}

		@Override
		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getWidth());
		}
	}

	static class AIFireballAttack extends Goal {
		private final EntityMagmaMonsterGrunt magma_monster;
		private int attackStep;
		private int attackTime;

		public AIFireballAttack(EntityMagmaMonsterGrunt magma_monsterIn) {
			magma_monster = magma_monsterIn;
			setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		@Override
		public boolean shouldExecute() {
			LivingEntity livingentity = magma_monster.getAttackTarget();
			return livingentity != null && livingentity.isAlive() && magma_monster.getMolten();
		}

		@Override
		public void startExecuting() {
			attackStep = 0;
		}

		@Override
		 public void tick() {
			--attackTime;
			LivingEntity livingentity = magma_monster.getAttackTarget();
			double d0 = magma_monster.getDistanceSq(livingentity);
			if (d0 < 4.0D) {
				if (attackTime <= 0) {
					attackTime = 20;
					magma_monster.attackEntityAsMob(livingentity);
				}
				magma_monster.getMoveHelper().setMoveTo(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), 1.0D);
			} else if (d0 < 256.0D) {
				double d1 = livingentity.getPosX() - magma_monster.getPosX();
				double d2 = livingentity.getBoundingBox().minY + (double) (livingentity.getHeight() / 2.0F) - (magma_monster.getPosY() + (double) (magma_monster.getHeight() / 2.0F));
				double d3 = livingentity.getPosZ() - magma_monster.getPosZ();
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
						magma_monster.getEntityWorld().playEvent((PlayerEntity) null, 1018, magma_monster.func_233580_cy_(), 0);
						SmallFireballEntity smallfireballentity = new SmallFireballEntity(magma_monster.getEntityWorld(), magma_monster, d1 + magma_monster.getRNG().nextGaussian() * (double) f, d2, d3 + magma_monster.getRNG().nextGaussian() * (double) f);
						smallfireballentity.setPosition(smallfireballentity.getPosX(), magma_monster.getPosY() + (double) (magma_monster.getHeight() / 2.0F) + 0.5D, smallfireballentity.getPosZ());
						magma_monster.getEntityWorld().addEntity(smallfireballentity);
					}
				}
				magma_monster.getLookController().setLookPositionWithEntity(livingentity, 10.0F, 10.0F);
				magma_monster.getNavigator().clearPath();
				magma_monster.getMoveHelper().setMoveTo(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), 1.0D);
			}
			super.tick();
		}
	}
}