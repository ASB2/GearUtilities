package GU.api.multiblock;

import java.util.List;

import net.minecraft.block.Block;

public class MultiBlockAbstract {
    
    private MultiBlockAbstract() {
        
    }
    
    public static interface IMultiBlock {
        
        IMultiBlockStructure getStructure();
    }
    
    public static interface IMultiBlockPart {
        
        boolean addMultiBlock(IMultiBlock multiBlock);
        
        void removeMultiBlock(IMultiBlock multiBlock);
        
        List<IMultiBlock> getMultiBlocks();
    }
    
    public static interface IMultiBlockCore extends IMultiBlockPart {
        
    }
    
    public static interface IMultiBlockMarker {
        
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
