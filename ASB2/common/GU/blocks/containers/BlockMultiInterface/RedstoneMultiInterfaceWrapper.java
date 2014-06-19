package GU.blocks.containers.BlockMultiInterface;

import UC.math.vector.Vector3i;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IRedstoneMultiBlock;
import GU.blocks.BlockMetadata.MetadataWrapper;
import GU.blocks.containers.TileMultiBase;

public class RedstoneMultiInterfaceWrapper extends MetadataWrapper {
    
    public RedstoneMultiInterfaceWrapper(String[] iconNames) {
        super(iconNames);
    }
    
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        
        return true;
    }
    
    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        
        return 0;
    }
    
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
        
        TileMultiBase tile = (TileMultiBase) world.getTileEntity(x, y, z);
        
        if (tile != null && ((TileMultiBase) tile).multiBlocks.size() == 1) {
            
            IMultiBlock multi = ((TileMultiBase) tile).multiBlocks.get(0);
            
            if (multi instanceof IRedstoneMultiBlock) {
                
                return ((IRedstoneMultiBlock) multi).getLevel(new Vector3i(x, y, z));
            }
        }
        return 16;
    }
    
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        
        TileMultiBase tile = (TileMultiBase) world.getTileEntity(x, y, z);
        
        if (tile != null && ((TileMultiBase) tile).multiBlocks.size() == 1) {
            
            IMultiBlock multi = ((TileMultiBase) tile).multiBlocks.get(0);
            
            if (multi instanceof IRedstoneMultiBlock) {
                
                return ((IRedstoneMultiBlock) multi).getLevel(new Vector3i(x, y, z));
            }
        }
        return 16;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        
        return new TileRedstoneMultiInterface();
    }
}
