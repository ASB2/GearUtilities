package GU.api.multiblock;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;

public interface ITankMultiBlock {

    int fill(ForgeDirection from, FluidStack resource, boolean doFill);
    
    FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain);
}
