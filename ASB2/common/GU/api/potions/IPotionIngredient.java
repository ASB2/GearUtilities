package GU.api.potions;

import net.minecraft.item.ItemStack;

public interface IPotionIngredient {
    
    PotionEffect getPotionEffect(ItemStack stack);
}
