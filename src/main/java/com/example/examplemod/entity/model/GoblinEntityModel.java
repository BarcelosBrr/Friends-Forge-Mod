package com.example.examplemod.entity.model;

import com.example.examplemod.entity.custom.GoblinEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;

public class GoblinEntityModel <T extends GoblinEntity> extends EntityModel<T>{
	private final ModelRenderer bb_main;
	private final ModelRenderer footRight_r1;

	public GoblinEntityModel() {
		texWidth = 128;
		texHeight = 128;

		bb_main = new ModelRenderer(this);
		bb_main.setPos(0.0F, 24.0F, 0.0F);
		bb_main.texOffs(0, 0).addBox(-6.0F, -12.0F, -4.0F, 12.0F, 10.0F, 9.0F, 0.0F, false);
		bb_main.texOffs(0, 19).addBox(-5.0F, -10.0F, -4.5F, 10.0F, 7.0F, 10.0F, 0.0F, false);
		bb_main.texOffs(32, 28).addBox(-5.0F, -21.0F, -3.5F, 10.0F, 10.0F, 8.0F, 0.0F, false);
		bb_main.texOffs(0, 4).addBox(-4.0F, -24.0F, -1.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		bb_main.texOffs(4, 19).addBox(-3.0F, -23.0F, -1.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);
		bb_main.texOffs(0, 19).addBox(1.0F, -23.0F, -1.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);
		bb_main.texOffs(0, 0).addBox(2.0F, -24.0F, -1.0F, 2.0F, 4.0F, 0.0F, 0.0F, false);
		bb_main.texOffs(33, 0).addBox(5.0F, -11.0F, -8.0F, 2.0F, 2.0F, 7.0F, 0.0F, false);
		bb_main.texOffs(4, 6).addBox(2.75F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);
		bb_main.texOffs(4, 4).addBox(-4.75F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);
		bb_main.texOffs(20, 36).addBox(5.5F, -17.0F, -7.0F, 1.0F, 9.0F, 2.0F, 0.0F, false);
		bb_main.texOffs(0, 36).addBox(5.5F, -16.0F, -10.0F, 1.0F, 3.0F, 6.0F, 0.0F, false);

		footRight_r1 = new ModelRenderer(this);
		footRight_r1.setPos(0.0F, 0.0F, 0.0F);
		bb_main.addChild(footRight_r1);
		setRotationAngle(footRight_r1, 1.5708F, 0.0F, 0.0F);
		footRight_r1.texOffs(4, 0).addBox(-4.75F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);
		footRight_r1.texOffs(4, 2).addBox(2.75F, -2.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, false);
		footRight_r1.texOffs(30, 19).addBox(-7.0F, -2.0F, 4.0F, 2.0F, 2.0F, 7.0F, 0.0F, false);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bb_main.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
