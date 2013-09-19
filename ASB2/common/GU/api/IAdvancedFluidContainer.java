package GU.api;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.IFluidTank;

public interface IAdvancedFluidContainer {

    IFluidTank getTank(ItemStack stack);
}
