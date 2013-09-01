package GU.api.module;

import net.minecraftforge.fluids.FluidStack;

public abstract interface IModuleProvider {
    
    ModuleType getModuleType();
    FluidStack getFluid();
    String getName();
}
