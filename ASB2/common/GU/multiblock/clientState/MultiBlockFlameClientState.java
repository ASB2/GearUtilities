package GU.multiblock.clientState;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

import GU.info.Models;
import GU.render.noise.NoiseManager;
import UC.math.vector.Vector3i;

public class MultiBlockFlameClientState extends MultiBlockClientState {
    
    Vector3i renderHandler;
    Vector3i multiBlockSize;
    
    public MultiBlockFlameClientState(Vector3i renderHandler, Vector3i multiBlockSize) {
        
        this.renderHandler = renderHandler;
        this.multiBlockSize = multiBlockSize;
        
        // if (this.tank == null) {
        //
        // this.tank = new FluidTank(1000000);
        // }
        //
        // if (this.tank.getFluidAmount() <= 0) {
        //
        // this.tank.setFluid(new FluidStack(FluidRegistry.WATER, 1000000));
        // }
    }
    
    public void setRenderHandler(Vector3i renderHandler) {
        
        this.renderHandler = renderHandler;
    }
    
    public void setMultiBlockSize(Vector3i multiBlockSize) {
        
        this.multiBlockSize = multiBlockSize;
    }
    
    public Vector3i getRenderHandler() {
        return renderHandler;
    }
    
    public Vector3i getMultiBlockSize() {
        return multiBlockSize;
    }
    
    @Override
    public void render(double x, double y, double z, double f) {
        
        GL11.glPushMatrix();
        
        GL11.glDisable(GL11.GL_LIGHTING);
        
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        NoiseManager.bindImage();
        
        GL11.glTranslated((x + 1) - ((multiBlockSize.getX() - 1) / 2.0), (y + 1) - ((multiBlockSize.getY() - 1) / 2.0), (z + 1) - ((multiBlockSize.getZ() - 1) / 2.0));
        
        GL11.glScaled(.5, .5, .5);
        
        {
            final double divide = 1.3;
            GL11.glScaled((multiBlockSize.getX() - 1) / divide, (multiBlockSize.getY() - 5) / divide, (multiBlockSize.getZ() - 1) / divide);
        }
        
        {
            GL11.glPushMatrix();
            GL11.glScaled(.8, .8, .8);
            Models.ModelRhombicuboctahedron.renderPart("Rhombicuboctahedron");
            GL11.glPopMatrix();
        }
        
        {
            GL11.glPushMatrix();
            
            GL11.glRotated(Minecraft.getSystemTime() / 17, 1, 0, 0);
            GL11.glRotated(Minecraft.getSystemTime() / 17, 0, 1, 0);
            GL11.glRotated(Minecraft.getSystemTime() / 17, 0, 0, 1);
            
            GL11.glDisable(GL11.GL_CULL_FACE);
            Models.ModelRhombicuboctahedron.renderPart("Main_Faces");
            GL11.glEnable(GL11.GL_CULL_FACE);
            
            GL11.glPopMatrix();
        }
        
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
}