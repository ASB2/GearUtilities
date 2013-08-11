package GU.utils;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public final class UtilFluid {

    public static boolean emptyTank(IFluidHandler source, ForgeDirection from, IFluidHandler destination) {

        int amount = 1000;

        ForgeDirection oppositeDirection = UtilDirection.translateDirectionToOpposite(from);

        boolean isSuccessful = false;

        if(source != null && destination != null) {

            for(FluidTankInfo info: source.getTankInfo(from)) {

                if(info.fluid != null) {

                    FluidStack fluidStack = info.fluid.copy();

                    if(fluidStack.amount >= amount) {

                        fluidStack.amount = amount;

                        if(destination.canFill(oppositeDirection, fluidStack.getFluid())) {

                            if(source.canDrain(from, fluidStack.getFluid())) {

                                int loop = 0;
                                while(destination.fill(oppositeDirection, fluidStack, true) == 0) {
                                    loop++;
                                    amount = UtilMisc.getNumberDivided(amount, 2);
                                    
                                    if(loop >= 500) {
                                        break;
                                    }
                                }
                                source.drain(from, fluidStack, true);
                                isSuccessful = true;
                            }
                            else {

                                isSuccessful = false;
                            }
                        }
                    }
                }
            }
        } 
        return isSuccessful;
    }

    public static boolean moveFluid(IFluidHandler source, ForgeDirection from, IFluidHandler destination, int amount) {

        ForgeDirection oppositeDirection = UtilDirection.translateDirectionToOpposite(from);

        boolean isSuccessful = false;

        if(source != null && destination != null) {

            for(FluidTankInfo info: source.getTankInfo(from)) {

                if(info.fluid != null) {

                    FluidStack fluidStack = info.fluid.copy();

                    if(fluidStack.amount >= amount) {

                        fluidStack.amount = amount;

                        if(destination.canFill(oppositeDirection, fluidStack.getFluid())) {

                            if(source.canDrain(from, fluidStack.getFluid())) {

                                if (destination.fill(oppositeDirection, fluidStack, true) != 0) {

                                    source.drain(from, fluidStack, true);
                                    isSuccessful = true;
                                }
                                else {

                                    isSuccessful = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return isSuccessful;
    }

    public static boolean addFluidToTank(IFluidHandler destination, ForgeDirection from, FluidStack fluid) {

        boolean itWorked = false;

        ForgeDirection oppositeDirection = UtilDirection.translateDirectionToOpposite(from);

        if(fluid != null && destination != null) {

            for(FluidTankInfo info: destination.getTankInfo(from)) {

                if(info.fluid != null) {

                    if(info.fluid.isFluidEqual(fluid)) {

                        if(destination.canFill(oppositeDirection, fluid.getFluid())) {

                            if(destination.fill(oppositeDirection, fluid, true) != 0) {

                                itWorked = true;
                            }
                            else {
                                itWorked = false;
                            }
                        }
                    }
                }
                else {

                    if(destination.canFill(oppositeDirection, fluid.getFluid())) {

                        if(destination.fill(oppositeDirection, fluid, true) != 0) {

                            itWorked = true;
                        }
                        else {

                            itWorked = false;
                        }
                    }
                }
            }
        }
        return itWorked;
    }

    public static boolean removeFluidToTank(IFluidHandler destination, ForgeDirection from, FluidStack fluid) {

        ForgeDirection oppositeDirection = UtilDirection.translateDirectionToOpposite(from);

        if(fluid != null && destination != null) {

            for(FluidTankInfo info: destination.getTankInfo(from)) {

                if(info.fluid != null) {

                    if(info.fluid.isFluidEqual(fluid)) {

                        if(destination.canDrain(oppositeDirection, fluid.getFluid())) {

                            destination.drain(oppositeDirection, fluid, true);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean isFull(FluidTankInfo[] info) {

        boolean isSucesful = false;

        for(FluidTankInfo tInfo: info) {

            if(tInfo != null) {

                if(!(tInfo.fluid.amount < tInfo.capacity)) {

                    isSucesful = true;
                }
                else {
                    isSucesful = false;
                }

            }

        }
        return isSucesful;
    }
}
