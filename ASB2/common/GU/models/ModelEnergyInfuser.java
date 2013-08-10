package GU.models;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import org.lwjgl.opengl.GL11;

public class ModelEnergyInfuser extends ModelBase {
    
    ModelRenderer Center;
    ModelRenderer Base;
    ModelRenderer Top;
  
  public ModelEnergyInfuser() {
      
    textureWidth = 128;
    textureHeight = 64;
    
      Center = new ModelRenderer(this, 0, 19);
      Center.addBox(-5F, 0F, -5F, 10, 16, 10);
      Center.setRotationPoint(0F, 8F, 0F);
      Center.setTextureSize(128, 64);
      Center.mirror = true;
      setRotation(Center, 0F, 0F, 0F);
      Base = new ModelRenderer(this, 42, 44);
      Base.addBox(-8F, 0F, -8F, 16, 4, 16);
      Base.setRotationPoint(0F, 20F, 0F);
      Base.setTextureSize(128, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 1);
      Top.addBox(-7.5F, 0F, -7.5F, 15, 2, 15);
      Top.setRotationPoint(0F, 10F, 0F);
      Top.setTextureSize(128, 64);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, 0F);
  }
  
  @Override
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Center.render(f5);
    Base.render(f5);
    Top.render(f5);
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

  
boolean finishProduct = false;
  
  int ticks;
  public void renderWithRotation(float x, float y, float z) {
      
      ticks++;
      
      GL11.glPushMatrix();
      
      GL11.glTranslatef(x + 0.5f, y  + 1.5f, z + 0.5f);
      
      GL11.glRotatef(180, 180, 1, 1);
      
      Center.render(0.0625F);
      Base.render(0.0625F);
      
     
      
      GL11.glPopMatrix();
      //
      GL11.glPushMatrix();
      
      GL11.glTranslatef(x + 0.5f, y  + 1.5f, z + 0.5f);
      
      Random rand = new Random();
      
      if(finishProduct)
      GL11.glRotatef(180, 180, 1, rand.nextInt(180));
      
      else {
          GL11.glRotatef(180, 180, 1, ticks);
      }
      Top.render(0.0625F);
      
      if(ticks > 180)
          ticks = 0;
      GL11.glPopMatrix();
  }

public void renderNoRotation(float x, float y, float z) {
      
      ticks++;
      
      GL11.glPushMatrix();
      
      GL11.glTranslatef(x + 0.5f, y  + 1.5f, z + 0.5f);
      
      GL11.glRotatef(180, 180, 1, 1);
      
      Center.render(0.0625F);
      Base.render(0.0625F);
      Top.render(0.0625F);
      
      GL11.glPopMatrix();

  }
}
