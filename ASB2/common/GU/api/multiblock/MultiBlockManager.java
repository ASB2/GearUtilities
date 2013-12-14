package GU.api.multiblock;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.vector.Vector3;

public abstract class MultiBlockManager {
    
    protected World worldObj;
    protected Set<Vector3> comprisingBlocks = new HashSet<Vector3>();
    protected Vector3 multiBlockCore;
    protected int relativeXPlus, relativeYPlus, relativeZPlus;
    
    public MultiBlockManager(World worldObj) {
        
        this.worldObj = worldObj;
    }
    
    public MultiBlockManager(World worldObj, Vector3 multiBlockCore, int relativeXPlus, int relativeYPlus, int relativeZPlus) {
        
        this.worldObj = worldObj;
        this.multiBlockCore = multiBlockCore;
        this.relativeXPlus = relativeXPlus;
        this.relativeYPlus = relativeYPlus;
        this.relativeZPlus = relativeZPlus;
    }
    
    public World getWorld() {
        return worldObj;
    }
    
    public Set<Vector3> getComprisingBlocks() {
        
        return Collections.unmodifiableSet(comprisingBlocks);
    }
    
    public Vector3 getMultiBlockCore() {
        
        return multiBlockCore.clone();
    }
    
    public int[] getRelativePlus() {
        
        return new int[] { relativeXPlus, relativeYPlus, relativeZPlus };
    }
    
    public abstract boolean isMultiBlockValid();
    
    public abstract void update();
    
    public void save(NBTTagCompound tag) {
        
        
    }
    
    public void load(NBTTagCompound tag) {
        
    }
}
