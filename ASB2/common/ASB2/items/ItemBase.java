package ASB2.items;

import net.minecraft.item.ItemStack;


public class ItemBase extends Item {
    
    String ign = "";
    
    public ItemBase() {
    }
    
    public ItemBase setDisplayName(String name) {
        
        ign = name;
        return this;
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        return ign;
    }
}
