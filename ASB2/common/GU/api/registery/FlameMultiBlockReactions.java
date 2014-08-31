package GU.api.registery;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

public enum FlameMultiBlockReactions {
    
    INSTANCE;
    
    List<RecipeWrapper> recipes = new ArrayList<RecipeWrapper>();
    
    public List<RecipeWrapper> getRecipes() {
        
        return recipes;
    }
    
    public static class RecipeWrapper {
        
        ItemStack[] input;
        
        ItemStack[] output;
        
        float powerInputt;
        
        float powerOutput;
        
        public RecipeWrapper(ItemStack[] input, ItemStack[] output, float powerInputt, float powerOutput) {
            this.input = input;
            this.output = output;
            this.powerInputt = powerInputt;
            this.powerOutput = powerOutput;
        }
        
        public ItemStack[] getInput() {
            return input;
        }
        
        public void setInput(ItemStack[] input) {
            this.input = input;
        }
        
        public ItemStack[] getOutput() {
            return output;
        }
        
        public void setOutput(ItemStack[] output) {
            this.output = output;
        }
        
        public float getPowerInputt() {
            return powerInputt;
        }
        
        public void setPowerInputt(float powerInputt) {
            this.powerInputt = powerInputt;
        }
        
        public float getPowerOutput() {
            return powerOutput;
        }
        
        public void setPowerOutput(float powerOutput) {
            this.powerOutput = powerOutput;
        }
    }
}
