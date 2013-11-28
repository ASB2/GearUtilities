package GU.blocks.containers.BlockGyro.BlockSolarGyro;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import ASB2.utils.UtilRender;
import GU.blocks.containers.BlockGyro.GyroRenderer;
import GU.info.Models;
import GU.info.Textures;
import GU.info.Variables;

public class SolarGyroRenderer extends GyroRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float f) {

        GL11.glPushMatrix();

        GL11.glTranslated(x + .5f, y + .09f, z + .5f);
        GL11.glScalef(.5f, .5f, .5f);

        UtilRender.renderTexture(this.getBaseTexture());
        Models.ModelGyro.renderPart("Base");

        UtilRender.renderTexture(this.getCenterTexture());
        Models.ModelGyro.renderPart("Center");

        if(tileentity.worldObj.canBlockSeeTheSky(tileentity.xCoord, tileentity.yCoord, tileentity.zCoord) && tileentity.worldObj.isDaytime()) {

            GL11.glRotatef(Minecraft.getSystemTime() / Variables.ANIMATION_SPEED, 0F, 1F, 0F);
        }

        GL11.glPushMatrix();
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");

        GL11.glRotatef(90f, 0, 1, 0);
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");

        GL11.glRotatef(-180f, 0, 1, 0);
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");

        GL11.glRotatef(-90f, 0, 1, 0);
        UtilRender.renderTexture(this.getOuterTexture());
        Models.ModelGyro.renderPart("Outer");
        GL11.glPopMatrix();

        GL11.glPopMatrix();
    }

    @Override
    public ResourceLocation getBaseTexture() {

        return Textures.SOLAR_GYRO_BASE;
    }

    @Override
    public ResourceLocation getCenterTexture() {

        return Textures.SOLAR_GYRO_CENTER;
    }

    @Override
    public ResourceLocation getOuterTexture() {

        return Textures.SOLAR_GYRO_OUTER;
    }
}
