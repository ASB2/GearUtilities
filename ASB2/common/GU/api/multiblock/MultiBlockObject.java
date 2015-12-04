package GU.api.multiblock;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import UC.math.vector.Vector3i;

public class MultiBlockObject {
    
    private MultiBlockObject() {
        // TODO Auto-generated constructor stub
    }
    
    public static class FluidHandlerWrapper implements IFluidHandler {
        
        public FluidTank fluidTank = new FluidTank(0);
        
        public FluidHandlerWrapper(int capacity) {
            
            fluidTank.setCapacity(capacity);
        }
        
        public FluidHandlerWrapper() {
            
        }
        
        public FluidHandlerWrapper setFluidTank(FluidTank tank) {
            
            fluidTank = tank;
            return this;
        }
        
        public FluidTank getFluidTank() {
            
            return fluidTank;
        }
        
        @Override
        public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
            
            return fluidTank.fill(resource, doFill);
        }
        
        @Override
        public boolean canFill(ForgeDirection from, Fluid fluid) {
            
            if (fluidTank != null) {
                
                if (fluid != null) {
                    
                    if (fluidTank.getFluid() != null) {
                        
                        if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 0))) {
                            
                            return true;
                        }
                    } else {
                        
                        return true;
                    }
                }
            }
            return false;
        }
        
        @Override
        public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
            
            if (resource == null || !resource.isFluidEqual(fluidTank.getFluid())) {
                
                return null;
            }
            
            return fluidTank.drain(resource.amount, doDrain);
        }
        
        @Override
        public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
            
            return fluidTank.drain(maxDrain, doDrain);
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
        
        public IFluidTank getFluidTank(Vector3i tilePosition) {
            
            return fluidTank;
        }
        
        public NBTTagCompound save(NBTTagCompound tag) {
            
            tag.setTag("fluidTank", fluidTank.writeToNBT(new NBTTagCompound()));
            return tag;
        }
        
        public void load(NBTTagCompound tag) {
            
            fluidTank.readFromNBT(tag.getCompoundTag("fluidTank"));
        }
    }
}
