package GU.api.multiblock;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilEntity;
import ASB2.vector.Vector3;

public class MultiBlockBase {
    
    /*
     * The world the multiblock is in
     */
    protected World worldObj;
    
    /*
     * List of all the block the multiblock currently occupies
     */
    protected Set<Vector3> comprisingBlocks = new HashSet<Vector3>();
    
    /*
     * The core of the multiblock where its extra data should be loaded
     */
    protected Vector3 multiBlockCore;
    
    /*
     * The amount in each direction the multiblock extends from the core
     */
    protected int xSize, ySize, zSize;
    
    protected boolean isInvalidated = false;
    
    protected MultiBlockBase() {
    }
    
    public MultiBlockBase(World worldObj, Vector3 multiBlockCore, int xChange, int yChange, int zChange) {
        
        this.worldObj = worldObj;
        this.multiBlockCore = multiBlockCore;
        this.xSize = xChange;
        this.ySize = yChange;
        this.zSize = zChange;
    }
    
    public void setWorld(World world) {
        this.worldObj = world;
    }
    
    public World getWorld() {
        return worldObj;
    }
    
    public Set<Vector3> getComprisingBlocks() {
        
        return new HashSet<Vector3>(comprisingBlocks);
    }
    
    private void setMultiBlockCore(Vector3 vector) {
        multiBlockCore = vector;
    }
    
    public Vector3 getMultiBlockCore() {
        return multiBlockCore.clone();
    }
    
    public int[] getRelativeSize() {
        return new int[] { xSize, ySize, zSize };
    }
    
    public boolean isMultiBlockAreaValid() {
        return false;
    }
    
    public boolean makeMultiBlockValid() {
        return false;
    }
    
    public void update() {
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        return false;
    }
    
    public void invalidate() {
        
        for (Vector3 vector : comprisingBlocks) {
            
            TileEntity tile = vector.getTileEntity(worldObj);
            
            if (tile != null && tile instanceof IMultiBlockPart) {
                
                ((IMultiBlockPart) tile).removeStructure();
            }
        }
        comprisingBlocks.clear();
        isInvalidated = true;
        UtilEntity.sendClientChat("Invalidated Everything");
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setInteger("relativeXPlus", xSize);
        tag.setInteger("relativeYPlus", ySize);
        tag.setInteger("relativeZPlus", zSize);
        tag.setCompoundTag("multiBlockCore", this.multiBlockCore.writeToNBT(new NBTTagCompound()));
        tag.setBoolean("isInvalidated", isInvalidated);
        return tag;
    }
    
    public static MultiBlockBase load(NBTTagCompound tag) {
        
        MultiBlockBase multiBlock = new MultiBlockBase();
        multiBlock.xSize = tag.getInteger("relativeXPlus");
        multiBlock.ySize = tag.getInteger("relativeYPlus");
        multiBlock.zSize = tag.getInteger("relativeZPlus");
        multiBlock.setMultiBlockCore(Vector3.readFromNBT(tag.getCompoundTag("multiBlockCore")));
        multiBlock.isInvalidated = tag.getBoolean("isInvalidated");
        return multiBlock;
    }
}
