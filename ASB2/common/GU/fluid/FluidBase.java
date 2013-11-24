package GU.fluid;

import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;

public class FluidBase extends Fluid {

    Icon texture;
    int hexColor;

    public FluidBase(String fluidName, int hexColor) {
        super(fluidName);
        this.hexColor =hexColor;
        net.minecraftforge.fluids.FluidRegistry.registerFluid(this);
        GU.FluidRegistry.GU_FLUIDS.add(this);
    }

    public Fluid setIcon(Icon icon) {

        texture = icon;
        this.setStillIcon(icon);
        this.setFlowingIcon(icon);
        return this;
    }

    @Override
    public Icon getIcon() {

        return texture;
    }

    @Override
    public int getColor() {

        return hexColor;
    }
}
