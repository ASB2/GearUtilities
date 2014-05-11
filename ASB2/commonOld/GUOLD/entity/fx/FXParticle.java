package GUOLD.entity.fx;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class FXParticle extends EntityFX {

    ResourceLocation texture;
    float red;
    float green;
    float blue;

    public FXParticle(World world, double x, double y, double z,
            ResourceLocation texture, float red, float green, float blue) {
        super(world, x, y, z);
    }

    @Override
    public void renderParticle(Tessellator tessellator, float f, float f1,
            float f2, float f3, float f4, float f5) {

    }

}
