package GU.blocks.containers;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.inventory.IInventory;
import net.minecraftforge.fluids.IFluidHandler;
import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.IMultiBlockPart;

public class TileMultiBase extends TileBase implements IMultiBlockPart {
    
    protected boolean isInMultiBlock = false, destoryTileWithNotMultiBlock = false;
    Set<IMultiBlock> multiBlocks = new HashSet<IMultiBlock>(), unchangable = new HashSet<IMultiBlock>();
    protected int fluidMultiBlocks, itemMultiBlock;
    
    public TileMultiBase() {
        
    }
    
    @Override
    public void updateEntity() {
        
        if (!this.isInMultiBlock && destoryTileWithNotMultiBlock) {
            
            worldObj.removeBlockTileEntity(xCoord, yCoord, zCoord);
        }
    }
    
    @Override
    public void invalidate() {
        
        for (IMultiBlock multi : multiBlocks)
            multi.invalidate();
        super.invalidate();
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        
        if (multiBlock instanceof IFluidHandler) {
            fluidMultiBlocks += 1;
        }
        
        if (multiBlock instanceof IInventory) {
            itemMultiBlock += 1;
        }
        if (multiBlocks.add(multiBlock)) {
            isInMultiBlock = true;
            unchangable = Collections.unmodifiableSet(multiBlocks);
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
        unchangable = Collections.unmodifiableSet(multiBlocks);
        isInMultiBlock = multiBlocks.isEmpty() ? false : true;
    }
    
    @Override
    public Set<IMultiBlock> getComprisedMultiBlocks() {
        
        return unchangable;
    }
}
