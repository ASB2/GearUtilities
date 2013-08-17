package GU.fluid;

import net.minecraft.util.Icon;
import net.minecraftforge.fluids.Fluid;

public class FluidBase extends Fluid {

    int colorHex = 0xFFFFFF;
    Icon texture;
    
    public FluidBase(String fluidName, int colorHex) {
        super(fluidName);

        this.colorHex = colorHex;
        net.minecraftforge.fluids.FluidRegistry.registerFluid(this);
        //        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(fluidName, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(ItemRegistry.ItemStorageCrystal), new ItemStack(Item.bucketEmpty));
    }

    public Fluid setIcon(Icon icon) {
        
        texture = icon;
        return this;
    }
    
    public Icon getIcon() {
        
        return texture;        
    }
    
    public int getColor() {

        return colorHex;
    }
}
