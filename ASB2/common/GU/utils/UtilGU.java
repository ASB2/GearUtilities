package GU.utils;

import net.minecraft.item.ItemStack;
import GU.ItemRegistry;

public class UtilGU {
    
    public static boolean isWrench(ItemStack stack) {
        
        return stack != null && stack.getItem() == ItemRegistry.METADATA_ITEM && stack.getItemDamage() == 5;
    }
}
