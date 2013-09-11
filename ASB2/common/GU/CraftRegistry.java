package GU;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import GU.info.Variables;
import cpw.mods.fml.common.registry.GameRegistry;
import ASB2.utils.*;

public class CraftRegistry {

    public static void init() {        
                
        GameRegistry.addRecipe(new ItemStack(Item.bucketLava), new Object[] {
            "FFF", 
            "FSF", 
            "FBF", 
            'F', ItemRegistry.ItemCrystal.ItemFireCrystalShard,
            'S', Block.stone, 
            'B', Item.bucketEmpty });

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
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemTeleporter, 2), new Object[] {
            "ECE", 
            "CDC", 
            "ECE",
            
            'D', Item.diamond,
            'C', Variables.CRYSTALS_ALL,
            'E', Item.enderPearl}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 1), new Object[] {
            "CIC", 
            "IBI", 
            "CIC",
            
            'C', Variables.CRYSTALS_ALL,
            'I', Item.ingotIron,
            'B', BlockRegistry.BlockEnhancedBricks}));
        
        // CraftingManager.getInstance().getRecipeList().add(new
        // ShapedOreRecipe(new ItemStack(file.bioMass), new Object[]{"XXX",
        // "XXX", "XXX", Character.valueOf('X'), "bioMass"}));
        
        UtilRecipe.addStorageBlock9(new ItemStack(BlockRegistry.BlockGarnetBlock), new ItemStack(ItemRegistry.ItemGarnet));
        UtilRecipe.addSword(new ItemStack(ItemRegistry.ItemGarnet), new ItemStack(ItemRegistry.ItemGarnetSword));
        UtilRecipe.addPickaxe(new ItemStack(ItemRegistry.ItemGarnet), new ItemStack(ItemRegistry.ItemGarnetPickaxe));
        UtilRecipe.addShovel(new ItemStack(ItemRegistry.ItemGarnet), new ItemStack(ItemRegistry.ItemGarnetShovel));
        UtilRecipe.addAxe(new ItemStack(ItemRegistry.ItemGarnet), new ItemStack(ItemRegistry.ItemGarnetAxe));
        UtilRecipe.addHoe(new ItemStack(ItemRegistry.ItemGarnet), new ItemStack(ItemRegistry.ItemGarnetHoe));
        UtilRecipe.addScythe(new ItemStack(ItemRegistry.ItemGarnet), new ItemStack(ItemRegistry.ItemGarnetScythe));
        
        GameRegistry.addShapelessRecipe(new ItemStack(Item.seeds), Item.wheat);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemTeleporter), ItemRegistry.ItemTeleporter);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemLinker), ItemRegistry.ItemLinker);
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockEtherealConnectedGlass), BlockRegistry.BlockConnectedGlass , BlockRegistry.BlockFalseBlock);
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockConnectedGlass), BlockRegistry.BlockEtherealConnectedGlass);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.torchWood, 6), ItemRegistry.ItemCrystal.ItemFireCrystalShard, Item.stick));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.coal, 1, 1), ItemRegistry.ItemCrystal.ItemFireCrystalShard, "logWood"));
        
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 1), new ItemStack(BlockRegistry.BlockSender, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 2), new ItemStack(BlockRegistry.BlockSender, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 3), new ItemStack(BlockRegistry.BlockSender, 1, 2));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 4), new ItemStack(BlockRegistry.BlockSender, 1, 3));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 5), new ItemStack(BlockRegistry.BlockSender, 1, 4));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 6), new ItemStack(BlockRegistry.BlockSender, 1, 5));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 7), new ItemStack(BlockRegistry.BlockSender, 1, 6));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 0), new ItemStack(BlockRegistry.BlockSender, 1, 7));
        
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
