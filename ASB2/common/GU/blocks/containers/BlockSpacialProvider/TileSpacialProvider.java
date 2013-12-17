package GU.blocks.containers.BlockSpacialProvider;

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
import GU.multiblock.MultiBlockTank;

public class TileSpacialProvider extends TileBase implements ISpacialProvider {
    
    public static int MAX_DISTANCE = 16;
    MultiBlockManager currentMultiBlock;
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
        
        for (int i = 0; i <= MAX_DISTANCE; i++) {
            
            Vector3 position = new Vector3(last).add(direction, 1);
            TileEntity tile = position.getTileEntity(worldObj);
            
            if (tile != null && tile != this && tile instanceof ISpacialProvider) {
                
                return tile;
            } else if (tile == this) {
                return null;
            }
        }
        return null;
    }
    
    public int getDistanceToNearestProvider(ForgeDirection direction) {
        
        for (int i = 0; i <= MAX_DISTANCE; i++) {
            
            Vector3 position = new Vector3(this).add(direction, i);
            TileEntity tile = position.getTileEntity(worldObj);
            
            if (tile != null && tile != this && tile instanceof ISpacialProvider) {
                
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
    
    // @Override
    // public Set<Vector3> getProvidedTiles() {
    //
    // Set<Vector3> tileList = new HashSet<Vector3>();
    //
    // for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
    //
    // if (getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {
    //
    // TileEntity tile = this.getNearestProvider(direction);
    //
    // if (tile != null) {
    //
    // tileList.add(new Vector3(tile));
    // }
    // }
    // }
    // return tileList;
    // }
    
    @Override
    public boolean setStructure(MultiBlockManager multiBlock) {
        
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
    public MultiBlockManager getCurrentStructure() {
        
        return currentMultiBlock;
    }
    
    public boolean createMultiBlock() {
        
        return createMultiBlock(false);
    }
    
    public boolean createMultiBlock(boolean hasStructure) {
        
        TileSpacialProvider tile = (TileSpacialProvider) worldObj.getBlockTileEntity(xCoord, yCoord, zCoord);
        
        if (!hasStructure) {
            
            if (this.getCurrentStructure() == null) {
                
                int found = 0;
                
                for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                    
                    if (tile.getSideStateArray(direction.ordinal()) == EnumState.OUTPUT) {
                        
                        TileEntity foundTile = tile.getNearestProvider(direction);
                        
                        if (foundTile != null) {
                            
                            found++;
                        } else {
                            
                            return false;
                        }
                    }
                }
                
                if (found > 1) {
                    
                    MultiBlockTank tank = new MultiBlockTank(worldObj, new Vector3(xCoord, yCoord, zCoord), tile.getMultiBlockXChange(), tile.getMultiBlockYChange(), tile.getMultiBlockZChange());
                    UtilEntity.sendClientChat("First Check " + tank.isMultiBlockAreaValid());
                    boolean valid = tank.makeMultiBlockValid();
                    UtilEntity.sendClientChat("Second Check " + valid);
                    return valid;
                } else {
                    return false;
                }
            } else {
                
                return false;
            }
        } else {
            
            if (this.getCurrentStructure().getWorld() == null) {
                this.getCurrentStructure().setWorld(this.worldObj);
            }
            hasBufferedCreateMultiBlock = false;
            UtilEntity.sendClientChat("First Check " + this.getCurrentStructure().isMultiBlockAreaValid());
            boolean valid = this.getCurrentStructure().makeMultiBlockValid();
            UtilEntity.sendClientChat("Second Check " + valid);
            return valid;
        }
        // return false;
    }
    
    @Override
    public void invalidate() {
        
        if (this.getCurrentStructure() != null) {
            this.getCurrentStructure().invalidate();
        }
        super.invalidate();
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        
        if (this.getCurrentStructure() != null) {
            
            if (new Vector3(this).intEquals(this.getCurrentStructure().getMultiBlockCore())) {
                
                tag.setCompoundTag("multiBlockSave", this.getCurrentStructure().save(new NBTTagCompound()));
                tag.setBoolean("isInMultiBlock", isInMultiBlock);
            }
        }
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        
        MultiBlockManager core = new MultiBlockTank();
        core.load(tag.getCompoundTag("multiBlockSave"));
        this.setStructure(core);
        hasBufferedCreateMultiBlock = tag.getBoolean("isInMultiBlock");
    }
}
