package GU.api.power;

import net.minecraft.item.ItemStack;

public interface IPowerItem {

    IPowerProvider getPowerProvider(ItemStack stack);
}
