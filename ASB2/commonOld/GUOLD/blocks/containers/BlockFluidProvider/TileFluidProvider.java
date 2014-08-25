package GUOLD.blocks.containers.BlockFluidProvider;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import GUOLD.EnumState;
import GUOLD.blocks.containers.TileBase;

public class TileFluidProvider extends TileBase implements IFluidHandler {
    
    public TileFluidProvider() {
        
        fluidTank = new FluidTank(1000);
        sideState = new EnumState[] { EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE };
    }
    
    @Override
    public void updateEntity() {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, direction);
            
            if (tile != null) {
                
                if (tile instanceof IFluidHandler) {
                    
                    IFluidHandler fTile = (IFluidHandler) tile;
                    
                    if (this.getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {
                        
                        if (fluidTank.getFluid() != null) {
                            
                            UtilFluid.addFluidToTank(fTile, direction, fluidTank.getFluid(), true);
                        }
                    } else if (this.getSideStateArray(direction.ordinal()) == EnumState.INPUT) {
                        
                        UtilFluid.removeFluidFromTank(fTile, direction, 100, true);
                    }
                }
            }
        }
    }
    
    public void setFluid(FluidStack fluid) {
        
        fluidTank.setFluid(fluid);
        fluidTank.setCapacity(fluid.amount);
    }
    
    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {
        
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        
        if (isSneaking)
            direction = direction.getOpposite();
        
        sideState[direction.ordinal()] = sideState[direction.ordinal()].increment();
        world.markBlockForUpdate(xCoord, yCoord, zCoord);
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        return 0;
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        return false;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        
        return fluidTank.drain(resource.amount, false);
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        
        return fluidTank.drain(maxDrain, false);
    }
    
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        if (this.fluidTank.getFluid() != null) {
            
            if (fluidTank.getFluidAmount() > 0) {
                
                if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        
        return new FluidTankInfo[] { fluidTank.getInfo() };
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
    }
}
