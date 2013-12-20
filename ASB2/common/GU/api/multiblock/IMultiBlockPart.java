package GU.api.multiblock;

import java.util.Set;

public interface IMultiBlockPart {
    
    boolean addMultiBlock(IMultiBlock multiBlock);
    
    void removeMultiBlock(IMultiBlock multiBlock);
    
    Set<IMultiBlock> getComprisedMultiBlocks();
}
