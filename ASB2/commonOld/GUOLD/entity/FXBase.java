package GUOLD.entity;

import java.awt.Color;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class FXBase extends EntityFX {

    public FXBase(World world, double x, double y, double z, double motionX, double motionY, double motionZ, int age, IIcon texture, Color color) {
        super(world, x, y, z, motionX, motionY, motionZ);

        this.particleIcon = texture;
        this.particleScale = rand.nextFloat();
        this.particleRed = color.getRed();
        this.particleGreen = color.getGreen();
        this.particleBlue = color.getBlue();
        this.particleAlpha = color.getAlpha();
//        this.particleAge = age;

    }
    
    @Override
    public void onUpdate() {
        super.onUpdate();

    }

    @Override
    public int getFXLayer() {

        return 1;
    }
}