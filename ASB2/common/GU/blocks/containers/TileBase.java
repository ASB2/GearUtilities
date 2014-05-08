package GU.blocks.containers;

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
}
