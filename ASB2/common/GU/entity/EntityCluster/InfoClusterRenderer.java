package GU.entity.EntityCluster;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Textures;
import GU.info.*;

public class InfoClusterRenderer extends Render {

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {

        GL11.glPushMatrix();
        
        GL11.glTranslated(x, y, z);
        GL11.glScalef(.5f, .5f, .5f);
        
        UtilRender.renderTexture(func_110775_a(entity));
        Models.ModelCreationTable.renderAll();
        
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {

        return Textures.CREATION_TABLE;
    }
}