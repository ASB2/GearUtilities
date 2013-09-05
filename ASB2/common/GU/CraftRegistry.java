package GU;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import GU.info.Variables;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftRegistry {

    public static void init() {        
                
        GameRegistry.addRecipe(new ItemStack(Item.bucketLava), new Object[] {
            "FFF", 
            "FSF", 
            "FBF", 
            'F', ItemRegistry.ItemCrystal.ItemFireCrystalShard,
            'S', Block.stone, 
            'B', Item.bucketEmpty });

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockGarnetBlock), new Object[] { 
            "DDD", 
            "DDD", 
            "DDD", 
            'D', ItemRegistry.ItemGarnet });

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockSpeedyRoad, 8), new Object[] { 
            "MMM", 
            "QIQ", 
            "QQQ", 
            'M', Block.stone, 
            'Q', Block.blockNetherQuartz,
            'I', Item.ingotIron });
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (BlockRegistry.BlockEnhancedBricks, 4), new Object[] {
            "CBC", 
            "BRB", 
            "CBC",
            'C', Variables.CRYSTALS_ALL,
            'B', Block.stoneBrick,
            'R', Item.ingotIron}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (BlockRegistry.BlockEnhancedBricks, 8), new Object[] {
            "CBC", 
            "BRB", 
            "CBC",
            'C', Variables.CRYSTALS_ALL,
            'B', Block.stoneBrick,
            'R', Item.ingotGold}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack (BlockRegistry.BlockEnhancedBricks, 16), new Object[] {
            "CBC", 
            "BRB", 
            "CBC",
            'C', Variables.CRYSTALS_ALL,
            'B', Block.stoneBrick,
            'R', Item.diamond}));
        
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockLamp, 8), new Object[] {
            "ITI", 
            "TET", 
            "IRI",
            'E', BlockRegistry.BlockEnhancedBricks,
            'T', Block.torchWood,
            'R', Item.redstone,
            'I', Item.ingotIron});

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockConnectableTank, 1), new Object[] {
            "QGQ", 
            "GEG", 
            "QGQ",
            'Q', Item.netherQuartz,
            'G', Block.glass,
            'E', BlockRegistry.BlockEnhancedBricks});
        
        
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockConnectedGlass, 8), new Object[] {
            "EQE", 
            "IGI", 
            "EQE",
            'Q', Item.netherQuartz,
            'G', Block.glass,
            'E', BlockRegistry.BlockEnhancedBricks,
            'I', Item.ingotIron});
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockCreationTable), new Object[] {
            "ECE", 
            "GGG", 
            "ECE",
            
            'E', BlockRegistry.BlockEnhancedBricks,
            'C', Block.workbench,
            'Q', Item.netherQuartz,
            'I', Item.ingotIron,
            'G', BlockRegistry.BlockFalseBlock}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemAdvancedStick), new Object[] {
            "IQI", 
            "CSC", 
            "IQI",
            
            'S', Item.stick,
            'Q', Item.netherQuartz,
            'I', Item.ingotIron,
            'C', Variables.CRYSTALS_ALL}));


        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemPhantomPlacer, 1), new Object[] {
            "QIQ", 
            "CAC", 
            "CAC",
            'A', ItemRegistry.ItemAdvancedStick,
            'Q', Block.blockNetherQuartz,
            'I', Item.ingotIron,
            'C', Variables.CRYSTALS_ALL}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemLifeStealingBludgeoningStick), new Object[] {
            "WWW", 
            "ABA", 
            "ASA",
            
            'W', Item.swordIron,
            'C', Block.workbench,
            'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard,
            'A', ItemRegistry.ItemAdvancedStick,
            'S', Item.stick}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemTeleporter, 4), new Object[] {
            "ECE", 
            "CDC", 
            "ECE",
            
            'D', Item.diamond,
            'C', Variables.CRYSTALS_ALL,
            'E', Item.enderPearl}));
        
        // CraftingManager.getInstance().getRecipeList().add(new
        // ShapedOreRecipe(new ItemStack(file.bioMass), new Object[]{"XXX",
        // "XXX", "XXX", Character.valueOf('X'), "bioMass"}));

        GameRegistry.addShapelessRecipe(new ItemStack(Item.seeds), Item.wheat);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemTeleporter), ItemRegistry.ItemTeleporter);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemLinker), ItemRegistry.ItemLinker);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemGarnet, 9), BlockRegistry.BlockGarnetBlock);
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockEtherealConnectedGlass), BlockRegistry.BlockConnectedGlass , BlockRegistry.BlockFalseBlock);
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockConnectedGlass), BlockRegistry.BlockEtherealConnectedGlass);
    
        
         GameRegistry.addSmelting(Item.axeWood.itemID, new ItemStack(
         ItemRegistry.ItemCharcoalAxe), 1.F);
        
         GameRegistry.addSmelting(Item.pickaxeWood.itemID, new ItemStack(
         ItemRegistry.ItemCharcoalPickaxe), 1.F);
        
         GameRegistry.addSmelting(Item.shovelWood.itemID, new ItemStack(
         ItemRegistry.ItemCharcoalShovel), 1.F);
        
         GameRegistry.addSmelting(Item.swordWood.itemID, new ItemStack(
         ItemRegistry.ItemCharcoalSword), 1.F);
    }
}
