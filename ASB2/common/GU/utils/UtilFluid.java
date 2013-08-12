package GU.utils;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public final class UtilFluid {

    public static boolean moveFluid(IFluidHandler source, ForgeDirection from, IFluidHandler destination, int amount, boolean fillExtra) {

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
                        
                        else if(fillExtra) {
                            
                            FluidStack stackTwo = fluidStack.copy();
                            
                            if(stackTwo.amount >= info.capacity - info.fluid.amount) {
                                
                                stackTwo.amount = info.capacity - info.fluid.amount;
                                
                                if(destination.canFill(oppositeDirection, stackTwo.getFluid())) {

                                    if(source.canDrain(from, stackTwo.getFluid())) {

                                        if (destination.fill(oppositeDirection, stackTwo, true) != 0) {

                                            source.drain(from, stackTwo, true);
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

    public static boolean removeFluidFromTank(IFluidHandler destination, ForgeDirection from, FluidStack fluid) {

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
