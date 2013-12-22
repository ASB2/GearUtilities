package GU.blocks.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TileInventoryBase extends TileBase implements IInventory {
    
    public TileInventoryBase() {
        
        tileInventory = new Inventory(8, Inventory.STANDARD_STACKSIZE, "Inventory Base", true);
    }
    
    @Override
    public int getSizeInventory() {
        
        return tileInventory.getSizeInventory();
    }
    
    @Override
    public ItemStack getStackInSlot(int i) {
        
        return tileInventory.getStackInSlot(i);
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        
        return tileInventory.decrStackSize(slot, amount);
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        
        return tileInventory.getStackInSlotOnClosing(i);
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        
        tileInventory.setInventorySlotContents(i, itemStack);
    }
    
    @Override
    public boolean isInvNameLocalized() {
        
        return tileInventory.isInvNameLocalized();
    }
    
    @Override
    public int getInventoryStackLimit() {
        
        return tileInventory.getInventoryStackLimit();
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        
        return tileInventory.isUseableByPlayer(entityplayer);
    }
    
    @Override
    public void openChest() {
        
        tileInventory.openChest();
    }
    
    @Override
    public void closeChest() {
        
        tileInventory.closeChest();
    }
    
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        
        return tileInventory.isItemValidForSlot(i, itemstack);
    }
    
    @Override
    public String getInvName() {
        
        return tileInventory.getInvName();
    }
}
