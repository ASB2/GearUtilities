package GU.inventory;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ASB2.utils.UtilInventory;

public class Inventory implements ISidedInventory {
    
    public final static int STANDARD_STACKSIZE = 64;
    
    int inventorySize = 0;
    int maxStackSize = 64;
    Map<Integer, ItemStack> storedStacks;
    String inventoryName;
    boolean playerCanUse;
    boolean localized = true;
    
    boolean hasLoaded = false;
    
    public Inventory(int inventorySize, String inventoryName) {
        this(inventorySize, inventoryName, true);
    }
    
    public Inventory(int inventorySize, String inventoryName, boolean useableByPlayer) {
        this(inventorySize, STANDARD_STACKSIZE, inventoryName, useableByPlayer);
    }
    
    public Inventory(int inventorySize, int maxStackSize, String inventoryName, boolean useableByPlayer) {
        
        this.inventorySize = inventorySize;
        this.maxStackSize = maxStackSize;
        storedStacks = new HashMap<Integer, ItemStack>(inventorySize);
        this.inventoryName = inventoryName;
        playerCanUse = useableByPlayer;
    }
    
    public boolean hasLoaded() {
        
        return hasLoaded;
    }
    
    public Map<Integer, ItemStack> getItemArray() {
        
        return storedStacks;
    }
    
    public void setSizeInventory(int newSize) {
        
        this.inventorySize = newSize;
        storedStacks = new HashMap<Integer, ItemStack>();
    }
    
    @Override
    public int getSizeInventory() {
        
        return inventorySize;
    }
    
    @Override
    public ItemStack getStackInSlot(int i) {
        
        return storedStacks.get(i);
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        
        return UtilInventory.decreaseSlotContents(this, slot, amount);
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        
        return getStackInSlot(i);
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        
        storedStacks.put(i, itemstack);
    }
    
    public void setInventoryName(String newName) {
        
        this.inventoryName = newName;
    }
    
    @Override
    public String getInventoryName() {
        
        return inventoryName;
    }
    
    public void setIsInventoryNameLocalized(boolean newBoolean) {
        
        this.localized = newBoolean;
    }
    
    @Override
    public boolean hasCustomInventoryName() {
        
        return localized;
    }
    
    public void setInventoryStackLimit(int newLimit) {
        
        this.maxStackSize = newLimit;
    }
    
    @Override
    public int getInventoryStackLimit() {
        
        return maxStackSize;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        
        return playerCanUse;
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
        
        return true;
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        int slots = 0;
        
        for (ItemStack stack : storedStacks.values()) {
            
            slots++;
            
            if (stack != null) {
                
                tag.setTag("Slot: " + slots, stack.writeToNBT(new NBTTagCompound()));
            }
        }
        tag.setInteger("SizeInventory", slots);
        return tag;
    }
    
    public void load(NBTTagCompound tag) {
        
        hasLoaded = true;
        this.inventorySize = tag.getInteger("SizeInventory");
        
        storedStacks = new HashMap<Integer, ItemStack>();
        
        for (int currentItem = 0; currentItem < inventorySize; currentItem++) {
            
            ItemStack stack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Slot: " + currentItem));
            
            storedStacks.put(currentItem, stack);
        }
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
    
    @Override
    public void markDirty() {
        // TODO Auto-generated method stub
        
    }
}
