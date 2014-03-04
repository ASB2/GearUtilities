package GU.api.recipe;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public final class MultiPanelGrinderRecipe {

    private static MultiPanelGrinderRecipe instance = new MultiPanelGrinderRecipe();
    private ArrayList<SenderInfoHolder> recipeList = new ArrayList<SenderInfoHolder>();

    public static MultiPanelGrinderRecipe getInstance() {

        return instance;
    }

    public void addRecipe(int blockID, int blockMeta, ItemStack[] results) {

        recipeList.add(new SenderInfoHolder(blockID, blockMeta, results));
    }

    public ItemStack[] getResultsForBlock(int blockID, int blockMeta) {

        for (SenderInfoHolder recipe : recipeList) {

            if (recipe.getBlockInfo()[0] == blockID && (recipe.getBlockInfo()[1] == blockMeta || recipe.getBlockInfo()[1] == -1)) {

                return recipe.getResults();
            }
        }
        return null;
    }

    private class SenderInfoHolder {

        int blockId;
        int blockMeta;
        ItemStack[] results;

        SenderInfoHolder(int blockID, int blockMeta, ItemStack[] results) {

            this.blockId = blockID;
            this.blockMeta = blockMeta;
            this.results = results;
        }

        public int[] getBlockInfo() {

            return new int[] { blockId, blockMeta };
        }

        public ItemStack[] getResults() {

            return results;
        }
    }
}
