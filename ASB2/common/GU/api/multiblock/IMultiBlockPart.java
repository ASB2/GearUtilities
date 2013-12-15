package GU.api.multiblock;

import java.util.Set;

public interface IMultiBlockPart {
    
    boolean addToMultiBlock(MultiBlockManager multiBlock);    
    void removeMultiBlock(MultiBlockManager multiBlock);
    Set<MultiBlockManager> getComprizedStructures();
}
