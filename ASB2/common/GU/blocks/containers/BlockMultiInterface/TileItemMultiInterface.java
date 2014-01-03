package GU.blocks.containers.BlockMultiInterface;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.TileMultiBase;

public class TileItemMultiInterface extends TileMultiBase implements ISidedInventory {
    
    public TileItemMultiInterface() {
        
        this.destoryTileWithNotMultiBlock = true;
    }
    
    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {
        return super.addMultiBlock(multiBlock);
    }
    
    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
    }
    
    @Override
    public int getSizeInventory() {
        
        int sizeInventory = 0;
        
        for (IMultiBlock multi : this.getComprisedMultiBlocks()) {
            
            if (multi instanceof IInventory) {
                
                sizeInventory += ((IInventory) multi).getSizeInventory();
            }
        }
        return sizeInventory;
    }
    
    @Override
    public ItemStack getStackInSlot(int i) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ItemStack decrStackSize(int i, int j) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {
        // TODO Auto-generated method stub
        
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
    
}
