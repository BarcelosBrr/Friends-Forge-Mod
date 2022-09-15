package com.example.examplemod.entity.custom;

import com.example.examplemod.entity.ModEntityTypes;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.brain.task.WalkRandomlyTask;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class MarmotaEntity extends PigEntity{
	private static final Ingredient FOOD_ITEMS = Ingredient.of(Items.SUNFLOWER, Items.ALLIUM,
			Items.CORNFLOWER, Items.OXEYE_DAISY, Items.DANDELION,Items.POPPY, Items.BLUE_ORCHID, 
			Items.AZURE_BLUET, Items.RED_TULIP,Items.ORANGE_TULIP, Items.PINK_TULIP,
			Items.WHITE_TULIP,Items.LILY_OF_THE_VALLEY,Items.GRASS);
	
	public MarmotaEntity(EntityType<? extends PigEntity> p_i50250_1_, World p_i50250_2_) {
		super(p_i50250_1_, p_i50250_2_);
		// TODO Auto-generated constructor stub
	}
	public static AttributeModifierMap setAttributes() {
		return CreatureEntity.createMobAttributes()
				.add(Attributes.MAX_HEALTH, 15.0D)
				.add(Attributes.MOVEMENT_SPEED, 0.35F)
				.add(Attributes.ATTACK_DAMAGE, 4F)
				.add(Attributes.ATTACK_KNOCKBACK, 1F).build();
	}
	
	 @Override
	 protected void registerGoals() {
		  super.registerGoals();
		  this.goalSelector.addGoal(0, new SwimGoal(this));
	      this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
	      this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
	      this.goalSelector.addGoal(4, new TemptGoal(this, 1.2D, false, FOOD_ITEMS));
	      this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
	      this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
	      this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 6.0F));
	      this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	   }

	   protected int getExperiencePoints(PlayerEntity player)
	   {
	       return 4 + this.level.random.nextInt(7);
	   }
	   
	   public float getSteeringSpeed() {
		   return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.24F;
	   }
}
