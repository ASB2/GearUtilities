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
import GU.GearUtilities;
import GU.blocks.containers.TileBase;
import GU.packets.TankUpdatePacket;
import UC.Wait;
import UC.Wait.IWaitTrigger;

public class TileCreativeFluid extends TileBase implements IFluidHandler {
    
    public FluidTank fluidToSave, fluidTank;
    Wait packetWait;
    
    public TileCreativeFluid() {
        
        fluidToSave = new FluidTank(1000);
        fluidTank = new FluidTank(1000000);
        packetWait = new Wait(new PacketWait(), 10, 0);
    }
    
    @Override
    public void updateEntity() {
        
        packetWait.update();
        
        boolean isPowered = worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
        
        if (!isPowered) {
            
            if (fluidTank.getFluid() != null) {
                
                fluidTank.getFluid().amount = 1000000;
            }
            else {
                
                fluidTank.setFluid(fluidToSave.getFluid());
                
                if (fluidTank.getFluid() != null) {
                    
                    fluidTank.getFluid().amount = 1000000;
                }
            }
        }
        else {
            
            fluidTank.setFluid(null);
        }
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
            
            if (tile != null && tile instanceof IFluidHandler) {
                
                if (!isPowered) {
                    
                    UtilFluid.moveFluid(this, direction, ((IFluidHandler) tile), direction.getOpposite(), 100, true);
                }
                else {
                    
                    UtilFluid.moveFluid(((IFluidHandler) tile), direction, this, direction.getOpposite(), 100, true);
                }
            }
        }
    }
    
    @Override
    public TileBase setTank(FluidTank tank, int id) {
        
        switch (id) {
        
            case 0:
                fluidToSave = tank;
                fluidTank.setFluid(tank.getFluid() != null ? tank.getFluid().copy() : null);
                this.packetWait.getTrigger().trigger(0);
                break;
            
            case 1:
                fluidTank = tank;
                break;
        }
        return super.setTank(tank, id);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        fluidToSave.readFromNBT(tag.getCompoundTag("fluidToSave"));
        fluidTank.readFromNBT(tag.getCompoundTag("fluidTank"));
        super.readFromNBT(tag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        tag.setTag("fluidToSave", fluidToSave.writeToNBT(new NBTTagCompound()));
        tag.setTag("fluidTank", fluidTank.writeToNBT(new NBTTagCompound()));
        super.writeToNBT(tag);
    }
    
    private class PacketWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (!worldObj.isRemote && fluidToSave.getFluid() != null) GearUtilities.getPipeline().sendToDimension(new TankUpdatePacket(xCoord, yCoord, zCoord, fluidToSave, 0), worldObj.provider.dimensionId);
        }
        
        @Override
        public boolean shouldTick(int id) {
            // TODO Auto-generated method stub
            return true;
        }
    }
    
    @Override
    public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
        
        return fluidTank.fill(resource, doFill);
    }
    
    @Override
    public boolean canFill(ForgeDirection from, Fluid fluid) {
        
        if (fluid != null) {
            
            if (fluidTank.getFluid() != null) {
                
                if (this.fluidTank.getFluid().isFluidEqual(new FluidStack(fluid, 0))) {
                    
                    return true;
                }
            }
            else {
                
                return true;
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
}
