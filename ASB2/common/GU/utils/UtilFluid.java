package GU.utils;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public final class UtilFluid {

    public static boolean moveFluid(IFluidHandler source, ForgeDirection from, IFluidHandler destination, int amount, boolean doMove) {

        ForgeDirection oppositeDirection = from.getOpposite();

        boolean isSuccessful = false;

        if(source != null && destination != null) {

            for (FluidTankInfo info : source.getTankInfo(from)) {

                if(info != null) {

                    if(info.fluid != null) {

                        FluidStack fluidToMove = info.fluid.copy();

                        if(fluidToMove.amount >= amount) {

                            fluidToMove.amount = amount;

                            if(UtilFluid.addFluidToTank(destination, from, fluidToMove, false)) {

                                if(UtilFluid.removeFluidFromTank(source, oppositeDirection, fluidToMove, false)) {

                                    isSuccessful = true;
                                    
                                    if(doMove) {

                                        UtilFluid.addFluidToTank(destination, from, fluidToMove, true);
                                        UtilFluid.removeFluidFromTank(source, oppositeDirection, fluidToMove, true);
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

    public static boolean addFluidToTank(IFluidHandler destination, ForgeDirection from, FluidStack fluid, boolean doMove) {

        boolean itWorked = false;

        ForgeDirection oppositeDirection = from.getOpposite();

        if(fluid != null && destination != null && fluid != null) {

            for (FluidTankInfo info : destination.getTankInfo(from)) {

                if(info != null) {

                    if(info.fluid != null) {

                        if(!(info.fluid.isFluidEqual(fluid))) {

                            itWorked = false;
                            break;
                        }                        

                        if(destination.canFill(oppositeDirection, fluid.getFluid())) {

                            if(destination.fill(oppositeDirection, fluid, false) != 0) {

                                if(doMove)
                                    destination.fill(oppositeDirection, fluid, true);

                                itWorked = true;
                            } 
                            else {

                                itWorked = false;
                            }
                        } 
                    }
                }
            }
        }
        return itWorked;
    }

    public static boolean removeFluidFromTank(IFluidHandler destination, ForgeDirection from, Fluid fluid, int amount, boolean doMove) {

        return UtilFluid.removeFluidFromTank(destination, from, new FluidStack(fluid, amount), doMove);
    }

    public static boolean removeFluidFromTank(IFluidHandler destination, ForgeDirection from, FluidStack fluid, boolean doMove) {

        ForgeDirection oppositeDirection = from.getOpposite();
        boolean itWorked = false;
        if(fluid != null && destination != null) {

            if(destination.getTankInfo(from) != null) {

                for (FluidTankInfo info : destination.getTankInfo(oppositeDirection)) {

                    if(info != null) {

                        if(info.fluid != null) {

                            if(!(info.fluid.isFluidEqual(fluid))) {

                                itWorked = false;
                                break;
                            }
                        }

                        if(destination.canDrain(oppositeDirection, fluid.getFluid())) {

                            if(destination.drain(oppositeDirection, fluid, false) != null) {

                                if(doMove)
                                    destination.drain(oppositeDirection, fluid, true);
                                itWorked = true;
                            }
                        }
                    }
                }
            }
        }
        return itWorked;
    }

    public static boolean isFull(FluidTankInfo[] info) {

        boolean isSucesful = false;

        for (FluidTankInfo tInfo : info) {

            if(tInfo != null) {

                if(!(tInfo.fluid.amount < tInfo.capacity)) {

                    isSucesful = true;
                } else {
                    isSucesful = false;
                }

            }

        }
        return isSucesful;
    }
}
