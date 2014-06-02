package GU.api.multiblock;

import java.util.List;

import net.minecraft.world.World;

public class MultiBlockAbstract {
    
    private MultiBlockAbstract() {
        
    }
    
    public static interface IMultiBlock {
        
        void onBlockBreak(int x, int y, int z);
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
}
