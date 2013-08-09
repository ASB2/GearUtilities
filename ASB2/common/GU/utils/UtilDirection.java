package GU.utils;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.api.power.IPowerMisc;

public class UtilDirection {

    public static int[] translateDirectionToCoords(ForgeDirection direction, TileEntity tile) {

        switch(direction) {

            case DOWN: {
                return new int[]{tile.xCoord,tile.yCoord - 1,tile.zCoord};
            }
            case UP: {
                return new int[]{tile.xCoord,tile.yCoord + 1,tile.zCoord};
            }
            case NORTH: {
                return new int[]{tile.xCoord,tile.yCoord,tile.zCoord - 1};
            }
            case SOUTH: {
                return new int[]{tile.xCoord,tile.yCoord,tile.zCoord + 1};
            }
            case WEST: {
                return new int[]{tile.xCoord - 1,tile.yCoord,tile.zCoord};
            }
            case EAST: {
                return new int[]{tile.xCoord + 1,tile.yCoord,tile.zCoord};
            }
            case UNKNOWN:{
                return new int[]{tile.xCoord,tile.yCoord,tile.zCoord};
            }
        }

        return new int[]{tile.xCoord,tile.yCoord,tile.zCoord};
    }

    public static int[] translateDirectionToCoords(ForgeDirection direction, int xCoord, int yCoord, int zCoord) {

        switch(direction) {

            case DOWN: {
                return new int[]{xCoord,yCoord-1,zCoord};
            }
            case UP: {
                return new int[]{xCoord,yCoord+1,zCoord};
            }
            case NORTH: {
                return new int[]{xCoord,yCoord,zCoord-1};
            }
            case SOUTH: {
                return new int[]{xCoord,yCoord,zCoord+1};
            }
            case WEST: {
                return new int[]{xCoord-1,yCoord,zCoord};
            }
            case EAST: {
                return new int[]{xCoord+1,yCoord,zCoord};
            }
            case UNKNOWN:{
                return new int[]{xCoord,yCoord,zCoord};
            }
        }

        return new int[]{xCoord,yCoord,zCoord};
    }
    
    public static ForgeDirection translateDirectionToOpposite(ForgeDirection direction) {

        switch(direction) {

            case DOWN: {
                return ForgeDirection.UP;
            }
            case UP: {
                return ForgeDirection.DOWN;
            }
            case NORTH: {
                return ForgeDirection.SOUTH;
            }
            case SOUTH: {
                return ForgeDirection.NORTH;
            }
            case WEST: {
                return ForgeDirection.EAST;
            }
            case EAST: {
                return ForgeDirection.WEST;
            }
            case UNKNOWN:{
                return ForgeDirection.UNKNOWN;
            }
        }

        return ForgeDirection.UNKNOWN;
    }

    public static int translateDirectionToNumber(ForgeDirection direction) {

        int var1 = -1;

        switch(direction) {

            case DOWN: {
                var1 = 0;
                return var1;
            }
            case UP: {
                var1 = 1;
                return var1;
            }
            case NORTH: {
                var1 = 2;
                return var1;
            }                       
            case SOUTH: {
                var1 = 3;
                return var1;
            }
            case WEST: {
                var1 = 5;
                return var1;
            }
            case EAST: {
                var1 = 4;
                return var1;
            }
            default:{
                return var1;
            }
        }
    }

    public static ForgeDirection translateNumberToDirection(int direction) {

        switch(direction) {

            case 0: {
                return ForgeDirection.DOWN;
            }
            case 1: {
                return ForgeDirection.UP;
            }
            case 2: {
                return ForgeDirection.NORTH;
            }
            case 3: {
                return ForgeDirection.SOUTH;
            }
            case 5: {
                return ForgeDirection.WEST;
            }
            case 4: {
                return ForgeDirection.EAST;
            }
            case -1: {
                return ForgeDirection.UNKNOWN;
            }
            default: {
                return ForgeDirection.UNKNOWN;
            }
        }
    }

    public static String translateDirectionToString(ForgeDirection direction) {

        switch(direction) {

            case DOWN: {
                return "Down";
            }
            case UP: {
                return "Up";
            }
            case NORTH: {
                return "North";
            }
            case SOUTH: {
                return "South";
            }
            case WEST: {
                return "West";
            }
            case EAST: {
                return "East";
            }
            default:{

                return "Unknown";
            }
        }
    }

    public static TileEntity translateDirectionToTile(TileEntity tile, World world, ForgeDirection direction) {

        return world.getBlockTileEntity(UtilDirection.translateDirectionToCoords(direction, tile)[0], UtilDirection.translateDirectionToCoords(direction, tile)[1], UtilDirection.translateDirectionToCoords(direction, tile)[2]);
    }

    public static TileEntity translateDirectionToTile(World world, ForgeDirection direction, int xCoord, int yCoord, int zCoord) {

        return world.getBlockTileEntity(UtilDirection.translateDirectionToCoords(direction, xCoord, yCoord, zCoord)[0], UtilDirection.translateDirectionToCoords(direction, xCoord, yCoord, zCoord)[1], UtilDirection.translateDirectionToCoords(direction, xCoord, yCoord, zCoord)[2]);
    }

    public static TileEntity[] getArrayTilesAround(World world, TileEntity tile) {

        return new TileEntity[] {
                UtilDirection.translateDirectionToTile(tile, world, ForgeDirection.DOWN),
                UtilDirection.translateDirectionToTile(tile, world, ForgeDirection.UP),
                UtilDirection.translateDirectionToTile(tile, world, ForgeDirection.NORTH),
                UtilDirection.translateDirectionToTile(tile, world, ForgeDirection.SOUTH),
                UtilDirection.translateDirectionToTile(tile, world, ForgeDirection.WEST),
                UtilDirection.translateDirectionToTile(tile, world, ForgeDirection.EAST)};
    }

    public static TileEntity[] getArrayTilesAround(World world, int x, int y, int z) {

        return new TileEntity[] {
                UtilDirection.translateDirectionToTile(world, ForgeDirection.DOWN, x, y ,x),
                UtilDirection.translateDirectionToTile(world, ForgeDirection.UP, x, y ,x),
                UtilDirection.translateDirectionToTile(world, ForgeDirection.NORTH, x, y ,x),
                UtilDirection.translateDirectionToTile(world, ForgeDirection.SOUTH, x, y ,x),
                UtilDirection.translateDirectionToTile(world, ForgeDirection.WEST, x, y ,x),
                UtilDirection.translateDirectionToTile(world, ForgeDirection.EAST, x, y ,x)};
    }

    public static int getTilesNextTo(int x, int y, int z, World worldObj) {

        int numberNextTo = 0;
        //Bottom of Tile
        if(worldObj.getBlockTileEntity(x, y-1, z) instanceof IPowerMisc){
            numberNextTo++;
        }
        //top of Tile
        if(worldObj.getBlockTileEntity(x, y+1, z) instanceof IPowerMisc){
            numberNextTo++;
        }        
        //left
        if(worldObj.getBlockTileEntity(x, y, z-1) instanceof IPowerMisc){
            numberNextTo++;
        }
        //right
        if(worldObj.getBlockTileEntity(x, y, z+1) instanceof IPowerMisc){
            numberNextTo++;
        }
        //front
        if(worldObj.getBlockTileEntity(x+1, y, z) instanceof IPowerMisc){
            numberNextTo++;
        }
        //back
        if(worldObj.getBlockTileEntity(x-1, y, z) instanceof IPowerMisc){
            numberNextTo++;
        }
        return numberNextTo;
    }

    public static int getTilesNextTo(TileEntity tile, World worldObj) {

        int numberNextTo = 0;
        //Bottom of Tile
        if(worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord-1, tile.zCoord) instanceof TileEntity){
            numberNextTo++;
        }
        //top of Tile
        if(worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord+1, tile.zCoord) instanceof TileEntity){
            numberNextTo++;
        }        
        //left
        if(worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord, tile.zCoord-1) instanceof TileEntity){
            numberNextTo++;
        }
        //right
        if(worldObj.getBlockTileEntity(tile.xCoord, tile.yCoord, tile.zCoord+1) instanceof TileEntity){
            numberNextTo++;
        }
        //front
        if(worldObj.getBlockTileEntity(tile.xCoord+1, tile.yCoord, tile.zCoord) instanceof TileEntity){
            numberNextTo++;
        }
        //back
        if(worldObj.getBlockTileEntity(tile.xCoord-1, tile.yCoord, tile.zCoord) instanceof TileEntity){
            numberNextTo++;
        }
        return numberNextTo;
    }

    public static int translateDirectionToBlockId(World world, ForgeDirection direction, int xCoord, int yCoord, int zCoord) {

        int[] coords = UtilDirection.translateDirectionToCoords(direction, xCoord, yCoord, zCoord);

        return world.getBlockId(coords[0], coords[1], coords[2]);
    }

    public static int translateDirectionToBlockId(World world, ForgeDirection direction, TileEntity tile) {

        int[] coords = UtilDirection.translateDirectionToCoords(direction, tile.xCoord, tile.yCoord, tile.zCoord);

        return world.getBlockId(coords[0], coords[1], coords[2]);
    }

    public static boolean translateDirectionToIsBlockSolid(World world, ForgeDirection direction, int xCoord, int yCoord, int zCoord) {

        int[] coords = UtilDirection.translateDirectionToCoords(direction, xCoord, yCoord, zCoord);

        return world.isBlockSolidOnSide(coords[0], coords[1], coords[2], direction);
    }
}
