package GU.blocks.containers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraft.inventory.IInventory;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;

public class TileMultiBase extends TileBase implements IMultiBlockPart {
    
    protected boolean isInMultiBlock = false, destoryTileWithNotMultiBlock = false, destroyBlockWithNoMultiBlock = false;
    protected List<IMultiBlock> multiBlocks = new ArrayList<IMultiBlock>(), unchangable = new ArrayList<IMultiBlock>();
    protected int fluidMultiBlocks, itemMultiBlock;
    protected int maxMultiBlocks = -1;
    
    @Override
    public void updateEntity() {
        
        if (!this.isInMultiBlock && destoryTileWithNotMultiBlock) {
            
            worldObj.removeTileEntity(xCoord, yCoord, zCoord);
            
            if (destroyBlockWithNoMultiBlock) {
                worldObj.setBlockToAir(xCoord, yCoord, zCoord);
            }
        }
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
        
        if (multiBlocks.add(multiBlock)) {
            
            if (multiBlock instanceof IFluidHandler) {
                fluidMultiBlocks += 1;
            }
            
            if (multiBlock instanceof IInventory) {
                itemMultiBlock += 1;
            }
            
            isInMultiBlock = true;
            unchangable = Collections.unmodifiableList(multiBlocks);
            return true;
        }
        return false;
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        
        if (multiBlock instanceof IFluidHandler) {
            fluidMultiBlocks -= 1;
        }
        
        if (multiBlock instanceof IInventory) {
            itemMultiBlock -= 1;
        }
        
        multiBlocks.remove(multiBlock);
        unchangable = Collections.unmodifiableList(multiBlocks);
        isInMultiBlock = multiBlocks.isEmpty() ? false : true;
    }
    
    @Override
    public List<IMultiBlock> getMultiBlocks() {
        
        return unchangable;
    }
}