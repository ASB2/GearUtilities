package GU.blocks.containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;

public class TileMultiBase extends TileBase implements IMultiBlockPart {
    
    protected boolean isInMultiBlock = false, destoryTileWithNotMultiBlock = false, destroyBlockWithNoMultiBlock = false;
    public List<IMultiBlock> multiBlocks = new ArrayList<IMultiBlock>(), unchangable = new ArrayList<IMultiBlock>();
    protected int maxMultiBlocks = -1;
    
    private int wait = 0;
    
    protected void checkInvalidMultiBlocks() {
        
        wait++;
        
        if (wait == 5) {
            wait = 0;
            for (int index = 0; index < multiBlocks.size(); index++) {
                
                IMultiBlock block = multiBlocks.get(index);
                
                if (!block.isValid()) {
                    
                    this.removeMultiBlock(block);
                }
            }
        }
    }
    
    @Override
    public void updateEntity() {
        
        if (!this.isInMultiBlock && destoryTileWithNotMultiBlock) {
            
            worldObj.removeTileEntity(xCoord, yCoord, zCoord);
            
            if (destroyBlockWithNoMultiBlock) {
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
            }
        }
        
        checkInvalidMultiBlocks();
    }
    
    public TileMultiBase setMaxMultiBlocks(int maxMultiBlocks) {
        this.maxMultiBlocks = maxMultiBlocks;
        return this;
    }
    
    public int getMaxMultiBlocks() {
        
        return maxMultiBlocks;
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        
        if (maxMultiBlocks != -1) {
            
            if (multiBlocks.size() + 1 > maxMultiBlocks) {
                
                return false;
            }
        }
        
        if (multiBlocks.contains(multiBlock) || multiBlocks.add(multiBlock)) {
            
            isInMultiBlock = true;
            unchangable = Collections.unmodifiableList(multiBlocks);
            return true;
        }
        return false;
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        
        multiBlocks.remove(multiBlock);
        unchangable = Collections.unmodifiableList(multiBlocks);
        isInMultiBlock = multiBlocks.isEmpty() ? false : true;
    }
    
    @Override
    public List<IMultiBlock> getMultiBlocks() {
        
        return unchangable;
    }
    
    @Override
    public boolean isPositionValid(EnumMultiBlockPartPosition position) {
        
        return true;
    }
}