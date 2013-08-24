package GU.api.power;

import net.minecraft.item.ItemStack;

public interface IPowerItems {

    ItemPowerProvider getPowerProvider(ItemStack stack);
}
