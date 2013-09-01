package GU.api;

import net.minecraft.item.ItemStack;

public interface IItemInventory {

    /**
     * Gets the inventory the item contains
     */
    public int getInventory(ItemStack itemStack);
    
    /**
     * Called when an the contents of an Inventory change, usually
     */
    void onInventoryChanged(ItemInventory inventory);
    
    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    boolean isItemValidForSlot(ItemInventory inventory, int slot, ItemStack itemstack);
}
