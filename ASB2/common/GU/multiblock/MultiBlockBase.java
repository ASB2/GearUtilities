package GU.multiblock;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilVector;
import GU.BlockRegistry;
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
    
    boolean isValid = false, isConstructing = false, isDeconstructing = false;
    
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
                        
                        if (!placeBlock(vec)) {
                            
                            deconstruct();
                            return;
                        }
                    }
                }
            }
            isConstructing = false;
            isValid = true;
        }
    }
    
    public void deconstruct() {
        
        isConstructing = false;
        isValid = false;
        isDeconstructing = true;
        
        if (!world.isRemote) {
            
            for (int x = 0; x <= size.getX(); x++) {
                
                for (int y = 0; y <= size.getY(); y++) {
                    
                    for (int z = 0; z <= size.getZ(); z++) {
                        
                        Vector3i vec = positionRelativeTo.subtract(x, y, z);
                        deconstructBlock(vec);
                    }
                }
            }
        }
    }
    
    public boolean placeBlock(Vector3i position) {
        
        if (UtilBlock.isBlockAir(world, position.getX(), position.getY(), position.getZ())) {
            
            world.setBlock(position.getX(), position.getY(), position.getZ(), BlockRegistry.MULTI_BLOCK_PART);
        }
        
        TileEntity tile = UtilVector.getTileAtPostion(world, position);
        
        if (tile != null && tile instanceof IMultiBlockPart) {
            return ((IMultiBlockPart) tile).addMultiBlock(this);
        }
        return false;
    }
    
    public void deconstructBlock(Vector3i position) {
        
        UtilBlock.breakBlock(world, position.getX(), position.getY(), position.getZ());
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
        
        return tag;
    }
    
    public void load(NBTTagCompound tag) {
        
    }
}
