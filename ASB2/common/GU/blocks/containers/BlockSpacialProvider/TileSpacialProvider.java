package GU.blocks.containers.BlockSpacialProvider;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.multiblock.IMultiBlock;
import GU.api.multiblock.IMultiBlockCore;
import GU.api.multiblock.IMultiBlockPart;
import GU.blocks.containers.TileBase;

public class TileSpacialProvider extends TileBase implements IMultiBlockCore {
    
    public static int MAX_DISTANCE = 16;
    boolean isInMultiBlock = false, hasBufferedCreateMultiBlock = false;
    Set<IMultiBlock> multiBlocks = new HashSet<IMultiBlock>();
    
    public TileSpacialProvider() {
        
        sideState = new EnumState[] { EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE };
    }
    
    @Override
    public void updateEntity() {
        
        if (hasBufferedCreateMultiBlock) {
            
            if (worldObj != null) {
                
                if (this.createMultiBlock(true)) {
                    
                    hasBufferedCreateMultiBlock = false;
                }
            }
        }
    }
    
    public TileEntity getNearestProvider(ForgeDirection direction) {
        
        TileEntity last = this;
        
        for (int i = 1; i <= MAX_DISTANCE; i++) {
            
            Vector3 position = new Vector3(last).add(direction, i);
            TileEntity tile = position.getTileEntity(worldObj);
            
            if (tile != null && tile != this && tile instanceof IMultiBlockPart) {
                
                last = tile;
            } else if (tile == null) {
                
                return last == this ? null : last;
            }
        }
        return null;
    }
    
    public int getMultiBlockXChange() {
        
        int height = 0;
        
        if (sideState[ForgeDirection.EAST.ordinal()] != EnumState.NONE) {
            
            if (this.getNearestProvider(ForgeDirection.EAST) != null) {
                
                height = new Vector3(this).subtract(new Vector3(this.getNearestProvider(ForgeDirection.EAST))).intX();
            }
        }
        
        if (height == 0) {
            
            if (sideState[ForgeDirection.WEST.ordinal()] != EnumState.NONE) {
                
                if (this.getNearestProvider(ForgeDirection.WEST) != null) {
                    
                    height = new Vector3(this).subtract(new Vector3(this.getNearestProvider(ForgeDirection.WEST))).intX();
                }
            }
        }
        return height * -1;
    }
    
    public int getMultiBlockYChange() {
        
        int height = 0;
        
        if (sideState[ForgeDirection.UP.ordinal()] != EnumState.NONE) {
            
            if (this.getNearestProvider(ForgeDirection.UP) != null) {
                
                height = new Vector3(this).subtract(new Vector3(this.getNearestProvider(ForgeDirection.UP))).intY();
            }
        }
        
        if (height == 0) {
            
            if (sideState[ForgeDirection.DOWN.ordinal()] != EnumState.NONE) {
                
                if (this.getNearestProvider(ForgeDirection.DOWN) != null) {
                    
                    height = new Vector3(this).subtract(new Vector3(this.getNearestProvider(ForgeDirection.DOWN))).intY();
                }
            }
        }
        return height * -1;
    }
    
    public int getMultiBlockZChange() {
        
        int height = 0;
        
        if (sideState[ForgeDirection.SOUTH.ordinal()] != EnumState.NONE) {
            
            if (this.getNearestProvider(ForgeDirection.SOUTH) != null) {
                
                height = new Vector3(this).subtract(new Vector3(this.getNearestProvider(ForgeDirection.SOUTH))).intZ();
            }
        }
        
        if (height == 0) {
            
            if (sideState[ForgeDirection.NORTH.ordinal()] != EnumState.NONE) {
                
                if (this.getNearestProvider(ForgeDirection.NORTH) != null) {
                    
                    height = new Vector3(this).subtract(new Vector3(this.getNearestProvider(ForgeDirection.NORTH))).intZ();
                }
            }
        }
        return height * -1;
    }
    
    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {
        
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        
        if (isSneaking) {
            direction = direction.getOpposite();
        }
        if (sideState[direction.ordinal()] == EnumState.OUTPUT) {
            sideState[direction.ordinal()] = EnumState.NONE;
        } else {
            sideState[direction.ordinal()] = EnumState.OUTPUT;
        }
        world.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }
    
    public boolean createMultiBlock() {
        
        return createMultiBlock(false);
    }
    
    public boolean createMultiBlock(boolean hasStructure) {
        
        return false;
    }
    
    @Override
    public void invalidate() {
        
        for (IMultiBlock multi : multiBlocks)
            multi.invalidate();
        
        super.invalidate();
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        isInMultiBlock = true;
        return multiBlocks.add(multiBlock);
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        
        multiBlocks.remove(multiBlock);
        
        isInMultiBlock = multiBlocks.isEmpty() ? false : true;
    }
    
    @Override
    public Set<IMultiBlock> getComprisedMultiBlocks() {
        
        return Collections.unmodifiableSet(multiBlocks);
    }
}
