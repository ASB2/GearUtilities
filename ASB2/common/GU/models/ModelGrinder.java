package GU.models;
import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import GU.info.Models;

public class ModelGrinder extends ModelBase{

    private IModelCustom modelTutBox;

    public ModelGrinder() {

        modelTutBox = AdvancedModelLoader.loadModel(Models.GRINDER);
    }

    public void render() {

        modelTutBox.renderAll();
    }

    int rotate = 1;
    int updown = 0;

    public void render(double x, double y, double z) {

        float scale = 0.05F;
        GL11.glPushMatrix();

        GL11.glTranslatef((float)x + 0.5f, (float)y + 1.5f, (float)z + 0.5f);

        GL11.glScalef(scale, scale, scale);

        //GL11.glRotatef(updown, updown, updown, updown);

        this.render();

        GL11.glPopMatrix();
    }

    public int rotate() {

        if(rotate < 360) {
            rotate++;
        }
        else{
            rotate = 0;
        }
        return rotate;
    }
}

