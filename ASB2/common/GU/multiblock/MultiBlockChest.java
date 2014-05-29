package GU.multiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.inventory.Inventory;
import GU.api.multiblock.MultiBlockAbstract.IMultiBlockStructure;
import UC.color.Color4i;
import UC.math.vector.Vector3i;

public class MultiBlockChest extends MultiBlockBase implements ISidedInventory {
    
    Inventory inventory;
    
    public MultiBlockChest(World world, Vector3i positionRelativeTo, Vector3i size, Vector3i updater) {
        super(world, positionRelativeTo, size, updater);
        
        inventory = new Inventory(8 * ((this.size.getX() + 1) * (this.size.getY() + 1) * (this.size.getZ() + 1)), "Inventory Base", true);
    }
    
    public MultiBlockChest(World world) {
        super(world);
        inventory = new Inventory(0, "Inventory Base", true);
    }
    
    @Override
    public IMultiBlockStructure getStructure() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Color4i getDefaultBlockColor() {
        
        return Color4i.GREEN;
    }
    
    @Override
    public NBTTagCompound save(NBTTagCompound tag) {
        
        tag.setTag("Inventory", inventory.save(new NBTTagCompound()));
        return super.save(tag);
    }
    
    @Override
    public void load(NBTTagCompound tag) {
        
        inventory.load(tag.getCompoundTag("Inventory"));
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