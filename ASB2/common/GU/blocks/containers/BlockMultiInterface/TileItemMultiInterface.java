package GU.blocks.containers.BlockMultiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import ASB2.utils.UtilVector;
import GU.api.multiblock.MultiBlockAbstract.EnumMultiBlockPartPosition;
import GU.api.multiblock.MultiBlockAbstract.IInventoryMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IItemInterface;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlock;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.blocks.containers.TileMultiBase;
import UC.math.vector.Vector3i;

public class TileItemMultiInterface extends TileMultiBase implements IMultiBlockPart, IItemInterface, IInventory {
    
    Map<SlotHolder, IMultiBlock> inventorise = new HashMap<SlotHolder, IMultiBlock>();
    int maxSizeInventory = 0;
    Vector3i position;
    
    public TileItemMultiInterface() {
        
        this.setMaxMultiBlocks(2);
    }
    
    @Override
    public void updateEntity() {
        
        if (position == null) {
            
            position = UtilVector.createTileEntityVector(this);
        }
    }
    
    @Override
    public boolean isPositionValid(EnumMultiBlockPartPosition position) {
        
        return position == EnumMultiBlockPartPosition.FACE;
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
    
    @SuppressWarnings("unused")
    public void recalculate() {
        
        if (false) {
            
            int lastSlotMin = 0;
            
            for (IMultiBlock multi : this.getMultiBlocks()) {
                
                if (multi instanceof IInventoryMultiBlock) {
                    
                    int size = ((IInventoryMultiBlock) multi).getInventory(position).getSizeInventory();
                    
                    if (size > 0) {
                        
                        this.inventorise.put(new SlotHolder(lastSlotMin, ((IInventoryMultiBlock) multi).getInventory(position).getSizeInventory() + lastSlotMin), multi);
                        lastSlotMin += ((IInventoryMultiBlock) multi).getInventory(position).getSizeInventory() + 1;
                    }
                }
            }
            maxSizeInventory = lastSlotMin;
        }
        else {
            
            int minSlot = 0;
            maxSizeInventory = 0;
            
            for (IMultiBlock multi : this.getMultiBlocks()) {
                
                if (multi instanceof IInventoryMultiBlock) {
                    
                    int size = ((IInventoryMultiBlock) multi).getInventory(position).getSizeInventory();
                    
                    if (size > 0) {
                        
                        SlotHolder holder = new SlotHolder(minSlot, minSlot + size);
                        this.inventorise.put(holder, multi);
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
        
        return "Item Multi Interface";
    }
    
    @Override
    public boolean hasCustomInventoryName() {
        
        return true;
    }
    
    @Override
    public int getInventoryStackLimit() {
        
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
            
            TileEntity tile = worldObj.getTileEntity(xCoord + direction.offsetX, yCoord + direction.offsetY, zCoord + direction.offsetZ);
            
            if (tile != null && tile instanceof IInventory) {
                
                inventoryList.add((IInventory) tile);
            }
        }
        return inventoryList;
    }
}