package GU.api.multiblock;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidHandler;
import UC.math.vector.Vector3i;

public class MultiBlockAbstract {
    
    private MultiBlockAbstract() {
        
    }
    
    public static interface IMultiBlock {
        
        void onBlockBreak(int x, int y, int z);
        
        boolean isValid();
    }
    
    public static interface IMultiBlockPart {
        
        boolean addMultiBlock(IMultiBlock multiBlock);
        
        void removeMultiBlock(IMultiBlock multiBlock);
        
        List<IMultiBlock> getMultiBlocks();
        
        boolean isPositionValid(EnumMultiBlockPartPosition position);
    }
    
    public static interface IMultiBlockCore extends IMultiBlockPart {
        
    }
    
    public static interface IMultiBlockMarker {
        
        boolean isValid(World world, int x, int y, int z);
    }
    
    public static enum EnumMultiBlockPartPosition {
        
        EDGE, CORNER, FACE, INNER;
    }
    
    public static interface IFluidMultiBlock extends IMultiBlock {
        
        IFluidHandler getTank(Vector3i tilePosition);
    }
    
    public static interface IInventoryMultiBlock extends IMultiBlock {
        
        IInventory getInventory(Vector3i tilePosition);
    }
    
    public static interface IRedstoneMultiBlock extends IMultiBlock {
        
        int getRedstoneLevel(Vector3i tilePosition);
    }
    
    public static interface IGuiMultiBlock extends IMultiBlock {
        
        boolean openGui(Vector3i position, EntityPlayer player, int side);
    }
    
    public static interface IItemInterface extends IMultiBlockPart {
        
        List<IInventory> getAvaliableInventorys();
    }
    
    public static interface IFluidInterface extends IMultiBlockPart {
        
        List<IFluidHandler> getAvaliableInventorys();
    }
    
    public static interface IDataInterface extends IMultiBlockPart {
        
        boolean setMultiBlockData(NBTTagCompound tag);
        
        NBTTagCompound getMultiBlockData();
    }
}
