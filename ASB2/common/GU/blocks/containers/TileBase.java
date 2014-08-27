package GU.blocks.containers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTank;
import GU.GearUtilities;
import GU.packets.NBTPacket;

public class TileBase extends TileEntity {
    
    ForgeDirection orientation;
    
    public TileBase() {
        
        orientation = ForgeDirection.UP;
    }
    
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
    }
    
    public void sendNBTPacket(NBTTagCompound tag, int id) {
        
        if (!worldObj.isRemote)
            GearUtilities.getPipeline().sendToDimension(new NBTPacket(xCoord, yCoord, zCoord, tag, id), worldObj.provider.dimensionId);
    }
    
    public ForgeDirection getOrientation() {
        
        return ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
    }
    
    public TileBase setTank(FluidTank tank, int id) {
        
        return this;
    }
    
    public FluidTank getTank() {
        
        return null;
    }
    
    @Override
    public Packet getDescriptionPacket() {
        
        sendUpdatePacket();
        return null;
    }
    
    public void sendUpdatePacket() {
        
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        // TODO Auto-generated method stub
        super.readFromNBT(tag);
    }
}
