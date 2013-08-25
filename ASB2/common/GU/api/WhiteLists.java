package GU.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public class WhiteLists {

    private static WhiteLists instance = new WhiteLists();

    private List<ItemStack> Wrenches = new ArrayList<ItemStack>();
    
    public void addWrench(ItemStack stack) {
        
        Wrenches.add(stack);
    }
    
    public boolean isWrench(ItemStack stack) {
        
        if(stack == null) return false;
        
        if(Wrenches.contains(stack)) {
           
            return true;
        }
        else {
            
            for(ItemStack item : Wrenches) {
                
                if(item.isItemEqual(stack)) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    public static WhiteLists getInstance() {

        return instance;
    }
}
