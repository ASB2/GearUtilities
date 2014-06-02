package GU.blocks.containers.BlockMultiInterface;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.blocks.containers.TileMultiBase;

public class TileFluidMultiInterface extends TileMultiBase implements IMultiBlockPart, IFluidHandler {
    
    IFluidHandler handler1 = null, handler2 = null;
    
    public TileFluidMultiInterface() {
        
        this.setMaxMultiBlocks(2);
    }
    
    @Override
    public void updateEntity() {
        
        if (handler1 != null && handler2 != null) {
            
        }
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        
        if (super.addMultiBlock(multiBlock)) {
            
            if (multiBlock instanceof IFluidHandler) {
                
                if (handler1 == null) {
                    
                    handler1 = (IFluidHandler) multiBlock;
                }
                else if (handler2 == null) {
                    
                    handler2 = (IFluidHandler) multiBlock;
                }
            }
        }
        return false;
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        
        if (handler1 == multiBlock) {
            
            handler1 = null;
        }
        else if (handler2 == multiBlock) {
            
            handler2 = null;
        }
    }
    
    @Override
    public boolean isPositionValid(EnumMultiBlockPartPosition position) {
        
        return position == EnumMultiBlockPartPosition.FACE;
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        if (handler1 != null) {
            
            return handler1.fill(from, resource, doFill);
        }
        return 0;
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        if (handler1 != null) {
            
            return handler1.canFill(from, fluid);
        }
        return false;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        
        if (handler1 != null) {
            
            return handler1.drain(from, resource, doDrain);
        }
        return null;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        
        if (handler1 != null) {
            
            return handler1.drain(from, maxDrain, doDrain);
        }
        return null;
    }
    
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        if (handler1 != null) {
            
            return handler1.canDrain(from, fluid);
        }
        return false;
    }
    
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        
        if (handler1 != null) {
            
            return handler1.getTankInfo(from);
        }
        return null;
    }
}
