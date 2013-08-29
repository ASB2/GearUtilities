package GU.api.runes;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IRuneItem {

    void onUpdate(World world, IRuneBlock block, ItemStack stack, int x, int y, int z);
    boolean shouldUpdate(World world, IRuneBlock block, ItemStack stack, int x, int y, int z);
    void randomUpdate(World world, IRuneBlock block, ItemStack stack, int x, int y, int z);
    Item getItem();
}
