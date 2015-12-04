package GU.blocks.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import GU.GearUtilities;
import GU.packets.NBTPacket;

public class TileBase extends TileEntity {
    
    EnumFacing orientation;
    
    public TileBase() {
        
        orientation = EnumFacing.UP;
    }
    
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
    }
    
    public void sendNBTPacket(NBTTagCompound tag, int id) {
        
        if (!worldObj.isRemote)
            GearUtilities.getPipeline().sendToDimension(new NBTPacket(pos, tag, id), worldObj.provider.getDimensionId());
    }
    
    public EnumFacing getOrientation() {
        
        return EnumFacing.getOrientation(worldObj.getBlockMetadata(pos));
    }
    
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        
        return false;
    }
    
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        return rotateBlock(world, x, y, z, axis);
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
