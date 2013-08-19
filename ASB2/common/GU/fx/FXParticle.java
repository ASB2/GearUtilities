package GU.fx;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

public class FXParticle extends EntityFX {

    ResourceLocation texture;
    float red;
    float green;
    float blue;


    public FXParticle(World world, double x, double y, double z, ResourceLocation texture, float red, float green, float blue) {
        super(world, x, y, z);
    }

    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {

        tessellator.draw();
        GL11.glPushMatrix();

        GL11.glDepthMask(false);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, this.blendmode);
        ForgeHooksClient.bindTexture("/soaryn/xycraft/particles.png", 0);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.75F);
        int part = this.particle + this.particleAge / this.multiplier;

        float var8 = part % 8 / 8.0F;
        float var9 = var8 + 0.124875F;
        float var10 = part / 8 / 8.0F;
        float var11 = var10 + 0.124875F;
        float var12 = 0.1F * this.particleScale * ((this.particleMaxAge - this.particleAge + 1) / this.particleMaxAge);
        float var13 = (float)(this.prevPosX + (this.posX - this.prevPosX) * f - EntityFX.interpPosX);
        float var14 = (float)(this.prevPosY + (this.posY - this.prevPosY) * f - EntityFX.interpPosY);
        float var15 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * f - EntityFX.interpPosZ);
        float var16 = 1.0F;

        tessellator.startDrawingQuads();
        tessellator.setBrightness(240);

        tessellator.setColorRGBA_F(this.particleRed * var16, this.particleGreen * var16, this.particleBlue * var16, 1.0F);
        tessellator.addVertexWithUV(var13 - f1 * var12 - f4 * var12, var14 - f2 * var12, var15 - f3 * var12 - f5 * var12, var9, var11);
        tessellator.addVertexWithUV(var13 - f1 * var12 + f4 * var12, var14 + f2 * var12, var15 - f3 * var12 + f5 * var12, var9, var10);
        tessellator.addVertexWithUV(var13 + f1 * var12 + f4 * var12, var14 + f2 * var12, var15 + f3 * var12 + f5 * var12, var8, var10);
        tessellator.addVertexWithUV(var13 + f1 * var12 - f4 * var12, var14 - f2 * var12, var15 + f3 * var12 - f5 * var12, var8, var11);

        tessellator.draw();

        GL11.glDisable(3042);
        GL11.glDepthMask(true);

        GL11.glPopMatrix();
        GL11.glBindTexture(3553, Minecraft.getMinecraft().renderEngine.getTexture("/particles.png"));
        tessellator.startDrawingQuads();
    }

}
