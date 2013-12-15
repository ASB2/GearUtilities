package GU.blocks.containers.BlockSpacialProvider;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.multiblock.MultiBlockManager;
import GU.api.spacial.ISpacialProvider;
import GU.blocks.containers.TileBase;
import GU.multiblock.*;

public class TileSpacialProvider extends TileBase implements ISpacialProvider {
    
    public static int MAX_DISTANCE = 16;
    Set<MultiBlockManager> multiBlocksIAmIn = new HashSet<MultiBlockManager>();
    
    public TileSpacialProvider() {
        
        sideState = new EnumState[] { EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE };
    }
    
    @Override
    public void updateEntity() {
        
    }
    
    public TileEntity getNearestProvider(ForgeDirection direction) {
        
        for (int i = 0; i < MAX_DISTANCE; i++) {
            
            Vector3 position = new Vector3(this).add(direction, i);
            TileEntity tile = position.getTileEntity(worldObj);
            
            if (tile != null && tile != this && tile instanceof ISpacialProvider) {
                
                return tile;
            }
        }
        return null;
    }
    
    public int getMultiBlockHeight() {
        
        int height = 0;
        
        if (sideState[ForgeDirection.DOWN.ordinal()] != EnumState.NONE) {
            
            if (this.getNearestProvider(ForgeDirection.DOWN) != null) {
                
                height = new Vector3(this).subtract(new Vector3(this.getNearestProvider(ForgeDirection.DOWN))).intY();
            }
        }
        
        if (height == 0) {
            
            if (sideState[ForgeDirection.UP.ordinal()] != EnumState.NONE) {
                
                if (this.getNearestProvider(ForgeDirection.UP) != null) {
                    
                    height = new Vector3(this).subtract(new Vector3(this.getNearestProvider(ForgeDirection.UP))).intY();
                }
            }
        }
        return height;
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
        return height;
    }
    
    public int getMultiBlockZChange() {
        
        int height = 0;
        
        if (sideState[ForgeDirection.NORTH.ordinal()] != EnumState.NONE) {
            
            if (this.getNearestProvider(ForgeDirection.NORTH) != null) {
                
                height = new Vector3(this).subtract(new Vector3(this.getNearestProvider(ForgeDirection.NORTH))).intZ();
            }
        }
        
        if (height == 0) {
            
            if (sideState[ForgeDirection.NORTH.ordinal()] != EnumState.NONE) {
                
                if (this.getNearestProvider(ForgeDirection.NORTH) != null) {
                    
                    height = new Vector3(this).subtract(new Vector3(this.getNearestProvider(ForgeDirection.NORTH))).intZ();
                }
            }
        }
        return height;
    }
    
    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {
        
        ForgeDirection direction = ForgeDirection.getOrientation(side);
        
        if (sideState[direction.ordinal()] == EnumState.OUTPUT) {
            sideState[direction.ordinal()] = EnumState.NONE;
        } else {
            sideState[direction.ordinal()] = EnumState.OUTPUT;
        }
        world.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
    }
    
    @Override
    public Set<Vector3> getProvidedTiles() {
        
        Set<Vector3> tileList = new HashSet<Vector3>();
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if (getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {
                
                TileEntity tile = this.getNearestProvider(direction);
                
                if (tile != null) {
                    
                    tileList.add(new Vector3(tile));
                }
            }
        }
        return tileList;
    }
    
    @Override
    public boolean addToMultiBlock(MultiBlockManager multiBlock) {
        
        return multiBlocksIAmIn.add(multiBlock);
    }
    
    @Override
    public void removeMultiBlock(MultiBlockManager multiBlock) {
        
        multiBlocksIAmIn.remove(multiBlock);
    }
    
    @Override
    public Set<MultiBlockManager> getComprizedStructures() {
        
        return multiBlocksIAmIn;
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        
        for (int i = 0; i < multiBlocksIAmIn.toArray().length; i++) {
            
            MultiBlockManager core = (MultiBlockManager) multiBlocksIAmIn.toArray()[i];
            
            if (new Vector3(this).intEquals(core.getMultiBlockCore())) {
                
                tag.setCompoundTag("multiBlockClass" + i, core.save(new NBTTagCompound()));
            }
        }
        tag.setInteger("multiBlockSide", multiBlocksIAmIn.size());
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        
        for (int i = 0; i < tag.getInteger("multiBlockSide"); i++) {
            
            MultiBlockManager core = new MultiBlockTank(worldObj);
            core.load(tag.getCompoundTag("multiBlockClass" + i));
        }
    }
}
