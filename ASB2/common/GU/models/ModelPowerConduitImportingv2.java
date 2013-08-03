package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPowerConduitImportingv2 extends ModelBase {
    
    ModelRenderer Center_Square;
    ModelRenderer Ten_Top;
    ModelRenderer TwoTop;
    ModelRenderer FourTop;
    ModelRenderer SixTop;
    ModelRenderer Eight_Top;
    ModelRenderer Ten_Bottom;
    ModelRenderer Eight_Bottom;
    ModelRenderer SixBottom;
    ModelRenderer FourBottom;
    ModelRenderer TwoBottom;
    ModelRenderer TwoLeft;
    ModelRenderer FourLeft;
    ModelRenderer SixLeft;
    ModelRenderer EightLeft;
    ModelRenderer TenLeft;
    ModelRenderer TwoRight;
    ModelRenderer FourRight;
    ModelRenderer SixRight;
    ModelRenderer EightRight;
    ModelRenderer TenRight;
    ModelRenderer TwoFront;
    ModelRenderer FourFront;
    ModelRenderer SixFront;
    ModelRenderer EightFront;
    ModelRenderer TenFront;
    ModelRenderer TwoBack;
    ModelRenderer FourBack;
    ModelRenderer SixBack;
    ModelRenderer EightBack;
    ModelRenderer TenBack;

    public ModelPowerConduitImportingv2()
    {
        textureWidth = 128;
        textureHeight = 64;

        Center_Square = new ModelRenderer(this, 0, 56);
        Center_Square.addBox(-2F, -2F, -2F, 4, 4, 4);
        Center_Square.setRotationPoint(0F, 16F, 0F);
        Center_Square.setTextureSize(64, 32);
        Center_Square.mirror = true;
        setRotation(Center_Square, 0F, 0F, 0F);
        Ten_Top = new ModelRenderer(this, 0, 44);
        Ten_Top.addBox(-5F, 0F, -5F, 10, 2, 10);
        Ten_Top.setRotationPoint(0F, 8F, 0F);
        Ten_Top.setTextureSize(64, 32);
        Ten_Top.mirror = true;
        setRotation(Ten_Top, 0F, 0F, 0F);
        TwoTop = new ModelRenderer(this, 0, 41);
        TwoTop.addBox(-1F, 0F, -1F, 2, 1, 2);
        TwoTop.setRotationPoint(0F, 13F, 0F);
        TwoTop.setTextureSize(64, 32);
        TwoTop.mirror = true;
        setRotation(TwoTop, 0F, 0F, 0F);
        FourTop = new ModelRenderer(this, 0, 36);
        FourTop.addBox(-2F, 0F, -2F, 4, 1, 4);
        FourTop.setRotationPoint(0F, 12F, 0F);
        FourTop.setTextureSize(64, 32);
        FourTop.mirror = true;
        setRotation(FourTop, 0F, 0F, 0F);
        SixTop = new ModelRenderer(this, 0, 29);
        SixTop.addBox(-3F, 0F, -3F, 6, 1, 6);
        SixTop.setRotationPoint(0F, 11F, 0F);
        SixTop.setTextureSize(64, 32);
        SixTop.mirror = true;
        setRotation(SixTop, 0F, 0F, 0F);
        Eight_Top = new ModelRenderer(this, 0, 20);
        Eight_Top.addBox(-4F, 0F, -4F, 8, 1, 8);
        Eight_Top.setRotationPoint(0F, 10F, 0F);
        Eight_Top.setTextureSize(64, 32);
        Eight_Top.mirror = true;
        setRotation(Eight_Top, 0F, 0F, 0F);
        Ten_Bottom = new ModelRenderer(this, 0, 44);
        Ten_Bottom.addBox(-5F, 0F, -5F, 10, 2, 10);
        Ten_Bottom.setRotationPoint(0F, 22F, 0F);
        Ten_Bottom.setTextureSize(64, 32);
        Ten_Bottom.mirror = true;
        setRotation(Ten_Bottom, 0F, 0F, 0F);
        Eight_Bottom = new ModelRenderer(this, 0, 20);
        Eight_Bottom.addBox(-4F, 0F, -4F, 8, 1, 8);
        Eight_Bottom.setRotationPoint(0F, 21F, 0F);
        Eight_Bottom.setTextureSize(64, 32);
        Eight_Bottom.mirror = true;
        setRotation(Eight_Bottom, 0F, 0F, 0F);
        SixBottom = new ModelRenderer(this, 0, 29);
        SixBottom.addBox(-3F, 0F, -3F, 6, 1, 6);
        SixBottom.setRotationPoint(0F, 20F, 0F);
        SixBottom.setTextureSize(64, 32);
        SixBottom.mirror = true;
        setRotation(SixBottom, 0F, 0F, 0F);
        FourBottom = new ModelRenderer(this, 0, 36);
        FourBottom.addBox(-2F, 0F, -2F, 4, 1, 4);
        FourBottom.setRotationPoint(0F, 19F, 0F);
        FourBottom.setTextureSize(64, 32);
        FourBottom.mirror = true;
        setRotation(FourBottom, 0F, 0F, 0F);
        TwoBottom = new ModelRenderer(this, 0, 41);
        TwoBottom.addBox(-1F, 0F, -1F, 2, 1, 2);
        TwoBottom.setRotationPoint(0F, 18F, 0F);
        TwoBottom.setTextureSize(64, 32);
        TwoBottom.mirror = true;
        setRotation(TwoBottom, 0F, 0F, 0F);
        TwoLeft = new ModelRenderer(this, 41, 40);
        TwoLeft.addBox(0F, -1F, -1F, 1, 2, 2);
        TwoLeft.setRotationPoint(-3F, 16F, 0F);
        TwoLeft.setTextureSize(128, 64);
        TwoLeft.mirror = true;
        setRotation(TwoLeft, 0F, 0F, 0F);
        FourLeft = new ModelRenderer(this, 41, 32);
        FourLeft.addBox(0F, -2F, -2F, 1, 4, 4);
        FourLeft.setRotationPoint(-4F, 16F, 0F);
        FourLeft.setTextureSize(128, 64);
        FourLeft.mirror = true;
        setRotation(FourLeft, 0F, 0F, 0F);
        SixLeft = new ModelRenderer(this, 41, 20);
        SixLeft.addBox(0F, -3F, -3F, 1, 6, 6);
        SixLeft.setRotationPoint(-5F, 16F, 0F);
        SixLeft.setTextureSize(128, 64);
        SixLeft.mirror = true;
        setRotation(SixLeft, 0F, 0F, 0F);
        EightLeft = new ModelRenderer(this, 41, 4);
        EightLeft.addBox(0F, -4F, -4F, 1, 8, 8);
        EightLeft.setRotationPoint(-6F, 16F, 0F);
        EightLeft.setTextureSize(128, 64);
        EightLeft.mirror = true;
        setRotation(EightLeft, 0F, 0F, 0F);
        TenLeft = new ModelRenderer(this, 41, 44);
        TenLeft.addBox(0F, -5F, -5F, 2, 10, 10);
        TenLeft.setRotationPoint(-8F, 16F, 0F);
        TenLeft.setTextureSize(128, 64);
        TenLeft.mirror = true;
        setRotation(TenLeft, 0F, 0F, 0F);
        TwoRight = new ModelRenderer(this, 41, 40);
        TwoRight.addBox(0F, -1F, -1F, 1, 2, 2);
        TwoRight.setRotationPoint(2F, 16F, 0F);
        TwoRight.setTextureSize(128, 64);
        TwoRight.mirror = true;
        setRotation(TwoRight, 0F, 0F, 0F);
        FourRight = new ModelRenderer(this, 41, 32);
        FourRight.addBox(0F, -2F, -2F, 1, 4, 4);
        FourRight.setRotationPoint(3F, 16F, 0F);
        FourRight.setTextureSize(128, 64);
        FourRight.mirror = true;
        setRotation(FourRight, 0F, 0F, 0F);
        SixRight = new ModelRenderer(this, 41, 20);
        SixRight.addBox(0F, -3F, -3F, 1, 6, 6);
        SixRight.setRotationPoint(4F, 16F, 0F);
        SixRight.setTextureSize(128, 64);
        SixRight.mirror = true;
        setRotation(SixRight, 0F, 0F, 0F);
        EightRight = new ModelRenderer(this, 41, 4);
        EightRight.addBox(0F, -4F, -4F, 1, 8, 8);
        EightRight.setRotationPoint(5F, 16F, 0F);
        EightRight.setTextureSize(128, 64);
        EightRight.mirror = true;
        setRotation(EightRight, 0F, 0F, 0F);
        TenRight = new ModelRenderer(this, 41, 44);
        TenRight.addBox(6F, -5F, -5F, 2, 10, 10);
        TenRight.setRotationPoint(0F, 16F, 0F);
        TenRight.setTextureSize(128, 64);
        TenRight.mirror = true;
        setRotation(TenRight, 0F, 0F, 0F);
        TwoFront = new ModelRenderer(this, 66, 61);
        TwoFront.addBox(-1F, -1F, 0F, 2, 2, 1);
        TwoFront.setRotationPoint(0F, 16F, -3F);
        TwoFront.setTextureSize(128, 64);
        TwoFront.mirror = true;
        setRotation(TwoFront, 0F, 0F, 0F);
        FourFront = new ModelRenderer(this, 66, 56);
        FourFront.addBox(-2F, -2F, 0F, 4, 4, 1);
        FourFront.setRotationPoint(0F, 16F, -4F);
        FourFront.setTextureSize(128, 64);
        FourFront.mirror = true;
        setRotation(FourFront, 0F, 0F, 0F);
        SixFront = new ModelRenderer(this, 66, 49);
        SixFront.addBox(-3F, -3F, 0F, 6, 6, 1);
        SixFront.setRotationPoint(0F, 16F, -5F);
        SixFront.setTextureSize(128, 64);
        SixFront.mirror = true;
        setRotation(SixFront, 0F, 0F, 0F);
        EightFront = new ModelRenderer(this, 66, 40);
        EightFront.addBox(-4F, -4F, 0F, 8, 8, 1);
        EightFront.setRotationPoint(0F, 16F, -6F);
        EightFront.setTextureSize(128, 64);
        EightFront.mirror = true;
        setRotation(EightFront, 0F, 0F, 0F);
        TenFront = new ModelRenderer(this, 66, 28);
        TenFront.addBox(-5F, -5F, -7F, 10, 10, 2);
        TenFront.setRotationPoint(0F, 16F, -1F);
        TenFront.setTextureSize(128, 64);
        TenFront.mirror = true;
        setRotation(TenFront, 0F, 0F, 0F);
        TwoBack = new ModelRenderer(this, 66, 61);
        TwoBack.addBox(-1F, -1F, 0F, 2, 2, 1);
        TwoBack.setRotationPoint(0F, 16F, 2F);
        TwoBack.setTextureSize(128, 64);
        TwoBack.mirror = true;
        setRotation(TwoBack, 0F, 0F, 0F);
        FourBack = new ModelRenderer(this, 66, 56);
        FourBack.addBox(-2F, -2F, 0F, 4, 4, 1);
        FourBack.setRotationPoint(0F, 16F, 3F);
        FourBack.setTextureSize(128, 64);
        FourBack.mirror = true;
        setRotation(FourBack, 0F, 0F, 0F);
        SixBack = new ModelRenderer(this, 66, 49);
        SixBack.addBox(-3F, -3F, 0F, 6, 6, 1);
        SixBack.setRotationPoint(0F, 16F, 4F);
        SixBack.setTextureSize(128, 64);
        SixBack.mirror = true;
        setRotation(SixBack, 0F, 0F, 0F);
        EightBack = new ModelRenderer(this, 66, 40);
        EightBack.addBox(-4F, -4F, 0F, 8, 8, 1);
        EightBack.setRotationPoint(0F, 16F, 5F);
        EightBack.setTextureSize(128, 64);
        EightBack.mirror = true;
        setRotation(EightBack, 0F, 0F, 0F);
        TenBack = new ModelRenderer(this, 66, 28);
        TenBack.addBox(-5F, -5F, 0F, 10, 10, 2);
        TenBack.setRotationPoint(0F, 16F, 6F);
        TenBack.setTextureSize(128, 64);
        TenBack.mirror = true;
        setRotation(TenBack, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Center_Square.render(f5);
        Ten_Top.render(f5);
        TwoTop.render(f5);
        FourTop.render(f5);
        SixTop.render(f5);
        Eight_Top.render(f5);
        Ten_Bottom.render(f5);
        Eight_Bottom.render(f5);
        SixBottom.render(f5);
        FourBottom.render(f5);
        TwoBottom.render(f5);
        TwoLeft.render(f5);
        FourLeft.render(f5);
        SixLeft.render(f5);
        EightLeft.render(f5);
        TenLeft.render(f5);
        TwoRight.render(f5);
        FourRight.render(f5);
        SixRight.render(f5);
        EightRight.render(f5);
        TenRight.render(f5);
        TwoFront.render(f5);
        FourFront.render(f5);
        SixFront.render(f5);
        EightFront.render(f5);
        TenFront.render(f5);
        TwoBack.render(f5);
        FourBack.render(f5);
        SixBack.render(f5);
        EightBack.render(f5);
        TenBack.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    }

    public void renderTop() {

        Ten_Top.render(0.0625F);
        TwoTop.render(0.0625F);
        FourTop.render(0.0625F);
        SixTop.render(0.0625F);
        Eight_Top.render(0.0625F);
    }

    public void renderBottom() {
        
        Ten_Bottom.render(0.0625F);
        Eight_Bottom.render(0.0625F);
        SixBottom.render(0.0625F);
        FourBottom.render(0.0625F);
        TwoBottom.render(0.0625F);
    }

    public void renderLeft() {

        TwoLeft.render(0.0625F);
        FourLeft.render(0.0625F);
        SixLeft.render(0.0625F);
        EightLeft.render(0.0625F);
        TenLeft.render(0.0625F);
    }

    public void renderRight() {

        TwoRight.render(0.0625F);
        FourRight.render(0.0625F);
        SixRight.render(0.0625F);
        EightRight.render(0.0625F);
        TenRight.render(0.0625F);
    }

    public void renderFront() {

        TwoFront.render(0.0625F);
        FourFront.render(0.0625F);
        SixFront.render(0.0625F);
        EightFront.render(0.0625F);
        TenFront.render(0.0625F);
    }

    public void renderBack() {

        TwoBack.render(0.0625F);
        FourBack.render(0.0625F);
        SixBack.render(0.0625F);
        EightBack.render(0.0625F);
        TenBack.render(0.0625F);
    }

    public void renderAll(){

        Center_Square.render(0.0625F);
    }

}
