package GU.utils;

import net.minecraftforge.fluids.*;

public final class UtilFluid {

    public static boolean moveFluid(IFluidTank source, IFluidTank destination, int amount) {

        if(source.getFluid() != null) {

            FluidStack fluid = source.getFluid();

            if(fluid.amount <= amount) {

                if(destination.getFluid() != null) {

                    if(source.getFluid().isFluidEqual(destination.getFluid())) {

                        if(source.getFluidAmount() >= amount && destination.getCapacity() - destination.getFluidAmount() >= amount) {

                            fluid.amount = amount;

                            source.drain(amount, true);
                            destination.fill(fluid, true);
                            return true;
                        }
                    }
                } 
                else {

                    if(source.getFluidAmount() >= amount) {

                        fluid.amount = amount;

                        if(fluid.amount <= destination.getCapacity()) {

                            source.drain(amount, true);
                            destination.fill(fluid, true);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
