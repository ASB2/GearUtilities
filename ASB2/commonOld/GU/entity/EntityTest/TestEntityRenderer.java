package GU.entity.EntityTest;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import GU.info.Textures;

public class TestEntityRenderer extends Render {

    @Override
    public void doRender(Entity entity, double x, double y, double z, float yaw, float partialTick) {

        GL11.glPushMatrix();

        GL11.glTranslated(x + .5f, y + .5f, z + .5f);
        GL11.glScalef(.5f, .5f, .5f);

//        UtilRender.renderTexture(Textures.CREATION_TABLE);
//        Models.ModelCreationTable.renderAll();

        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {

        return Textures.BLANK;
    }
}
