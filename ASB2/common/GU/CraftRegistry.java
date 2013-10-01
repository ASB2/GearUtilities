package GU;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ASB2.utils.UtilRecipe;
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
        
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockCanvas, 8), new Object[] {
            "IWI", 
            "WEW", 
            "IWI",
            'E', BlockRegistry.BlockEnhancedBricks,
            'W', Block.cloth,
            'I', Item.ingotIron});

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockConnectableTank), new Object[] {
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


        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemPhantomPlacer), new Object[] {
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
        
        // CraftingManager.getInstance().getRecipeList().add(new
        // ShapedOreRecipe(new ItemStack(file.bioMass), new Object[]{"XXX",
        // "XXX", "XXX", Character.valueOf('X'), "bioMass"}));
        
        UtilRecipe.addStorageBlock9(BlockRegistry.BlockMetadataOre.BlockGarnetBlock, ItemRegistry.ItemCrystal.ItemGarnet);
        UtilRecipe.addSword(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetSword));
        UtilRecipe.addPickaxe(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetPickaxe));
        UtilRecipe.addShovel(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetShovel));
        UtilRecipe.addAxe(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetAxe));
        UtilRecipe.addHoe(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetHoe));
        UtilRecipe.addScythe(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetScythe));
        
        GameRegistry.addShapelessRecipe(new ItemStack(Item.seeds), Item.wheat);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemTeleporter), ItemRegistry.ItemTeleporter);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemLinker), ItemRegistry.ItemLinker);
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockEtherealConnectedGlass), BlockRegistry.BlockConnectedGlass , BlockRegistry.BlockFalseBlock);
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockConnectedGlass), BlockRegistry.BlockEtherealConnectedGlass);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.torchWood, 6), ItemRegistry.ItemCrystal.ItemFireCrystalShard, Item.stick));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.coal, 1, 1), ItemRegistry.ItemCrystal.ItemFireCrystalShard, "logWood"));
        
         GameRegistry.addSmelting(Item.axeWood.itemID, new ItemStack(
         ItemRegistry.ItemCharcoalAxe), 1.F);
        
         GameRegistry.addSmelting(Item.pickaxeWood.itemID, new ItemStack(
         ItemRegistry.ItemCharcoalPickaxe), 1.F);
        
         GameRegistry.addSmelting(Item.shovelWood.itemID, new ItemStack(
         ItemRegistry.ItemCharcoalShovel), 1.F);
        
         GameRegistry.addSmelting(Item.swordWood.itemID, new ItemStack(
         ItemRegistry.ItemCharcoalSword), 1.F);
         
         initTempRecipes();
    }
    
    public static void initTempRecipes() {     

        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemTeleporter, 2), new Object[] {
            "ECE", 
            "CDC", 
            "ECE",
            
            'D', Item.diamond,
            'C', Variables.CRYSTALS_ALL,
            'E', Item.enderPearl}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockSender, 1), new Object[] {
            "CIC", 
            "IBI", 
            "CIC",
            
            'C', Variables.CRYSTALS_ALL,
            'I', Item.ingotIron,
            'B', BlockRegistry.BlockEnhancedBricks}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemBloodStone), new Object[] {
            "BSB", 
            "SES", 
            "BSB",
            
            'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard,
            'S', BlockRegistry.BlockFalseBlock,
            'E', ItemRegistry.ItemCrystal.ItemEnergyCrystalShard}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicSword), new Object[] {
            "ESE", 
            "EBE", 
            "EAE",
            
            'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard,
            'S',ItemRegistry.ItemBloodStone,
            'E', BlockRegistry.BlockFalseBlock,
            'A',ItemRegistry.ItemAdvancedStick}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicPickaxe), new Object[] {
            "BBB", 
            "ESE", 
            "EAE",
            
            'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard,
            'S',ItemRegistry.ItemBloodStone,
            'E', BlockRegistry.BlockFalseBlock,
            'A',ItemRegistry.ItemAdvancedStick}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicShovel), new Object[] {
            "EBE", 
            "ESE", 
            "EAE",
            
            'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard,
            'S',ItemRegistry.ItemBloodStone,
            'E', BlockRegistry.BlockFalseBlock,
            'A',ItemRegistry.ItemAdvancedStick}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicAxe), new Object[] {
            "BBE", 
            "BSE", 
            "EAE",
            
            'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard,
            'S',ItemRegistry.ItemBloodStone,
            'E', BlockRegistry.BlockFalseBlock,
            'A',ItemRegistry.ItemAdvancedStick}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicAxe), new Object[] {
            "EBB", 
            "ESB", 
            "EAE",
            
            'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard,
            'S',ItemRegistry.ItemBloodStone,
            'E', BlockRegistry.BlockFalseBlock,
            'A',ItemRegistry.ItemAdvancedStick}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicHoe), new Object[] {
            "BBE", 
            "ESE", 
            "EAE",
            
            'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard,
            'S',ItemRegistry.ItemBloodStone,
            'E', BlockRegistry.BlockFalseBlock,
            'A',ItemRegistry.ItemAdvancedStick}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicHoe), new Object[] {
            "EBB", 
            "ESE", 
            "EAE",
            
            'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard,
            'S',ItemRegistry.ItemBloodStone,
            'E', BlockRegistry.BlockFalseBlock,
            'A',ItemRegistry.ItemAdvancedStick}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockSolarFocus), new Object[] {
            "AAA", 
            "EBE", 
            "ICI",
            
            'A', ItemRegistry.ItemCrystal.ItemAirCrystalShard,
            'S', ItemRegistry.ItemBloodStone,
            'E', BlockRegistry.BlockEnhancedBricks,
            'I', Item.ingotIron,
            'B', ItemRegistry.ItemBloodStone,
            'C', Block.chest}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockUniversalConduit), new Object[] {
            "IEI", 
            "IBI", 
            "ICI",
            
            'E', ItemRegistry.ItemCrystal.ItemEnergyCrystalShard,
            'I', Item.ingotIron,
            'B', Item.bucketEmpty,
            'C', Block.chest}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockConduitInterface), new Object[] {
            "IBI", 
            "CIE", 
            "IRI",
            
            'E', ItemRegistry.ItemCrystal.ItemEnergyCrystalShard,
            'I', Item.ingotIron,
            'R', Item.redstone,
            'B', Item.bucketEmpty,
            'C', Block.chest}));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemTradeStick), new Object[] {
            "EDE", 
            "DAD", 
            "EDE",
            
            'E', Item.emerald,
            'D', Item.diamond,
            'A', ItemRegistry.ItemAdvancedStick}));
        
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 1), new ItemStack(BlockRegistry.BlockSender, 1, 0));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 2), new ItemStack(BlockRegistry.BlockSender, 1, 1));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 3), new ItemStack(BlockRegistry.BlockSender, 1, 2));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 4), new ItemStack(BlockRegistry.BlockSender, 1, 3));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 5), new ItemStack(BlockRegistry.BlockSender, 1, 4));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 6), new ItemStack(BlockRegistry.BlockSender, 1, 5));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 7), new ItemStack(BlockRegistry.BlockSender, 1, 6));
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender, 1, 0), new ItemStack(BlockRegistry.BlockSender, 1, 7));
    
        initTempRecipesBlood();
    }
    
    public static void initTempRecipesBlood() {   
        
        GameRegistry.addShapelessRecipe(FluidContainerRegistry.fillFluidContainer(new FluidStack(GU.FluidRegistry.Blood, 1000), new ItemStack(ItemRegistry.ItemStorageCrystal)), new ItemStack(BlockRegistry.BlockSender, 1, 0));
    }
}
