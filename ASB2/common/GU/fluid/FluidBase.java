package GU.fluid;

import GU.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class FluidBase extends Fluid {

    int colorHex = 0xFFFFFF;

    public FluidBase(String fluidName, int colorHex) {
        super(fluidName);

        this.colorHex = colorHex;
        net.minecraftforge.fluids.FluidRegistry.registerFluid(this);
//        FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack(fluidName, FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(ItemRegistry.ItemStorageCrystal), new ItemStack(Item.bucketEmpty));
    }

    public int getColor() {

        return colorHex;
    }
}
