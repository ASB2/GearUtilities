package GU.multiblock;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.render.EnumInputIcon;
import GU.render.noise.NoiseManager;
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
    
    @SuppressWarnings("unused")
    @Override
    public void render(double x, double y, double z, double f) {
        
        if (false) {
            
            GL11.glPushMatrix();
            
            GL11.glDisable(GL11.GL_LIGHTING);
            
            GL11.glTranslated(x, y, z);
            
            GL11.glBegin(GL11.GL_QUADS); // Draw The Cube Using quads
            
            GL11.glColor3f(0.0f, 1.0f, 0.0f); // Color Blue
            GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Quad (Top)
            GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Top)
            GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Bottom Left Of The Quad (Top)
            GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Bottom Right Of The Quad (Top)
            
            GL11.glColor3f(1.0f, 0.5f, 0.0f); // Color Orange
            GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Top Right Of The Quad
                                                // (Bottom)
            GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Top Left Of The Quad
                                                 // (Bottom)
            GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad
                                                  // (Bottom)
            GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Quad
                                                 // (Bottom)
            GL11.glColor3f(1.0f, 0.0f, 0.0f); // Color Red
            GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Right Of The Quad (Front)
            GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Left Of The Quad (Front)
            GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Left Of The Quad
                                                 // (Front)
            GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Right Of The Quad
                                                // (Front)
            
            GL11.glColor3f(1.0f, 1.0f, 0.0f); // Color Yellow
            GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Top Right Of The Quad (Back)
            GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Top Left Of The Quad (Back)
            GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Bottom Left Of The Quad
                                                 // (Back)
            GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Bottom Right Of The Quad
                                                // (Back)
            
            GL11.glColor3f(0.0f, 0.0f, 1.0f); // Color Blue
            GL11.glVertex3f(-1.0f, 1.0f, 1.0f); // Top Right Of The Quad (Left)
            GL11.glVertex3f(-1.0f, 1.0f, -1.0f); // Top Left Of The Quad (Left)
            GL11.glVertex3f(-1.0f, -1.0f, -1.0f); // Bottom Left Of The Quad
                                                  // (Left)
            GL11.glVertex3f(-1.0f, -1.0f, 1.0f); // Bottom Right Of The Quad
                                                 // (Left)
            
            GL11.glColor3f(1.0f, 0.0f, 1.0f); // Color Violet
            GL11.glVertex3f(1.0f, 1.0f, -1.0f); // Top Right Of The Quad (Right)
            GL11.glVertex3f(1.0f, 1.0f, 1.0f); // Top Left Of The Quad (Right)
            GL11.glVertex3f(1.0f, -1.0f, 1.0f); // Bottom Left Of The Quad
                                                // (Right)
            GL11.glVertex3f(1.0f, -1.0f, -1.0f); // Bottom Right Of The Quad
                                                 // (Right)
            GL11.glEnd();
            
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
        else {
            
            GL11.glPushMatrix();
            
            GL11.glDisable(GL11.GL_LIGHTING);
            
            GL11.glTranslated(x, y, z);
            // GL11.glTranslated(.5, .5, .5);
            
            Tessellator tess = Tessellator.instance;
            
            UtilRender.bindBlockTextures();
            IIcon icon = NoiseManager.instance.blockNoiseIcon;
            
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
