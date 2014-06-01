package GU.multiblock;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import UC.math.vector.Vector3i;

public class MultiBlockTankClientState extends MultiBlockClientState {
    
    Vector3i renderHandler;
    Vector3i multiBlockSize;
    
    FluidTank tank;
    
    public MultiBlockTankClientState(Vector3i renderHandler, Vector3i multiBlockSize, FluidTank tank) {
        
        this.renderHandler = renderHandler;
        this.multiBlockSize = multiBlockSize;
        this.tank = tank;
    }
    
    @Override
    public void render(double x, double y, double z, double f) {
        
        if (tank != null && tank.getFluid() != null) {
            
            GL11.glPushMatrix();
            
            GL11.glDisable(GL11.GL_LIGHTING);
            
            GL11.glTranslated(x, y, z);
            
            Tessellator tess = Tessellator.instance;
            
            UtilRender.bindBlockTextures();
            
            // IIcon icon = NoiseManager.instance.blockNoiseIcon;
            IIcon icon = tank.getFluid().getFluid().getFlowingIcon();
            
            // Up
            tess.startDrawingQuads();
            
            tess.addVertexWithUV(0, 1, 1, icon.getMaxU(), icon.getMinV());
            tess.addVertexWithUV(1, 1, 1, icon.getMaxU(), icon.getMaxV());
            tess.addVertexWithUV(1, 1, 0, icon.getMinU(), icon.getMaxV());
            tess.addVertexWithUV(0, 1, 0, icon.getMinU(), icon.getMinV());
            
            tess.draw();
            
            // Down
            tess.startDrawingQuads();
            
            tess.addVertexWithUV(1, 0, 0, icon.getMaxU(), icon.getMinV());
            tess.addVertexWithUV(1, 0, 1, icon.getMaxU(), icon.getMaxV());
            tess.addVertexWithUV(0, 0, 1, icon.getMinU(), icon.getMaxV());
            tess.addVertexWithUV(0, 0, 0, icon.getMinU(), icon.getMinV());
            
            tess.draw();
            
            // North
            tess.startDrawingQuads();
            
            tess.addVertexWithUV(1, 0, 1, icon.getMaxU(), icon.getMaxV());
            tess.addVertexWithUV(1, 1, 1, icon.getMaxU(), icon.getMinV());
            tess.addVertexWithUV(0, 1, 1, icon.getMinU(), icon.getMinV());
            tess.addVertexWithUV(0, 0, 1, icon.getMinU(), icon.getMaxV());
            
            tess.draw();
            
            // South
            tess.startDrawingQuads();
            
            tess.addVertexWithUV(0, 1, 0, icon.getMinU(), icon.getMinV()); // South
            tess.addVertexWithUV(1, 1, 0, icon.getMaxU(), icon.getMinV());
            tess.addVertexWithUV(1, 0, 0, icon.getMaxU(), icon.getMaxV());
            tess.addVertexWithUV(0, 0, 0, icon.getMinU(), icon.getMaxV());
            
            tess.draw();
            
            // West
            tess.startDrawingQuads();
            
            tess.addVertexWithUV(1, 1, 1, icon.getMinU(), icon.getMinV());
            tess.addVertexWithUV(1, 0, 1, icon.getMinU(), icon.getMaxV());
            tess.addVertexWithUV(1, 0, 0, icon.getMaxU(), icon.getMaxV());
            tess.addVertexWithUV(1, 1, 0, icon.getMaxU(), icon.getMinV());
            
            tess.draw();
            
            // East
            tess.startDrawingQuads();
            
            tess.addVertexWithUV(0, 1, 0, icon.getMaxU(), icon.getMinV());
            tess.addVertexWithUV(0, 0, 0, icon.getMaxU(), icon.getMaxV());
            tess.addVertexWithUV(0, 0, 1, icon.getMinU(), icon.getMaxV());
            tess.addVertexWithUV(0, 1, 1, icon.getMinU(), icon.getMinV());
            
            tess.draw();
            
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
}
