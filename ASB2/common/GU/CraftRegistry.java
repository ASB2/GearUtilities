package GU;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import GU.api.color.VanillaColor;
import GU.api.recipe.GrinderRecipeManager;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftRegistry {
    
    public static void init() {
        
        initOreDictionary();
        initCrafting();
        initGrinder();
    }
    
    private static void initOreDictionary() {
        
        OreDictionary.registerOre(GU.info.Reference.STRUCTURE_CUBE_OREDIC, new ItemStack(BlockRegistry.METADATA_ORE, 1, 0));
        OreDictionary.registerOre("dyeBlack", Items.bone);
        OreDictionary.registerOre("oreElectisCrystal", new ItemStack(BlockRegistry.METADATA_ORE, 1, 0));
        OreDictionary.registerOre("blockElectisStone", new ItemStack(BlockRegistry.METADATA_ORE, 1, 1));
        OreDictionary.registerOre("itemBucket", new ItemStack(ItemRegistry.ITEM_FLUID, 1, 0));
        OreDictionary.registerOre("blockSand", new ItemStack(Blocks.sand, 1, 0));
        OreDictionary.registerOre("gemElectisShard", ItemRegistry.ELECTIS_CRYSTAL_SHARD.copy());
        OreDictionary.registerOre("gemElectisGarnet", ItemRegistry.GARNET.copy());
        OreDictionary.registerOre("itemElectisStick", new ItemStack(ItemRegistry.METADATA_ITEM, 1, 5));
        OreDictionary.registerOre("blockSnow", new ItemStack(Blocks.snow, 1, 0));
    }
    
    private static void initGrinder() {
        
        GrinderRecipeManager.getInstance().addRecipe("oreIron", "dustIron", "dustIron");
        GrinderRecipeManager.getInstance().addRecipe("oreGold", "dustGold", "dustGold");
        GrinderRecipeManager.getInstance().addRecipe("oreCoal", "dustCoal", "dustCoal");
        GrinderRecipeManager.getInstance().addRecipe("oreQuartz", "gemQuartz", "gemQuartz");        
        GrinderRecipeManager.getInstance().addRecipe("oreCopper", "dustCopper", "dustCopper");
        GrinderRecipeManager.getInstance().addRecipe("oreTin", "dustTin", "dustTin");
        GrinderRecipeManager.getInstance().addRecipe("oreLead", "dustLead", "dustLead");
        GrinderRecipeManager.getInstance().addRecipe("oreSilver", "dustSilver", "dustSilver");
        GrinderRecipeManager.getInstance().addRecipe("oreCobalt", "dustCobalt", "dustCobalt");
        GrinderRecipeManager.getInstance().addRecipe("oreUranium", "dustUranium", "dustUranium");
//        GrinderRecipeManager.getInstance().addRecipe("oreArdite", "dustArdite", "dustUranium");
        
        GrinderRecipeManager.getInstance().addRecipe("ingotIron", "dustIron");
        GrinderRecipeManager.getInstance().addRecipe("ingotGold", "dustGold");
        GrinderRecipeManager.getInstance().addRecipe("ingotCoal", "dustCoal");        
        GrinderRecipeManager.getInstance().addRecipe("ingotCopper", "dustCopper");
        GrinderRecipeManager.getInstance().addRecipe("ingotTin", "dustTin");
        GrinderRecipeManager.getInstance().addRecipe("ingotLead", "dustLead");
        GrinderRecipeManager.getInstance().addRecipe("ingotSilver", "dustSilver");
        GrinderRecipeManager.getInstance().addRecipe("ingotCobalt", "dustCobalt");
        GrinderRecipeManager.getInstance().addRecipe("ingotUranium", "ingotUranium");
        
        GrinderRecipeManager.getInstance().addRecipe("stone", "cobblestone");
        GrinderRecipeManager.getInstance().addRecipe("cobblestone", "blockSand");
        GrinderRecipeManager.getInstance().addRecipe("glass", "blockSand");
        GrinderRecipeManager.getInstance().addRecipe("oreRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone");
        
        GrinderRecipeManager.getInstance().addRecipe("blockSnow", "itemSnow", "itemSnow", "itemSnow", "itemSnow");
    }
    
    private static void initCrafting() {
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 0), new Object[] { "BBB", "BEB", "BBB",
        
        'B', new ItemStack(Blocks.stone_slab, 1, 3), 'E', "gemElectisShard" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 1), new Object[] { "BBB", "BEB", "BBB",
        
        'B', Blocks.cobblestone, 'E', "gemElectisShard" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 2), new Object[] { "BBB", "BEB", "BBB",
        
        'B', Blocks.stone, 'E', "gemElectisShard" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 3), new Object[] { "BBB", "BEB", "BBB",
        
        'B', new ItemStack(Blocks.stone_slab, 1, 0), 'E', "gemElectisShard" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 4), new Object[] { "BBB", "BEB", "BBB",
        
        'B', new ItemStack(Blocks.stonebrick, 1, 3), 'E', "gemElectisShard" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 5), new Object[] { "BBB", "BEB", "BBB",
        
        'B', new ItemStack(Blocks.stone_slab, 1, 5), 'E', "gemElectisShard" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.STRUCTURE_CUBES.get(VanillaColor.WHITE.ordinal()), 16, 6), new Object[] { "BBB", "BEB", "BBB",
        
        'B', new ItemStack(Blocks.stonebrick, 1, 0), 'E', "gemElectisShard" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 1, 0), new Object[] { "EIE", "IBI", "EIE",
        
        'B', Reference.STRUCTURE_CUBE_OREDIC, 'E', "gemElectisShard", 'I', Items.iron_ingot }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 4, 1), new Object[] { "ECE", "CSC", "ECE",
        
        'C', Blocks.chest, 'E', "gemElectisShard", 'S', new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 4, 2), new Object[] { "ECE", "CSC", "ECE",
        
        'C', Blocks.furnace, 'E', "gemElectisGarnet", 'S', new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 4, 3), new Object[] { "ECE", "CSC", "ECE",
        
        'C', "itemBucket", 'E', "gemElectisShard", 'S', new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 4, 5), new Object[] { "ECE", "CSC", "ECE",
        
        'E', "gemElectisGarnet", 'C', new ItemStack(BlockRegistry.ELECTIS_POLYHEDRON), 'S', new ItemStack(BlockRegistry.SPATIAL_PROVIDER, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ITEM_FLUID, 8, 0), new Object[] { " B ", "BEB", " B ",
        
        'B', "itemBucket", 'E', "gemElectisShard" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 4, 0), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Blocks.chest, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 4, 1), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', "itemBucket", 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 4, 2), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 1), 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 4, 4), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Items.redstone, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_INTERFACE, 4, 5), new Object[] { "ICI", "CSC", "ICI",
        
        'I', Items.iron_ingot, 'C', Items.stick, 'S', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.ELECTIS_POLYHEDRON, 4, 0), new Object[] { "III", "IEI", "III",
        
        'I', Items.iron_ingot, 'E', "gemElectisShard" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.METADATA_ITEM, 8, 5), new Object[] { "EEE", "ESE", "EEE",
        
        'E', Items.stick, 'S', "gemElectisShard" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.ITEM_ELECTIS_POLYHEDRON, 8, 0), new Object[] { "III", "IEI", "III",
        
        'I', BlockRegistry.ELECTIS_POLYHEDRON, 'E', new ItemStack(BlockRegistry.MULTI_INTERFACE, 1, 0) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.FLUID_ELECTIS_POLYHEDRON, 8, 0), new Object[] { "III", "IEI", "III",
        
        'I', BlockRegistry.ELECTIS_POLYHEDRON, 'E', new ItemStack(BlockRegistry.MULTI_INTERFACE, 1, 1) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.CONDUIT, 4, 0), new Object[] { "SAS", "IGI", "SAS",
        
        'I', Items.iron_ingot, 'S', Reference.STRUCTURE_CUBE_OREDIC, 'A', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 5), 'G', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 1) }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.MULTI_DIRECTIONAL_CONDUIT, 4, 0), new Object[] { "CIC", "ISI", "CIC",
        
        'I', Items.iron_ingot, 'S', Reference.STRUCTURE_CUBE_OREDIC, 'C', BlockRegistry.CONDUIT }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.METADATA_ITEM, 1, 4), new Object[] { "ISI", "SES", "ISI",
        
        'I', Items.iron_ingot, 'S', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 5), 'E', Items.ender_pearl }));
        
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.METADATA_ITEM, 1, 4), new ItemStack(ItemRegistry.METADATA_ITEM, 1, 4));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.TELEPORT_ALTAR, new Object[] { "SES", "EPE", "SES",
        
        'E', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 0), 'S', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 5), 'P', Items.ender_pearl }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.ELECTIS_ENERGY_CUBE, new Object[] { "GEG", "EPE", "GEG",
        
        'E', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 0), 'G', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 1), 'P', BlockRegistry.ELECTIS_POLYHEDRON }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.ELECTIS_DRILL, new Object[] { "PEP", "ECE", "PEP",
        
        'E', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 0), 'C', BlockRegistry.ELECTIS_ENERGY_CUBE, 'P', BlockRegistry.ELECTIS_POLYHEDRON }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.PHOTONIC_CONVERTER, new Object[] { "S S", "SGS", "CEC",
        
        'E', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 0), 'S', new ItemStack(BlockRegistry.METADATA_ORE, 1, 0), 'G', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 1), 'C', Reference.STRUCTURE_CUBE_OREDIC }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.METADATA_ITEM, 6, 1), new Object[] { "EEE", "YDY", "EEE",
        
        'E', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 0), 'D', "gemDiamond", 'Y', "dyeYellow" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.METADATA_ITEM, 6, 0), new Object[] { "EEE", "YDY", "EEE",
        
        'E', new ItemStack(ItemRegistry.METADATA_ITEM, 1, 1), 'D', "gemDiamond", 'Y', "dyeWhite" }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.METADATA_ITEM, 1, 3), new Object[] { " G ", " S ", " E ",
        
        'E', "gemElectisShard", 'S', "itemElectisStick", 'G', "gemElectisGarnet" }));
    }
}
