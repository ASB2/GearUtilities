package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTest extends ModelBase {

    public ModelRenderer attachment;
    public ModelRenderer post;
    public ModelRenderer stub;
    public ModelRenderer join;
    public ModelRenderer core;

    public ModelTest() {

        textureWidth = 64;
        textureHeight = 32;

        this.attachment = new ModelRenderer(this, 0, 0);
        this.attachment.addBox(-5.0F, -8.0F, -5.0F, 3, 2, 10);
        this.attachment.setTextureOffset(0, 12);
        this.attachment.addBox(2.0F, -8.0F, -5.0F, 3, 2, 10);
        this.attachment.setTextureOffset(0, 24);
        this.attachment.addBox(-2.0F, -8.0F, -5.0F, 4, 2, 3);
        this.attachment.setTextureOffset(0, 29);
        this.attachment.addBox(-2.0F, -8.0F, 2.0F, 4, 2, 3);

        this.stub = new ModelRenderer(this, 0, 34);
        this.stub.addBox(-2.0F, -8.0F, -2.0F, 4, 2, 4);

        this.join = new ModelRenderer(this, 0, 40);
        this.join.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4);

        this.post = new ModelRenderer(this, 0, 48);
        this.post.addBox(-2.0F, -8.0F, -2.0F, 4, 6, 4);

        this.core = new ModelRenderer(this, 0, 0);
        this.core.addBox(-6.0F, -6.0F, -6.0F, 12, 12, 12);
    }

    public static void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.rotationPointX = x;
        model.rotationPointY = y;
        model.rotationPointZ = z;
    }
}