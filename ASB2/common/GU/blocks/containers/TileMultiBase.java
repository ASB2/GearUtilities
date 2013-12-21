package GU.blocks.containers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.IMultiBlockPart;

public class TileMultiBase extends TileBase implements IMultiBlockPart {
    
    protected boolean isInMultiBlock = false;
    Set<IMultiBlock> multiBlocks = new HashSet<IMultiBlock>();
    
    public TileMultiBase() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void invalidate() {
        
        for (IMultiBlock multi : multiBlocks)
            multi.invalidate();
        
        super.invalidate();
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        isInMultiBlock = true;
        return multiBlocks.add(multiBlock);
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        
        multiBlocks.remove(multiBlock);
        
        isInMultiBlock = multiBlocks.isEmpty() ? false : true;
    }
    
    @Override
    public Set<IMultiBlock> getComprisedMultiBlocks() {
        
        return Collections.unmodifiableSet(multiBlocks);
    }
}
