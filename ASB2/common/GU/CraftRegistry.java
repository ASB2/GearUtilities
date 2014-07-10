package GU;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import GU.api.color.VanillaColor;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftRegistry {
    
    public static void init() {
        
        OreDictionary.registerOre(GU.info.Reference.STRUCTURE_CUBE_OREDIC, new ItemStack(BlockRegistry.METADATA_ORE, 1, 0));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 8, 0), new Object[] { "BBB", "BEB", "BBB",
        
        'B', Blocks.stonebrick, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPACIAL_PROVIDER, 1, 0), new Object[] { "IEI", "EBE", "IEI",
        
        'B', Reference.STRUCTURE_CUBE_OREDIC, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD, 'I', Items.iron_ingot }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPACIAL_PROVIDER, 1, 1), new Object[] { "ECE", "CSC", "ECE",
        
        'C', Blocks.chest, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD, 'S', new ItemStack(BlockRegistry.SPACIAL_PROVIDER, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPACIAL_PROVIDER, 1, 2), new Object[] { "ECE", "CSC", "ECE",
        
        'C', Blocks.furnace, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD, 'S', new ItemStack(BlockRegistry.SPACIAL_PROVIDER, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPACIAL_PROVIDER, 1, 3), new Object[] { "ECE", "CSC", "ECE",
        
        'C', Items.bucket, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD, 'S', new ItemStack(BlockRegistry.SPACIAL_PROVIDER, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ITEM_FLUID, 8, 0), new Object[] { " B ", "BEB", " B ",
        
        'B', Items.bucket, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 1, 0), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Blocks.chest, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 1, 1), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Items.bucket, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 1, 2), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', BlockRegistry.ELECTIS_POLYHEDRON, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 1, 4), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Items.redstone, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 1, 5), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Items.stick, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.ELECTIS_POLYHEDRON, 1, 0), new Object[] { "III", "IEI", "III",
        
        'I', Items.iron_ingot, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.METADATA_ITEM, 2, 5), new Object[] { "EEE", "ESE", "EEE",
        
        'S', Items.stick, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        for (VanillaColor color : VanillaColor.values()) {
            
            if (color != VanillaColor.NONE) {
                
                GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(color.ordinal()), 1), Reference.STRUCTURE_CUBE_OREDIC, color.getVinillaDye()));
            }
        }
    }
}
