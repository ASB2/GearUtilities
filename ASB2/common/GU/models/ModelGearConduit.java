package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import GU.info.Models;

public class ModelGearConduit extends ModelBase{

    private IModelCustom modelTutBox;

    public ModelGearConduit() {

        modelTutBox = AdvancedModelLoader.loadModel(Models.GEAR_CONDUIT);
    }

    public void render() {

        modelTutBox.renderAll();
    }

    int rotate = 1;
    int updown = 0;

    public void render(double x, double y, double z) {

        GL11.glPushMatrix();

        GL11.glTranslatef((float)x + 0.4f, (float)y + 0.1f, (float)z + 0.7f);

        GL11.glScalef(0.05f, 0.05f, 0.05f);

        GL11.glRotatef(0F, 180F, 0F, 0F);

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

    public void renderDown(double x, double y, double z) {
        
        GL11.glScalef(0.02f, 0.02f, 0.02f);

        GL11.glRotatef(180F, 0F, 0F, 0F);

        GL11.glTranslatef((float) x - 3F, (float) y - 70F, (float) z - 5F); 

        this.render();  
    }

    public void renderUp(double x, double y, double z) {
        modelTutBox.renderAll();
        GL11.glTranslatef((float) x + 1F, (float) y + 19.5F, (float) z - 7F);        
        GL11.glRotatef(180F, 1F, 0F, 0F);

    }

    public void renderWest(double x, double y, double z) {
        modelTutBox.renderAll();
        GL11.glTranslatef((float) x + 13F, (float) y + 7F, (float) z + .5F);
        GL11.glRotatef(90F, 0F, 0F, 1F);        
    }

    public void renderEast(double x, double y, double z) {
        modelTutBox.renderAll();
        GL11.glTranslatef((float) x - 8F, (float) y + 13F - 1.8F, (float) z + .5F);
        GL11.glRotatef(-90F, 0F, 0F, 1F);        
    }

    public void renderSouth(double x, double y, double z) {
        modelTutBox.renderAll();
        GL11.glTranslatef((float) x + 0F, (float) y + 5F, (float) z - 14F);
        GL11.glRotatef(90F, 1F, 0F, 0F);

    }

    public void renderNorth(double x, double y, double z) {
        modelTutBox.renderAll();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 12F, (float) z + 7F);
        GL11.glRotatef(-90F, 1F, 0F, 0F);        
    }

}

