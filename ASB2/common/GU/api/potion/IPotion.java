package GU.api.potion;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public interface IPotion {
    
    void setDuration(ItemStack stack, int amount);
    int getDuration(ItemStack stack);
    
    void setStrength(ItemStack stack, int amount);
    int getStrength(ItemStack stack);
    
    ArrayList<ItemStack> getIngredients(ItemStack stack);
    
    void setIngredients(ItemStack stack, ArrayList<ItemStack> ingredients);
}
