package GU.models;

import net.minecraft.client.model.ModelBase;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import GU.info.Models;

public class ModelGear extends ModelBase{

    private IModelCustom modelTutBox;

    public ModelGear() {

        modelTutBox = AdvancedModelLoader.loadModel(Models.GEAR);
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
        
        GL11.glScalef(0.03f, 0.03f, 0.03f);

        GL11.glTranslatef((float) x + 0F, (float) y + 45F, (float) z + 0F); 

        this.render();  
    }

    public void renderUp(double x, double y, double z) {
        
        GL11.glScalef(0.03f, 0.03f, 0.03f);

        GL11.glTranslatef((float) x + 0F, (float) y + 45F, (float) z + 0F); 

        this.render();  

    }

    public void renderWest(double x, double y, double z) {

        GL11.glScalef(0.03f, 0.03f, 0.03f);

        GL11.glTranslatef((float) x + 0F, (float) y + 45F, (float) z + 0F); 

        GL11.glRotatef(90F, 0F, 0F, 1F);  

        this.render(); 

    }

    public void renderEast(double x, double y, double z) {

        GL11.glScalef(0.03f, 0.03f, 0.03f);

        GL11.glTranslatef((float) x + 0F, (float) y + 45F, (float) z + 0F); 

        GL11.glRotatef(90F, 0F, 0F, 1F);  

        this.render();        
    }

    public void renderSouth(double x, double y, double z) {

        GL11.glScalef(0.03f, 0.03f, 0.03f);

        GL11.glTranslatef((float) x + 0F, (float) y + 45F, (float) z + 0F); 

        GL11.glRotatef(90F, 0F, 0F, 1F);  

        this.render(); 

    }

    public void renderNorth(double x, double y, double z) {

        GL11.glScalef(0.03f, 0.03f, 0.03f);

        GL11.glTranslatef((float) x + 0F, (float) y + 45F, (float) z + 0F); 

        GL11.glRotatef(90F, 0F, 0F, 1F);  

        this.render();        
    }

}

