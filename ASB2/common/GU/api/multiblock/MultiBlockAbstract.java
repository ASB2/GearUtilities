package GU.api.multiblock;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class MultiBlockAbstract {
    
    private MultiBlockAbstract() {
        
    }
    
    public static interface IMultiBlock {
        
        IMultiBlockStructure getStructure();
        
        void onBlockBreak(int x, int y, int z);
    }
    
    public static interface IMultiBlockPart {
        
        boolean addMultiBlock(IMultiBlock multiBlock);
        
        void removeMultiBlock(IMultiBlock multiBlock);
        
        List<IMultiBlock> getMultiBlocks();
    }
    
    public static interface IMultiBlockCore extends IMultiBlockPart {
        
    }
    
    public static interface IMultiBlockMarker {
        
        boolean isValid(World world, int x, int y, int z);
    }
    
    public static interface IMultiBlockStructure {
        
        /**
         * Gets structures orginized from top to bottom
         * 
         */
        IMultiBlockRow[] getStructure();
    }
    
    public static interface IMultiBlockRow {
        
        int getXSize();
        
        int getZSize();
        
        Block getBlockAtPosition(int x, int z);
    }
}
