package GU.api;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IAdvancedPlantable {

    boolean isFullyGrown(World world, int x, int y, int z);    
    ArrayList<ItemStack> getDrops(World world, int x, int y, int z);
}
