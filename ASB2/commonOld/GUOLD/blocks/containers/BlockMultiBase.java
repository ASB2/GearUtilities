package GUOLD.blocks.containers;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import GUOLD.api.multiblock.IMultiBlock;
import GUOLD.api.multiblock.IMultiBlockPart;

public class BlockMultiBase extends ContainerBase {
    
    public BlockMultiBase(Material material) {
        super(material);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void onBlockPreDestroy(World world, int x, int y, int z, int par5) {
        
    }
    
    @Override
    public void onBlockHarvested(World world, int x, int y, int z, int par5, EntityPlayer par6EntityPlayer) {
        
        IMultiBlockPart multi = (IMultiBlockPart) world.getTileEntity(x, y, z);
        
        if (multi != null && multi instanceof TileMultiBase) {
            
            for (int i = 0; i < ((TileMultiBase) multi).multiBlocks.size(); i++) {
                
                IMultiBlock multiBlock = ((TileMultiBase) multi).multiBlocks.get(i);
                multiBlock.invalidate();
            }
        }
    }
    
    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
