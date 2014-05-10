package GU.blocks.containers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileBase extends TileEntity {
    
    ForgeDirection orientation;
    
    public TileBase() {
        
        orientation = ForgeDirection.UP;
    }
    
    public ForgeDirection getOrientation() {
        
        return ForgeDirection.getOrientation(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        // super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        // TODO Auto-generated method stub
        // super.readFromNBT(tag);
    }
}
