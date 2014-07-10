package GU.api;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IWrenchable {
    
    boolean triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side);
}
