package GU.api;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WhiteLists {

    private static WhiteLists instance = new WhiteLists();

    private List<ItemStack> wrenches = new ArrayList<ItemStack>();
    private List<ItemStack> emptyBottles = new ArrayList<ItemStack>();
    
    public WhiteLists() {

        emptyBottles.add(new ItemStack(Item.glassBottle));
    }

    public void addWrench(ItemStack stack) {

        if(stack != null) {

            wrenches.add(stack);
        }
    }

    public boolean isWrench(ItemStack stack) {

        if(stack == null) return false;

        if(wrenches.contains(stack)) {

            return true;
        }
        else {

            for(ItemStack item : wrenches) {

                if(item.isItemEqual(stack)) {

                    return true;
                }
            }
        }
        return false;
    }

    public void addEmptyBottle(ItemStack stack) {

        if(stack != null) {
            
            emptyBottles.add(stack);
        }
    }

    public boolean isEmptyBottle(ItemStack stack) {

        if(stack == null) return false;

        if(emptyBottles.contains(stack)) {

            return true;
        }
        else {

            for(ItemStack item : emptyBottles) {

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
