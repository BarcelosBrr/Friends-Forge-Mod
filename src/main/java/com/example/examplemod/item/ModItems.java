package com.example.examplemod.item;

import javax.swing.text.TabableView;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.ModEntityTypes;
import com.example.examplemod.item.custom.ModSpawnEgg;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	public static final DeferredRegister<Item> ITEMS =
			DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);
	
	public static final RegistryObject<Item> SUPER_POTATO = ITEMS.register("superpotato",
			() -> new Item(new Item.Properties().food(new Food.Builder().nutrition(8)
					.alwaysEat().saturationMod(15.2f).effect(() ->
					new EffectInstance(Effects.HEAL, 940), 1).effect(() -> (
					new EffectInstance(Effects.ABSORPTION, 1200)), 1).effect(() -> (
					new EffectInstance(Effects.HEALTH_BOOST, 1000)), 1).effect(() -> (
					new EffectInstance(Effects.DIG_SPEED, 600)), 0.6f).effect(() -> (
					new EffectInstance(Effects.DAMAGE_BOOST, 800)), 0.4f).effect(() -> (
					new EffectInstance(Effects.DAMAGE_RESISTANCE, 960)), 0.9f).effect(() -> (
					new EffectInstance(Effects.FIRE_RESISTANCE, 760)), 1f).effect(() -> (
					new EffectInstance(Effects.MOVEMENT_SPEED, 670)), 0.4f).build()).tab(ItemGroup.TAB_FOOD)));
	
	public static final RegistryObject<Item> MARMOTA_SPAWN_EGG = ITEMS.register("marmota_spawn_egg", 
			() -> new ModSpawnEgg(ModEntityTypes.MARMOTA, 0xa86250, 0x3d210b, new 
					Item.Properties().stacksTo(1).tab(ItemGroup.TAB_MISC)));
	
	public static final RegistryObject<Item> GOBLIN_SPAWN_EGG = ITEMS.register("goblin_spawn_egg", 
			() -> new ModSpawnEgg(ModEntityTypes.GOBLIN, 0x68ff93, 0x3d8c61, new 
					Item.Properties().stacksTo(1).tab(ItemGroup.TAB_MISC)));
	
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
