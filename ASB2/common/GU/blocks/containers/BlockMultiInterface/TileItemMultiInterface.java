package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import ASB2.inventory.Inventory;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockPart;
import GU.blocks.containers.TileMultiBase;

public class TileItemMultiInterface extends TileMultiBase implements IMultiBlockPart, ISidedInventory {
    
    Inventory inventory;
    
    public TileItemMultiInterface() {
        
        inventory = new Inventory(0, "Inventory Base", true);
    }
    
    @Override
    public void updateEntity() {
        // TODO Auto-generated method stub
        super.updateEntity();
    }
    
    @Override
    public int getSizeInventory() {
        
        return inventory.getSizeInventory();
    }
    
    @Override
    public ItemStack getStackInSlot(int i) {
        
        return inventory.getStackInSlot(i);
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        
        return inventory.decrStackSize(slot, amount);
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        
        return inventory.getStackInSlotOnClosing(i);
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        
        inventory.setInventorySlotContents(i, itemStack);
    }
    
    @Override
    public boolean hasCustomInventoryName() {
        
        return inventory.hasCustomInventoryName();
    }
    
    @Override
    public int getInventoryStackLimit() {
        
        return inventory.getInventoryStackLimit();
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        
        return inventory.isUseableByPlayer(entityplayer);
    }
    
    @Override
    public void openInventory() {
        
        inventory.openInventory();
    }
    
    @Override
    public void closeInventory() {
        
        inventory.closeInventory();
    }
    
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        
        return inventory.isItemValidForSlot(i, itemstack);
    }
    
    @Override
    public String getInventoryName() {
        
        return inventory.getInventoryName();
    }
    
    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        
        return inventory.getAccessibleSlotsFromSide(var1);
    }
    
    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        
        return inventory.canInsertItem(i, itemstack, j);
    }
    
    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        
        return inventory.canExtractItem(i, itemstack, j);
    }
}
