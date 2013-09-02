package GU.blocks.containers.BlockAdvancedPotionBrewery;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import GU.api.WhiteLists;
import GU.api.potion.IPotion;
import GU.api.potion.IPotionIngredient;

public class PotionIngredientSlot extends Slot {

    public PotionIngredientSlot(IInventory par1iInventory, int par2, int par3, int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    public boolean isItemValid(ItemStack itemStack) {
        
        if(itemStack != null) {
            
            if(itemStack.getItem() instanceof IPotionIngredient || itemStack.getItem() instanceof IPotion) {
                
                return true;
            }
            
            return WhiteLists.getInstance().isEmptyBottle(itemStack);
        }
        return false;
    }
}
