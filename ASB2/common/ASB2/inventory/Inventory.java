package ASB2.inventory;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IChatComponent;
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
    boolean infiniteSlots = false;
    
    int[][] accesibleSlotsFromSide;
    
    public Inventory(String inventoryName) {
        this(0, inventoryName, true);
    }
    
    public Inventory(int inventorySize, String inventoryName) {
        this(inventorySize, inventoryName, true);
    }
    
    public Inventory(int inventorySize, String inventoryName, boolean useableByPlayer) {
        this(inventorySize, STANDARD_STACKSIZE, inventoryName, useableByPlayer);
    }
    
    public Inventory(int inventorySize, int maxStackSize, String inventoryName, boolean useableByPlayer) {
        
        if (infiniteSlots = inventorySize == -1) {
            inventorySize = 17;
        }
        this.inventorySize = inventorySize;
        this.maxStackSize = maxStackSize;
        storedStacks = new HashMap<Integer, ItemStack>();
        this.inventoryName = inventoryName;
        playerCanUse = useableByPlayer;
        
        accesibleSlotsFromSide = new int[EnumFacing.values().length][inventorySize];
    }
    
    public Inventory() {
        this(0, "Not Specified");
    }
    
    public boolean hasLoaded() {
        
        return hasLoaded;
    }
    
    public Map<Integer, ItemStack> getItemArray() {
        
        return storedStacks;
    }
    
    public void setSizeInventory(int newSize) {
        
        this.inventorySize = newSize;
        storedStacks.clear();
    }
    
    @Override
    public int getSizeInventory() {
        
        return infiniteSlots ? storedStacks.size() + 1 : inventorySize;
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
    
    // @Override
    // public String getInventoryName() {
    //
    // return inventoryName;
    // }
    
    public void setIsInventoryNameLocalized(boolean newBoolean) {
        
        this.localized = newBoolean;
    }
    
    // @Override
    // public boolean hasCustomInventoryName() {
    //
    // return localized;
    // }
    
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
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {
        
        return true;
    }
    
    public NBTTagCompound save(NBTTagCompound tag) {
        
        int slots = 0;
        
        for (ItemStack stack : storedStacks.values()) {
            
            if (stack != null) {
                
                tag.setTag("Slot: " + slots, stack.writeToNBT(new NBTTagCompound()));
            }
            slots++;
        }
        tag.setInteger("SizeInventory", slots);
        return tag;
    }
    
    public void load(NBTTagCompound tag) {
        
        hasLoaded = true;
        this.inventorySize = tag.getInteger("SizeInventory");
        
        storedStacks.clear();
        
        for (int currentItem = 0; currentItem < inventorySize; currentItem++) {
            
            storedStacks.put(currentItem, ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Slot: " + currentItem)));
        }
    }
    
    @Override
    public void markDirty() {
        // TODO Auto-generated method stub
        
    }
    
    public boolean isEmpty() {
        
        return this.storedStacks.size() == 0;
    }
    
    public void clear() {
        
        storedStacks.clear();
    }
    
    @Override
    public void openInventory(EntityPlayer player) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void closeInventory(EntityPlayer player) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public int getField(int id) {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public void setField(int id, int value) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public int getFieldCount() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public String getCommandSenderName() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public boolean hasCustomName() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public IChatComponent getDisplayName() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        
        return accesibleSlotsFromSide[side.ordinal()];
    }
    
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        // TODO Auto-generated method stub
        return true;
    }
}
