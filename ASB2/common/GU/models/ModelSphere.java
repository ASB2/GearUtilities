package GU.models;

import java.util.Random;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import GU.info.Models;

public class ModelSphere extends ModelBase {

    private IModelCustom modelTutBox;

    public ModelSphere() {

        modelTutBox = AdvancedModelLoader.loadModel(Models.TETRA);
    }

    public void render() {
        modelTutBox.renderAll();
    }

    int rotate = 1;
    int updown = 0;

    public void render(double x, double y, double z) {
        
        GL11.glPushMatrix();
        Random rand = new Random();

        updown = (int) (rand.nextFloat() * 360);

        GL11.glTranslatef((float)x + 0.5f, (float)y + 0.5f, (float)z + 0.5f);

        GL11.glScalef(0.5f, 0.5f, 0.5f);

        GL11.glRotatef(updown, updown, updown, updown);
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

