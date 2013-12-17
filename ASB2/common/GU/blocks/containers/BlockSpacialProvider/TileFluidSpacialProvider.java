package GU.blocks.containers.BlockSpacialProvider;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileFluidSpacialProvider extends TileSpacialProvider implements IFluidHandler {
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).fill(from, resource, doFill);
        }
        return 0;
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).canFill(from, fluid);
        }
        return false;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).drain(from, resource, doDrain);
        }
        return null;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).drain(from, maxDrain, doDrain);
        }
        return null;
    }
    
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).canDrain(from, fluid);
        }
        return false;
    }
    
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        
        if (this.getCurrentStructure() != null && this.getCurrentStructure() instanceof IFluidHandler) {
            
            return ((IFluidHandler) this.getCurrentStructure()).getTankInfo(from);
        }
        return null;
    }
}
