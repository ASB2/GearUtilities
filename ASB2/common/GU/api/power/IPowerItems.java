package GU.api.power;

import net.minecraft.item.ItemStack;

public interface IPowerItems {

    IItemPowerProvider getPowerProvider(ItemStack stack);
}
