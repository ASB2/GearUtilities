package GUOLD.blocks.containers.BlockCentrifuge;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilFluid;
import GUOLD.blocks.containers.TileFluidBase;

public class TileCentrifuge extends TileFluidBase {
    
    FluidTank outputTank = new FluidTank(1000);
    
    public TileCentrifuge() {
        
        fluidTank = new FluidTank(1000);
    }
    
    @Override
    public void updateEntity() {
        
        TileEntity source = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation());
        TileEntity destination = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation().getOpposite());
        
        if (source != null && source instanceof IFluidHandler) {
            
            UtilFluid.moveFluid((IFluidHandler) source, this.getOrientation(), this.getOrientation().getOpposite(), this, 2, true);
        }
        
        if (fluidTank.drain(2, false) != null && outputTank.fill(new FluidStack(GUOLD.FluidRegistry.LifeEssenceLiquid, 1), false) > 0) {
            
            fluidTank.drain(2, true);
            outputTank.fill(new FluidStack(GUOLD.FluidRegistry.LifeEssenceLiquid, 1), true);
        }
        
        if (destination != null && destination instanceof IFluidHandler) {
            
            UtilFluid.moveFluid(this, this.getOrientation().getOpposite(), this.getOrientation(), (IFluidHandler) destination, 1, true);
        }
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        return GUOLD.FluidRegistry.LifeEssenceGas == resource.getFluid() ? super.fill(from, resource, doFill) : 0;
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        return GUOLD.FluidRegistry.LifeEssenceGas == fluid ? super.canFill(from, fluid) : false;
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
        
        if (resource == null || !resource.isFluidEqual(outputTank.getFluid())) {
            
            return null;
        }
        
        return outputTank.drain(resource.amount, doDrain);
    }
    
    @Override
    public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
        
        return outputTank.drain(maxDrain, doDrain);
    }
    
    @Override
    public boolean canDrain(ForgeDirection from, Fluid fluid) {
        
        if (this.outputTank.getFluid() != null) {
            
            if (outputTank.getFluidAmount() > 0) {
                
                if (this.outputTank.getFluid().isFluidEqual(new FluidStack(fluid, 1))) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public FluidTankInfo[] getTankInfo(ForgeDirection from) {
        
        return new FluidTankInfo[] { this.getOrientation() == from ? fluidTank.getInfo() : this.getOrientation().getOpposite() == from ? outputTank.getInfo() : null };
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        
        outputTank.readFromNBT(tag.getCompoundTag("outputTank"));
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        
        tag.setTag("outputTank", outputTank.writeToNBT(new NBTTagCompound()));
    }
}
