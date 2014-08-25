package GUOLD.fluid;

import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;

public class FluidBase extends Fluid {
    
    int hexColor;
    
    public FluidBase(String fluidName, int hexColor) {
        super(fluidName);
        this.hexColor = hexColor;
        net.minecraftforge.fluids.FluidRegistry.registerFluid(this);
        GUOLD.FluidRegistry.GU_FLUIDS.add(this);
    }
    
    public Fluid setIcon(IIcon icon) {
        
        this.setStillIcon(icon);
        this.setFlowingIcon(icon);
        return this;
    }
    
    @Override
    public int getColor() {
        
        return hexColor;
    }
}
