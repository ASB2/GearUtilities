package GU.api.multiblock;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilEntity;
import ASB2.vector.Cuboid;
import ASB2.vector.ICuboidIterator;
import ASB2.vector.Vector3;

public class MultiBlockBase implements IMultiBlock, ICuboidIterator {
    
    protected World worldObj;
    protected boolean isValid = false;
    protected Set<Vector3> composingBlock = new HashSet<Vector3>();
    protected Set<Vector3> multiBlockInterfaces = new HashSet<Vector3>();
    protected Set<Vector3> multiBlockCores = new HashSet<Vector3>();
    protected Cuboid size;
    
    public MultiBlockBase(World world) {
        
        this.worldObj = world;
    }
    
    public MultiBlockBase(World world, Cuboid size) {
        this(world);
        this.size = size;
    }
    
    public boolean isStructureValid() {
        
        // if ((this.size.getXSize() + 1) % 2 == 0 || (this.size.getYSize() + 1)
        // % 2 == 0 || (this.size.getZSize() + 1) % 2 == 0) {
        //
        // return false;
        // }
        
        for (Vector3 vector : size.getCornerBlocks()) {
            
            TileEntity tile = vector.getTileEntity(worldObj);
            
            if (tile == null || !(tile instanceof IMultiBlockCore) || !isValidCore(vector, tile)) {
                
                return false;
            }
        }
        
        for (Vector3 vector : size.getCorners()) {
            
            Block block = vector.getBlock(getWorldObj());
            
            if (block == null || !block.isBlockNormalCube(getWorldObj(), vector.intX(), vector.intY(), vector.intZ())) {
                
                return false;
            }
        }
        return size.iterate(this, 0);
    }
    
    @Override
    public boolean create() {
        
        if (size.iterate(this, 1)) {
            
            createWorked();
            return true;
        }
        return false;
    }
    
    public boolean isValidCore(Vector3 vector, TileEntity tile) {
        
        return true;
    }
    
    public void createWorked() {
        
    }
    
    @Override
    public boolean iterate(Vector3 vector, Object... providedInfo) {
        
        if ((Integer) providedInfo[0] == 0) {
            
            TileEntity tile = vector.getTileEntity(this.getWorldObj());
            
            if (tile != null) {
                
                if (tile instanceof IMultiBlockPart) {
                    
                    return true;
                }
            } else {
                
                Block block = vector.getBlock(this.getWorldObj());
                
                if (block != null && block instanceof ISpecialTileMultiBlock) {
                    
                    tile = ((ISpecialTileMultiBlock) block).getBlockTileEntity(this.getWorldObj(), vector.intX(), vector.intY(), vector.intZ());
                    
                    if (tile != null && tile instanceof IMultiBlockPart) {
                        
                        return true;
                    }
                }
            }
            return false;
        }
        
        if ((Integer) providedInfo[0] == 1) {
            
            TileEntity tile = vector.getTileEntity(this.getWorldObj());
            
            if (tile == null) {
                
                Block block = Block.blocksList[vector.getBlockID(this.getWorldObj())];
                
                if (block != null && block instanceof ISpecialTileMultiBlock) {
                    
                    tile = ((ISpecialTileMultiBlock) block).getBlockTileEntity(this.getWorldObj(), vector.intX(), vector.intY(), vector.intZ());
                    
                    if (tile == null) {
                        
                        return false;
                    }
                }
                return false;
            }
            if (!(tile instanceof IMultiBlockPart)) {
                
                return false;
            } else {
                
                if (!((IMultiBlockPart) tile).addMultiBlock(this)) {
                    
                    return false;
                }
            }
            if (tile instanceof IMultiBlockInterface) {
                
                multiBlockInterfaces.add(vector);
            }
            if (tile instanceof IMultiBlockCore) {
                
                multiBlockCores.add(vector);
            }
            return true;
        }
        
        if ((Integer) providedInfo[0] == 2) {
            
            TileEntity tile = vector.getTileEntity(this.getWorldObj());
            
            if (tile != null) {
                
                if (tile instanceof IMultiBlockPart) {
                    
                    ((IMultiBlockPart) tile).removeMultiBlock(this);
                }
            }
            return true;
        }
        return false;
    }
    
    @Override
    public void invalidate() {
        
        UtilEntity.sendClientChat("Structure Invalidated");
        
        isValid = false;
        composingBlock.clear();
        multiBlockInterfaces.clear();
        multiBlockCores.clear();
        this.getSize().iterate(this, 2);
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
        
        size.save(tag);
        return tag;
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        this.size = Cuboid.load(tag);
        postLoad();
    }
    
    public void postLoad() {
        
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        // TODO Auto-generated method stub
        return false;
    }
}
