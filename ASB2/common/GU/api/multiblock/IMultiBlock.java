package GU.api.multiblock;

import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;

public interface IMultiBlock {
    
    boolean create();
    
    void invalidate();
    
    void setWorld(World world);
    
    World getWorldObj();
    
    Set<Vector3> getContainingBlocks();
    
    Set<Vector3> getMultiBlockInterfaces();
    
    Cuboid getSize();
    
    NBTTagCompound save(NBTTagCompound tag);
    
    void load(NBTTagCompound tag);
    
    boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ);
}
