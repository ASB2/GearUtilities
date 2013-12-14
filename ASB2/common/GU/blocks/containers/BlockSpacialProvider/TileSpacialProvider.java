package GU.blocks.containers.BlockSpacialProvider;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.ISpacialProvider;
import GU.blocks.containers.TileBase;

public class TileSpacialProvider extends TileBase implements ISpacialProvider {
    
    public static int MAX_DISTANCE = 16;
    public Map<Vector3, ForgeDirection> tiles = new HashMap<Vector3, ForgeDirection>();
    
    public TileSpacialProvider() {
        
        sideState = new EnumState[] { EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE };
    }
    
    @Override
    public void updateEntity() {
        
        boolean hasAll = false;
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if (sideState[direction.ordinal()] == EnumState.OUTPUT) {
                
                if (getNearestProvider(direction) != null) {
                    
                    tiles.put(new Vector3(getNearestProvider(direction)), direction);
                    hasAll = true;
                } else {
                    hasAll = false;
                }
            }
        }
        
        if (hasAll && tiles.size() == 3) {
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                if (sideState[direction.ordinal()] == EnumState.OUTPUT) {
                    
                    hasAll = getNearestProvider(direction) != null;
                }
            }
            // UtilEntity.sendClientChat("I got everything i need: Boom baby");
        }
    }
    
    public TileEntity getNearestProvider(ForgeDirection direction) {
        
        for (int i = 0; i < MAX_DISTANCE; i++) {
            
            Vector3 position = new Vector3(this).add(direction, i);
            TileEntity tile = position.getTileEntity(worldObj);
            
            if (tile != null && tile instanceof ISpacialProvider) {
                
                return tile;
            } else {
                
                return null;
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
}
