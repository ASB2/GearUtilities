package GUOLD.api.flame;

import net.minecraft.item.ItemStack;

public interface IFlameHandler {

    FlameStack getHeldFlame(ItemStack stack);
}
