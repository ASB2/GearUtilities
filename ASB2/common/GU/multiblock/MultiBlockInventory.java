package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.inventory.Inventory;
import UC.math.vector.Vector3i;

public abstract class MultiBlockInventory extends MultiBlockBase implements ISidedInventory {
    
    Inventory inventory;
    
    public MultiBlockInventory(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        inventory = new Inventory();
    }
    
    public MultiBlockInventory(World world) {
        super(world);
        inventory = new Inventory();
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        if (inventory != null) tag.setTag("Inventory", inventory.save(new NBTTagCompound()));
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        if (inventory != null) inventory.load(tag.getCompoundTag("Inventory"));
        super.load(tag);
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
    
    @Override
    public void markDirty() {
        // TODO Auto-generated method stub
        
    }
}