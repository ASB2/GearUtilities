package GU.api.recipe;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public final class MultiPanelGrinderRecipe {
    
    private static MultiPanelGrinderRecipe instance = new MultiPanelGrinderRecipe();
    private ArrayList<SenderInfoHolder> recipeList = new ArrayList<SenderInfoHolder>();
    
    public static MultiPanelGrinderRecipe getInstance() {
        
        return instance;
    }
    
    public void addRecipe(Block blockID, int blockMeta, ItemStack[] results) {
        
        recipeList.add(new SenderInfoHolder(blockID, blockMeta, results));
    }
    
    public ItemStack[] getResultsForBlock(Block blockID, int blockMeta) {
        
        for (SenderInfoHolder recipe : recipeList) {
            
            if (recipe.getBlock() == blockID && (recipe.getBlockMeta() == blockMeta || recipe.getBlockMeta() == -1)) {
                
                return recipe.getResults();
            }
        }
        return null;
    }
    
    private class SenderInfoHolder {
        
        Block block;
        int blockMeta;
        ItemStack[] results;
        
        private SenderInfoHolder(Block block, int blockMeta, ItemStack[] results) {
            
            this.block = block;
            this.blockMeta = blockMeta;
            this.results = results;
        }
        
        public int getBlockMeta() {
            
            return blockMeta;
        }
        
        public Block getBlock() {
            return block;
        }
        
        public ItemStack[] getResults() {
            
            return results;
        }
    }
}
