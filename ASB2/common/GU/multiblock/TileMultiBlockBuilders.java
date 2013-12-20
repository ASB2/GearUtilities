package GU.multiblock;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.IMultiBlockPart;
import GU.blocks.containers.TileBase;

public class TileMultiBlockBuilders extends TileBase implements IMultiBlockPart {
    
    Set<IMultiBlock> multiBlocks = new HashSet<IMultiBlock>();
    
    public TileMultiBlockBuilders() {
        
        this.useSidesRendering = false;
    }
    
    @Override
    public void updateEntity() {
    }
    
    @Override
    public void invalidate() {
        
        for (IMultiBlock multi : multiBlocks)
            multi.invalidate();
        
        super.invalidate();
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        
        return multiBlocks.add(multiBlock);
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        
        multiBlocks.remove(multiBlock);
    }
    
    @Override
    public Set<IMultiBlock> getComprisedMultiBlocks() {
        
        return Collections.unmodifiableSet(multiBlocks);
    }
    
    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {
        // TODO Auto-generated method stub
        // super.triggerBlock(world, isSneaking, itemStack, x, y, z, side);
    }
}
