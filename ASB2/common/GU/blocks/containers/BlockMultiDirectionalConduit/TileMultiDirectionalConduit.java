package GU.blocks.containers.BlockMultiDirectionalConduit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.block.BlockAir;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.BlockConduit.EnumConduitType;

public class TileMultiDirectionalConduit extends TileBase {
    
    public Map<TileEntity, ForgeDirection> getAvaliableTiles(EnumConduitType type) {
        
        Map<TileEntity, ForgeDirection> tileList = new HashMap<TileEntity, ForgeDirection>();
        List<TileMultiDirectionalConduit> conduits = new ArrayList<TileMultiDirectionalConduit>();
        
        conduits.add(this);
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            for (int distance = 1; distance <= 7; distance++) {
                
                int x = xCoord + (distance * direction.offsetX), y = yCoord + (distance * direction.offsetY), z = zCoord + (distance * direction.offsetZ);
                
                TileEntity tile = worldObj.getTileEntity(x, y, z);
                
                if (tile != null) {
                    
                    if (type.checkObject(tile)) {
                        
                        tileList.put(tile, direction);
                        break;
                    } else if (tile instanceof TileMultiDirectionalConduit) {
                        
                        ((TileMultiDirectionalConduit) tile).getAvaliableTilesIndirect(type, tileList, conduits);
                    }
                } else if (worldObj.getBlock(x, y, z) instanceof BlockAir) {
                    
                    continue;
                } else {
                    
                    break;
                }
            }
        }
        return tileList;
    }
    
    private Map<TileEntity, ForgeDirection> getAvaliableTilesIndirect(EnumConduitType type, Map<TileEntity, ForgeDirection> tileList, List<TileMultiDirectionalConduit> conduits) {
        
        conduits.add(this);
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            for (int distance = 1; distance <= 7; distance++) {
                
                int x = xCoord + (distance * direction.offsetX), y = yCoord + (distance * direction.offsetY), z = zCoord + (distance * direction.offsetZ);
                
                TileEntity tile = worldObj.getTileEntity(x, y, z);
                
                if (tile != null) {
                    
                    if (type.checkObject(tile)) {
                        
                        tileList.put(tile, direction);
                        break;
                    } else if (tile instanceof TileMultiDirectionalConduit) {
                        
                        if (!conduits.contains(tile)) {
                            
                            ((TileMultiDirectionalConduit) tile).getAvaliableTilesIndirect(type, tileList, conduits);
                        } else {
                            
                            continue;
                        }
                    }
                } else if (worldObj.getBlock(x, y, z) instanceof BlockAir) {
                    
                    continue;
                } else {
                    
                    break;
                }
            }
        }
        return null;
    }
}
