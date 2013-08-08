package GU.blocks.containers.BlockTestTank;

import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidTank;
import GU.blocks.containers.TileBase;

public class TileTestTank extends TileBase {
    
    private int maxLiquid = FluidContainerRegistry.BUCKET_VOLUME * 10;
    
    public TileTestTank() {

        fluidTank = new FluidTank(maxLiquid);
    }
    
    @Override
    public void updateEntity() {
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }
}
