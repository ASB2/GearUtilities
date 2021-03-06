package GU.blocks.containers;

import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;

public abstract class BlockMultiContainerBase extends BlockContainerBase {
    
    public BlockMultiContainerBase(Material material) {
        super(material);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            IMultiBlockPart multiBlockPart = ((IMultiBlockPart) tile);
            
            List<IMultiBlock> multiBlocks = multiBlockPart.getMultiBlocks();
            
            for (int index = 0; index < multiBlocks.size(); index++) {
                
                IMultiBlock multiBlock = multiBlocks.get(index);
                
                multiBlock.onBlockBreak(x, y, z);
            }
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }
}
