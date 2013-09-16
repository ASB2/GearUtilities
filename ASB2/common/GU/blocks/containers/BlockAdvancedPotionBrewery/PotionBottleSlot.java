package GU.blocks.containers.BlockAdvancedPotionBrewery;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import GU.api.potion.IPotionBottle;

public class PotionBottleSlot extends Slot {

    public PotionBottleSlot(IInventory par1iInventory, int par2, int par3, int par4) {
        super(par1iInventory, par2, par3, par4);
    }

 public boolean isItemValid(ItemStack itemStack) {
        
        if(itemStack != null) {
            
            if(itemStack.getItem() instanceof IPotionBottle) {
                
                return true;
            }
        }
        return false;
    }
}
