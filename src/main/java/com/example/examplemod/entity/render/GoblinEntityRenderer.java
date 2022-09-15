package com.example.examplemod.entity.render;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.custom.GoblinEntity;
import com.example.examplemod.entity.custom.MarmotaEntity;
import com.example.examplemod.entity.model.GoblinEntityModel;
import com.example.examplemod.entity.model.MarmotaEntityModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GoblinEntityRenderer extends MobRenderer<GoblinEntity, GoblinEntityModel<GoblinEntity>>{
	protected static final ResourceLocation TEXTURE =
			new ResourceLocation(ExampleMod.MOD_ID, "textures/entity/goblin_texture.png");
	
	public GoblinEntityRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GoblinEntityModel<>(), 0.5f);
		this.shadowRadius = 0.5f;
	}
	
	@Override
	public ResourceLocation getTextureLocation(GoblinEntity p_110775_1_) {
		return TEXTURE;
	}
}
