package GU.entity.EntityFamilar;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Models;
import GU.info.Textures;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class FamilarsRenderer extends Render {

    @Override
    public void doRender(Entity entity, double x, double y, double z, float f, float f1) {

        GL11.glPushMatrix();

        GL11.glTranslated(x, y, z );
        GL11.glScalef(.5f, .5f, .5f);

        UtilRender.renderTexture(Textures.CREATION_TABLE);
        Models.ModelCreationTable.renderAll();

        GL11.glPopMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity entity) {

        return Textures.MULTI_PANEL;
    }

}
