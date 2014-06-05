package GU.blocks.containers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTank;

public class TileBase extends TileEntity {
    
    ForgeDirection orientation;
    
    public TileBase() {
        
        orientation = ForgeDirection.UP;
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
    public void writeToNBT(NBTTagCompound tag) {
        
        super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        // TODO Auto-generated method stub
        super.readFromNBT(tag);
    }
}
