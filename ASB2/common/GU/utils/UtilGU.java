package GU.utils;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import ASB2.utils.UtilItemStack;
import GU.ItemRegistry;

public class UtilGU {
    
    public static boolean isWrench(ItemStack stack) {
        
        return stack != null && stack.getItem() == ItemRegistry.METADATA_ITEM && stack.getItemDamage() == 5;
    }
    
    public static void setFluid(ItemStack stack, Fluid fluid) {
        
        if (fluid != null)
            UtilItemStack.setNBTTagString(stack, "fluidStored", fluid.getName());
    }
    
    public static Fluid getFluid(ItemStack stack) {
        
        return stack != null ? FluidRegistry.getFluid(UtilItemStack.getNBTTagString(stack, "fluidStored")) : null;
    }
}
