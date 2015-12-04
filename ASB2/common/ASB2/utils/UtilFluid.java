package ASB2.utils;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;


public final class UtilFluid {
    
    public static boolean moveFluid(IFluidHandler source, ForgeDirection from, IFluidHandler destination, ForgeDirection to, boolean doMove) {
        
        int amount = 1000000;
        
        while (true) {
            
            if (!UtilFluid.moveFluid(source, from, to, destination, amount, doMove)) {
                
                if (amount >= 1000) {
                    
                    amount = amount - 1000;
                }
                else if (amount >= 500) {
                    
                    amount = amount - 10;
                }
                else {
                    
                    return false;
                }
            }
            else {
                
                return true;
            }
        }
    }
    
    public static boolean moveFluid(IFluidHandler source, ForgeDirection from, ForgeDirection to, IFluidHandler destination, int amount, boolean doMove) {
        
        boolean isSuccessful = false;
        
        FluidStack fluidToMove = UtilFluid.removeFluidFromTankFluidStack(source, from, amount, false);
        
        if (fluidToMove != null) {
            
            fluidToMove = fluidToMove.copy();
            
            if (fluidToMove.amount >= amount) {
                
                fluidToMove.amount = amount;
            }
            else {
                
                return false;
            }
            
            if (UtilFluid.addFluidToTank(destination, to, fluidToMove, false)) {
                
                isSuccessful = true;
                
                if (doMove) {
                    
                    UtilFluid.addFluidToTank(destination, to, fluidToMove, true);
                    UtilFluid.removeFluidFromHandler(source, from, fluidToMove, true);
                }
            }
            
            else {
                
                isSuccessful = false;
            }
        }
        else {
            
            isSuccessful = false;
        }
        return isSuccessful;
    }
    
    public static boolean moveFluid(FluidTank source, ForgeDirection from, IFluidHandler destination, int amount, boolean doMove) {
        
        boolean isSuccessful = false;
        
        if (UtilFluid.addFluidToTank(destination, from, source.getFluid(), false)) {
            
            if (source.getFluidAmount() >= amount) {
                
                isSuccessful = true;
                
                if (doMove) {
                    
                    UtilFluid.addFluidToTank(destination, from, source.getFluid(), true);
                    source.setCapacity(amount);
                }
            }
        }
        
        return isSuccessful;
    }
    
    public static boolean moveFluid(IFluidHandler source, ForgeDirection from, IFluidHandler destination, ForgeDirection to, int amount, boolean doMove) {
        
        FluidStack fluidToMove = UtilFluid.removeFluidFromTankFluidStack(source, to, amount, false);
        
        if (fluidToMove != null) {
            
            fluidToMove = fluidToMove.copy();
            
            if (fluidToMove.amount >= amount) {
                
                fluidToMove.amount = amount;
                
                if (UtilFluid.addFluidToTank(destination, from, fluidToMove, false)) {
                    
                    if (doMove) {
                        
                        UtilFluid.addFluidToTank(destination, from, fluidToMove, true);
                        UtilFluid.removeFluidFromHandler(source, to, fluidToMove, true);
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean addFluidToTank(IFluidHandler destination, ForgeDirection from, FluidStack fluid, boolean doMove) {
        
        if (destination.canFill(from, fluid.getFluid())) {
            
            if (destination.fill(from, fluid, false) != 0) {
                
                return destination.fill(from, fluid, doMove) != 0;                
            }
        }
        return false;
    }
    
    public static FluidStack removeFluidFromTankFluidStack(IFluidHandler destination, ForgeDirection from, int amount, boolean doMove) {
        
        if (destination != null) {
            
            if (destination.getTankInfo(from) != null) {
                
                for (FluidTankInfo info : destination.getTankInfo(from)) {
                    
                    if (info != null) {
                        
                        if (info.fluid != null) {
                            
                            if (info.fluid.amount >= amount) {
                                
                                if (destination.canDrain(from, info.fluid.getFluid())) {
                                    
                                    if (destination.drain(from, info.fluid, false) != null) {
                                        
                                        return destination.drain(from, info.fluid, doMove);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public static boolean removeFluidFromTank(IFluidHandler tank, ForgeDirection from, int amount, boolean doMove) {
        
        if (tank.getTankInfo(from) != null) {
            
            for (FluidTankInfo info : tank.getTankInfo(from)) {
                
                if (info != null) {
                    
                    if (info.fluid != null) {
                        
                        if (info.fluid.amount >= amount) {
                            
                            if (tank.canDrain(from, info.fluid.getFluid())) {
                                
                                if (tank.drain(from, info.fluid, false) != null) {
                                    
                                    if (doMove) tank.drain(from, info.fluid, true);
                                    
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean removeFluidFromTank(IFluidHandler tank, ForgeDirection from, Fluid fluid, int amount, boolean doMove) {
        
        return UtilFluid.removeFluidFromHandler(tank, from, new FluidStack(fluid, amount), doMove);
    }
    
    public static boolean removeFluidFromHandler(IFluidHandler tank, ForgeDirection from, FluidStack fluid, boolean doMove) {
        
        if (tank.canDrain(from, fluid.getFluid())) {
            
            if (tank.drain(from, fluid, false) != null) {
                
                return tank.drain(from, fluid, doMove) != null;
            }
        }
        return false;
    }
}
