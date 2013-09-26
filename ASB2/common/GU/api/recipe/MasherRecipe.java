package GU.api.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public final class MasherRecipe {

    private static ArrayList<MasherRecipeHolder> recipes = new ArrayList<MasherRecipeHolder>();

    public static boolean addRecipe(ItemStack[] input, ItemStack resultItem, FluidStack resultFluid, float energy) {

        if(input != null && (resultItem != null || resultFluid != null)) {

            MasherRecipeHolder recipe = new MasherRecipeHolder(input, resultFluid, resultItem, energy);
            recipes.add(recipe);
            return true;
        }
        return false;
    }

    public static MasherRecipeHolder findRecipe(ItemStack[] input) {
        
        for(MasherRecipeHolder current : recipes) {
            
            if(current.getInput().equals(input)) {
                
                return current;
            }
        }
        return null;
    }   

    public static class MasherRecipeHolder {

        ItemStack[] input;
        FluidStack outputFluid; 
        ItemStack outputItem;        
        float energyRequired = 0;


        public MasherRecipeHolder(ItemStack[] input, FluidStack outputFluid, ItemStack outputItem, float energy) {

            this.input = input;
            this.outputFluid = outputFluid;
            this.outputItem = outputItem;
            energyRequired = energy;
        }

        public ItemStack[] getInput() {

            return input;
        }

        public FluidStack getFluidOutput() {
        
            return outputFluid;
        }
        
        public ItemStack getItemOutput() {
            
            return outputItem;
        }
        
        public float getEnergyRequired() {

            return energyRequired;
        }
    }
}
