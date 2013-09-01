package GU.api;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IItemInventory {

    /**
     * Gets the inventory the item contains
     */
    IInventory getInventory(ItemStack itemStack);
    
    /**
     * Called when an the contents of an Inventory change, usually
     */
    void onInventoryChanged(IInventory inventory, ItemStack containerItem);
    
    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    boolean isItemValidForSlot(IInventory inventory, ItemStack containerItem, int slot, ItemStack itemstack);
    }
