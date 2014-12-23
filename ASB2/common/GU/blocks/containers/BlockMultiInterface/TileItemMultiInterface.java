package GU.blocks.containers.BlockMultiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilInventory;
import ASB2.utils.UtilVector;
import GU.api.EnumSideState;
import GU.api.color.AbstractColorable.IColorableTile;
import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.api.multiblock.MultiBlockAbstract.IInventoryMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IItemInterface;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.blocks.containers.TileMultiBase;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class TileItemMultiInterface extends TileMultiBase implements IMultiBlockPart, IItemInterface, IInventory, IColorableTile {
    
    Map<SlotHolder, IInventoryMultiBlock> inventorise = new HashMap<SlotHolder, IInventoryMultiBlock>();
    int maxSizeInventory = 0;
    Vector3i position;
    public EnumSideState[] sideState;
    
    public TileItemMultiInterface() {
        
        this.setMaxMultiBlocks(2);
        sideState = new EnumSideState[ForgeDirection.values().length];
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            sideState[direction.ordinal()] = EnumSideState.NONE;
        }
    }
    
    int wait = 0;
    
    @Override
    public void updateEntity() {
        
        if (position == null) {
            
            position = UtilVector.createTileEntityVector(this);
        }
        
        wait++;
        
        if (!inventorise.isEmpty() && !worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord) && !worldObj.isRemote) {
            
            wait = 0;
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                EnumSideState side = sideState[direction.ordinal()];
                
                if (side == EnumSideState.OUTPUT || side == EnumSideState.INPUT) {
                    
                    TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                    
                    if (tile != null) {
                        
                        if (tile instanceof ISidedInventory) {
                            
                            if (side == EnumSideState.INPUT) {
                                
                                UtilInventory.moveItems((ISidedInventory) tile, direction, 1, 1, this);
                                // UtilInventory.moveEntireISidedInventory((ISidedInventory)
                                // tile, direction, this);
                            } else {
                                
                                UtilInventory.moveItems(this, 1, 1, (ISidedInventory) tile, direction);
                                // UtilInventory.moveEntireISidedInventory(this,
                                // direction, (ISidedInventory) tile);
                            }
                        }
                        
                        if (tile instanceof IInventory) {
                            
                            if (side == EnumSideState.INPUT) {
                                
                                UtilInventory.moveItems((IInventory) tile, 1, 1, this);
                                // UtilInventory.moveEntireInventory((IInventory)
                                // tile, this);
                            } else {
                                UtilInventory.moveItems(this, 1, 1, (IInventory) tile);
                                // UtilInventory.moveEntireInventory(this,
                                // (IInventory) tile);
                            }
                        }
                    }
                }
            }
        }
        
        super.updateEntity();
    }
    
    @Override
    public Color4i getColor(ForgeDirection direction) {
        
        Color4i color = sideState[direction.ordinal()].getColor();
        color = color.getRed() == 255 && color.getGreen() == 255 && color.getRed() == 255 ? Color4i.GREEN : color;
        
        switch (sideState[direction.ordinal()]) {
        
            case BOTH:
                return sideState[direction.ordinal()].getColor();
            case INPUT:
                return Color4i.BLUE;
            case NONE:
                return Color4i.GREEN;
            case OUTPUT:
                return Color4i.ORANGE;
            default:
                break;
        }
        return new Color4i(color.getRed(), (color.getGreen() + 255) / 2, color.getBlue());
    }
    
    @Override
    public boolean setColor(Color4i color, ForgeDirection direction) {
        
        return false;
    }
    
    @Override
    public boolean isPositionValid(EnumMultiBlockPartPosition position) {
        
        return position == EnumMultiBlockPartPosition.FACE;
    }
    
    @Override
    public boolean triggerBlock(World world, EntityPlayer player, int x, int y, int z, ForgeDirection axis) {
        
        sideState[axis.ordinal()] = sideState[axis.ordinal()].increment();
        
        if (sideState[axis.ordinal()] == EnumSideState.BOTH) {
            
            sideState[axis.ordinal()] = sideState[axis.ordinal()].increment();
        }
        
        world.markBlockForUpdate(x, y, z);
        return true;
    }
    
    @Override
    public void sendUpdatePacket() {
        
        NBTTagCompound tag = new NBTTagCompound();
        
        this.writeToNBT(tag);
        this.sendNBTPacket(tag, 0);
        super.sendUpdatePacket();
    }
    
    @Override
    public void readNBTPacket(NBTTagCompound tag, int id) {
        
        this.readFromNBT(tag);
        super.readNBTPacket(tag, id);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tag) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            tag.setInteger("sideState" + direction.ordinal(), sideState[direction.ordinal()].ordinal());
        }
        super.writeToNBT(tag);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag) {
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            sideState[direction.ordinal()] = EnumSideState.values()[tag.getInteger("sideState" + direction.ordinal())];
        }
        super.readFromNBT(tag);
    }
    
    private class SlotHolder {
        
        public int minSlot, maxSlot;
        
        public SlotHolder(int minSlot, int maxSlot) {
            this.minSlot = minSlot;
            this.maxSlot = maxSlot;
        }
        
        public int getMinSlot() {
            
            return minSlot;
        }
        
        public int getMaxSlot() {
            
            return maxSlot;
        }
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        
        if (super.addMultiBlock(multiBlock)) {
            
            recalculate();
            return true;
        }
        return false;
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        recalculate();
    }
    
    public void recalculate() {
        
        int minSlot = 0;
        maxSizeInventory = 0;
        inventorise.clear();
        
        for (IMultiBlock multi : this.getMultiBlocks()) {
            
            if (multi instanceof IInventoryMultiBlock) {
                
                IInventory inventory = ((IInventoryMultiBlock) multi).getInventory(position);
                
                if (inventory != null) {
                    
                    int size = inventory.getSizeInventory();
                    
                    if (size > 0) {
                        
                        SlotHolder holder = new SlotHolder(minSlot, minSlot + size);
                        this.inventorise.put(holder, (IInventoryMultiBlock) multi);
                        minSlot += size + 1;
                        maxSizeInventory += size;
                    }
                }
            }
        }
    }
    
    public IInventory redirectSlot(int slot) {
        
        for (SlotHolder slots : inventorise.keySet()) {
            
            if (slots.getMinSlot() <= slot && slots.getMaxSlot() >= slot) {
                
                return ((IInventoryMultiBlock) inventorise.get(slots)).getInventory(position);
            }
        }
        return null;
    }
    
    @Override
    public int getSizeInventory() {
        
        return maxSizeInventory;
    }
    
    @Override
    public ItemStack getStackInSlot(int i) {
        
        IInventory inventory = redirectSlot(i);
        
        if (inventory != null) {
            
            return inventory.getStackInSlot(i);
        }
        return null;
    }
    
    @Override
    public ItemStack decrStackSize(int i, int j) {
        
        IInventory inventory = redirectSlot(i);
        
        if (inventory != null) {
            
            return inventory.decrStackSize(i, j);
        }
        return null;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        
        IInventory inventory = redirectSlot(i);
        
        if (inventory != null) {
            
            return inventory.getStackInSlotOnClosing(i);
        }
        return null;
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        
        IInventory inventory = redirectSlot(i);
        
        if (inventory != null) {
            
            inventory.setInventorySlotContents(i, itemstack);
        }
    }
    
    @Override
    public String getInventoryName() {
        
        if (inventorise != null && inventorise.size() == 1) {
            
            IInventory inventory = inventorise.entrySet().iterator().next().getValue().getInventory(position);
            
            if (inventory != null) {
                
                return inventory.getInventoryName();
            }
        }
        return "Item Multi Interface";
    }
    
    @Override
    public boolean hasCustomInventoryName() {
        
        return true;
    }
    
    @Override
    public int getInventoryStackLimit() {
        
        if (inventorise != null && inventorise.size() == 1) {
            
            IInventory inventory = inventorise.entrySet().iterator().next().getValue().getInventory(position);
            
            if (inventory != null) {
                
                return inventory.getInventoryStackLimit();
            }
        }
        return 64;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public void openInventory() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void closeInventory() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        
        IInventory inventory = redirectSlot(i);
        
        if (inventory != null) {
            
            return inventory.isItemValidForSlot(i, itemstack);
        }
        return false;
    }
    
    @Override
    public List<IInventory> getAvaliableInventorys() {
        
        List<IInventory> inventoryList = new ArrayList<IInventory>();
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if (sideState[direction.ordinal()] == EnumSideState.OUTPUT) {
                
                TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
                
                if (tile != null && tile instanceof IInventory) {
                    
                    inventoryList.add((IInventory) tile);
                }
            }
        }
        return inventoryList;
    }
}