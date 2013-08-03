package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPowerConduitMovingv2 extends ModelBase {
    
    ModelRenderer Center_Square;
    ModelRenderer WireLeft;
    ModelRenderer WireBack;
    ModelRenderer WireRight;
    ModelRenderer WireFront;
    ModelRenderer WireTop;
    ModelRenderer WireBottom;

    public ModelPowerConduitMovingv2() {
        
        textureWidth = 128;
        textureHeight = 64;

        Center_Square = new ModelRenderer(this, 0, 17);
        Center_Square.addBox(-2F, -2F, -2F, 4, 4, 4);
        Center_Square.setRotationPoint(0F, 16F, 0F);
        Center_Square.setTextureSize(64, 32);
        Center_Square.mirror = true;
        setRotation(Center_Square, 0F, 0F, 0F);
        WireLeft = new ModelRenderer(this, 0, 32);
        WireLeft.addBox(0F, -2F, -2F, 12, 4, 4);
        WireLeft.setRotationPoint(-14F, 16F, 0F);
        WireLeft.setTextureSize(64, 32);
        WireLeft.mirror = true;
        setRotation(WireLeft, 0F, 0F, 0F);
        WireBack = new ModelRenderer(this, 17, 0);
        WireBack.addBox(-2F, -2F, 0F, 4, 4, 12);
        WireBack.setRotationPoint(0F, 16F, 2F);
        WireBack.setTextureSize(64, 32);
        WireBack.mirror = true;
        setRotation(WireBack, 0F, 0F, 0F);
        WireRight = new ModelRenderer(this, 0, 32);
        WireRight.addBox(0F, -2F, -2F, 12, 4, 4);
        WireRight.setRotationPoint(2F, 16F, 0F);
        WireRight.setTextureSize(64, 32);
        WireRight.mirror = true;
        setRotation(WireRight, 0F, 0F, 0F);
        WireFront = new ModelRenderer(this, 17, 0);
        WireFront.addBox(-2F, -2F, 0F, 4, 4, 12);
        WireFront.setRotationPoint(0F, 16F, -14F);
        WireFront.setTextureSize(64, 32);
        WireFront.mirror = true;
        setRotation(WireFront, 0F, 0F, 0F);
        WireTop = new ModelRenderer(this, 0, 0);
        WireTop.addBox(-2F, 0F, -2F, 4, 12, 4);
        WireTop.setRotationPoint(0F, 2F, 0F);
        WireTop.setTextureSize(64, 32);
        WireTop.mirror = true;
        setRotation(WireTop, 0F, 0F, 0F);
        WireBottom = new ModelRenderer(this, 0, 0);
        WireBottom.addBox(-2F, 0F, -2F, 4, 12, 4);
        WireBottom.setRotationPoint(0F, 18F, 0F);
        WireBottom.setTextureSize(64, 32);
        WireBottom.mirror = true;
        setRotation(WireBottom, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Center_Square.render(f5);
        WireLeft.render(f5);
        WireBack.render(f5);
        WireRight.render(f5);
        WireFront.render(f5);
        WireTop.render(f5);
        WireBottom.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    }

    public void renderTop() {

        WireTop.render(0.0625F);
    }

    public void renderBottom() {

        WireBottom.render(0.0625F);
    }

    public void renderLeft() {

        WireLeft.render(0.0625F);
    }

    public void renderRight() {

        WireRight.render(0.0625F);
    }

    public void renderFront() {

        WireFront.render(0.0625F);
    }

    public void renderBack() {

        WireBack.render(0.0625F);
    }
    
    public void renderAll() {

        Center_Square.render(0.0625F);
    }

}
