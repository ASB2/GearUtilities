package GU.blocks.containers.BlockSpacialProvider;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.multiblock.IMultiBlockPart;
import GU.api.multiblock.MultiBlockBase;
import GU.api.spacial.ISpacialProvider;
import GU.blocks.containers.TileBase;

public class TileSpacialProvider extends TileBase implements ISpacialProvider {
    
    public static int MAX_DISTANCE = 16;
    MultiBlockBase currentMultiBlock;
    boolean isInMultiBlock = false, hasBufferedCreateMultiBlock = false;
    
    public TileSpacialProvider() {
        
        sideState = new EnumState[] { EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE };
    }
    
    @Override
    public void updateEntity() {
        
        if (hasBufferedCreateMultiBlock) {
            
            if (worldObj != null) {
                
                if (this.getCurrentStructure() != null) {
                    
                    if (this.createMultiBlock(true)) {
                        
                        hasBufferedCreateMultiBlock = false;
                    }
                }
            }
        }
    }
    
    public TileEntity getNearestProvider(ForgeDirection direction) {
        
        TileEntity last = this;
        
        for (int i = 1; i <= MAX_DISTANCE; i++) {
            
            Vector3 position = new Vector3(last).add(direction, 1);
            TileEntity tile = position.getTileEntity(worldObj);
            
            if (tile != null && tile != this && tile instanceof IMultiBlockPart) {
                
                return tile;
            } else if (tile == this) {
                return null;
            }
        }
        return null;
    }
    
    public int getDistanceToNearestProvider(ForgeDirection direction) {
        
        for (int i = 1; i <= MAX_DISTANCE; i++) {
            
            Vector3 position = new Vector3(this).add(direction, i);
            TileEntity tile = position.getTileEntity(worldObj);
            
            if (tile != null && tile != this && tile instanceof IMultiBlockPart) {
                
                return i;
            }
        }
        return 0;
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
    
    @Override
    public boolean setStructure(MultiBlockBase multiBlock) {
        
        if (currentMultiBlock == null) {
            
            isInMultiBlock = true;
            currentMultiBlock = multiBlock;
            return true;
        }
        return false;
    }
    
    @Override
    public void removeStructure() {
        
        isInMultiBlock = false;
        currentMultiBlock = null;
    }
    
    @Override
    public MultiBlockBase getCurrentStructure() {
        
        return currentMultiBlock;
    }
    
    public boolean createMultiBlock() {
        
        return createMultiBlock(false);
    }
    
    public boolean createMultiBlock(boolean hasStructure) {
        
        return false;
    }
    
    @Override
    public void invalidate() {
        
        if (this.getCurrentStructure() != null) {
            this.getCurrentStructure().invalidate();
        }
        super.invalidate();
    }
}
