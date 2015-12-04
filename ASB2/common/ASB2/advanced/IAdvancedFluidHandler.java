package ASB2.advanced;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidTank;


public interface IAdvancedFluidHandler {

    IFluidTank[] getTank(ItemStack stack);
}
