package GUOLD.entity.EntityQuanta;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GUOLD.info.Models;
import GUOLD.info.Textures;

public class QuantaRenderer extends Render {
    
    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {
        
        GL11.glPushMatrix();
        
        GL11.glTranslated(x, y + .75f, z);
        GL11.glScalef(.2f, .2f, .2f);
        
        UtilRender.renderTexture(Textures.CRYSTAL_1);
        Models.ModelCrystal1.renderAll();
        
        GL11.glPopMatrix();
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {
        
        return Textures.CRYSTAL_1;
    }
}
