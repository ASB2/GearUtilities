package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelItemReciever extends ModelBase {

    ModelRenderer Bottom;
    ModelRenderer LevelTwo;
    ModelRenderer LevelThree;
    ModelRenderer Level4;

    public ModelItemReciever() {

        textureWidth = 128;
        textureHeight = 64;

        Bottom = new ModelRenderer(this, 0, 0);
        Bottom.addBox(-7F, 0F, -7F, 14, 1, 14);
        Bottom.setRotationPoint(0F, 23F, 0F);
        Bottom.setTextureSize(64, 32);
        Bottom.mirror = true;
        setRotation(Bottom, 0F, 0F, 0F);
        LevelTwo = new ModelRenderer(this, 0, 17);
        LevelTwo.addBox(-6F, 0F, -6F, 12, 2, 12);
        LevelTwo.setRotationPoint(0F, 21F, 0F);
        LevelTwo.setTextureSize(64, 32);
        LevelTwo.mirror = true;
        setRotation(LevelTwo, 0F, 0F, 0F);
        LevelThree = new ModelRenderer(this, 0, 33);
        LevelThree.addBox(-4F, 0F, -4F, 8, 2, 8);
        LevelThree.setRotationPoint(0F, 19F, 0F);
        LevelThree.setTextureSize(64, 32);
        LevelThree.mirror = true;
        setRotation(LevelThree, 0F, 0F, 0F);
        Level4 = new ModelRenderer(this, 0, 45);
        Level4.addBox(-2F, 0F, -2F, 4, 4, 4);
        Level4.setRotationPoint(0F, 13F, 0F);
        Level4.setTextureSize(128, 64);
        Level4.mirror = true;
        setRotation(Level4, 0F, 0F, 0F);
    }

    public void renderAll() {

        Bottom.render(0.0625F);
        LevelTwo.render(0.0625F);
        LevelThree.render(0.0625F);
        Level4.render(0.0625F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Bottom.render(f5);
        LevelTwo.render(f5);
        LevelThree.render(f5);
        Level4.render(f5);
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

}
