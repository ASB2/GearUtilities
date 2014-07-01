package GU.multiblock.clientState;

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
        
    }
}