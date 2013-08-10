package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTCEnergyFocus extends ModelBase 
{
    ModelRenderer Bottom;
    ModelRenderer SecondLevel;
    ModelRenderer ThirdLevel;
    ModelRenderer FourthLevel;
    ModelRenderer FifthLevel;
    ModelRenderer SixthLevel;
    ModelRenderer SeventhLevel;
    ModelRenderer EightLevel;
    ModelRenderer NinthLevel;

    public ModelTCEnergyFocus() {
        
        textureWidth = 128;
        textureHeight = 64;

        Bottom = new ModelRenderer(this, 0, 47);
        Bottom.addBox(-8F, 0F, -8F, 16, 1, 16);
        Bottom.setRotationPoint(0F, 23F, 0F);
        Bottom.setTextureSize(128, 64);
        Bottom.mirror = true;
        setRotation(Bottom, 0F, 0F, 0F);
        SecondLevel = new ModelRenderer(this, 0, 32);
        SecondLevel.addBox(-7F, 0F, -7F, 14, 1, 14);
        SecondLevel.setRotationPoint(0F, 22F, 0F);
        SecondLevel.setTextureSize(128, 64);
        SecondLevel.mirror = true;
        setRotation(SecondLevel, 0F, 0F, 0F);
        ThirdLevel = new ModelRenderer(this, 0, 19);
        ThirdLevel.addBox(-6F, 0F, -6F, 12, 1, 12);
        ThirdLevel.setRotationPoint(0F, 21F, 0F);
        ThirdLevel.setTextureSize(128, 64);
        ThirdLevel.mirror = true;
        setRotation(ThirdLevel, 0F, 0F, 0F);
        FourthLevel = new ModelRenderer(this, 0, 8);
        FourthLevel.addBox(-5F, 0F, -5F, 10, 1, 10);
        FourthLevel.setRotationPoint(0F, 20F, 0F);
        FourthLevel.setTextureSize(128, 64);
        FourthLevel.mirror = true;
        setRotation(FourthLevel, 0F, 0F, 0F);
        FifthLevel = new ModelRenderer(this, 66, 55);
        FifthLevel.addBox(-4F, 0F, -4F, 8, 1, 8);
        FifthLevel.setRotationPoint(0F, 19F, 0F);
        FifthLevel.setTextureSize(128, 64);
        FifthLevel.mirror = true;
        setRotation(FifthLevel, 0F, 0F, 0F);
        SixthLevel = new ModelRenderer(this, 66, 47);
        SixthLevel.addBox(-3F, 0F, -3F, 6, 1, 6);
        SixthLevel.setRotationPoint(0F, 18F, 0F);
        SixthLevel.setTextureSize(128, 64);
        SixthLevel.mirror = true;
        setRotation(SixthLevel, 0F, 0F, 0F);
        SeventhLevel = new ModelRenderer(this, 66, 41);
        SeventhLevel.addBox(-2F, 0F, -2F, 4, 1, 4);
        SeventhLevel.setRotationPoint(0F, 17F, 0F);
        SeventhLevel.setTextureSize(128, 64);
        SeventhLevel.mirror = true;
        setRotation(SeventhLevel, 0F, 0F, 0F);
        EightLevel = new ModelRenderer(this, 66, 37);
        EightLevel.addBox(-1F, 0F, -1F, 2, 1, 2);
        EightLevel.setRotationPoint(0F, 16F, 0F);
        EightLevel.setTextureSize(128, 64);
        EightLevel.mirror = true;
        setRotation(EightLevel, 0F, 0F, 0F);
        NinthLevel = new ModelRenderer(this, 66, 34);
        NinthLevel.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
        NinthLevel.setRotationPoint(0F, 15F, 0F);
        NinthLevel.setTextureSize(128, 64);
        NinthLevel.mirror = true;
        setRotation(NinthLevel, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Bottom.render(f5);
        SecondLevel.render(f5);
        ThirdLevel.render(f5);
        FourthLevel.render(f5);
        FifthLevel.render(f5);
        SixthLevel.render(f5);
        SeventhLevel.render(f5);
        EightLevel.render(f5);
        NinthLevel.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }

    public void renderAll() {

        Bottom.render(0.0625F);
        SecondLevel.render(0.0625F);
        ThirdLevel.render(0.0625F);
        FourthLevel.render(0.0625F);
        FifthLevel.render(0.0625F);
        SixthLevel.render(0.0625F);
        SeventhLevel.render(0.0625F);
        EightLevel.render(0.0625F);
        NinthLevel.render(0.0625F);
    }
}
