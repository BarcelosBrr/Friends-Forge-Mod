package com.example.examplemod.entity;

import java.util.function.Supplier;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.custom.GoblinEntity;
import com.example.examplemod.entity.custom.MarmotaEntity;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
			DeferredRegister.create(ForgeRegistries.ENTITIES, ExampleMod.MOD_ID);
	
	 public static final RegistryObject<EntityType<MarmotaEntity>> MARMOTA =
	            ENTITY_TYPES.register("marmota",
	                  () -> EntityType.Builder.of(MarmotaEntity::new,EntityClassification.CREATURE)
	                  .sized(0.8f, 0.6f)
                      .build(new ResourceLocation(ExampleMod.MOD_ID, "marmota_texture").toString()));
	 
	 public static final RegistryObject<EntityType<GoblinEntity>> GOBLIN =
			 ENTITY_TYPES.register("goblin", () ->
					 EntityType.Builder.of(GoblinEntity::new, EntityClassification.MONSTER)
					 .sized(0.8f,0.8f)
					 .build(new ResourceLocation(ExampleMod.MOD_ID, "goblin_texture").toString()));
	 
	 
	 
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
}
