package GU.multiblock;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.multiblock.IMultiBlockPart;
import GU.api.multiblock.MultiBlockBase;
import GU.blocks.containers.TileBase;

public class TileMultiBlockBuilders extends TileBase implements IMultiBlockPart {
    
    MultiBlockBase currentMultiBlock;
    
    public TileMultiBlockBuilders() {
        
        this.useSidesRendering = false;
    }
    
    @Override
    public void updateEntity() {
    }
    
    @Override
    public boolean setStructure(MultiBlockBase multiBlock) {
        
        if (currentMultiBlock == null) {
            
            currentMultiBlock = multiBlock;
            return true;
        }
        return false;
    }
    
    @Override
    public void removeStructure() {
        
        currentMultiBlock = null;
    }
    
    @Override
    public MultiBlockBase getCurrentStructure() {
        
        return currentMultiBlock;
    }
    
    @Override
    public void invalidate() {
        
        if (this.getCurrentStructure() != null) {
            this.getCurrentStructure().invalidate();
        }
        super.invalidate();
    }
    
    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {
        // TODO Auto-generated method stub
        // super.triggerBlock(world, isSneaking, itemStack, x, y, z, side);
    }
}
