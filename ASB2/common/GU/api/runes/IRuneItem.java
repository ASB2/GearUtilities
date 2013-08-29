package GU.api.runes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IRuneItem {

    void onUpdate(IRuneBlock block, ItemStack stack, int x, int y, int z);
    boolean shouldUpdate(IRuneBlock block, ItemStack stack, int x, int y, int z);
    void onRemoval(IRuneBlock block, ItemStack stack, int x, int y, int z);
    Item getItem();
}
