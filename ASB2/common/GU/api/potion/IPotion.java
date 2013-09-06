package GU.api.potion;

import net.minecraft.item.ItemStack;
import GU.api.module.item.IItemModuleAccepter;

public interface IPotion extends IItemModuleAccepter {
    
    void setDuration(ItemStack stack, int amount);
    int getDuration(ItemStack stack);
    
    void setStrength(ItemStack stack, int amount);
    int getStrength(ItemStack stack);
}
