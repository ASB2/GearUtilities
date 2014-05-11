package GUOLD.api.potion;

import net.minecraft.item.ItemStack;

public interface IPotionBottle extends IPotionIngredient {

    boolean isThrowable(ItemStack stack); 
}
