package com.example.examplemod.item.custom;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class ModSpawnEgg extends SpawnEggItem{
	protected static final List<ModSpawnEgg> UNNADED_EGGS = new ArrayList<>();
	private final Lazy<? extends EntityType<?>> entityTypeSupplier;
	
	public ModSpawnEgg(final RegistryObject<? extends EntityType<?>> entityTypeSupplier,
			int primaryColorIn, int secondaryColorIn, Properties builder) {
		super(null,primaryColorIn,secondaryColorIn,builder);
		this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
		UNNADED_EGGS.add(this);
	}
	
	public static void initSpawnEggs() {
		final Map<EntityType<?>, SpawnEggItem> EGGS = ObfuscationReflectionHelper.getPrivateValue(
				SpawnEggItem.class, null, "field_195987");
		
		/*DefaultDispenseItemBehavior dispenseItemBehaviour = new DefaultDispenseItemBehavior() {
			protected ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
				Direction direction = source.getBlockState().get(DispenserBlock.FACING);
			}
		};*/
		
		
		for(final SpawnEggItem spawnEgg : UNNADED_EGGS) {
			EGGS.put(spawnEgg.getType(null), spawnEgg);
		}
		
		UNNADED_EGGS.clear();
	}
	
	@Override
	public EntityType<?> getType(CompoundNBT nbt) {
		return this.entityTypeSupplier.get();
	}
}
