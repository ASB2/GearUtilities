package GU.blocks.containers.BlockCreativeMetadata;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import ASB2.utils.UtilFluid;
import GU.blocks.containers.TileBase;

public class TileCreativeFluid extends TileBase implements IFluidHandler {
    
    public FluidTank fluidTank;
    
    public TileCreativeFluid() {
        
        fluidTank = new FluidTank(1000000);
    }
    
    @Override
    public void updateEntity() {
        
        boolean isPowered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
        
        if (!isPowered) {
            
            if (fluidTank.getFluid() != null) {
                
                fluidTank.getFluid().amount = 1000000;
            } else {
                
                if (fluidTank.getFluid() != null) {
                    
                    fluidTank.getFluid().amount = 1000000;
                }
            }
        } else {
            
            fluidTank.setFluid(null);
        }
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
            
            if (tile != null && tile instanceof IFluidHandler) {
                
                if (!isPowered) {
                    
                    UtilFluid.moveFluid(this, direction, ((IFluidHandler) tile), direction.getOpposite(), 100, true);
                } else {
                    
                    UtilFluid.moveFluid(((IFluidHandler) tile), direction, this, direction.getOpposite(), 100, true);
                }
            }
        }
    }
    
    @Override
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        
        tag.setTag("fluidTank", fluidTank.writeToNBT(new NBTTagCompound()));
        this.sendNBTPacket(tag, 0);
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {

        fluidTank.readFromNBT(tag.getCompoundTag("fluidTank"));
        super.readNBTPacket(tag, id);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {

        fluidTank.readFromNBT(tag.getCompoundTag("fluidTank"));
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {

        tag.setTag("fluidTank", fluidTank.writeToNBT(new NBTTagCompound()));
        super.writeToNBT(tag);
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        return resource != null ? resource.amount : 0;
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        return true;
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
        
        if (this.fluidTank.getFluid() != null && fluid != null) {
            
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
}
