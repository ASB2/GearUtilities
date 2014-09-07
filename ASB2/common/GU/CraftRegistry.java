package GU;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import GU.api.color.VanillaColor;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftRegistry {
    
    public static void init() {
        
        OreDictionary.registerOre(GU.info.Reference.STRUCTURE_CUBE_OREDIC, new ItemStack(BlockRegistry.METADATA_ORE, 1, 0));
        OreDictionary.registerOre("dyeBlack", Items.bone);
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 0), new Object[] { "BBB", "BEB", "BBB",
        
        'B', new ItemStack(Blocks.stone_slab, 1, 3), 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 1), new Object[] { "BBB", "BEB", "BBB",
        
        'B', Blocks.cobblestone, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 2), new Object[] { "BBB", "BEB", "BBB",
        
        'B', Blocks.stone, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 3), new Object[] { "BBB", "BEB", "BBB",
        
        'B', new ItemStack(Blocks.stone_slab, 1, 0), 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 4), new Object[] { "BBB", "BEB", "BBB",
        
        'B', new ItemStack(Blocks.stonebrick, 1, 3), 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 5), new Object[] { "BBB", "BEB", "BBB",
        
        'B', new ItemStack(Blocks.stone_slab, 1, 5), 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 6), new Object[] { "BBB", "BEB", "BBB",
        
        'B', new ItemStack(Blocks.stonebrick, 1, 0), 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 1, 0), new Object[] { "EIE", "IBI", "EIE",
        
        'B', Reference.STRUCTURE_CUBE_OREDIC, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD, 'I', Items.iron_ingot }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 4, 1), new Object[] { "ECE", "CSC", "ECE",
        
        'C', Blocks.chest, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD, 'S', new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 4, 2), new Object[] { "ECE", "CSC", "ECE",
        
        'C', Blocks.furnace, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD, 'S', new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 4, 3), new Object[] { "ECE", "CSC", "ECE",
        
        'C', Items.bucket, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD, 'S', new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ITEM_FLUID, 8, 0), new Object[] { " B ", "BEB", " B ",
        
        'B', Items.bucket, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 4, 0), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Blocks.chest, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 4, 1), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Items.bucket, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 4, 2), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', BlockRegistry.ELECTIS_POLYHEDRON, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 4, 4), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Items.redstone, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 4, 5), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Items.stick, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.ELECTIS_POLYHEDRON, 1, 0), new Object[] { "III", "IEI", "III",
        
        'I', Items.iron_ingot, 'E', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.METADATA_ITEM, 8, 5), new Object[] { "EEE", "ESE", "EEE",
        
        'E', Items.stick, 'S', ItemRegistry.ELECTIS_CRYSTAL_SHARD }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.ITEM_ELECTIS_POLYHEDRON, 8, 0), new Object[] { "III", "IEI", "III",
        
        'I', BlockRegistry.ELECTIS_POLYHEDRON, 'E', new ItemStack(BlockRegistry.MULTI_INTERFACE, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.FLUID_ELECTIS_POLYHEDRON, 8, 0), new Object[] { "III", "IEI", "III",
        
        'I', BlockRegistry.ELECTIS_POLYHEDRON, 'E', new ItemStack(BlockRegistry.MULTI_INTERFACE, 1, 1) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.CONDUIT, 4, 0), new Object[] { "SAS", "IEI", "SAS",
        
        'I', Items.iron_ingot, 'S', Reference.STRUCTURE_CUBE_OREDIC, 'A', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 5), 'E', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_DIRECTIONAL_CONDUIT, 4, 0), new Object[] { "CIC", "ISI", "CIC",
        
        'I', Items.iron_ingot, 'S', Reference.STRUCTURE_CUBE_OREDIC, 'C', BlockRegistry.CONDUIT }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.METADATA_ITEM, 1, 4), new Object[] { "ISI", "SES", "ISI",
        
        'I', Items.iron_ingot, 'S', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 5), 'E', Items.ender_pearl }));
        
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.METADATA_ITEM, 1, 4), new ItemStack(ItemRegistry.METADATA_ITEM, 1, 4));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.TELEPORT_ALTAR, new Object[] { "SES", "EPE", "SES",
        
        'E', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 0), 'S', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 5), 'P', Items.ender_pearl }));
    }
}
