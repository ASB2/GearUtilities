package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelConduit extends ModelBase {
    
    ModelRenderer Center_Square;
    ModelRenderer WireLeft;
    ModelRenderer WireBack;
    ModelRenderer WireRight;
    ModelRenderer WireFront;
    ModelRenderer WireTop;
    ModelRenderer WireBottom;
    ModelRenderer RingTop;
    ModelRenderer RingBottom;
    ModelRenderer RingFront;
    ModelRenderer RingBack;
    ModelRenderer RingLeft;
    ModelRenderer RingRight;
  
  public ModelConduit() {
      
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
      RingTop = new ModelRenderer(this, 0, 41);
      RingTop.addBox(-3F, 0F, -3F, 6, 4, 6);
      RingTop.setRotationPoint(0F, 8F, 0F);
      RingTop.setTextureSize(64, 32);
      RingTop.mirror = true;
      setRotation(RingTop, 0F, 0F, 0F);
      RingBottom = new ModelRenderer(this, 0, 41);
      RingBottom.addBox(-3F, 0F, -3F, 6, 4, 6);
      RingBottom.setRotationPoint(0F, 20F, 0F);
      RingBottom.setTextureSize(64, 32);
      RingBottom.mirror = true;
      setRotation(RingBottom, 0F, 0F, 0F);
      RingFront = new ModelRenderer(this, 0, 52);
      RingFront.addBox(-3F, -3F, 0F, 6, 6, 4);
      RingFront.setRotationPoint(0F, 16F, -8F);
      RingFront.setTextureSize(64, 32);
      RingFront.mirror = true;
      setRotation(RingFront, 0F, 0F, 0F);
      RingBack = new ModelRenderer(this, 0, 52);
      RingBack.addBox(-3F, -3F, 0F, 6, 6, 4);
      RingBack.setRotationPoint(0F, 16F, 4F);
      RingBack.setTextureSize(64, 32);
      RingBack.mirror = true;
      setRotation(RingBack, 0F, 0F, 0F);
      RingLeft = new ModelRenderer(this, 21, 52);
      RingLeft.addBox(0F, -3F, -3F, 4, 6, 6);
      RingLeft.setRotationPoint(4F, 16F, 0F);
      RingLeft.setTextureSize(64, 32);
      RingLeft.mirror = true;
      setRotation(RingLeft, 0F, 0F, 0F);
      RingRight = new ModelRenderer(this, 21, 52);
      RingRight.addBox(0F, -3F, -3F, 4, 6, 6);
      RingRight.setRotationPoint(-8F, 16F, 0F);
      RingRight.setTextureSize(64, 32);
      RingRight.mirror = true;
      setRotation(RingRight, 0F, 0F, 0F);
  }
  
  @Override
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
    RingTop.render(f5);
    RingBottom.render(f5);
    RingFront.render(f5);
    RingBack.render(f5);
    RingLeft.render(f5);
    RingRight.render(f5);
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

  public void renderWireUP() {
      //FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      WireTop.render(0.0625F);
  }

  public void renderRingUP() {
      //FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      RingTop.render(0.0625F);
  }
  
  public void renderWireDOWN() {
     // FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      WireBottom.render(0.0625F);
  }

  public void renderRingDOWN() {
      //FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      RingBottom.render(0.0625F);
  }
  
  public void renderWireWEST() {
     // FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      WireLeft.render(0.0625F);
  }

  public void renderRingWEST() {
     // FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      RingRight.render(0.0625F);
  }
  
  public void renderWireEAST() {
     // FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      WireRight.render(0.0625F);
  }

  public void renderRingEAST() {
    //  FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      RingLeft.render(0.0625F);
  }
  
  public void renderWireSOUTH() {
   //   FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      WireFront.render(0.0625F);
  }

  public void renderRingSOUTH() {
   //   FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      RingFront.render(0.0625F);
  }
  
  public void renderWireNORTH() {
   //   FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      WireBack.render(0.0625F);
  }

  public void renderRingNORTH() {
    //  FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      RingBack.render(0.0625F);
  }
  
  public void renderCenter() {
   //   FMLClientHandler.instance().getClient().renderEngine.bindTexture(TEXTURES.CONDUIT_MOVING);
      Center_Square.render(0.0625F);
  }
}
