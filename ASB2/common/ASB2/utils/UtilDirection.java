package ASB2.utils;

import java.util.Random;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import UC.math.vector.Vector3i;

public class UtilDirection {
    
    public static ForgeDirection translateDirectionToRandomRightAngle(ForgeDirection direction) {
        
        int rand = new Random().nextInt(4);
        
        if (direction == ForgeDirection.UP || direction == ForgeDirection.DOWN) {
            
            switch (rand) {
            
                case 0:
                    return NORTH;
                case 1:
                    return SOUTH;
                case 2:
                    return WEST;
                case 3:
                    return EAST;
                default:
                    return UNKNOWN;
            }
        }
        
        if (direction == ForgeDirection.NORTH || direction == ForgeDirection.SOUTH) {
            
            switch (rand) {
            
                case 0:
                    return UP;
                case 1:
                    return DOWN;
                case 2:
                    return WEST;
                case 3:
                    return EAST;
                default:
                    return UNKNOWN;
            }
        }
        
        if (direction == ForgeDirection.WEST || direction == ForgeDirection.EAST) {
            
            switch (rand) {
            
                case 0:
                    return UP;
                case 1:
                    return DOWN;
                case 2:
                    return NORTH;
                case 3:
                    return SOUTH;
                default:
                    return UNKNOWN;
            }
        }
        return ForgeDirection.UNKNOWN;
        
    }
    
    public static int[] translateDirectionToCoords(ForgeDirection direction, TileEntity tile) {
        
        return translateDirectionToCoords(direction, tile.xCoord, tile.yCoord, tile.zCoord);
    }
    
    public static int[] translateDirectionToCoords(ForgeDirection direction, int xCoord, int yCoord, int zCoord) {
        
        return new int[] { xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ };
    }
    
    public static TileEntity translateDirectionToTile(TileEntity tile, IBlockAccess world, ForgeDirection direction) {
        
        return translateDirectionToTile(world, direction, tile.xCoord, tile.yCoord, tile.zCoord);
    }
    
    public static TileEntity translateDirectionToTile(Vector3i vector, IBlockAccess world, ForgeDirection direction) {
        
        return translateDirectionToTile(world, direction, vector.getX(), vector.getY(), vector.getZ());
    }
    
    public static TileEntity translateDirectionToTile(IBlockAccess world, ForgeDirection direction, int xCoord, int yCoord, int zCoord) {
        
        int[] coords = UtilDirection.translateDirectionToCoords(direction, xCoord, yCoord, zCoord);
        return world.getTileEntity(coords[0], coords[1], coords[2]);
    }
    
    public static Block translateDirectionToBlock(IBlockAccess world, ForgeDirection direction, int xCoord, int yCoord, int zCoord) {
        
        int[] coords = UtilDirection.translateDirectionToCoords(direction, xCoord, yCoord, zCoord);
        
        return world.getBlock(coords[0], coords[1], coords[2]);
    }
    
    public static Block translateDirectionToBlock(IBlockAccess world, ForgeDirection direction, TileEntity tile) {
        
        return translateDirectionToBlock(world, direction, tile.xCoord, tile.yCoord, tile.zCoord);
    }
    
    public static int translateDirectionToBlockMeta(IBlockAccess world, ForgeDirection direction, int xCoord, int yCoord, int zCoord) {
        
        int[] coords = UtilDirection.translateDirectionToCoords(direction, xCoord, yCoord, zCoord);
        
        return world.getBlockMetadata(coords[0], coords[1], coords[2]);
    }
    
    public static int translateDirectionToBlockMeta(IBlockAccess world, ForgeDirection direction, TileEntity tile) {
        
        int[] coords = UtilDirection.translateDirectionToCoords(direction, tile.xCoord, tile.yCoord, tile.zCoord);
        
        return world.getBlockMetadata(coords[0], coords[1], coords[2]);
    }
    
    public static boolean translateDirectionToIsBlockSolid(World world, ForgeDirection direction, int xCoord, int yCoord, int zCoord) {
        
        int[] coords = UtilDirection.translateDirectionToCoords(direction, xCoord, yCoord, zCoord);
        
        return world.isSideSolid(coords[0], coords[1], coords[2], direction);
    }
}
