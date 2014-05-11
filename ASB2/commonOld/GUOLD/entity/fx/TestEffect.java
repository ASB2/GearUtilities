package GUOLD.entity.fx;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import ASB2.vector.Vector3;

public class TestEffect extends EntityFX {

    Vector3 position = new Vector3();
    Vector3 end = new Vector3();

    public TestEffect(World world, Vector3 position, Vector3 end) {
        super(world, position.x, position.y, position.z);

        this.end = end;
        this.init();
    }

    public void init() {

        particleMaxAge = 1;
        this.particleGravity = 1;
        this.multipleParticleScaleBy(5);
        this.setSize(1F, 1F);
        this.noClip = true;
    }

    @Override
    public void onUpdate() {

        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.setDead();
        }

        this.motionY -= 0.04D * this.particleGravity;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if (this.onGround) {

            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }

    @Override
    public void renderParticle(Tessellator tessellator, float par2, float par3,
            float par4, float par5, float par6, float par7) {

        float f6 = this.particleTextureIndexX / 16.0F;
        float f7 = f6 + 0.0624375F;
        float f8 = this.particleTextureIndexY / 16.0F;
        float f9 = f8 + 0.0624375F;
        float f10 = 0.1F * this.particleScale;

        if (this.particleIcon != null) {

            f6 = this.particleIcon.getMinU();
            f7 = this.particleIcon.getMaxU();
            f8 = this.particleIcon.getMinV();
            f9 = this.particleIcon.getMaxV();
        }

        float f11 = (float) (this.prevPosX + (this.posX - this.prevPosX) * par2 - interpPosX);
        float f12 = (float) (this.prevPosY + (this.posY - this.prevPosY) * par2 - interpPosY);
        float f13 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - interpPosZ);
        float f14 = 1.0F;
        tessellator.setColorRGBA_F(this.particleRed * f14, this.particleGreen
                * f14, this.particleBlue * f14, this.particleAlpha);
        tessellator.addVertexWithUV(f11 - par3 * f10 - par6 * f10, f12 - par4
                * f10, f13 - par5 * f10 - par7 * f10, f7, f9);
        tessellator.addVertexWithUV(f11 - par3 * f10 + par6 * f10, f12 + par4
                * f10, f13 - par5 * f10 + par7 * f10, f7, f8);
        tessellator.addVertexWithUV(f11 + par3 * f10 + par6 * f10, f12 + par4
                * f10, f13 + par5 * f10 + par7 * f10, f6, f8);
        tessellator.addVertexWithUV(f11 + par3 * f10 - par6 * f10, f12 - par4
                * f10, f13 + par5 * f10 - par7 * f10, f6, f9);
    }
}
