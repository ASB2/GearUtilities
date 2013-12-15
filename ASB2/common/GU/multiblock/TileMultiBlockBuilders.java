package GU.multiblock;

import java.util.HashSet;
import java.util.Set;

import GU.blocks.containers.TileBase;
import GU.api.multiblock.*;

public class TileMultiBlockBuilders extends TileBase implements IMultiBlockPart {
    
    Set<MultiBlockManager> multiBlocksIAmIn = new HashSet<MultiBlockManager>();
    
    public TileMultiBlockBuilders() {
        
        this.useSidesRendering = false;
    }
    
    @Override
    public void updateEntity() {
        
        // if (multiBlocksIAmIn.isEmpty()) {
        // worldObj.setBlockToAir(xCoord, yCoord, zCoord);
        // }
        super.updateEntity();
    }
    
    @Override
    public boolean addToMultiBlock(MultiBlockManager multiBlock) {
        
        return multiBlocksIAmIn.add(multiBlock);
    }
    
    @Override
    public void removeMultiBlock(MultiBlockManager multiBlock) {
        
        multiBlocksIAmIn.remove(multiBlock);
    }
    
    @Override
    public Set<MultiBlockManager> getComprizedStructures() {
        
        return multiBlocksIAmIn;
    }
}
