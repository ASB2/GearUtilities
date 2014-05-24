package GU.blocks.containers;

import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BlockMultiMetadataContainerBase extends BlockMetadataContainerBase {
    
    public BlockMultiMetadataContainerBase(Material material) {
        super(material);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata) {
        
        TileEntity tile = world.getTileEntity(x, y, z);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            IMultiBlockPart multiBlockPart = ((IMultiBlockPart) tile);
            
            for (IMultiBlock multiBlock : multiBlockPart.getMultiBlocks()) {
                
                multiBlock.onBlockBreak(x, y, z);
            }
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }
}
