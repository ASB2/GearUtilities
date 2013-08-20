package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModeLaser2 extends ModelBase {

    ModelRenderer Bottom;
    ModelRenderer Top;
    ModelRenderer Right;
    ModelRenderer Left;
    ModelRenderer Back;
    ModelRenderer LeftHandle;
    ModelRenderer RightHandle;
    ModelRenderer CenterLens;
    ModelRenderer TopHandle;
    ModelRenderer BottomHandle;
    ModelRenderer LensFocus;

    public ModeLaser2() {

        textureWidth = 128;
        textureHeight = 64;

        Bottom = new ModelRenderer(this, 0, 0);
        Bottom.addBox(-8F, 0F, -8F, 16, 1, 16);
        Bottom.setRotationPoint(0F, 23F, 0F);
        Bottom.setTextureSize(128, 64);
        Bottom.mirror = true;
        setRotation(Bottom, 0F, 0F, 0F);
        Top = new ModelRenderer(this, 0, 0);
        Top.addBox(-8F, 0F, -8F, 16, 1, 16);
        Top.setRotationPoint(0F, 8F, 0F);
        Top.setTextureSize(128, 64);
        Top.mirror = true;
        setRotation(Top, 0F, 0F, 0F);
        Right = new ModelRenderer(this, 0, 18);
        Right.addBox(0F, -7F, -8F, 1, 14, 16);
        Right.setRotationPoint(-8F, 16F, 0F);
        Right.setTextureSize(128, 64);
        Right.mirror = true;
        setRotation(Right, 0F, 0F, 0F);
        Left = new ModelRenderer(this, 0, 18);
        Left.addBox(0F, -7F, -8F, 1, 14, 16);
        Left.setRotationPoint(7F, 16F, 0F);
        Left.setTextureSize(128, 64);
        Left.mirror = true;
        setRotation(Left, 0F, 0F, 0F);
        Back = new ModelRenderer(this, 0, 49);
        Back.addBox(-7F, -7F, 0F, 14, 14, 1);
        Back.setRotationPoint(0F, 16F, 7F);
        Back.setTextureSize(128, 64);
        Back.mirror = true;
        setRotation(Back, 0F, 0F, 0F);
        LeftHandle = new ModelRenderer(this, 65, 0);
        LeftHandle.addBox(-2F, -1F, -2F, 4, 2, 2);
        LeftHandle.setRotationPoint(5F, 16F, -6F);
        LeftHandle.setTextureSize(128, 64);
        LeftHandle.mirror = true;
        setRotation(LeftHandle, 0F, 0F, 0F);
        RightHandle = new ModelRenderer(this, 65, 0);
        RightHandle.addBox(-2F, -1F, -2F, 4, 2, 2);
        RightHandle.setRotationPoint(-5F, 16F, -6F);
        RightHandle.setTextureSize(128, 64);
        RightHandle.mirror = true;
        setRotation(RightHandle, 0F, 0F, 0F);
        CenterLens = new ModelRenderer(this, 35, 18);
        CenterLens.addBox(-2F, -2F, 0F, 4, 4, 11);
        CenterLens.setRotationPoint(0F, 16F, -4F);
        CenterLens.setTextureSize(128, 64);
        CenterLens.mirror = true;
        setRotation(CenterLens, 0F, 0F, 0F);
        TopHandle = new ModelRenderer(this, 65, 5);
        TopHandle.addBox(-1F, -2F, -1F, 2, 4, 2);
        TopHandle.setRotationPoint(0F, 11F, -7F);
        TopHandle.setTextureSize(128, 64);
        TopHandle.mirror = true;
        setRotation(TopHandle, 0F, 0F, 0F);
        BottomHandle = new ModelRenderer(this, 65, 5);
        BottomHandle.addBox(-1F, -2F, -1F, 2, 4, 2);
        BottomHandle.setRotationPoint(0F, 21F, -7F);
        BottomHandle.setTextureSize(128, 64);
        BottomHandle.mirror = true;
        setRotation(BottomHandle, 0F, 0F, 0F);
        LensFocus = new ModelRenderer(this, 35, 34);
        LensFocus.addBox(-3F, -3F, 0F, 6, 6, 1);
        LensFocus.setRotationPoint(0F, 16F, -8F);
        LensFocus.setTextureSize(128, 64);
        LensFocus.mirror = true;
        setRotation(LensFocus, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3,
            float f4, float f5) {

        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Bottom.render(f5);
        Top.render(f5);
        Right.render(f5);
        Left.render(f5);
        Back.render(f5);
        LeftHandle.render(f5);
        RightHandle.render(f5);
        CenterLens.render(f5);
        TopHandle.render(f5);
        BottomHandle.render(f5);
        LensFocus.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {

        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3,
            float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

    }

    public void renderLaser() {

        Bottom.render(0.0625F);
        Top.render(0.0625F);
        Right.render(0.0625F);
        Left.render(0.0625F);
        Back.render(0.0625F);
        LeftHandle.render(0.0625F);
        RightHandle.render(0.0625F);
        CenterLens.render(0.0625F);
        TopHandle.render(0.0625F);
        BottomHandle.render(0.0625F);
    }

    public void renderFocus() {

        LensFocus.render(0.0625F);
    }
}
