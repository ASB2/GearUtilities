package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnergySender extends ModelBase {

    ModelRenderer Base;
    ModelRenderer BackLeft_Post;
    ModelRenderer Front_Left_Post;
    ModelRenderer Front_Right_Post;
    ModelRenderer Back_Right_Post;
    ModelRenderer Center_Box;

    public ModelEnergySender() {

        textureWidth = 128;
        textureHeight = 64;

        Base = new ModelRenderer(this, 0, 0);
        Base.addBox(-8F, 0F, -8F, 16, 7, 16);
        Base.setRotationPoint(0F, 17F, 0F);
        Base.setTextureSize(128, 64);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        BackLeft_Post = new ModelRenderer(this, 0, 25);
        BackLeft_Post.addBox(-1F, 0F, -1F, 2, 16, 2);
        BackLeft_Post.setRotationPoint(-7F, 8F, 7F);
        BackLeft_Post.setTextureSize(128, 64);
        BackLeft_Post.mirror = true;
        setRotation(BackLeft_Post, 0F, 0F, 0F);
        Front_Left_Post = new ModelRenderer(this, 0, 25);
        Front_Left_Post.addBox(-1F, 0F, -1F, 2, 16, 2);
        Front_Left_Post.setRotationPoint(-7F, 8F, -7F);
        Front_Left_Post.setTextureSize(128, 64);
        Front_Left_Post.mirror = true;
        setRotation(Front_Left_Post, 0F, 0F, 0F);
        Front_Right_Post = new ModelRenderer(this, 0, 25);
        Front_Right_Post.addBox(-1F, 0F, -1F, 2, 16, 2);
        Front_Right_Post.setRotationPoint(7F, 8F, -7F);
        Front_Right_Post.setTextureSize(128, 64);
        Front_Right_Post.mirror = true;
        setRotation(Front_Right_Post, 0F, 0F, 0F);
        Back_Right_Post = new ModelRenderer(this, 0, 25);
        Back_Right_Post.addBox(-1F, 0F, -1F, 2, 16, 2);
        Back_Right_Post.setRotationPoint(7F, 8F, 7F);
        Back_Right_Post.setTextureSize(128, 64);
        Back_Right_Post.mirror = true;
        setRotation(Back_Right_Post, 0F, 0F, 0F);
        Center_Box = new ModelRenderer(this, 10, 25);
        Center_Box.addBox(-4F, 0F, -4F, 8, 6, 8);
        Center_Box.setRotationPoint(0F, 11F, 0F);
        Center_Box.setTextureSize(128, 64);
        Center_Box.mirror = true;
        setRotation(Center_Box, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        Base.render(f5);
        BackLeft_Post.render(f5);
        Front_Left_Post.render(f5);
        Front_Right_Post.render(f5);
        Back_Right_Post.render(f5);
        Center_Box.render(f5);
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
        BackLeft_Post.render(0.0625F);
        Front_Left_Post.render(0.0625F);
        Front_Right_Post.render(0.0625F);
        Back_Right_Post.render(0.0625F);
        Center_Box.render(0.0625F);
    }
}
