package GU.api.power;

import net.minecraft.item.ItemStack;

public interface IPowerItems {

    IPowerProvider getPowerProvider(ItemStack stack);
}
