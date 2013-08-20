package GU;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class GUFuelHandler implements IFuelHandler {

    @Override
    public int getBurnTime(ItemStack fuel) {

        int id = fuel.itemID;

        if (id == (ItemRegistry.ItemFireCrystalShard.itemID)) {

            return 1600;
        }

        return 0;
    }

}
