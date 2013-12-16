package GU.blocks.containers.BlockSpacialProvider;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilEntity;
import ASB2.vector.Vector3;
import GU.EnumState;
import GU.api.multiblock.MultiBlockManager;
import GU.api.spacial.ISpacialProvider;
import GU.blocks.containers.TileBase;
import GU.multiblock.*;

public class TileSpacialProvider extends TileBase implements ISpacialProvider {
    
    public static int MAX_DISTANCE = 16;
    Set<MultiBlockManager> multiBlocksIAmIn = new HashSet<MultiBlockManager>();
    boolean isInMultiBlock = false, hasBufferedCreateMultiBlock = false;
    
    public TileSpacialProvider() {
        
        sideState = new EnumState[] { EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE, EnumState.NONE };
    }
    
    @Override
    public void updateEntity() {
        
        if (hasBufferedCreateMultiBlock) {
            
            if (this.createMultiBlock()) {
                
                hasBufferedCreateMultiBlock = false;
            }
        }
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
    
    public int getMultiBlockHeight() {
        
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
        isInMultiBlock = true;
        return multiBlocksIAmIn.add(multiBlock);
    }
    
    @Override
    public void removeMultiBlock(MultiBlockManager multiBlock) {
        
        multiBlocksIAmIn.remove(multiBlock);
        
        if (multiBlocksIAmIn.isEmpty()) {
            isInMultiBlock = false;
        }
    }
    
    @Override
    public Set<MultiBlockManager> getComprizedStructures() {
        
        return multiBlocksIAmIn;
    }
    
    public boolean createMultiBlock() {
        
        TileSpacialProvider tile = (TileSpacialProvider) worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
        
        Set<Vector3> multiBlockList = new HashSet<Vector3>();
        multiBlockList.clear();
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if (tile.getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {
                
                TileEntity foundTile = tile.getNearestProvider(direction);
                if (foundTile != null) {
                    
                    multiBlockList.add(new Vector3(foundTile));
                } else {
                    return false;
                }
            }
        }
        
        if (!worldObj.isRemote) {
            MultiBlockTank tank = new MultiBlockTank(worldObj, new Vector3(xCoord, yCoord, zCoord), tile.getMultiBlockXChange(), tile.getMultiBlockHeight(), tile.getMultiBlockZChange());
            UtilEntity.sendClientChat(tank.isMultiBlockAreaValid() + "");
            boolean valid = tank.makeMultiBlockValid();
            UtilEntity.sendClientChat(valid + "");
            return valid;
        }
        return false;
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
        tag.setBoolean("isInMultiBlock", isInMultiBlock);
        
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        
        for (int i = 0; i < tag.getInteger("multiBlockSide"); i++) {
            
            MultiBlockManager core = new MultiBlockTank(worldObj);
            core.load(tag.getCompoundTag("multiBlockClass" + i));
        }
        hasBufferedCreateMultiBlock = tag.getBoolean("isInMultiBlock");
    }
}
