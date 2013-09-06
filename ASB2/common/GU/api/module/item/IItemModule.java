package GU.api.module.item;

import net.minecraft.item.ItemStack;

public interface IItemModule {
    
    void onAdded(IItemModuleAccepter moduleHolder, ItemStack stack);
    void onRemoved(IItemModuleAccepter moduleHolder, ItemStack stack);
}
