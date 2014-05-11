package GUOLD;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import GUOLD.fluid.FluidBase;
import GUOLD.items.ItemStorageCrystal.ItemStorageCrystal;

public class FluidRegistry {
    
    public static List<ItemStack> STORAGE_CRYSTAL_LIST = new ArrayList<ItemStack>();
    public static List<FluidBase> GU_FLUIDS = new ArrayList<FluidBase>();
    
    public static FluidBase LifeEssenceGas, LifeEssenceLiquid;
    
    public static void initFluids() {
        
        LifeEssenceGas = new FluidBase("Life Essence Gas", Color.LIGHT_GRAY.hashCode());
        LifeEssenceGas.setGaseous(true);
        LifeEssenceGas.setLuminosity(8);
        LifeEssenceLiquid = new FluidBase("Life Essence Liquid", Color.LIGHT_GRAY.hashCode());
    }
    
    public static void registerFluidContainers() {
        
        for (Fluid fluid : net.minecraftforge.fluids.FluidRegistry.getRegisteredFluids().values()) {
            
            ItemStack filled = new ItemStack(ItemRegistry.ItemStorageCrystal, 1, fluid.getID());
            ((ItemStorageCrystal) filled.getItem()).setFluidStack(filled, new FluidStack(fluid, ((ItemStorageCrystal) filled.getItem()).getCapasity(filled)));
            FluidContainerRegistry.registerFluidContainer(new FluidStack(fluid, ((ItemStorageCrystal) filled.getItem()).getCapasity(filled)), filled, new ItemStack(ItemRegistry.ItemStorageCrystal, 1, 0));
            STORAGE_CRYSTAL_LIST.add(filled);
        }
    }
}
