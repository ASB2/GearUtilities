package GUOLD;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class GUFuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {

        if(ItemRegistry.ItemCrystal.ItemFireCrystalShard.isItemEqual(fuel)) {
            
            return 1600;
        }
        return 0;
    }

}
