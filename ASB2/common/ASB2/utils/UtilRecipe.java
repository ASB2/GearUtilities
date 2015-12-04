package ASB2.utils;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class UtilRecipe {
    
    public static void addStorageBlock9(ItemStack block, ItemStack item) {
        
        block.stackSize = 1;
        item.stackSize = 1;
        
        GameRegistry.addRecipe(block, new Object[] { "III", "III", "III", 'I', item });
        item.stackSize = 9;
        GameRegistry.addShapelessRecipe(item, block);
    }
    
    public static void addStorageBlock4(ItemStack block, ItemStack item) {
        
        block.stackSize = 1;
        item.stackSize = 1;
        
        GameRegistry.addRecipe(block, new Object[] { "II ", "II ", "   ", 'I', item });
        item.stackSize = 4;
        GameRegistry.addShapelessRecipe(item, block);
    }
    
    public static void addSword(ItemStack blade, ItemStack sword) {
        
        GameRegistry.addRecipe(sword, new Object[] { " B ", " B ", " S ", 'B', blade, 'S', Items.stick });
    }
    
    public static void addPickaxe(ItemStack blade, ItemStack pickaxe) {
        
        GameRegistry.addRecipe(pickaxe, new Object[] { "BBB", " S ", " S ", 'B', blade, 'S', Items.stick });
    }
    
    public static void addShovel(ItemStack blade, ItemStack shovel) {
        
        GameRegistry.addRecipe(shovel, new Object[] { " B ", " S ", " S ", 'B', blade, 'S', Items.stick });
    }
    
    public static void addAxe(ItemStack blade, ItemStack hoe) {
        
        GameRegistry.addRecipe(hoe, new Object[] { " BB", " SB", " S ", 'B', blade, 'S', Items.stick });
        
        GameRegistry.addRecipe(hoe, new Object[] { "BB ", "BS ", " S ", 'B', blade, 'S', Items.stick });
    }
    
    public static void addHoe(ItemStack blade, ItemStack hoe) {
        
        GameRegistry.addRecipe(hoe, new Object[] { " BB", " S ", " S ", 'B', blade, 'S', Items.stick });
        
        GameRegistry.addRecipe(hoe, new Object[] { "BB ", " S ", " S ", 'B', blade, 'S', Items.stick });
    }
    
    public static void addScythe(ItemStack blade, ItemStack scythe) {
        
        GameRegistry.addRecipe(scythe, new Object[] { " B ", "  B", "SB ", 'B', blade, 'S', Items.stick });
    }
}
