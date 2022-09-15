package com.example.examplemod.entity.model;

import com.example.examplemod.entity.custom.MarmotaEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MarmotaEntityModel <T extends MarmotaEntity> extends EntityModel<T>{
	private final ModelRenderer bb_main;
	private final ModelRenderer leg4_r1;
	
	public MarmotaEntityModel() {
		texWidth = 64;
		texHeight = 64;

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		

		leg4_r1 = new ModelRenderer(this);
		leg4_r1.setPos(0.0F, 0.0F, 0.0F);
		bb_main.addChild(leg4_r1);
		setRotationAngle(leg4_r1, 0.0F, -1.5708F, 0.0F);
		leg4_r1.texOffs(0, 0).addBox(5.0F, -2.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		leg4_r1.texOffs(0, 4).addBox(-2.0F, -2.0F, -6.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		leg4_r1.texOffs(18, 16).addBox(-2.0F, -2.0F, 4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		leg4_r1.texOffs(24, 18).addBox(4.0F, -2.0F, 4.0F, 2.0F, 2.0F, 2.0F, 0.0F, false);
		leg4_r1.texOffs(0, 16).addBox(-8.0F, -13.0F, -3.0F, 6.0F, 7.0F, 6.0F, 0.0F, false);
		leg4_r1.texOffs(0, 0).addBox(-3.0F, -8.0F, -5.0F, 10.0F, 6.0F, 10.0F, 0.0F, false);
	}
	
	@Override
	public void setupAnim(T p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_, float p_225597_5_,
			float p_225597_6_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
	
}
