package GU.multiblock;

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
import UC.AbstractUpdateable;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public abstract class MultiBlockBase implements IMultiBlock, AbstractUpdateable {
    
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
        
        if (isConstructing) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        Vector3i vec = positionRelativeTo.subtract(x, y, z);
                        
                        if ((x == size.getX() && y == size.getX() && z == size.getZ()) || (x == 0 && y == 0 && z == 0) || (x == size.getX() && y == 0 && z == 0) || (x == 0 && y == size.getY() && z == 0) || (x == 0 && y == 0 && z == size.getZ()) || (x == size.getX() && y == size.getY() && z == 0) || (x == size.getX() && y == 0 && z == size.getZ()) || (x == 0 && y == size.getY() && z == size.getZ())) {
                            
                            if (!placeCornerBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        if (vec.getX() == 0 && positionRelativeTo.getY() == vec.getY() && positionRelativeTo.getZ() == vec.getZ()) {
                            
                            if (!placeCornerBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (!placeInnerBlock(vec)) {
                            
                            deconstruct();
                            return;
                        }
                    }
                }
            }
            isConstructing = false;
            isValid = true;
        }
        else if (forceLoad) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        Vector3i vec = positionRelativeTo.subtract(x, y, z);
                        
                        if ((x == size.getX() && y == size.getX() && z == size.getZ()) || (x == 0 && y == 0 && z == 0) || (x == size.getX() && y == 0 && z == 0) || (x == 0 && y == size.getY() && z == 0) || (x == 0 && y == 0 && z == size.getZ()) || (x == size.getX() && y == size.getY() && z == 0) || (x == size.getX() && y == 0 && z == size.getZ()) || (x == 0 && y == size.getY() && z == size.getZ())) {
                            
                            if (!placeCornerBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        if (vec.getX() == 0 && positionRelativeTo.getY() == vec.getY() && positionRelativeTo.getZ() == vec.getZ()) {
                            
                            if (!placeCornerBlock(vec)) {
                                
                                deconstruct();
                                return;
                            }
                        }
                        else if (!placeInnerBlock(vec)) {
                            
                            deconstruct();
                            return;
                        }
                    }
                }
            }
            forceLoad = false;
            isValid = true;
        }
    }
    
    public void deconstruct() {
        
        isConstructing = false;
        isValid = false;
        isDeconstructing = true;
        
        for (int x = 0; x <= size.getX(); x++) {
            
            for (int y = 0; y <= size.getY(); y++) {
                
                for (int z = 0; z <= size.getZ(); z++) {
                    
                    Vector3i vec = positionRelativeTo.subtract(x, y, z);
                    deconstructBlock(vec);
                }
            }
        }
    }
    
    public boolean placeInnerBlock(Vector3i position) {
        
        if (UtilBlock.isBlockAir(world, position.getX(), position.getY(), position.getZ())) {
            
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART);
        }
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            boolean worked = ((IMultiBlockPart) tile).addMultiBlock(this);
            
            if (worked) {
                
                if (tile instanceof IColorableTile) {
                    
                    for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                        
                        ((IColorableTile) tile).setColor(this.getDefaultBlockColor(), direction);
                    }
                }
            }
            return worked;
        }
        return false;
    }
    
    public boolean placeCornerBlock(Vector3i position) {
        
        if (UtilBlock.isBlockAir(world, position.getX(), position.getY(), position.getZ())) {
            
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.SPACIAL_PROVIDER, 1, 3);
        }
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            boolean worked = ((IMultiBlockPart) tile).addMultiBlock(this);
            
            if (worked) {
                
                if (tile instanceof IColorableTile) {
                    
                    ((IColorableTile) tile).setColor(this.getDefaultBlockColor(), ForgeDirection.UNKNOWN);
                }
            }
            return worked;
        }
        return false;
    }
    
    public void deconstructBlock(Vector3i position) {
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            
            ((IMultiBlockPart) tile).removeMultiBlock(this);
        }
    }
    
    public boolean startCreation() {
        
        if (!isValid && !isConstructing) {
            
            isConstructing = true;
            
            TileEntity tile = UtilVector.getTileAtPostion(world, updater);
            
            if (tile != null && tile instanceof IMultiBlockPart) {
                
                return ((IMultiBlockPart) tile).addMultiBlock(this);
            }
        }
        return false;
    }
    
    public boolean createAfterLoad() {
        
        return false;
    }
    
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
}
