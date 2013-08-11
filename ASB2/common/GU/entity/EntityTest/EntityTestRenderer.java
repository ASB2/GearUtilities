package GU.entity.EntityTest;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import GU.info.Textures;
import GU.models.ModelSphere;
import GU.utils.*;

public class EntityTestRenderer extends Render {

    ModelSphere model;

    public EntityTestRenderer() {

        model = new ModelSphere();
    }

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {

        GL11.glPushMatrix();

        
        UtilRender.renderTexture(func_110775_a(entity));
        model.render(x, y, z);
        
        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation func_110775_a(Entity entity) {

        return Textures.BLANK;
    } 
}
