package GU;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import GU.utils.UtilGU;

public class FluidRegistry {
    
    public static List<ItemStack> FluidCrystalArrayList = new ArrayList<ItemStack>();
    
    // public static Fluid STEAM = new Fluid("Steam") {
    //
    // public net.minecraft.util.IIcon getStillIcon() {
    // return NoiseManager.instance.blockNoiseIcon;
    // }
    //
    // public net.minecraft.util.IIcon getFlowingIcon() {
    // return NoiseManager.instance.blockNoiseIcon;
    // }
    // };
    
    public static void initFluids() {
        
        // STEAM.setGaseous(true);
        // net.minecraftforge.fluids.FluidRegistry.registerFluid(STEAM);
    }
    
    public static void registerFluidContainers() {
        
        for (Fluid fluid : net.minecraftforge.fluids.FluidRegistry.getRegisteredFluids().values()) {
            
            ItemStack filled = new ItemStack(ItemRegistry.ITEM_FLUID, 1, fluid.getID());
            UtilGU.setFluid(filled, fluid);
            FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, 1000), filled, new ItemStack(ItemRegistry.ITEM_FLUID));
            FluidCrystalArrayList.add(filled);
        }
    }
}
