package GU.entity.fx;

import java.awt.Color;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import GU.entity.*;

public class FXBeam extends FXBase {

    public FXBeam(World world, double x, double y, double z, double motionX, double motionY, double motionZ, int age, Icon texture, Color color) {
        super(world, x, y, z, motionX, motionY, motionZ, age, texture, color);
    }

    @Override
    public void renderParticle(Tessellator tess, float x, float y, float z, float par5, float par6, float par7) {
        // TODO Auto-generated method stub
    }
}
