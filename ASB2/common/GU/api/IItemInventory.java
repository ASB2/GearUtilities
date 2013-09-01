package GU.api;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IItemInventory {

    /**
     * Gets the inventory the item contains
     */
    IInventory getInventory(ItemStack itemStack);
}
