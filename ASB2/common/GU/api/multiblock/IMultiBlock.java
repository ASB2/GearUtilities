package GU.api.multiblock;

import java.util.Set;

import net.minecraft.world.World;
import ASB2.vector.Vector3;

public interface IMultiBlock {
    
    boolean isStructureValid();
    
    void invalidate();
    
    void setWorld(World world);
    
    World getWorldObj();
    
    Set<Vector3> getContainingBlocks();
    
    Set<Vector3> getMultiBlockInterfaces();
}
