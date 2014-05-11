package GUOLD.api.potion;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public interface IPotion {
    
    void addItemModule(ItemStack accepter, ItemStack moduleToAdd);    
    ArrayList<ItemStack> getModules(ItemStack stack);
    
    void setDuration(ItemStack stack, int amount);
    int getDuration(ItemStack stack);
    
    void setStrength(ItemStack stack, int amount);
    int getStrength(ItemStack stack);
    
    void setThrowable(ItemStack stack, boolean isthrowable);
    boolean getThrowable(ItemStack stack);
}
