package GU.api.multiblock;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.vector.Cuboid;
import ASB2.vector.Vector3;

public class MultiBlockBase implements IMultiBlock {
    
    protected World worldObj;
    protected boolean isValid = false;
    protected Set<Vector3> composingBlock = new HashSet<Vector3>();
    protected Set<Vector3> multiBlockInterfaces = new HashSet<Vector3>();
    protected Cuboid size;
    
    public MultiBlockBase(World world) {
        
        this.worldObj = world;
    }
    
    public MultiBlockBase(World world, Cuboid size) {
        this(world);
        this.size = size;
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
    
    @Override
    public Cuboid getSize() {
        
        return size.clone();
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setCompoundTag("size", size.save(new NBTTagCompound()));
        return tag;
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        this.size = Cuboid.load(tag.getCompoundTag("size"));
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        // TODO Auto-generated method stub
        return false;
    }
}
