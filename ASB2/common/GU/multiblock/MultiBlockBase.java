package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilVector;
import GU.BlockRegistry;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import UC.IAbstractUpdateable;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public abstract class MultiBlockBase implements IMultiBlock, IAbstractUpdateable {
    
    World world;
    Vector3i positionRelativeTo;
    Vector3i size;
    Vector3i updater;
    
    boolean isValid = false, isConstructing = false, isDeconstructing = false, forceLoad = false;
    
    public MultiBlockBase(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        this(world);
        this.positionRelativeTo = positionRelativeTo;
        this.size = size;
        this.updater = updater;
    }
    
    public MultiBlockBase(World world) {
        
        this.world = world;
    }
    
    @Override
    public void update(Object... objects) {
        
    }
    
    public void deconstruct() {
        
        isConstructing = false;
        isValid = false;
        isDeconstructing = true;
        
        for (int x = 0; x <= size.getX(); x++) {
            
            for (int y = 0; y <= size.getY(); y++) {
                
                for (int z = 0; z <= size.getZ(); z++) {
                    
                    deconstructBlock(positionRelativeTo.subtract(x, y, z));
                }
            }
        }
    }
    
    public void deconstructBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            ((IMultiBlockPart) tile).removeMultiBlock(this);
        }
    }
    
    public boolean checkBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            return ((IMultiBlockPart) tile).addMultiBlock(this);
        }
        return false;
    }
    
    public boolean placeRenderBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile == null || !(tile instanceof IMultiBlockPart)) {
            
            UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART_RENDER);
            
            return checkBlock(position);
        }
        return false;
    }
    
    public boolean placeGlassBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        if (tile == null || !(tile instanceof IMultiBlockPart)) {
            
            UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART_GLASS);
        }
        
        tile = UtilVector.getTileAtPostion(world, position);
        
        if (checkBlock(position)) {
            
            if (tile instanceof IColorableTile) {
                
                ((IColorableTile) tile).setColor(this.getDefaultBlockColor(), ForgeDirection.UNKNOWN);
            }
            return true;
        }
        return false;
    }
    
    public boolean placeAirBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile == null || !(tile instanceof IMultiBlockPart)) {
            
            UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART_AIR);
        }
        
        tile = UtilVector.getTileAtPostion(world, position);
        
        if (checkBlock(position)) {
            
            checkColor(position);
            return true;
        }
        return false;
    }
    
    public boolean placeEdgeBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        if (tile == null || !(tile instanceof IMultiBlockPart)) {
            
            UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART, 1, 3);
        }
        
        tile = UtilVector.getTileAtPostion(world, position);
        
        if (checkBlock(position)) {
            
            checkColor(position);
            return true;
        }
        return false;
    }
    
    public boolean placeCornerBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        if (tile == null || !(tile instanceof IMultiBlockPart)) {
            
            UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART, 2, 3);
        }
        
        tile = UtilVector.getTileAtPostion(world, position);
        
        if (checkBlock(position)) {
            
            checkColor(position);
            return true;
        }
        return false;
    }
    
    public boolean placeInnerBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        if (tile == null || !(tile instanceof IMultiBlockPart)) {
            
            UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART);
        }
        
        tile = UtilVector.getTileAtPostion(world, position);
        
        if (checkBlock(position)) {
            
            if (tile instanceof IColorableTile) {
                
                ((IColorableTile) tile).setColor(this.getDefaultBlockColor(), ForgeDirection.UNKNOWN);
            }
            return true;
        }
        return false;
    }
    
    public void checkColor(Vector3i vector) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, vector);
        
        if (tile instanceof IColorableTile) {
            
            ((IColorableTile) tile).setColor(this.getDefaultBlockColor(), ForgeDirection.UNKNOWN);
        }
    }
    
    public abstract boolean startCreation();
    
    public abstract void render(double x, double y, double z, float f);
    
    public Color4i getDefaultBlockColor() {
        
        return Color4i.WHITE;
    }
    
    public Vector3i getCore() {
        
        return positionRelativeTo;
    }
    
    public Vector3i getSpacialProvider() {
        
        return updater;
    }
    
    public boolean isValid() {
        
        return isValid;
    }
    
    @Override
    public void onBlockBreak(int x, int y, int z) {
        
        this.isValid = false;
        
        if (!isDeconstructing) {
            
            deconstruct();
        }
    }
    
    public void onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        
        if (!isValid) {
            
            this.deconstructBlock(new Vector3i(x, y, z));
        }
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setTag("core", UtilVector.saveVector(new NBTTagCompound(), positionRelativeTo));
        tag.setTag("size", UtilVector.saveVector(new NBTTagCompound(), size));
        tag.setTag("updater", UtilVector.saveVector(new NBTTagCompound(), updater));
        tag.setBoolean("isValid", isValid);
        return tag;
    }
    
    public void load(NBTTagCompound tag) {
        
        positionRelativeTo = UtilVector.loadVector3i(tag.getCompoundTag("core"));
        size = UtilVector.loadVector3i(tag.getCompoundTag("size"));
        updater = UtilVector.loadVector3i(tag.getCompoundTag("updater"));
        this.forceLoad = tag.getBoolean("isValid");
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (forceLoad ? 1231 : 1237);
        result = prime * result + (isConstructing ? 1231 : 1237);
        result = prime * result + (isDeconstructing ? 1231 : 1237);
        result = prime * result + (isValid ? 1231 : 1237);
        result = prime * result + ((positionRelativeTo == null) ? 0 : positionRelativeTo.hashCode());
        result = prime * result + ((size == null) ? 0 : size.hashCode());
        result = prime * result + ((updater == null) ? 0 : updater.hashCode());
        result = prime * result + ((world == null) ? 0 : world.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof MultiBlockBase)) return false;
        MultiBlockBase other = (MultiBlockBase) obj;
        if (forceLoad != other.forceLoad) return false;
        if (isConstructing != other.isConstructing) return false;
        if (isDeconstructing != other.isDeconstructing) return false;
        if (isValid != other.isValid) return false;
        if (positionRelativeTo == null) {
            if (other.positionRelativeTo != null) return false;
        }
        else if (!positionRelativeTo.equals(other.positionRelativeTo)) return false;
        if (size == null) {
            if (other.size != null) return false;
        }
        else if (!size.equals(other.size)) return false;
        if (updater == null) {
            if (other.updater != null) return false;
        }
        else if (!updater.equals(other.updater)) return false;
        if (world == null) {
            if (other.world != null) return false;
        }
        else if (!world.equals(other.world)) return false;
        return true;
    }
    
}
