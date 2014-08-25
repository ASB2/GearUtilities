package GUOLD.blocks.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import GUOLD.inventory.Inventory;

public class TileInventoryBase extends TileBase implements IInventory {
    
    public TileInventoryBase() {
        
        tileInventory = new Inventory(0, "Inventory Base", true);
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
    public boolean hasCustomInventoryName() {
        
        return tileInventory.hasCustomInventoryName();
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
    public void openInventory() {
        
        tileInventory.openInventory();
    }
    
    @Override
    public void closeInventory() {
        
        tileInventory.closeInventory();
    }
    
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        
        return tileInventory.isItemValidForSlot(i, itemstack);
    }
    
    @Override
    public String getInventoryName() {
        
        return tileInventory.getInventoryName();
    }
}
