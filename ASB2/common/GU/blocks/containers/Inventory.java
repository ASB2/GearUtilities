package GU.blocks.containers;

import ASB2.utils.UtilInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Inventory implements IInventory {
    
    public final static int STANDARD_STACKSIZE = 64;
    
    int inventorySize = 0;
    int maxStackSize = 64;
    ItemStack[] storedStacks;
    String inventoryName;
    boolean playerCanUse;
    boolean localized = true;
    
    public Inventory(int inventorySize, String inventoryName, boolean useableByPlayer) {
        this(inventorySize, 64, inventoryName, useableByPlayer);
    }
    
    public Inventory(int inventorySize, int maxStackSize, String inventoryName, boolean useableByPlayer) {
        
        this.inventorySize = inventorySize;
        this.maxStackSize = maxStackSize;
        storedStacks = new ItemStack[inventorySize];
        this.inventoryName = inventoryName;
        playerCanUse = useableByPlayer;
    }
    
    public ItemStack[] getItemArray() {
        
        return storedStacks;
    }
    
    public void setSizeInventory(int newSize) {
        
        this.inventorySize = newSize;
    }
    
    @Override
    public int getSizeInventory() {
        
        return inventorySize;
    }
    
    @Override
    public ItemStack getStackInSlot(int i) {
        
        return storedStacks[i];
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int amount) {
        
        return UtilInventory.decreaseSlotContents(this, slot, amount);
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        
        return storedStacks[i];
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        
        storedStacks[i] = itemstack;
    }
    
    public void setInventoryName(String newName) {
        
        this.inventoryName = newName;
    }
    
    @Override
    public String getInvName() {
        
        return inventoryName;
    }
    
    public void setIsInventoryNameLocalized(boolean newBoolean) {
        
        this.localized = newBoolean;
    }
    
    @Override
    public boolean isInvNameLocalized() {
        
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
    public void onInventoryChanged() {
        
        // TODO Auto-generated method stub
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {
        
        return playerCanUse;
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
        
        return true;
    }
    
    public void save(NBTTagCompound tag) {
        
        NBTTagList nbttaglist = new NBTTagList();
        
        for (int i = 0; i < storedStacks.length; i++) {
            
            if (storedStacks[i] != null) {
                
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                nbttagcompound.setByte("Slot", (byte) i);
                storedStacks[i].writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }
        tag.setTag("Items", nbttaglist);
    }
    
    public void load(NBTTagCompound tag) {
        
        NBTTagList nbttaglist = tag.getTagList("Items");
        
        storedStacks = new ItemStack[storedStacks.length];
        
        for (int i = 0; i < nbttaglist.tagCount(); i++) {
            
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.tagAt(i);
            byte byte0 = nbttagcompound.getByte("Slot");
            
            if (byte0 >= 0 && byte0 < storedStacks.length) {
                
                storedStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound);
            }
        }
    }
}
