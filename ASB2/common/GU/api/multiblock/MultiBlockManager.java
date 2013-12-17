package GU.api.multiblock;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.vector.Vector3;

public abstract class MultiBlockManager {
    
    protected World worldObj;
    protected Set<Vector3> comprisingBlocks = new HashSet<Vector3>();
    protected Vector3 multiBlockCore;
    protected int relativeXPlus, relativeYPlus, relativeZPlus;
    
    public MultiBlockManager() {
    }
    
    public MultiBlockManager(World worldObj, Vector3 multiBlockCore, int relativeXPlus, int relativeYPlus, int relativeZPlus) {
        
        this.worldObj = worldObj;
        this.multiBlockCore = multiBlockCore;
        this.relativeXPlus = relativeXPlus;
        this.relativeYPlus = relativeYPlus;
        this.relativeZPlus = relativeZPlus;
    }
    
    public void setWorld(World world) {
        
        worldObj = world;
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
    
    public abstract boolean isMultiBlockAreaValid();
    
    public abstract boolean makeMultiBlockValid();
    
    public abstract void update();
    
    public abstract boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ);
    
    public void invalidate() {
        
        for (Vector3 vector : comprisingBlocks) {
            
            TileEntity tile = vector.getTileEntity(worldObj);
            
            if (tile != null && tile instanceof IMultiBlockPart) {
                
                ((IMultiBlockPart) tile).removeStructure(this);
            }
        }
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setInteger("relativeXPlus", relativeXPlus);
        tag.setInteger("relativeYPlus", relativeYPlus);
        tag.setInteger("relativeZPlus", relativeZPlus);
        tag.setCompoundTag("multiBlockCore", this.multiBlockCore.writeToNBT(new NBTTagCompound()));
//        
//        NBTTagCompound subTag = new NBTTagCompound();
//        List<NBTTagCompound> vectorTags = new ArrayList<NBTTagCompound>();
//        
//        for (Vector3 vector : comprisingBlocks) {
//            
//            vectorTags.add(vector.writeToNBT(new NBTTagCompound()));
//        }
//        
//        for (int i = 0; i < vectorTags.size(); i++) {
//            
//            subTag.setCompoundTag("comprisingBlocks" + i, vectorTags.get(i));
//        }
//        subTag.setInteger("vectorsSize", vectorTags.size());
//        tag.setCompoundTag("multiBlock", subTag);
        return tag;
    }
    
    public NBTTagCompound load(NBTTagCompound tag) {
        
        relativeXPlus = tag.getInteger("relativeXPlus");
        relativeYPlus = tag.getInteger("relativeYPlus");
        relativeZPlus = tag.getInteger("relativeZPlus");
        
        multiBlockCore = Vector3.readFromNBT(tag.getCompoundTag("multiBlockCore"));
//        
//        NBTTagCompound subTag = tag.getCompoundTag("multiBlock");
//        
//        for (int i = 0; i < subTag.getInteger("vectorsSize"); i++) {
//            
//            comprisingBlocks.add(Vector3.readFromNBT(subTag.getCompoundTag("comprisingBlocks" + i)));
//        }
        return tag;
    }
}
