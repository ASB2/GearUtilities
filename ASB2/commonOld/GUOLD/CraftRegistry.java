package GUOLD;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilRecipe;
import GUOLD.api.color.VanillaColor;
import GUOLD.api.recipe.MultiPanelGrinderRecipe;
import GUOLD.blocks.containers.BlockMultiPanel.TileMultiPanel;
import GUOLD.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftRegistry {
    
    public static void init() {
        
        GameRegistry.addRecipe(new ItemStack(Items.lava_bucket), new Object[] { "FFF", "FSF", "FBF", 'F', ItemRegistry.ItemCrystal.ItemFireCrystalShard, 'S', Blocks.stone, 'B', Items.bucket });
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockEnhancedBricks, 4), new Object[] { "CBC", "BRB", "CBC", 'C', Reference.CRYSTALS_ALL, 'B', Blocks.stonebrick, 'R', Items.iron_ingot }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockEnhancedBricks, 8), new Object[] { "CBC", "BRB", "CBC", 'C', Reference.CRYSTALS_ALL, 'B', Blocks.stonebrick, 'R', Items.gold_ingot }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockEnhancedBricks, 16), new Object[] { "CBC", "BRB", "CBC", 'C', Reference.CRYSTALS_ALL, 'B', Blocks.stonebrick, 'R', Items.diamond }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemAdvancedStick), new Object[] { "IQI", "CSC", "IQI",
        
        'S', Items.stick, 'Q', Items.quartz, 'I', Items.iron_ingot, 'C', Reference.CRYSTALS_ALL }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemPhantomPlacer), new Object[] { "QIQ", "CAC", "CAC", 'A', ItemRegistry.ItemAdvancedStick, 'Q', Blocks.quartz_block, 'I', Items.iron_ingot, 'C', Reference.CRYSTALS_ALL }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemLifeStealingBludgeoningStick), new Object[] { "WWW", "ABA", "ASA",
        
        'W', Items.iron_sword, 'C', Blocks.crafting_table, 'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'A', ItemRegistry.ItemAdvancedStick, 'S', Items.stick }));
        
        // GameRegistry.addRecipe(new ShapedOreRecipe(new
        // ItemStack(BlockRegistry.BlockStructureCube, 1, 0), new Object[] { "",
        // "ABA", "ASA",
        //
        // 'W', Items.swordIron, 'C', Blocks.workbench, 'B',
        // ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'A',
        // ItemRegistry.ItemAdvancedStick, 'S', Items.stick }));
        // CraftingManager.getInstance().getRecipeList().add(new
        // ShapedOreRecipe(new ItemStack(file.bioMass), new Object[]{"XXX",
        // "XXX", "XXX", Character.valueOf('X'), "bioMass"}));
        
        UtilRecipe.addStorageBlock9(BlockRegistry.BlockMetadataOre.BlockGarnetBlock, ItemRegistry.ItemCrystal.ItemGarnet);
        UtilRecipe.addSword(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetSword));
        UtilRecipe.addPickaxe(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetPickaxe));
        UtilRecipe.addShovel(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetShovel));
        UtilRecipe.addAxe(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetAxe));
        UtilRecipe.addHoe(ItemRegistry.ItemCrystal.ItemGarnet, new ItemStack(ItemRegistry.ItemGarnetHoe));
        
        GameRegistry.addShapelessRecipe(new ItemStack(Items.wheat_seeds), Items.wheat, Items.wheat);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemTeleporter), ItemRegistry.ItemTeleporter);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemLinker), ItemRegistry.ItemLinker);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Blocks.torch, 6), ItemRegistry.ItemCrystal.ItemFireCrystalShard, Items.stick));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Items.coal, 1, 1), ItemRegistry.ItemCrystal.ItemFireCrystalShard, "logWood"));
        
        GameRegistry.addSmelting(Items.wooden_axe, new ItemStack(ItemRegistry.ItemCharcoalAxe), 1.F);
        GameRegistry.addSmelting(Items.wooden_pickaxe, new ItemStack(ItemRegistry.ItemCharcoalPickaxe), 1.F);
        GameRegistry.addSmelting(Items.wooden_shovel, new ItemStack(ItemRegistry.ItemCharcoalShovel), 1.F);
        GameRegistry.addSmelting(Items.wooden_sword, new ItemStack(ItemRegistry.ItemCharcoalSword), 1.F);
        
        GameRegistry.addSmelting(Items.stone_hoe, new ItemStack(ItemRegistry.ItemSmoothStoneHoe), 1.F);
        GameRegistry.addSmelting(Items.stone_axe, new ItemStack(ItemRegistry.ItemSmoothStoneAxe), 1.F);
        GameRegistry.addSmelting(Items.stone_pickaxe, new ItemStack(ItemRegistry.ItemSmoothStonePickaxe), 1.F);
        GameRegistry.addSmelting(Items.stone_shovel, new ItemStack(ItemRegistry.ItemSmoothStoneShovel), 1.F);
        GameRegistry.addSmelting(Items.stone_sword, new ItemStack(ItemRegistry.ItemSmoothStoneSword), 1.F);
        
        UtilRecipe.addSword(new ItemStack(Blocks.stone), new ItemStack(ItemRegistry.ItemSmoothStoneSword));
        UtilRecipe.addPickaxe(new ItemStack(Blocks.stone), new ItemStack(ItemRegistry.ItemSmoothStonePickaxe));
        UtilRecipe.addShovel(new ItemStack(Blocks.stone), new ItemStack(ItemRegistry.ItemSmoothStoneShovel));
        UtilRecipe.addAxe(new ItemStack(Blocks.stone), new ItemStack(ItemRegistry.ItemSmoothStoneAxe));
        UtilRecipe.addHoe(new ItemStack(Blocks.stone), new ItemStack(ItemRegistry.ItemSmoothStoneHoe));
        
        initTempRecipes();
        initGrinderRecipies();
        
    }
    
    public static void initTempRecipes() {
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemBasicDestructionCatalyst, 1), new Object[] { "PPP", "CCC", "GEG",
        
        'P', Items.diamond_pickaxe, 'C', Reference.CRYSTALS_ALL, 'G', Items.gold_ingot, 'E', Items.emerald }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemTeleporter, 2), new Object[] { "ECE", "CDC", "ECE",
        
        'D', Items.diamond, 'C', Reference.CRYSTALS_ALL, 'E', Items.ender_pearl }));
        
        ItemStack base = new ItemStack(BlockRegistry.BlockMultiPanel);
        UtilItemStack.setNBTTagInt(base, "mode", 0);
        GameRegistry.addRecipe(new ShapedOreRecipe(base, new Object[] { "ICI", "QDQ", "EBE",
        
        'C', Reference.CRYSTALS_ALL, 'I', Items.iron_ingot, 'Q', Items.quartz, 'D', Items.diamond, 'E', BlockRegistry.BlockEnhancedBricks }));
        
        ItemStack itemMover = new ItemStack(BlockRegistry.BlockMultiPanel);
        UtilItemStack.setNBTTagInt(itemMover, "mode", TileMultiPanel.ITEM_MOVEMENT);
        GameRegistry.addRecipe(new ShapedOreRecipe(itemMover, new Object[] { "QCQ", "ESE", "CQC",
        
        'C', ItemRegistry.ItemCrystal.ItemAirCrystalShard, 'Q', Items.quartz, 'E', Items.ender_pearl, 'S', base }));
        
        ItemStack fluidMover = new ItemStack(BlockRegistry.BlockMultiPanel);
        UtilItemStack.setNBTTagInt(fluidMover, "mode", TileMultiPanel.FLUID_MOVEMENT);
        GameRegistry.addRecipe(new ShapedOreRecipe(fluidMover, new Object[] { "QCQ", "ESE", "CQC",
        
        'C', ItemRegistry.ItemCrystal.ItemWaterCrystalShard, 'Q', Items.quartz, 'E', Items.ender_pearl, 'S', base }));
        
        ItemStack grinder = new ItemStack(BlockRegistry.BlockMultiPanel);
        UtilItemStack.setNBTTagInt(grinder, "mode", TileMultiPanel.GRINDING);
        GameRegistry.addRecipe(new ShapedOreRecipe(grinder, new Object[] { "DID", "CSC", "QCQ",
        
        'C', ItemRegistry.ItemCrystal.ItemEarthCrystalShard, 'Q', Items.quartz, 'D', Items.diamond, 'S', base, 'I', Items.iron_ingot }));
        
        ItemStack blockPlacer = new ItemStack(BlockRegistry.BlockMultiPanel);
        UtilItemStack.setNBTTagInt(blockPlacer, "mode", TileMultiPanel.BLOCK_PLACE);
        GameRegistry.addRecipe(new ShapedOreRecipe(blockPlacer, new Object[] { "DQD", "CSC", "QIQ",
        
        'C', ItemRegistry.ItemCrystal.ItemEarthCrystalShard, 'Q', Items.quartz, 'D', Blocks.dispenser, 'S', base, 'I', Items.iron_ingot }));
        
        ItemStack blockBreaker = new ItemStack(BlockRegistry.BlockMultiPanel);
        UtilItemStack.setNBTTagInt(blockBreaker, "mode", TileMultiPanel.BLOCK_BREAK);
        GameRegistry.addRecipe(new ShapedOreRecipe(blockBreaker, new Object[] { "IPI", "CSC", "QIQ",
        
        'C', ItemRegistry.ItemCrystal.ItemGarnet, 'Q', Items.quartz, 'P', Items.diamond_pickaxe, 'S', base, 'I', Items.iron_ingot }));
        
        ItemStack smelter = new ItemStack(BlockRegistry.BlockMultiPanel);
        UtilItemStack.setNBTTagInt(smelter, "mode", TileMultiPanel.SMELTER);
        GameRegistry.addRecipe(new ShapedOreRecipe(smelter, new Object[] { "FCF", "QSQ", "CQC",
        
        'C', ItemRegistry.ItemCrystal.ItemFireCrystalShard, 'Q', Items.quartz, 'F', Blocks.furnace, 'S', base }));
        
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockMultiPanel), base);
        
        // Breaker
        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemStorageCrystal), new Object[] { " G ", "GCG", " G ",
        
        'G', Blocks.glass, 'C', Reference.CRYSTALS_ALL }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.BlockSpacialProvider.STANDARD_SPACIAL_PROVIDER, new Object[] { "ICI", "CEC", "ICI",
        
        'C', Reference.CRYSTALS_ALL, 'I', Items.iron_ingot, 'E', BlockRegistry.BlockEnhancedBricks }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.BlockSpacialProvider.FLUID_SPACIAL_PROVIDER, new Object[] { "ICI", "CEC", "ICI",
        
        'C', ItemRegistry.ItemCrystal.ItemWaterCrystalShard, 'I', Items.iron_ingot, 'E', BlockRegistry.BlockEnhancedBricks }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.BlockSpacialProvider.FURNACE_SPACIAL_PROVIDER, new Object[] { "ICI", "CEC", "ICI",
        
        'C', ItemRegistry.ItemCrystal.ItemFireCrystalShard, 'I', Items.iron_ingot, 'E', BlockRegistry.BlockEnhancedBricks }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.BlockSpacialProvider.CHEST_SPACIAL_PROVIDER, new Object[] { "ICI", "CEC", "ICI",
        
        'C', ItemRegistry.ItemCrystal.ItemEarthCrystalShard, 'I', Items.iron_ingot, 'E', BlockRegistry.BlockEnhancedBricks }));
        
        // Breaker
        ItemStack structureBrick0 = BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[0].copy();
        structureBrick0.stackSize = 16;
        
        GameRegistry.addRecipe(new ShapedOreRecipe(structureBrick0, new Object[] { "SSS", "SES", "SSS",
        
        'S', Blocks.stone, 'E', BlockRegistry.BlockEnhancedBricks }));
        
        ItemStack structureBrick1 = BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[1].copy();
        structureBrick1.stackSize = 16;
        
        GameRegistry.addRecipe(new ShapedOreRecipe(structureBrick1, new Object[] { "SSS", "SES", "SSS",
        
        'S', Blocks.stone, 'E', BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[0] }));
        
        ItemStack structureBrick2 = BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[2].copy();
        structureBrick2.stackSize = 16;
        
        GameRegistry.addRecipe(new ShapedOreRecipe(structureBrick2, new Object[] { "SSS", "SES", "SSS",
        
        'S', BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[0], 'E', Items.iron_ingot }));
        
        ItemStack structureBrick3 = BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[3].copy();
        structureBrick3.stackSize = 16;
        
        GameRegistry.addRecipe(new ShapedOreRecipe(structureBrick3, new Object[] { "SSS", "SES", "SSS",
        
        'S', BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[2], 'E', Items.iron_ingot }));
        
        ItemStack structureGlass4 = BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[4].copy();
        structureGlass4.stackSize = 16;
        
        GameRegistry.addRecipe(new ShapedOreRecipe(structureGlass4, new Object[] { "GSG", "GEG", "GSG",
        
        'S', Blocks.stone, 'E', BlockRegistry.BlockEnhancedBricks, 'G', Blocks.glass }));
        
        ItemStack structureBrick5 = BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[5].copy();
        structureBrick5.stackSize = 16;
        GameRegistry.addRecipe(new ShapedOreRecipe(structureBrick5, new Object[] { "SSS", "SES", "SSS",
        
        'S', Blocks.stonebrick, 'E', BlockRegistry.BlockEnhancedBricks }));
        
        GameRegistry.addShapelessRecipe(BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[6], BlockRegistry.BlockStructureCube.STRUCTURE_CUBES[5]);
        
        // Breaker
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.BlockMultiInterface.ITEM_INTERFACE, new Object[] { "ICI", "CEC", "ICI",
        
        'C', ItemRegistry.ItemCrystal.ItemEarthCrystalShard, 'I', Items.iron_ingot, 'E', BlockRegistry.BlockEnhancedBricks }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.BlockMultiInterface.FLUID_INTERFACE, new Object[] { "ICI", "CEC", "ICI",
        
        'C', ItemRegistry.ItemCrystal.ItemWaterCrystalShard, 'I', Items.iron_ingot, 'E', BlockRegistry.BlockEnhancedBricks }));
        
        GameRegistry.addRecipe(new ShapedOreRecipe(BlockRegistry.BlockMultiInterface.POWER_INTERFACE, new Object[] { "ICI", "CEC", "ICI",
        
        'C', ItemRegistry.ItemCrystal.ItemAirCrystalShard, 'I', Items.iron_ingot, 'E', BlockRegistry.BlockEnhancedBricks }));
        
        // Breaker
    }
    
    public static void initGrinderRecipies() {
        
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.stone, 0, new ItemStack[] { new ItemStack(Blocks.cobblestone) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.cobblestone, -1, new ItemStack[] { new ItemStack(Blocks.sand) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.grass, -1, new ItemStack[] { new ItemStack(Blocks.dirt) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.gravel, -1, new ItemStack[] { new ItemStack(Items.flint, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.wool, -1, new ItemStack[] { new ItemStack(Blocks.sponge) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.redstone_lamp, -1, new ItemStack[] { new ItemStack(Items.redstone, 4), new ItemStack(Items.glowstone_dust, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.redstone_lamp, -1, new ItemStack[] { new ItemStack(Items.redstone, 4), new ItemStack(Items.glowstone_dust, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.dropper, -1, new ItemStack[] { new ItemStack(Blocks.cobblestone, 7), new ItemStack(Items.redstone) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.beacon, -1, new ItemStack[] { new ItemStack(Blocks.sand, 5), new ItemStack(Blocks.obsidian), new ItemStack(Items.nether_star) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.anvil, -1, new ItemStack[] { new ItemStack(Items.iron_ingot, 32) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.enchanting_table, -1, new ItemStack[] { new ItemStack(Items.book), new ItemStack(Blocks.obsidian, 5), new ItemStack(Items.diamond, 2) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.torch, -1, new ItemStack[] { new ItemStack(Items.stick, 2) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.lever, -1, new ItemStack[] { new ItemStack(Blocks.cobblestone), new ItemStack(Items.stick) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.ladder, -1, new ItemStack[] { new ItemStack(Items.stick, 7) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.furnace, -1, new ItemStack[] { new ItemStack(Blocks.cobblestone, 8) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.furnace, -1, new ItemStack[] { new ItemStack(Blocks.cobblestone, 8) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.piston, -1, new ItemStack[] { new ItemStack(Blocks.piston), new ItemStack(Items.slime_ball) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.stone_button, -1, new ItemStack[] { new ItemStack(Blocks.stone) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.pumpkin, -1, new ItemStack[] { new ItemStack(Blocks.pumpkin), new ItemStack(Blocks.torch) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.clay, -1, new ItemStack[] { new ItemStack(Items.clay_ball, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.ender_chest, -1, new ItemStack[] { new ItemStack(Items.ender_pearl), new ItemStack(Items.blaze_powder), new ItemStack(Blocks.obsidian, 8) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.glowstone, -1, new ItemStack[] { new ItemStack(Items.glowstone_dust, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.flower_pot, -1, new ItemStack[] { new ItemStack(Items.brick, 3) });
        
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.snow, -1, new ItemStack[] { new ItemStack(Items.snowball, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.nether_brick, -1, new ItemStack[] { new ItemStack(Items.netherbrick, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.stonebrick, -1, new ItemStack[] { new ItemStack(Blocks.stone, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.melon_stem, -1, new ItemStack[] { new ItemStack(Items.melon, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.mycelium, -1, new ItemStack[] { new ItemStack(Blocks.dirt), new ItemStack(Blocks.brown_mushroom_block), new ItemStack(Blocks.red_mushroom_block) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.hay_block, -1, new ItemStack[] { new ItemStack(Items.wheat, 9) });
        
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.hardened_clay, -1, new ItemStack[] { new ItemStack(Items.clay_ball, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.stained_glass, -1, new ItemStack[] { new ItemStack(Items.clay_ball, 4) });
        
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.sandstone, 0, new ItemStack[] { new ItemStack(Blocks.sand, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.sandstone, 1, new ItemStack[] { new ItemStack(Blocks.sand, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.sandstone, 2, new ItemStack[] { new ItemStack(Blocks.sand, 4) });
        
//        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.wood, 0, new ItemStack[] { new ItemStack(Blocks.planks, 6, 0) });
//        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.wood, 1, new ItemStack[] { new ItemStack(Blocks.planks, 6, 1) });
//        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.wood, 2, new ItemStack[] { new ItemStack(Blocks.planks, 6, 2) });
//        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.wood, 3, new ItemStack[] { new ItemStack(Blocks.planks, 6, 3) });
        
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.coal_ore, -1, new ItemStack[] { new ItemStack(Items.coal, 6) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.quartz_ore, -1, new ItemStack[] { new ItemStack(Items.quartz, 6) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.redstone_ore, -1, new ItemStack[] { new ItemStack(Items.redstone, 6) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.diamond_ore, -1, new ItemStack[] { new ItemStack(Items.diamond, 3) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.emerald_ore, -1, new ItemStack[] { new ItemStack(Items.emerald, 3) });
        
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.coal_block, -1, new ItemStack[] { new ItemStack(Items.coal, 9) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.quartz_block, -1, new ItemStack[] { new ItemStack(Items.quartz, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.iron_block, -1, new ItemStack[] { new ItemStack(Items.iron_ingot, 9) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.gold_block, -1, new ItemStack[] { new ItemStack(Items.gold_ingot, 9) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.redstone_block, -1, new ItemStack[] { new ItemStack(Items.redstone, 9) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.diamond_block, -1, new ItemStack[] { new ItemStack(Items.diamond, 9) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.emerald_block, -1, new ItemStack[] { new ItemStack(Items.emerald, 9) });
        
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.web, 0, new ItemStack[] { new ItemStack(Items.string, 8) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.piston, 0, new ItemStack[] { new ItemStack(Items.slime_ball), new ItemStack(Blocks.piston) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.dispenser, -1, new ItemStack[] { new ItemStack(Blocks.cobblestone, 7), new ItemStack(Items.bow), new ItemStack(Items.redstone) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.tnt, -1, new ItemStack[] { new ItemStack(Items.gunpowder, 5), new ItemStack(Blocks.sand, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.brick_block, -1, new ItemStack[] { new ItemStack(Items.brick, 4) });
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.mossy_cobblestone, -1, new ItemStack[] { new ItemStack(Blocks.cobblestone), new ItemStack(Blocks.vine, 6) });
        
        ItemStack stack = VanillaColor.getVinillaDye(VanillaColor.BLUE);
        stack.stackSize = 8;
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.lapis_ore, -1, new ItemStack[] { stack });
        stack.stackSize = 9;
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.lapis_block, -1, new ItemStack[] { stack });
        
        stack = VanillaColor.getVinillaDye(VanillaColor.RED);
        stack.stackSize = 4;
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.red_flower, -1, new ItemStack[] { stack });
        
        stack = VanillaColor.getVinillaDye(VanillaColor.YELLOW);
        stack.stackSize = 4;
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.yellow_flower, -1, new ItemStack[] { stack });
        
        stack = VanillaColor.getVinillaDye(VanillaColor.GREEN);
        stack.stackSize = 4;
        MultiPanelGrinderRecipe.getInstance().addRecipe(Blocks.cactus, -1, new ItemStack[] { stack });
    }
}
