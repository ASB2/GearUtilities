package GU.entity.EntityTileFinder;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Textures;

public class InfoClusterRenderer extends Render {

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {

        GL11.glPushMatrix();

        GL11.glTranslated(x, y, z);
        GL11.glScalef(.5f, .5f, .5f);
        GL11.glRotated(0 - yaw, 0, 0, 0);
        UtilRender.renderTexture(Textures.MULTI_PANEL);
        Models.ModelMultiPanel.renderAll();

        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        // TODO Auto-generated method stub
        return Textures.MULTI_PANEL;
    }
}