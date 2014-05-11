package GUOLD.blocks.containers.BlockEssenceDiffuser;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import GUOLD.blocks.containers.TileFluidBase;

public class TileEssenceDiffuser extends TileFluidBase {
    
    public TileEssenceDiffuser() {
        
        this.fluidTank = new FluidTank(GUOLD.FluidRegistry.LifeEssenceGas, 0, 1000);
        renderDoubles = new double[]{.1, 0, 0, 0};
    }
    
    @Override
    public void updateEntity() {


        if (renderDoubles[3] == 0) {
            
            if (renderDoubles[0] < 1) {
                
                renderDoubles[0] += .01;
            } else if (renderDoubles[1] < 1) {
                
                renderDoubles[1] += .01;
            } else if (renderDoubles[2] < 1) {
                
                renderDoubles[2] += .001;
            } else {
                renderDoubles[3] = 1;
            }
        } else {
            
            if (renderDoubles[0] > 0) {
                
                renderDoubles[0] -= .01;
            } else if (renderDoubles[1] > 0) {
                
                renderDoubles[1] -= .01;
            } else if (renderDoubles[2] > 0) {
                
                renderDoubles[2] -= .01;
            } else {
                
                renderDoubles[3] = 0;
            }
        }
        
        if (worldObj.rand.nextInt(500) == 1) {
            UtilFluid.addFluidToTank(this, this.getOrientation(), new FluidStack(GUOLD.FluidRegistry.LifeEssenceGas, 100), true);
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
