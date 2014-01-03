package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.TileMultiBase;

public class TileItemMultiInterface extends TileMultiBase implements ISidedInventory {
    
    private SlotWrapper[] slots;
    
    public TileItemMultiInterface() {
        this.destoryTileWithNotMultiBlock = true;
        resetMultiBlock();
    }
    
    // ON MULTIBLOCK CHANGE: call this method //
    private void resetMultiBlock() {
    	// TODO: recalculate comprisedMultiBlocks
    	// TODO: idealy recalculate overall inventorySize and return that in getSizeInventory(). would be better in performance than calculating it everytime.
    
    	slots = new SlotWrapper[getSizeInventory()];
    
    	int currentSlotIndex = 0;
    
    	for(IMultiBlock multi: getComprisedMultiBlocks()) {
            if(multi instanceof IInventory) {
                IInventory inventory = ((IInventory)multi)
                for(int subIndex = 0; subIndex<inventory.getSizeInventory(); subIndex++) {
    				slots[currentSlotIndex] = new SlotWrapper(inventory, subIndex);
    				currentSlotIndex++;
    			}
            }
        }
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {        
        boolean result = super.addMultiBlock(multiBlock);
        
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
    }
    
    @Override
    public int getSizeInventory() {
        
    int sizeInventory = 0;
    
    for(IMultiBlock multi: this.getComprisedMultiBlocks()) {
        
        if(multi instanceof IInventory) {
            
            sizeInventory += ((IInventory)multi).getSizeInventory();
        }
    }
        return sizeInventory;
    }
    
    @Override
    public ItemStack getStackInSlot(int i) {
        if (i>= getSizeInventory()) return null; // TODO: check if something should happen in this case
        else return slots[i].getStack();
    }
    
    @Override
    public ItemStack decrStackSize(int i, int j) {
        if (i>= getSizeInventory()) return null; // TODO: check if something should happen in this case
        else return slots[i].decrStack(j);
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if (i>= getSizeInventory()) return null; // TODO: check if something should happen in this case
        else return slots[i].getStackOnClosing();
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        if (i>= getSizeInventory()) return; // TODO: check if something should happen in this case
        else slots[i].setSlotContents(itemstack);
    }
    
    @Override
    public String getInvName() {
        // TODO Auto-generated method stub
        return "";
    }
    
    @Override
    public boolean isInvNameLocalized() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public int getInventoryStackLimit() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public void openChest() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void closeChest() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        // TODO Auto-generated method stub
        return false;
    }
    
    /**
     * Inner class to wrap the slots of the contained inventories
     */
    private class SlotWrapper {
    	private final int index;
    	private final IInventory inventory;
    
    	public SlotWrapper(IInventory inventory, int index) {
    		this.inventory = inventory;
    		this.index = index;
    	}
    
    	public ItemStack getStack() {
    		return inventory.getStackInSlot(index);
    	}
    
    	public ItemStack decrStack(int j) {
    		return inventory.decrStackInSlot(index, j);
    	}
    
    	public ItemStack getStackOnClosing() {
            return inventory.getStackInSlotOnClosing(index);
        }
        
        @Override
        public void setSlotContents(ItemStack itemstack) {
            inventory.setInventorySlotContents(index, itemstack);
        }
    }
    
}
