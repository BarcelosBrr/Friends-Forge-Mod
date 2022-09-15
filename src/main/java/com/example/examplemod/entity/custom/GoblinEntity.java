package com.example.examplemod.entity.custom;

import java.util.Random;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveThroughVillageGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class GoblinEntity extends ZombieEntity{

	public GoblinEntity(EntityType<? extends ZombieEntity> p_i48549_1_, World p_i48549_2_) {
		super(p_i48549_1_, p_i48549_2_);
	}
	public static AttributeModifierMap setAttributes() {
		return CreatureEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 25.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.3F)
				.add(Attributes.ATTACK_DAMAGE, 3.5F)
				.add(Attributes.ATTACK_KNOCKBACK, 1F)
				.add(Attributes.FOLLOW_RANGE, 30.0D)
				.add(Attributes.ARMOR, 1.5D)
				.add(Attributes.SPAWN_REINFORCEMENTS_CHANCE).build();
	}
	@Override
	protected void registerGoals() {
	  super.registerGoals();
	   this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	   this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	   this.addBehaviourGoals();
    }
	
	 protected void addBehaviourGoals() {
	      this.goalSelector.addGoal(2, new ZombieAttackGoal(this, 1.0D, false));
	      this.goalSelector.addGoal(6, new MoveThroughVillageGoal(this, 1.0D, true, 4, this::canBreakDoors));
	      this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(ZombifiedPiglinEntity.class));
	      this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
	      this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.BABY_ON_LAND_SELECTOR));  
	 }
	 public boolean canBreakDoors() {
		 return false;
	 }
	 
	 private double getRandomArbitrary(int min, int max) {
		return Math.random() * (max - min) + min;
	 }
	 
	 public void addAdditionalSaveData(CompoundNBT p_213281_1_) {
		 super.addAdditionalSaveData(p_213281_1_);
	     p_213281_1_.putBoolean("IsBaby", this.isBaby());
	 }
	 
	 public void readAdditionalSaveData(CompoundNBT p_70037_1_) {
	      super.readAdditionalSaveData(p_70037_1_);
	      this.setBaby(p_70037_1_.getBoolean("IsBaby"));
	 }
	 
	 public void dropCustomDeathLoot(DamageSource p_213333_1_, int p_213333_2_, boolean p_213333_3_) {
		 super.dropCustomDeathLoot(p_213333_1_, p_213333_2_, p_213333_3_);
				this.setItemSlotAndDropWhenKilled(EquipmentSlotType.CHEST, new ItemStack(Items.DIAMOND, 1));
	 }
}
