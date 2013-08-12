package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLaser extends ModelBase {

    ModelRenderer Base;
    ModelRenderer Stem;
    ModelRenderer Head;
    ModelRenderer TopCover;

    public ModelLaser() {

        textureWidth = 128;
        textureHeight = 64;

        Base = new ModelRenderer(this, 0, 0);
        Base.addBox(-7F, 0F, -7F, 14, 2, 14);
        Base.setRotationPoint(0F, 22F, 0F);
        Base.setTextureSize(64, 32);
        Base.mirror = true;
        setRotation(Base, 0F, 0F, 0F);
        Stem = new ModelRenderer(this, 0, 17);
        Stem.addBox(-2F, 0F, -2F, 4, 10, 4);
        Stem.setRotationPoint(0F, 12F, 0F);
        Stem.setTextureSize(64, 32);
        Stem.mirror = true;
        setRotation(Stem, 0F, 0F, 0F);
        Head = new ModelRenderer(this, 0, 32);
        Head.addBox(-5F, 0F, -7F, 10, 5, 11);
        Head.setRotationPoint(0F, 7F, 0F);
        Head.setTextureSize(64, 32);
        Head.mirror = true;
        setRotation(Head, 0F, 0F, 0F);
        TopCover = new ModelRenderer(this, 0, 49);
        TopCover.addBox(-5F, 0F, -5F, 10, 1, 10);
        TopCover.setRotationPoint(0F, 6F, 0F);
        TopCover.setTextureSize(128, 64);
        TopCover.mirror = true;
        setRotation(TopCover, 0F, 0F, 0F);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {      
        super.render(entity, f, f1, f2, f3, f4, f5);

        setRotationAngles(f, f1, f2, f3, f4, f5,entity);
        Base.render(f5);
        Stem.render(f5);
        Head.render(f5);
        TopCover.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {

        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

    }

    public void renderBase() {

        Base.render(0.0625F);
        Stem.render(0.0625F);
        TopCover.render(0.0625F);
    }

    public void renderHead() {

        Head.render(0.0625F);
    }
}
