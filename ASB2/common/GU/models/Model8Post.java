package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Model8Post extends ModelBase {

    ModelRenderer post;

    public Model8Post() {

        textureWidth = 64;
        textureHeight = 32;
        
        post = new ModelRenderer(this, 0, 0);
        post.addBox(-2F, 0F, -2F, 4, 8, 4);
        post.setRotationPoint(0F, 16F, 0F);
        post.setTextureSize(64, 32);
        post.mirror = true;
          setRotation(post, 0F, 0F, 0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
      super.render(entity, f, f1, f2, f3, f4, f5);
      setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      post.render(f5);
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
        
        post.render(0.0625F);
    }
}