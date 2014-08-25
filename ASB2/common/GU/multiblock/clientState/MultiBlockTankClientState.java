package GU.multiblock.clientState;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.FluidTank;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilRender;
import GU.info.Reference;
import UC.math.vector.Vector3i;

public class MultiBlockTankClientState extends MultiBlockClientState {
    
    Vector3i renderHandler;
    Vector3i multiBlockSize;
    
    FluidTank tank;
    
    public MultiBlockTankClientState(Vector3i renderHandler, Vector3i multiBlockSize, FluidTank tank) {
        
        this.renderHandler = renderHandler;
        this.multiBlockSize = multiBlockSize;
        this.tank = tank;
        
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
    
    public void setTank(FluidTank tank) {
        this.tank = tank;
    }
    
    public Vector3i getRenderHandler() {
        return renderHandler;
    }
    
    public Vector3i getMultiBlockSize() {
        return multiBlockSize;
    }
    
    public FluidTank getTank() {
        return tank;
    }
    
    @Override
    public void render(double x, double y, double z, double f) {
        
        if (tank != null && tank.getFluidAmount() > 0) {
            
            double xSize = this.multiBlockSize.getX(), ySize = this.multiBlockSize.getY(), zSize = this.multiBlockSize.getZ();
            double scaledHeight = ((tank.getFluidAmount() / (double) tank.getCapacity()) * (ySize - 1)) + .01;
            double xScaledPos = ((xSize) / 2) - .0001, xScaledNeg = -(((xSize) / 2) - 1) + .0001, zScaledPos = ((zSize) / 2) - .0001, zScaledNeg = -(((zSize) / 2) - 1) + .0001;
            
            GL11.glPushMatrix();
            
            GL11.glDisable(GL11.GL_LIGHTING);
            
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            
            // GL11.glEnable(cap);
            GL11.glTranslated(x - ((xSize - 2) / 2), y - (ySize - 2), z - ((zSize - 2) / 2));
            
            Tessellator tess = Tessellator.instance;
            
            UtilRender.bindBlockTextures();
            
            // IIcon icon = NoiseManager.instance.blockNoiseIcon;
            IIcon sideIcon = tank.getFluid().getFluid().getFlowingIcon();
            IIcon topIcon = tank.getFluid().getFluid().getStillIcon();
            int renderBrightness = Reference.BRIGHT_BLOCK;
            
            if (tank.getFluid().getFluid().isGaseous()) {
                
                double density = ((tank.getFluidAmount() / (double) tank.getCapacity()) * 1) + .15;
                scaledHeight = (ySize - 1) + .01;
                GL11.glColor4d(1, 1, 1, density);
            }
            else {
                
                GL11.glColor4d(1, 1, 1, .93);
            }
            
            // Gl11.gl
            
            // Up
            tess.startDrawingQuads();
            tess.setBrightness(renderBrightness);
            tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledPos, topIcon.getMaxU(), topIcon.getMinV());
            tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledPos, topIcon.getMaxU(), topIcon.getMaxV());
            tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledNeg, topIcon.getMinU(), topIcon.getMaxV());
            tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledNeg, topIcon.getMinU(), topIcon.getMinV());
            
            tess.draw();
            
            // Down
            tess.startDrawingQuads();
            
            tess.setBrightness(renderBrightness);
            tess.addVertexWithUV(xScaledPos, 0, zScaledNeg, topIcon.getMaxU(), topIcon.getMinV());
            tess.addVertexWithUV(xScaledPos, 0, zScaledPos, topIcon.getMaxU(), topIcon.getMaxV());
            tess.addVertexWithUV(xScaledNeg, 0, zScaledPos, topIcon.getMinU(), topIcon.getMaxV());
            tess.addVertexWithUV(xScaledNeg, 0, zScaledNeg, topIcon.getMinU(), topIcon.getMinV());
            
            tess.draw();
            
            // North
            tess.startDrawingQuads();
            
            tess.setBrightness(renderBrightness);
            tess.addVertexWithUV(xScaledPos, 0, zScaledPos, sideIcon.getMaxU(), sideIcon.getMaxV());
            tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledPos, sideIcon.getMaxU(), sideIcon.getMinV());
            tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledPos, sideIcon.getMinU(), sideIcon.getMinV());
            tess.addVertexWithUV(xScaledNeg, 0, zScaledPos, sideIcon.getMinU(), sideIcon.getMaxV());
            
            tess.draw();
            
            // South
            tess.startDrawingQuads();
            
            tess.setBrightness(renderBrightness);
            tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledNeg, sideIcon.getMinU(), sideIcon.getMinV()); // South
            tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledNeg, sideIcon.getMaxU(), sideIcon.getMinV());
            tess.addVertexWithUV(xScaledPos, 0, zScaledNeg, sideIcon.getMaxU(), sideIcon.getMaxV());
            tess.addVertexWithUV(xScaledNeg, 0, zScaledNeg, sideIcon.getMinU(), sideIcon.getMaxV());
            
            tess.draw();
            
            // West
            tess.startDrawingQuads();
            
            tess.setBrightness(renderBrightness);
            tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledPos, sideIcon.getMinU(), sideIcon.getMinV());
            tess.addVertexWithUV(xScaledPos, 0, zScaledPos, sideIcon.getMinU(), sideIcon.getMaxV());
            tess.addVertexWithUV(xScaledPos, 0, zScaledNeg, sideIcon.getMaxU(), sideIcon.getMaxV());
            tess.addVertexWithUV(xScaledPos, scaledHeight, zScaledNeg, sideIcon.getMaxU(), sideIcon.getMinV());
            
            tess.draw();
            
            // East
            tess.startDrawingQuads();
            
            tess.setBrightness(renderBrightness);
            tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledNeg, sideIcon.getMaxU(), sideIcon.getMinV());
            tess.addVertexWithUV(xScaledNeg, 0, zScaledNeg, sideIcon.getMaxU(), sideIcon.getMaxV());
            tess.addVertexWithUV(xScaledNeg, 0, zScaledPos, sideIcon.getMinU(), sideIcon.getMaxV());
            tess.addVertexWithUV(xScaledNeg, scaledHeight, zScaledPos, sideIcon.getMinU(), sideIcon.getMinV());
            
            tess.draw();
            
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glPopMatrix();
        }
    }
}
