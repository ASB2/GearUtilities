package GU.blocks.containers.BlockSpacialProvider;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.multiblock.MultiBlockManager;
import GU.api.spacial.ISpacialProvider;
import GU.blocks.containers.TileBase;

public class TileSpacialProvider extends TileBase implements ISpacialProvider {
    
    public static int MAX_DISTANCE = 16;
    Set<MultiBlockManager> multiBlocksIAmIn = new HashSet<MultiBlockManager>();
    
    public TileSpacialProvider() {
        
        sideState = new EnumState[] { EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE };
    }
    
    @Override
    public void updateEntity() {
        
    }
    
    public TileEntity getNearestProvider(ForgeDirection direction) {
        
        for (int i = 0; i < MAX_DISTANCE; i++) {
            
            Vector3 position = new Vector3(this).add(direction, i);
            TileEntity tile = position.getTileEntity(worldObj);
            
            if (tile != null && tile != this && tile instanceof ISpacialProvider) {
                
                return tile;
            }
        }
        return null;
    }
    
    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {
        
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        
        if (sideState[direction.ordinal()] == EnumState.OUTPUT) {
            sideState[direction.ordinal()] = EnumState.NONE;
        } else {
            sideState[direction.ordinal()] = EnumState.OUTPUT;
        }
        world.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }
    
    @Override
    public Set<Vector3> getProvidedTiles() {
        
        Set<Vector3> tileList = new HashSet<Vector3>();
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if (getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {
                
                TileEntity tile = this.getNearestProvider(direction);
                
                if (tile != null) {
                    
                    tileList.add(new Vector3(tile));
                }
            }
        }
        return tileList;
    }
    
    @Override
    public boolean addToMultiBlock(MultiBlockManager multiBlock) {
        
        return multiBlocksIAmIn.add(multiBlock);
    }
    
    @Override
    public void removeMultiBlock(MultiBlockManager multiBlock) {
        
        multiBlocksIAmIn.remove(multiBlock);
    }
    
    @Override
    public Set<MultiBlockManager> getComprizedStructures() {
        
        return multiBlocksIAmIn;
    }
}
