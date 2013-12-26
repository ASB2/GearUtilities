package GU.blocks.containers.BlockEssenceDiffuser;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import GU.blocks.containers.TileFluidBase;

public class TileEssenceDiffuser extends TileFluidBase {
    
    public TileEssenceDiffuser() {
        
        this.fluidTank = new FluidTank(GU.FluidRegistry.LifeEssenceGas, 0, 1000);
        renderDoubles = new double[1];
    }
    
    @Override
    public void updateEntity() {

        if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
            
            renderDoubles[0]+= 1;
        }
        
        if (worldObj.rand.nextInt(500) == 1) {
            UtilFluid.addFluidToTank(this, this.getOrientation(), new FluidStack(GU.FluidRegistry.LifeEssenceGas, 100), true);
        }
        
        TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());
        
        if (tile != null && tile instanceof IFluidHandler) {
            
            UtilFluid.moveFluid(this, this.getOrientation(), ((IFluidHandler) tile), this.getOrientation().getOpposite(), 100, true);
        }
    }
    
    @Override
    public void trigger(int id) {
        // TODO Auto-generated method stub
        super.trigger(id);
    }
}
