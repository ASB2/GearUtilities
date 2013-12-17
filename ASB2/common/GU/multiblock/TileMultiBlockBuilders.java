package GU.multiblock;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.multiblock.IMultiBlockPart;
import GU.api.multiblock.MultiBlockManager;
import GU.blocks.containers.TileBase;

public class TileMultiBlockBuilders extends TileBase implements IMultiBlockPart {
    
    MultiBlockManager currentMultiBlock;
    
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
    public boolean setStructure(MultiBlockManager multiBlock) {
        
        if (currentMultiBlock == null) {
            
            currentMultiBlock = multiBlock;
            return true;
        }
        return false;
    }
    
    @Override
    public void removeStructure(MultiBlockManager multiBlock) {
        
        currentMultiBlock = null;
    }
    
    @Override
    public MultiBlockManager getCurrentStructure() {
        
        return currentMultiBlock;
    }
    
    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {
        // TODO Auto-generated method stub
        // super.triggerBlock(world, isSneaking, itemStack, x, y, z, side);
    }
}
