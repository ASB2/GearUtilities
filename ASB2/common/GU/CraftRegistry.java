package GU;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import GU.info.Variables;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftRegistry {

    public static void init() {

        OreDictionary.registerOre(Variables.CRYSTALS_ALL, ItemRegistry.ItemAirCrystalShard);
        OreDictionary.registerOre(Variables.CRYSTALS_ALL, ItemRegistry.ItemEarthCrystalShard);
        OreDictionary.registerOre(Variables.CRYSTALS_ALL, ItemRegistry.ItemFireCrystalShard);
        OreDictionary.registerOre(Variables.CRYSTALS_ALL, ItemRegistry.ItemWaterCrystalShard);
        OreDictionary.registerOre(Variables.CRYSTALS_ALL, ItemRegistry.ItemEnergyCrystalShard);
                
        GameRegistry.addRecipe(new ItemStack(Item.bucketLava), new Object[] {
            "FFF", 
            "FSF", 
            "FBF", 
            'F', ItemRegistry.ItemFireCrystalShard,
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

        GameRegistry.addRecipe(new ItemStack(ItemRegistry.ItemPhantomPlacer, 1), new Object[] {
            "QIQ", 
            " S ", 
            " S ",
            'S', Item.stick,
            'Q', Block.blockNetherQuartz,
            'I', Item.ingotIron });
        
        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockLamp, 8), new Object[] {
            "ITI", 
            "TET", 
            "ERE",
            'E', BlockRegistry.BlockEnhancedBricks,
            'T', Block.torchWood,
            'R', Item.redstone,
            'I', Item.ingotIron });

        // CraftingManager.getInstance().getRecipeList().add(new
        // ShapedOreRecipe(new ItemStack(file.bioMass), new Object[]{"XXX",
        // "XXX", "XXX", Character.valueOf('X'), "bioMass"}));

        GameRegistry.addShapelessRecipe(new ItemStack(Item.seeds), new ItemStack(Item.wheat));
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemTeleporter), new ItemStack(ItemRegistry.ItemTeleporter));
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemLinker), new ItemStack(ItemRegistry.ItemLinker));
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemGarnet, 9), new ItemStack(BlockRegistry.BlockGarnetBlock));
    }

    // public static void registrySmelting() {
    //
    // GameRegistry.addSmelting(Item.axeWood.itemID, new ItemStack(
    // ItemRegistry.ItemCharcoalAxe), 1.F);
    //
    // GameRegistry.addSmelting(Item.pickaxeWood.itemID, new ItemStack(
    // ItemRegistry.ItemCharcoalPickaxe), 1.F);
    //
    // GameRegistry.addSmelting(Item.shovelWood.itemID, new ItemStack(
    // ItemRegistry.ItemCharcoalShovel), 1.F);
    //
    // GameRegistry.addSmelting(Item.swordWood.itemID, new ItemStack(
    // ItemRegistry.ItemCharcoalSword), 1.F);
    // }
}
