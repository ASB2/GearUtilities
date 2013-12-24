package GU.blocks.containers.BlockCentrifuge;

import net.minecraftforge.fluids.FluidTank;
import GU.blocks.containers.TileFluidBase;

public class TileCentrifuge extends TileFluidBase {
    
    public TileCentrifuge() {

        this.fluidTank = new FluidTank(1000);
    }
    
    @Override
    public void updateEntity() {
        // TODO Auto-generated method stub
        super.updateEntity();
    }
}
