package GU.api.recipe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class GrinderRecipeManager {
    
    Map<String, String[]> registeredRecipies = new HashMap<String, String[]>();
    
    private static GrinderRecipeManager instance = new GrinderRecipeManager();
    
    public GrinderRecipeManager() {
        // TODO Auto-generated constructor stub
    }
    
    public void addRecipe(String input, String... output) {
        
        registeredRecipies.put(input, output);
    }
    
    public void addRecipe(String input, int multiplyOutput, String... output) {
        
        String[] changed = new String[multiplyOutput * output.length];
        
        for (int index = 0; index < multiplyOutput; index++) {
            
            for (int position = 0; position < output.length; position++) {
                
                changed[(index * output.length) + position] = output[position];
            }
        }
        registeredRecipies.put(input, changed);
    }
    
    public void addRecipeOreDustIngot(String input) {
        
        registeredRecipies.put("ore" + input, new String[] { "dust" + input, "dust" + input });
        registeredRecipies.put("ingot" + input, new String[] { "dust" + input });
    }
    
    public void addRecipeOreDustGem(String input) {
        
        registeredRecipies.put("ore" + input, new String[] { "dust" + input, "dust" + input });
        registeredRecipies.put("gem" + input, new String[] { "dust" + input });
    }
    
    public String[] removeRecipe(String input) {
        
        return registeredRecipies.remove(input);
    }
    
    public String[] getOutput(String input) {
        
        return registeredRecipies.get(input);
    }
    
    public ItemStack[] getOutputItemStack(String input) {
        
        String[] output = this.getOutput(input);
        
        if (output != null) {
            
            ItemStack[] itemOutput = new ItemStack[output.length];
            
            for (int index = 0; index < output.length; index++) {
                
                String string = output[index];
                
                List<ItemStack> stackList = OreDictionary.getOres(string);
                
                if (stackList.size() > 0) {
                    
                    itemOutput[index] = stackList.get(0);
                }
            }
            return itemOutput.length != 0 ? itemOutput : null;
        }
        return null;
    }
    
    public boolean hasOutput(String input) {
        
        String[] output = registeredRecipies.get(input);
        
        if (output == null) {
            
            return false;
        }
        
        if (output.length <= 0) {
            
            return false;
        }
        
        ItemStack[] outputStack = this.getOutputItemStack(input);
        
        if (outputStack != null) {
            
            for (int index = 0; index < outputStack.length; index++) {
                
                if (outputStack[index] == null) {
                    
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static String getOreDictionaryName(ItemStack stack) {
        
        int[] oreNumber = OreDictionary.getOreIDs(stack);
        
        if (oreNumber.length > 0) {
            
            return OreDictionary.getOreName(oreNumber[0]);
        }
        return "unknown";
    }
    
    public static GrinderRecipeManager getInstance() {
        
        return instance;
    }
}
