package GU.api.module.item;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public interface IItemModuleAccepter {

    void addItemModule(ItemStack accepter, ItemStack moduleToAdd);    
    ArrayList<ItemStack> getModules(ItemStack stack);
}
