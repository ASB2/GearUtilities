package GU.blocks.containers.BlockMultiInterface;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.TileMultiBase;

public class TileFluidMultiInterface extends TileMultiBase implements IFluidHandler {
    
    public TileFluidMultiInterface() {
        
        this.destoryTileWithNotMultiBlock = true;
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        for (IMultiBlock multi : this.getComprisedMultiBlocks()) {
            
            if (multi instanceof IFluidHandler) {
                
                int fill = ((IFluidHandler) multi).fill(from, resource, doFill);
                
                if (fill > 0) {
                    
                    return fill;
                }
            }
        }
        return 0;
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        for (IMultiBlock multi : this.getComprisedMultiBlocks()) {
            
            if (multi instanceof IFluidHandler) {
                
                boolean canFill = ((IFluidHandler) multi).canFill(from, fluid);
                
                if (canFill) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        
        for (IMultiBlock multi : this.getComprisedMultiBlocks()) {
            
            if (multi instanceof IFluidHandler) {
                
                FluidStack drain = ((IFluidHandler) multi).drain(from, resource, doDrain);
                
                if (drain != null && drain.amount > 0) {
                    
                    return drain;
                }
            }
        }
        return null;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        
        for (IMultiBlock multi : this.getComprisedMultiBlocks()) {
            
            if (multi instanceof IFluidHandler) {
                
                FluidStack drain = ((IFluidHandler) multi).drain(from, maxDrain, doDrain);
                
                if (drain != null && drain.amount > 0) {
                    
                    return drain;
                }
            }
        }
        return null;
    }
    
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        for (IMultiBlock multi : this.getComprisedMultiBlocks()) {
            
            if (multi instanceof IFluidHandler) {
                
                boolean canD = ((IFluidHandler) multi).canDrain(from, fluid);
                
                if (canD) {
                    
                    return canD;
                }
            }
        }
        return false;
    }
    
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        
        List<FluidTankInfo> info = new ArrayList<FluidTankInfo>();
        
        for (IMultiBlock multi : this.getComprisedMultiBlocks()) {
            
            if (multi instanceof IFluidHandler) {
                
                for (FluidTankInfo handler : ((IFluidHandler) multi).getTankInfo(from)) {
                    
                    info.add(handler);
                }
            }
        }
        return (FluidTankInfo[]) info.toArray();
    }
}
