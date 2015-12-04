package ASB2.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import UC.math.vector.Vector3i;

public final class UtilVector {
    
    public static Vector3i createTileEntityVector(TileEntity tile) {
        
        return new Vector3i(tile.xCoord, tile.yCoord, tile.zCoord);
    }
    
    public static TileEntity getTileAtPostion(World world, Vector3i vector) {
        
        return world.getTileEntity(vector.getX(), vector.getY(), vector.getZ());
    }
    
    public static NBTTagCompound saveVector(NBTTagCompound tag, Vector3i vector) {
        
        tag.setInteger("X", vector.getX());
        tag.setInteger("Y", vector.getY());
        tag.setInteger("Z", vector.getZ());
        return tag;
    }
    
    public static Vector3i loadVector3i(NBTTagCompound tag) {
        
        return new Vector3i(tag.getInteger("X"), tag.getInteger("Y"), tag.getInteger("Z"));
    }
    
    public static NBTTagCompound saveVector(NBTTagCompound tag, Vector3d vector) {
        
        tag.setDouble("X", vector.getX());
        tag.setDouble("Y", vector.getY());
        tag.setDouble("Z", vector.getZ());
        return tag;
    }
    
    public static Vector3d loadVector3d(NBTTagCompound tag) {
        
        return new Vector3d(tag.getDouble("X"), tag.getDouble("Y"), tag.getDouble("Z"));
    }
}
