package GU.api;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.power.IPowerProvider;

public interface ISolarFocus {

    void damageFocus(ItemStack stack, World world, int x, int y, int z, IPowerProvider solar);
    
    int getPowerForTick(ItemStack stack, World world, int x, int y, int z, IPowerProvider solar);
}
