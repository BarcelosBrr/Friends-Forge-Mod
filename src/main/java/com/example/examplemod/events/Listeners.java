package com.example.examplemod.events;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.ModEntityTypes;
import com.example.examplemod.entity.custom.GoblinEntity;
import com.example.examplemod.entity.custom.MarmotaEntity;
import com.example.examplemod.item.custom.ModSpawnEgg;

import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Listeners {
	@SubscribeEvent
	public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
		event.put(ModEntityTypes.MARMOTA.get(), MarmotaEntity.setAttributes());
		event.put(ModEntityTypes.GOBLIN.get(), GoblinEntity.setAttributes());
	}
}
