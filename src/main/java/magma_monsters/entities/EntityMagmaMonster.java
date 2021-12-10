package magma_monsters.entities;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.Random;

import magma_monsters.MagmaMonsters;
import magma_monsters.ModSounds;
import magma_monsters.configs.Config;
import magma_monsters.network.QuenchMessage;
import magma_monsters.particles.ClientParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.IndirectEntityDamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkDirection;

public class EntityMagmaMonster extends Monster {
	private static final EntityDataAccessor<Boolean> IS_MOLTEN = SynchedEntityData.defineId(EntityMagmaMonster.class, EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Integer> MOLTEN_TIMER = SynchedEntityData.defineId(EntityMagmaMonster.class, EntityDataSerializers.INT);
	protected Goal aiFireballAttack;

	public EntityMagmaMonster(EntityType<? extends EntityMagmaMonster> type, Level level) {
		super(type, level);
		setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
		setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
		setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
		xpReward = 10;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		entityData.define(IS_MOLTEN, true);
		entityData.define(MOLTEN_TIMER, 50);
	}

	public void setMolten(boolean molten) {
		entityData.set(IS_MOLTEN, molten);
	}

	public boolean getMolten() {
		return entityData.get(IS_MOLTEN).booleanValue();
	}

	public void setMoltenTimer(int moltenTimer) {
		entityData.set(MOLTEN_TIMER, moltenTimer);
	}

	public int getMoltenTimer() {
		return entityData.get(MOLTEN_TIMER).intValue();
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("molten", getMolten());
		compound.putInt("molten_timer", getMoltenTimer());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		entityData.set(IS_MOLTEN, compound.getBoolean("molten"));
		entityData.set(MOLTEN_TIMER, compound.getInt("molten_timer"));
	}

	@Override
	protected void registerGoals() {
		aiFireballAttack = new EntityMagmaMonster.AIFireballAttack(this);
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(1, aiFireballAttack);
		goalSelector.addGoal(2, new EntityMagmaMonster.AttackGoal(this));
		goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
		goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers(EntityMagmaMonster.class).setAlertOthers(EntityMagmaMonsterGrunt.class));
		targetSelector.addGoal(1, new EntityMagmaMonster.TargetGoal<>(this, Player.class));
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, Config.MAGMA_MONSTER_HEALTH.get()) //health
				.add(Attributes.FOLLOW_RANGE, 32D) //follow range
				.add(Attributes.MOVEMENT_SPEED, 0.23000000417232513D) //move speed
				.add(Attributes.ATTACK_DAMAGE, Config.MAGMA_MONSTER_ATTACK_DAMAGE.get()); //attack damage	
	}

	@SuppressWarnings("deprecation")
	public static boolean canSpawnHere(EntityType<EntityMagmaMonster> entity, LevelAccessor level, MobSpawnType spawn_reason, BlockPos pos, Random random) {
		if(isDimBlacklisted(getDimensionRegName(((Level) level).dimension())))
			return false;
		BlockPos.MutableBlockPos blockPosMutable = pos.mutable();
		do {
			blockPosMutable.move(Direction.UP);
		} while (level.getFluidState(blockPosMutable).is(FluidTags.LAVA));
		return level.getBlockState(blockPosMutable).isAir() && blockPosMutable.getY() <= Config.MAGMA_MONSTER_SPAWN_Y_HEIGHT.get();
	}

	public static boolean isDimBlacklisted(String dimensionIn) {
		if(Config.MAGMA_MONSTER_BLACKLISTED_DIMS.get().contains(dimensionIn))
			return true;
		return false;
	}

	public static String getDimensionRegName(ResourceKey<Level> reg) {
		return reg.location().toString();
	}

	@Override
	public float getWalkTargetValue(BlockPos pos, LevelReader level) {
		if (level.getBlockState(pos).getFluidState().is(FluidTags.LAVA)) {
			return 10.0F;
		} else {
			return this.isInLava() ? Float.NEGATIVE_INFINITY : 0.0F;
		}
	}

	@Override
    public boolean checkSpawnObstruction(LevelReader level) {
        return level.isUnobstructed(this);
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

	@SuppressWarnings("deprecation")
	@Override
	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		int randomAmount = 1 + random.nextInt(2 + looting);
		for (int count = 0; count < randomAmount; ++count)
			if(getMolten())
				spawnAtLocation(new ItemStack(random.nextBoolean() ? Items.BLAZE_POWDER : Items.MAGMA_CREAM), 0F);
			else
				spawnAtLocation(new ItemStack(Item.byBlock(Blocks.OBSIDIAN)), 0F);
	}

	@Override
	public void tick() {
		super.tick();
		if (level.isClientSide && level.getGameTime() % 40 == 0 && getMolten())
			lavaParticles(getX(), getY() + 1.3D, getZ(), random);

		if (!level.isClientSide) {
			if (getMolten() && getMoltenTimer() < 50)
				setMoltenTimer(getMoltenTimer() + 1);

			if (!getMolten() && getMoltenTimer() > 0)
				setMoltenTimer(getMoltenTimer() - 1);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void lavaParticles(double x, double y, double z, Random rand) {
		ClientParticles.spawnCustomParticle("lava", x, y, z, 0F, 0F, 0F);
	}

	public void changeParticles(Entity entity, float x, float y, float z, byte type) {
		Iterator<? extends Player> players = entity.level.players().iterator();
		while (players.hasNext()) {
			Player playersNear = players.next();
			if ((playersNear).distanceToSqr(entity) < 1024.0D) {
				MagmaMonsters.NETWORK_WRAPPER.sendTo(new QuenchMessage(x, y, z, type), ((ServerPlayer) playersNear).connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
			}
		}
	}

	@Override
	  public void aiStep() {
		super.aiStep();
		if (!level.isClientSide) {
			if (isInWaterOrRain() && !isInLava() && getMolten()) {
		        level.playSound(null, getX(), getY(), getZ(), SoundEvents.LAVA_EXTINGUISH, SoundSource.HOSTILE, 1F, 2.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
		        changeParticles(this, (float)getX(), (float)getY() + 0.9F, (float)getZ(), (byte) 0);
				setMolten(false);
				getAttribute(Attributes.ARMOR).setBaseValue(10D);
			}

			if (isInLava() && !getMolten()) {
		        level.playSound(null, getX(), getY(), getZ(), SoundEvents.FIRECHARGE_USE, SoundSource.HOSTILE, 1F, 0.6F + (level.random.nextFloat() - level.random.nextFloat()) * 0.8F);
		        changeParticles(this, (float)getX(), (float)getY() + 0.9F, (float)getZ(), (byte) 1);
				setMolten(true);
				getAttribute(Attributes.ARMOR).setBaseValue(0D);
			}

			if (random.nextInt(40) == 0 && Config.MAGMA_MONSTER_BLOCK_FIRE.get() && getMolten()) {
				int i = Mth.floor(getX());
				int j = Mth.floor(getY());
				int k = Mth.floor(getZ());
				for (int l = 0; l < 4; ++l) {
					i = Mth.floor(getX() + (double) ((float) (l % 2 * 2 - 1) * 0.25F));
					j = Mth.floor(getY());
					k = Mth.floor(getZ() + (double) ((float) (l / 2 % 2 * 2 - 1) * 0.25F));
					BlockPos blockpos = new BlockPos(i, j, k);
					BlockState blockstate = BaseFireBlock.getState(level, blockpos);
					if (level.getBlockState(blockpos).getMaterial() == Material.AIR && BaseFireBlock.canBePlacedAt(level, blockpos, Direction.DOWN))
						level.setBlock(blockpos, blockstate, 11);
				}
			}
		}
	}

	@Override
	public boolean doHurtTarget(Entity entity) {
		if (hasLineOfSight(entity)) {
			int modifier =  !getMolten() ? 2 : 1;
			boolean hasHitTarget = entity.hurt(DamageSource.mobAttack(this), (float) ((int) this.getAttribute(Attributes.ATTACK_DAMAGE).getBaseValue() * modifier));
			if (hasHitTarget) {
				if (entity instanceof LivingEntity && !getMolten()) {
					int duration = Config.MAGMA_MONSTER_SLOWNESS_EFFECT_DURATION.get();
					if (duration > 0)
						((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, duration * 20, 0));
				}
			}
			return hasHitTarget;
		}
		return false;
	}

	@Override
	public boolean hurt(DamageSource source, float damage) {
		if (source instanceof IndirectEntityDamageSource && !getMolten()) {
			level.playSound((Player) null, getX(), getY(), getZ(), SoundEvents.STONE_BUTTON_CLICK_OFF, SoundSource.HOSTILE, 2.5F, 3F);
			return false;
		}
		return super.hurt(source, damage);
	}

	static class TargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
		public TargetGoal(EntityMagmaMonster magma_monster, Class<T> classTarget) {
			super(magma_monster, classTarget, true);
		}
	}

	static class AttackGoal extends MeleeAttackGoal {
		public AttackGoal(EntityMagmaMonster magma_monster) {
			super(magma_monster, 1D, false);
		}

		@Override
		protected double getAttackReachSqr(LivingEntity attackTarget) {
			return (double) (4.0F + attackTarget.getBbWidth());
		}
	}

	static class AIFireballAttack extends Goal {
		private final EntityMagmaMonster magma_monster;
		private int attackStep;
		private int attackTime;

		public AIFireballAttack(EntityMagmaMonster magma_monsterIn) {
			magma_monster = magma_monsterIn;
			setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
		}

		@Override
		public boolean canUse() {
			LivingEntity livingentity = magma_monster.getTarget();
			return livingentity != null && livingentity.isAlive() && magma_monster.getMolten();
		}

		@Override
		public void start() {
			attackStep = 0;
		}

		@Override
		 public void tick() {
			--attackTime;
			LivingEntity livingentity = magma_monster.getTarget();
			double d0 = magma_monster.distanceToSqr(livingentity);

			if (d0 < 4.0D) {
				if (attackTime <= 0) {
					attackTime = 20;
					magma_monster.doHurtTarget(livingentity);
				}

				magma_monster.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
			} else if (d0 < 256.0D && magma_monster.getMolten()) {
				double d1 = livingentity.getX() - magma_monster.getX();
				double d2 = livingentity.getBoundingBox().minY + (double) (livingentity.getBbHeight() / 2.0F) - (magma_monster.getY() + (double) (magma_monster.getBbHeight() / 2.0F));
				double d3 = livingentity.getZ() - magma_monster.getZ();

				if (attackTime <= 0) {
					++attackStep;

					if (attackStep == 1) {
						attackTime = 60;
					} else if (attackStep <= 4) {
						attackTime = 6;
					} else {
						attackTime = 100;
						attackStep = 0;
					}

					if (attackStep > 1) {
						double f = Math.sqrt(Math.sqrt(d0)) * 0.5D;
						magma_monster.level.levelEvent((Player) null, 1018, magma_monster.blockPosition(), 0);

						for (int i = 0; i < 1; ++i) {
							SmallFireball smallfireballentity = new SmallFireball(magma_monster.level, magma_monster, d1 + magma_monster.getRandom().nextGaussian() * (double) f, d2, d3 + magma_monster.getRandom().nextGaussian() * (double) f);
							smallfireballentity.setPos(smallfireballentity.getX(), magma_monster.getY() + (double) (magma_monster.getBbHeight() / 2.0F) + 0.5D, smallfireballentity.getZ());
							magma_monster.level.addFreshEntity(smallfireballentity);
						}
					}
				}

				magma_monster.getLookControl().setLookAt(livingentity, 10.0F, 10.0F);
				magma_monster.getNavigation().isDone();
				magma_monster.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 1.0D);
			}

			super.tick();
		}
	}
}