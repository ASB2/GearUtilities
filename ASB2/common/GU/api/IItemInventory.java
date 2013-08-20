package GU.api;

import net.minecraft.item.ItemStack;

public interface IItemInventory {

    public int getSizeInventory(ItemStack itemStack);

    public ItemStack getStackInSlot(ItemStack itemStack, int slot);

    public boolean decreaseStackSize(ItemStack itemStack, int slot, int amount);

    public boolean setStackInSlot(ItemStack itemStack, int slot);

    public String getName(ItemStack itemStack);

    public int getMaxStackSize(ItemStack itemStack);

    public boolean isStackValidForSlot(ItemStack itemStack, int slot);
}
