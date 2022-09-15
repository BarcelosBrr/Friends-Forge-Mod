package com.example.examplemod.entity.render;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.entity.custom.MarmotaEntity;
import com.example.examplemod.entity.model.MarmotaEntityModel;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MarmotaEntityRenderer extends MobRenderer<MarmotaEntity, MarmotaEntityModel<MarmotaEntity>>{
	protected static final ResourceLocation TEXTURE =
			new ResourceLocation(ExampleMod.MOD_ID, "textures/entity/marmota_texture.png");
	
	public MarmotaEntityRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new MarmotaEntityModel<>(), 0.5f);
		this.shadowRadius = 0.5f;
	}

	@Override
	public ResourceLocation getTextureLocation(MarmotaEntity p_110775_1_) {
		return TEXTURE;
	}
}
