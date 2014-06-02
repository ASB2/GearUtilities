package GU.blocks.containers.BlockMultiInterface;

import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import ASB2.utils.UtilVector;
import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.api.multiblock.MultiBlockAbstract.IFluidMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.blocks.containers.TileMultiBase;
import UC.math.vector.Vector3i;

public class TileFluidMultiInterface extends TileMultiBase implements IMultiBlockPart, IFluidHandler {
    
    int fluidPerTick = 100;
    
    IFluidMultiBlock handler1 = null, handler2 = null;
    Vector3i position;
    
    public TileFluidMultiInterface() {
        
        this.setMaxMultiBlocks(2);
    }
    
    @Override
    public void updateEntity() {
        
        if (position == null) {
            
            position = UtilVector.createTileEntityVector(this);
        }
        fluidPerTick = 10;
        // if (Minecraft.getSystemTime() % 5 == 0) {
        
        if (handler1 != null && handler2 != null) {
            
            IFluidTank handler1Tank = handler1.getFluidTank(position);
            
            IFluidTank handler2Tank = handler2.getFluidTank(position);
            
            if (handler1Tank != null && handler2Tank != null) {
                
                if (handler1Tank.getFluidAmount() > handler2Tank.getFluidAmount()) {
                    
                    FluidStack handler1Drain = handler1Tank.drain(fluidPerTick, false);
                    
                    if (handler1Drain != null) {
                        
                        int handler2FillAmount = handler2Tank.fill(handler1Drain.copy(), false);
                        
                        if (handler2FillAmount > 0) {
                            
                            handler2Tank.fill(handler1Tank.drain(fluidPerTick, true), true);
                        }
                    }
                }
                else if (handler1Tank.getFluidAmount() < handler2Tank.getFluidAmount()) {
                    
                    FluidStack handler2Drain = handler2Tank.drain(fluidPerTick, false);
                    
                    if (handler2Drain != null) {
                        
                        int handler1FillAmount = handler1Tank.fill(handler2Drain.copy(), false);
                        
                        if (handler1FillAmount > 0) {
                            
                            handler1Tank.fill(handler2Tank.drain(fluidPerTick, true), true);
                        }
                    }
                }
            }
            // }
        }
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        
        if (super.addMultiBlock(multiBlock)) {
            
            if (multiBlock instanceof IFluidMultiBlock) {
                
                if (handler1 == null) {
                    
                    handler1 = (IFluidMultiBlock) multiBlock;
                }
                else if (handler2 == null) {
                    
                    handler2 = (IFluidMultiBlock) multiBlock;
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        
        if (handler1 == multiBlock) {
            
            handler1 = null;
            
            if (handler2 != null) {
                
                handler1 = handler2;
                handler2 = null;
            }
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
            
            return handler1.fill(position, from, resource, doFill);
        }
        return 0;
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        if (handler1 != null) {
            
            return handler1.canFill(position, from, fluid);
        }
        return false;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        
        if (handler1 != null) {
            
            return handler1.drain(position, from, resource, doDrain);
        }
        return null;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        
        if (handler1 != null) {
            
            return handler1.drain(position, from, maxDrain, doDrain);
        }
        return null;
    }
    
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        if (handler1 != null) {
            
            return handler1.canDrain(position, from, fluid);
        }
        return false;
    }
    
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        
        if (handler1 != null) {
            
            return handler1.getTankInfo(position, from);
        }
        return null;
    }
}
