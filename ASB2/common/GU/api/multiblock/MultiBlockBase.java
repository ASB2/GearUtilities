package GU.api.multiblock;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.world.World;
import ASB2.vector.Vector3;

public class MultiBlockBase implements IMultiBlock {
    
    World worldObj;
    boolean isValid = false;
    Set<Vector3> composingBlock = new HashSet<Vector3>();
    Set<Vector3> multiBlockInterfaces = new HashSet<Vector3>();
    
    public MultiBlockBase(World world) {
        
        this.worldObj = world;
    }
    
    @Override
    public boolean isStructureValid() {
        
        return isValid;
    }
    
    @Override
    public void invalidate() {
        
        isValid = false;
    }
    
    @Override
    public void setWorld(World world) {
        
        worldObj = world;
    }
    
    @Override
    public World getWorldObj() {
        
        return worldObj;
    }
    
    @Override
    public Set<Vector3> getContainingBlocks() {
        
        return composingBlock;
    }
    
    @Override
    public Set<Vector3> getMultiBlockInterfaces() {
        
        return multiBlockInterfaces;
    }
}
