package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCable extends ModelBase {

    ModelRenderer Wire;
    ModelRenderer Center;

    public ModelCable() {

        textureWidth = 64;
        textureHeight = 32;

        Wire = new ModelRenderer(this, 0, 9);
        Wire.addBox(-2F, 0F, -2F, 4, 6, 4);
        Wire.setRotationPoint(0F, 18F, 0F);
        Wire.setTextureSize(64, 32);
        Wire.mirror = true;
        setRotation(Wire, 0F, 0F, 0F);
        Center = new ModelRenderer(this, 0, 0);
        Center.addBox(-2F, -2F, -2F, 4, 4, 4);
        Center.setRotationPoint(0F, 16F, 0F);
        Center.setTextureSize(64, 32);
        Center.mirror = true;
        setRotation(Center, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {

        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(entity, f, f1, f2, f3, f4, f5);
        Wire.render(f5);
        Center.render(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {

        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void setRotationAngles(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

    }

    public void renderCenter() {

        Center.render(0.0625F);
    }

    public void renderCable() {

        Wire.render(0.0625F);
    }
}
