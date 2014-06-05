package GU.blocks.containers.BlockCreativeMetadata;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;
import GU.GearUtilities;
import GU.blocks.containers.TileBase;
import GU.packets.TankUpdatePacket;
import UC.Wait;
import UC.Wait.IWaitTrigger;

public class TileCreativeFluid extends TileBase {
    
    FluidTank fluidToSave, fluidTank;
    Wait packetWait;
    
    public TileCreativeFluid() {
        
        fluidToSave = new FluidTank(1000);
        fluidTank = new FluidTank(1000);
        packetWait = new Wait(new PacketWait(), 20, 0);
    }
    
    @Override
    public void updateEntity() {
        
        packetWait.update();
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
            
            if (tile != null && tile instanceof IFluidHandler) {
                
                // inventoryList.add((IInventory) tile);
            }
        }
    }
    
    @Override
    public TileBase setTank(FluidTank tank, int id) {
        
        switch (id) {
        
            case 0:
                fluidToSave = tank;
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
        tag.setTag("fuelTank", fluidTank.writeToNBT(new NBTTagCompound()));
        super.writeToNBT(tag);
    }
    
    private class PacketWait implements IWaitTrigger {
        
        @Override
        public void trigger(int id) {
            
            if (!worldObj.isRemote) GearUtilities.getPipeline().sendToDimension(new TankUpdatePacket(xCoord, yCoord, zCoord, fluidToSave, 0), worldObj.provider.dimensionId);
        }
        
        @Override
        public boolean shouldTick(int id) {
            // TODO Auto-generated method stub
            return true;
        }
    }
}
