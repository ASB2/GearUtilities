package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IRedstoneMultiBlock;
import GU.blocks.BlockMetadata.BlockMetadataWrapper;
import GU.blocks.containers.TileMultiBase;
import UC.math.vector.Vector3i;

public class RedstoneMultiInterfaceWrapper extends BlockMetadataWrapper {
    
    public RedstoneMultiInterfaceWrapper(String[] iconNames) {
        super(iconNames);
    }
    
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        
        return true;
    }
    
    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        
        TileMultiBase tile = (TileMultiBase) world.getTileEntity(x, y, z);
        
        if (tile != null && !tile.getWorldObj().isRemote && !((TileMultiBase) tile).multiBlocks.isEmpty()) {
            
            IMultiBlock multi = ((TileMultiBase) tile).multiBlocks.get(0);
            
            if (multi != null && multi instanceof IRedstoneMultiBlock) {
                
                return ((IRedstoneMultiBlock) multi).getRedstoneLevel(new Vector3i(x, y, z));
            }
        }
        return 0;
    }
    
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
        
        TileMultiBase tile = (TileMultiBase) world.getTileEntity(x, y, z);
        
        if (tile != null && !tile.getWorldObj().isRemote && !((TileMultiBase) tile).multiBlocks.isEmpty()) {
            
            IMultiBlock multi = ((TileMultiBase) tile).multiBlocks.get(0);
            
            if (multi != null && multi instanceof IRedstoneMultiBlock) {
                
                return ((IRedstoneMultiBlock) multi).getRedstoneLevel(new Vector3i(x, y, z));
            }
        }
        return 0;
    }
    
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        
        TileMultiBase tile = (TileMultiBase) world.getTileEntity(x, y, z);
        
        if (tile != null && !tile.getWorldObj().isRemote && !((TileMultiBase) tile).multiBlocks.isEmpty()) {
            
            IMultiBlock multi = ((TileMultiBase) tile).multiBlocks.get(0);
            
            if (multi != null && multi instanceof IRedstoneMultiBlock) {
                
                return ((IRedstoneMultiBlock) multi).getRedstoneLevel(new Vector3i(x, y, z));
            }
        }
        return 0;
    }
    
    @Override
    public TileEntity createNewTileEntity(World var1, int metadata) {
        
        return new TileRedstoneMultiInterface();
    }
}
