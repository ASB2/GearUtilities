package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelConduitInterface extends ModelBase {

    ModelRenderer Base;
    ModelRenderer Level2;
    ModelRenderer Center;

    public ModelConduitInterface() {

        textureWidth = 64;
        textureHeight = 32;

        Base = new ModelRenderer(this, 0, 0);
        Base.addBox(-8F, 0F, -8F, 16, 1, 16);
        Base.setRotationPoint(0F, 23F, 0F);
        Base.setTextureSize(64, 32);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Level2 = new ModelRenderer(this, 0, 18);
        Level2.addBox(-5F, 0F, -5F, 10, 4, 10);
        Level2.setRotationPoint(0F, 19F, 0F);
        Level2.setTextureSize(64, 32);
        Level2.mirror = true;
        setRotation(Level2, 0F, 0F, 0F);
        Center = new ModelRenderer(this, 41, 18);
        Center.addBox(-2F, -2F, -2F, 4, 4, 4);
        Center.setRotationPoint(0F, 16F, 0F);
        Center.setTextureSize(64, 32);
        Center.mirror = true;
        setRotation(Center, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Base.render(f5);
        Level2.render(f5);
        Center.render(f5);
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

        Base.render(0.0625F);
        Level2.render(0.0625F);
        Center.render(0.0625F);
    }
}
