package GU;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilRecipe;
import GU.api.color.VanillaColor;
import GU.api.recipe.SenderRecipe;
import GU.blocks.containers.BlockSender.TileSender;
import GU.info.Variables;
import cpw.mods.fml.common.registry.GameRegistry;

public class CraftRegistry {

    public static void init() {
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.beefCooked));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.beefRaw));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.chickenCooked));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.chickenRaw));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.egg));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.fermentedSpiderEye));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.fishCooked));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.fishRaw));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.ghastTear));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.porkCooked));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.porkRaw));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.rottenFlesh));
        OreDictionary.registerOre(Variables.MISC_MEAT, new ItemStack(Item.spiderEye));

        GameRegistry.addRecipe(new ItemStack(Item.bucketLava), new Object[]{"FFF", "FSF", "FBF", 'F', ItemRegistry.ItemCrystal.ItemFireCrystalShard, 'S', Block.stone, 'B', Item.bucketEmpty});

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockSpeedyRoad, 8), new Object[]{"MMM", "QIQ", "QQQ", 'M', Block.stone, 'Q', Block.blockNetherQuartz, 'I', Item.ingotIron});

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockEnhancedBricks, 4), new Object[]{"CBC", "BRB", "CBC", 'C', Variables.CRYSTALS_ALL, 'B', Block.stoneBrick, 'R', Item.ingotIron}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockEnhancedBricks, 8), new Object[]{"CBC", "BRB", "CBC", 'C', Variables.CRYSTALS_ALL, 'B', Block.stoneBrick, 'R', Item.ingotGold}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockEnhancedBricks, 16), new Object[]{"CBC", "BRB", "CBC", 'C', Variables.CRYSTALS_ALL, 'B', Block.stoneBrick, 'R', Item.diamond}));

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockLamp, 8), new Object[]{"ITI", "TET", "IRI", 'E', BlockRegistry.BlockEnhancedBricks, 'T', Block.torchWood, 'R', Block.cloth, 'I', Item.ingotIron});

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockCanvas, 8), new Object[]{"IWI", "WEW", "IWI", 'E', BlockRegistry.BlockEnhancedBricks, 'W', Block.cloth, 'I', Item.ingotIron});

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockConnectableTank, 4), new Object[]{"QGQ", "GEG", "QGQ", 'Q', Item.netherQuartz, 'G', Block.glass, 'E', BlockRegistry.BlockEnhancedBricks});

        GameRegistry.addRecipe(new ItemStack(BlockRegistry.BlockConnectedGlass, 8), new Object[]{"EQE", "IGI", "EQE", 'Q', Item.netherQuartz, 'G', Block.glass, 'E', BlockRegistry.BlockEnhancedBricks, 'I', Item.ingotIron});

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(BlockRegistry.BlockCreationTable), new Object[]{"ECE", "GGG", "ECE",

        'E', BlockRegistry.BlockEnhancedBricks, 'C', Block.workbench, 'Q', Item.netherQuartz, 'I', Item.ingotIron, 'G', BlockRegistry.BlockFalseBlock}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemAdvancedStick), new Object[]{"IQI", "CSC", "IQI",

        'S', Item.stick, 'Q', Item.netherQuartz, 'I', Item.ingotIron, 'C', Variables.CRYSTALS_ALL}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemPhantomPlacer), new Object[]{"QIQ", "CAC", "CAC", 'A', ItemRegistry.ItemAdvancedStick, 'Q', Block.blockNetherQuartz, 'I', Item.ingotIron, 'C', Variables.CRYSTALS_ALL}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemLifeStealingBludgeoningStick), new Object[]{"WWW", "ABA", "ASA",

        'W', Item.swordIron, 'C', Block.workbench, 'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'A', ItemRegistry.ItemAdvancedStick, 'S', Item.stick}));

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

        GameRegistry.addShapelessRecipe(new ItemStack(Item.seeds), Item.wheat, Item.wheat);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemTeleporter), ItemRegistry.ItemTeleporter);
        GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.ItemLinker), ItemRegistry.ItemLinker);
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockEtherealConnectedGlass), BlockRegistry.BlockConnectedGlass, BlockRegistry.BlockFalseBlock);
        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockConnectedGlass), BlockRegistry.BlockEtherealConnectedGlass);
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Block.torchWood, 6), ItemRegistry.ItemCrystal.ItemFireCrystalShard, Item.stick));
        GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(Item.coal, 1, 1), ItemRegistry.ItemCrystal.ItemFireCrystalShard, "logWood"));

        GameRegistry.addSmelting(Item.axeWood.itemID, new ItemStack(ItemRegistry.ItemCharcoalAxe), 1.F);
        GameRegistry.addSmelting(Item.pickaxeWood.itemID, new ItemStack(ItemRegistry.ItemCharcoalPickaxe), 1.F);
        GameRegistry.addSmelting(Item.shovelWood.itemID, new ItemStack(ItemRegistry.ItemCharcoalShovel), 1.F);
        GameRegistry.addSmelting(Item.swordWood.itemID, new ItemStack(ItemRegistry.ItemCharcoalSword), 1.F);

        GameRegistry.addSmelting(Item.hoeStone.itemID, new ItemStack(ItemRegistry.ItemSmoothStoneHoe), 1.F);
        GameRegistry.addSmelting(Item.axeStone.itemID, new ItemStack(ItemRegistry.ItemSmoothStoneAxe), 1.F);
        GameRegistry.addSmelting(Item.pickaxeStone.itemID, new ItemStack(ItemRegistry.ItemSmoothStonePickaxe), 1.F);
        GameRegistry.addSmelting(Item.shovelStone.itemID, new ItemStack(ItemRegistry.ItemSmoothStoneShovel), 1.F);
        GameRegistry.addSmelting(Item.swordStone.itemID, new ItemStack(ItemRegistry.ItemSmoothStoneSword), 1.F);

        UtilRecipe.addSword(new ItemStack(Block.stone), new ItemStack(ItemRegistry.ItemSmoothStoneSword));
        UtilRecipe.addPickaxe(new ItemStack(Block.stone), new ItemStack(ItemRegistry.ItemSmoothStonePickaxe));
        UtilRecipe.addShovel(new ItemStack(Block.stone), new ItemStack(ItemRegistry.ItemSmoothStoneShovel));
        UtilRecipe.addAxe(new ItemStack(Block.stone), new ItemStack(ItemRegistry.ItemSmoothStoneAxe));
        UtilRecipe.addHoe(new ItemStack(Block.stone), new ItemStack(ItemRegistry.ItemSmoothStoneHoe));

        initTempRecipes();
        initGrinderRecipies();
    }

    public static void initGrinderRecipies() {

        SenderRecipe.getInstance().addRecipe(Block.stone.blockID, 0, new ItemStack[]{new ItemStack(Block.cobblestone)});
        SenderRecipe.getInstance().addRecipe(Block.cobblestone.blockID, -1, new ItemStack[]{new ItemStack(Block.sand)});
        SenderRecipe.getInstance().addRecipe(Block.grass.blockID, -1, new ItemStack[]{new ItemStack(Block.dirt)});
        SenderRecipe.getInstance().addRecipe(Block.gravel.blockID, -1, new ItemStack[]{new ItemStack(Item.flint, 4)});
        SenderRecipe.getInstance().addRecipe(Block.cloth.blockID, -1, new ItemStack[]{new ItemStack(Block.sponge)});
        SenderRecipe.getInstance().addRecipe(Block.redstoneLampIdle.blockID, -1, new ItemStack[]{new ItemStack(Item.redstone, 4), new ItemStack(Item.glowstone, 4)});
        SenderRecipe.getInstance().addRecipe(Block.redstoneLampActive.blockID, -1, new ItemStack[]{new ItemStack(Item.redstone, 4), new ItemStack(Item.glowstone, 4)});
        SenderRecipe.getInstance().addRecipe(Block.dropper.blockID, -1, new ItemStack[]{new ItemStack(Block.cobblestone, 7), new ItemStack(Item.redstone)});
        SenderRecipe.getInstance().addRecipe(Block.beacon.blockID, -1, new ItemStack[]{new ItemStack(Block.sand, 5), new ItemStack(Block.obsidian), new ItemStack(Item.netherStar)});
        SenderRecipe.getInstance().addRecipe(Block.anvil.blockID, -1, new ItemStack[]{new ItemStack(Item.ingotIron, 32)});
        SenderRecipe.getInstance().addRecipe(Block.enchantmentTable.blockID, -1, new ItemStack[]{new ItemStack(Item.book), new ItemStack(Block.obsidian, 5), new ItemStack(Item.diamond, 2)});
        SenderRecipe.getInstance().addRecipe(Block.torchWood.blockID, -1, new ItemStack[]{new ItemStack(Item.stick, 2)});
        SenderRecipe.getInstance().addRecipe(Block.lever.blockID, -1, new ItemStack[]{new ItemStack(Block.cobblestone), new ItemStack(Item.stick)});
        SenderRecipe.getInstance().addRecipe(Block.ladder.blockID, -1, new ItemStack[]{new ItemStack(Item.stick, 7)});
        SenderRecipe.getInstance().addRecipe(Block.furnaceIdle.blockID, -1, new ItemStack[]{new ItemStack(Block.cobblestone, 8)});
        SenderRecipe.getInstance().addRecipe(Block.furnaceBurning.blockID, -1, new ItemStack[]{new ItemStack(Block.cobblestone, 8)});
        SenderRecipe.getInstance().addRecipe(Block.pistonStickyBase.blockID, -1, new ItemStack[]{new ItemStack(Block.pistonBase), new ItemStack(Item.slimeBall)});
        SenderRecipe.getInstance().addRecipe(Block.stoneButton.blockID, -1, new ItemStack[]{new ItemStack(Block.stone)});
        SenderRecipe.getInstance().addRecipe(Block.pumpkinLantern.blockID, -1, new ItemStack[]{new ItemStack(Block.pumpkin), new ItemStack(Block.torchWood)});
        SenderRecipe.getInstance().addRecipe(Block.blockClay.blockID, -1, new ItemStack[]{new ItemStack(Item.clay, 4)});
        SenderRecipe.getInstance().addRecipe(Block.enderChest.blockID, -1, new ItemStack[]{new ItemStack(Item.enderPearl), new ItemStack(Item.blazePowder), new ItemStack(Block.obsidian, 8)});
        SenderRecipe.getInstance().addRecipe(Block.glowStone.blockID, -1, new ItemStack[]{new ItemStack(Item.glowstone, 4)});
        SenderRecipe.getInstance().addRecipe(Block.flowerPot.blockID, -1, new ItemStack[]{new ItemStack(Item.brick, 3)});

        SenderRecipe.getInstance().addRecipe(Block.snow.blockID, -1, new ItemStack[]{new ItemStack(Item.snowball, 4)});
        SenderRecipe.getInstance().addRecipe(Block.netherBrick.blockID, -1, new ItemStack[]{new ItemStack(Item.netherrackBrick, 4)});
        SenderRecipe.getInstance().addRecipe(Block.stoneBrick.blockID, -1, new ItemStack[]{new ItemStack(Block.stone, 4)});
        SenderRecipe.getInstance().addRecipe(Block.melon.blockID, -1, new ItemStack[]{new ItemStack(Item.melon, 4)});
        SenderRecipe.getInstance().addRecipe(Block.mycelium.blockID, -1, new ItemStack[]{new ItemStack(Block.dirt), new ItemStack(Block.mushroomBrown), new ItemStack(Block.mushroomRed)});
        SenderRecipe.getInstance().addRecipe(Block.hay.blockID, -1, new ItemStack[]{new ItemStack(Item.wheat, 9)});

        SenderRecipe.getInstance().addRecipe(Block.hardenedClay.blockID, -1, new ItemStack[]{new ItemStack(Item.clay, 4)});
        SenderRecipe.getInstance().addRecipe(Block.stainedClay.blockID, -1, new ItemStack[]{new ItemStack(Item.clay, 4)});

        SenderRecipe.getInstance().addRecipe(Block.sandStone.blockID, 0, new ItemStack[]{new ItemStack(Block.sand, 4)});
        SenderRecipe.getInstance().addRecipe(Block.sandStone.blockID, 1, new ItemStack[]{new ItemStack(Block.sand, 4)});
        SenderRecipe.getInstance().addRecipe(Block.sandStone.blockID, 2, new ItemStack[]{new ItemStack(Block.sand, 4)});

        SenderRecipe.getInstance().addRecipe(Block.wood.blockID, 0, new ItemStack[]{new ItemStack(Block.planks, 6, 0)});
        SenderRecipe.getInstance().addRecipe(Block.wood.blockID, 1, new ItemStack[]{new ItemStack(Block.planks, 6, 1)});
        SenderRecipe.getInstance().addRecipe(Block.wood.blockID, 2, new ItemStack[]{new ItemStack(Block.planks, 6, 2)});
        SenderRecipe.getInstance().addRecipe(Block.wood.blockID, 3, new ItemStack[]{new ItemStack(Block.planks, 6, 3)});

        SenderRecipe.getInstance().addRecipe(Block.oreCoal.blockID, -1, new ItemStack[]{new ItemStack(Item.coal, 6)});
        SenderRecipe.getInstance().addRecipe(Block.oreNetherQuartz.blockID, -1, new ItemStack[]{new ItemStack(Item.netherQuartz, 6)});
        SenderRecipe.getInstance().addRecipe(Block.oreRedstone.blockID, -1, new ItemStack[]{new ItemStack(Item.redstone, 6)});
        SenderRecipe.getInstance().addRecipe(Block.oreDiamond.blockID, -1, new ItemStack[]{new ItemStack(Item.diamond, 3)});
        SenderRecipe.getInstance().addRecipe(Block.oreEmerald.blockID, -1, new ItemStack[]{new ItemStack(Item.emerald, 3)});

        SenderRecipe.getInstance().addRecipe(Block.coalBlock.blockID, -1, new ItemStack[]{new ItemStack(Item.coal, 9)});
        SenderRecipe.getInstance().addRecipe(Block.blockNetherQuartz.blockID, -1, new ItemStack[]{new ItemStack(Item.netherQuartz, 4)});
        SenderRecipe.getInstance().addRecipe(Block.blockIron.blockID, -1, new ItemStack[]{new ItemStack(Item.ingotIron, 9)});
        SenderRecipe.getInstance().addRecipe(Block.blockGold.blockID, -1, new ItemStack[]{new ItemStack(Item.ingotGold, 9)});
        SenderRecipe.getInstance().addRecipe(Block.blockRedstone.blockID, -1, new ItemStack[]{new ItemStack(Item.redstone, 9)});
        SenderRecipe.getInstance().addRecipe(Block.blockDiamond.blockID, -1, new ItemStack[]{new ItemStack(Item.diamond, 9)});
        SenderRecipe.getInstance().addRecipe(Block.blockEmerald.blockID, -1, new ItemStack[]{new ItemStack(Item.emerald, 9)});

        SenderRecipe.getInstance().addRecipe(Block.web.blockID, 0, new ItemStack[]{new ItemStack(Item.silk, 8)});
        SenderRecipe.getInstance().addRecipe(Block.pistonStickyBase.blockID, 0, new ItemStack[]{new ItemStack(Item.slimeBall), new ItemStack(Block.pistonBase)});
        SenderRecipe.getInstance().addRecipe(Block.dispenser.blockID, -1, new ItemStack[]{new ItemStack(Block.cobblestone, 7), new ItemStack(Item.bow), new ItemStack(Item.redstone)});
        SenderRecipe.getInstance().addRecipe(Block.tnt.blockID, -1, new ItemStack[]{new ItemStack(Item.gunpowder, 5), new ItemStack(Block.sand, 4)});
        SenderRecipe.getInstance().addRecipe(Block.brick.blockID, -1, new ItemStack[]{new ItemStack(Item.brick, 4)});
        SenderRecipe.getInstance().addRecipe(Block.cobblestoneMossy.blockID, -1, new ItemStack[]{new ItemStack(Block.cobblestone), new ItemStack(Block.vine, 6)});

        ItemStack stack = VanillaColor.getVinillaDye(VanillaColor.BLUE);
        stack.stackSize = 8;
        SenderRecipe.getInstance().addRecipe(Block.oreLapis.blockID, -1, new ItemStack[]{stack});
        stack.stackSize = 9;
        SenderRecipe.getInstance().addRecipe(Block.blockLapis.blockID, -1, new ItemStack[]{stack});

        stack = VanillaColor.getVinillaDye(VanillaColor.RED);
        stack.stackSize = 4;
        SenderRecipe.getInstance().addRecipe(Block.plantRed.blockID, -1, new ItemStack[]{stack});

        stack = VanillaColor.getVinillaDye(VanillaColor.YELLOW);
        stack.stackSize = 4;
        SenderRecipe.getInstance().addRecipe(Block.plantYellow.blockID, -1, new ItemStack[]{stack});

        stack = VanillaColor.getVinillaDye(VanillaColor.GREEN);
        stack.stackSize = 4;
        SenderRecipe.getInstance().addRecipe(Block.cactus.blockID, -1, new ItemStack[]{stack});
    }

    public static void initTempRecipes() {

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemBasicDestructionCatalyst, 1), new Object[]{"PPP", "CCC", "GEG",

        'P', Item.pickaxeDiamond, 'C', Variables.CRYSTALS_ALL, 'G', Item.ingotGold, 'E', Item.emerald}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemTeleporter, 2), new Object[]{"ECE", "CDC", "ECE",

        'D', Item.diamond, 'C', Variables.CRYSTALS_ALL, 'E', Item.enderPearl}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemBloodStone), new Object[]{"BSB", "SES", "BSB",

        'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'S', BlockRegistry.BlockFalseBlock, 'E', ItemRegistry.ItemCrystal.ItemEnergyCrystalShard}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicSword), new Object[]{"ESE", "EBE", "EAE",

        'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'S', ItemRegistry.ItemBloodStone, 'E', BlockRegistry.BlockFalseBlock, 'A', ItemRegistry.ItemAdvancedStick}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicPickaxe), new Object[]{"BBB", "ESE", "EAE",

        'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'S', ItemRegistry.ItemBloodStone, 'E', BlockRegistry.BlockFalseBlock, 'A', ItemRegistry.ItemAdvancedStick}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicShovel), new Object[]{"EBE", "ESE", "EAE",

        'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'S', ItemRegistry.ItemBloodStone, 'E', BlockRegistry.BlockFalseBlock, 'A', ItemRegistry.ItemAdvancedStick}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicAxe), new Object[]{"BBE", "BSE", "EAE",

        'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'S', ItemRegistry.ItemBloodStone, 'E', BlockRegistry.BlockFalseBlock, 'A', ItemRegistry.ItemAdvancedStick}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicAxe), new Object[]{"EBB", "ESB", "EAE",

        'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'S', ItemRegistry.ItemBloodStone, 'E', BlockRegistry.BlockFalseBlock, 'A', ItemRegistry.ItemAdvancedStick}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicHoe), new Object[]{"BBE", "ESE", "EAE",

        'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'S', ItemRegistry.ItemBloodStone, 'E', BlockRegistry.BlockFalseBlock, 'A', ItemRegistry.ItemAdvancedStick}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemRunicHoe), new Object[]{"EBB", "ESE", "EAE",

        'B', ItemRegistry.ItemCrystal.ItemBloodCrystalShard, 'S', ItemRegistry.ItemBloodStone, 'E', BlockRegistry.BlockFalseBlock, 'A', ItemRegistry.ItemAdvancedStick}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemTradeStick), new Object[]{"EDE", "DAD", "EDE",

        'E', Item.emerald, 'D', Item.diamond, 'A', ItemRegistry.ItemAdvancedStick}));

        GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ItemRegistry.ItemShifter), new Object[]{"RCQ", "IQC", "RIR",

        'C', Variables.CRYSTALS_ALL, 'I', Item.ingotIron, 'Q', Item.netherQuartz, 'R', VanillaColor.getVinillaDye(VanillaColor.RED)}));

        ItemStack base = new ItemStack(BlockRegistry.BlockSender);
        UtilItemStack.setNBTTagInt(base, "mode", 0);
        GameRegistry.addRecipe(new ShapedOreRecipe(base, new Object[]{"ICI", "QDQ", "EBE",

        'C', Variables.CRYSTALS_ALL, 'I', Item.ingotIron, 'Q', Item.netherQuartz, 'D', Item.diamond, 'E', BlockRegistry.BlockEnhancedBricks, 'B', ItemRegistry.ItemBloodStone}));

        ItemStack itemMover = new ItemStack(BlockRegistry.BlockSender);
        UtilItemStack.setNBTTagInt(itemMover, "mode", TileSender.ITEM_MOVEMENT);
        GameRegistry.addRecipe(new ShapedOreRecipe(itemMover, new Object[]{"QCQ", "ESE", "CQC",

        'C', ItemRegistry.ItemCrystal.ItemAirCrystalShard, 'Q', Item.netherQuartz, 'E', Item.enderPearl, 'S', base}));

        ItemStack fluidMover = new ItemStack(BlockRegistry.BlockSender);
        UtilItemStack.setNBTTagInt(fluidMover, "mode", TileSender.FLUID_MOVEMENT);
        GameRegistry.addRecipe(new ShapedOreRecipe(fluidMover, new Object[]{"QCQ", "ESE", "CQC",

        'C', ItemRegistry.ItemCrystal.ItemWaterCrystalShard, 'Q', Item.netherQuartz, 'E', Item.enderPearl, 'S', base}));

        ItemStack grinder = new ItemStack(BlockRegistry.BlockSender);
        UtilItemStack.setNBTTagInt(grinder, "mode", TileSender.GRINDING);
        GameRegistry.addRecipe(new ShapedOreRecipe(grinder, new Object[]{"DID", "CSC", "QCQ",

        'C', ItemRegistry.ItemCrystal.ItemEarthCrystalShard, 'Q', Item.netherQuartz, 'D', Item.diamond, 'S', base, 'I', Item.ingotIron}));

        ItemStack blockPlacer = new ItemStack(BlockRegistry.BlockSender);
        UtilItemStack.setNBTTagInt(blockPlacer, "mode", TileSender.BLOCK_PLACE);
        GameRegistry.addRecipe(new ShapedOreRecipe(blockPlacer, new Object[]{"DQD", "CSC", "QIQ",

        'C', ItemRegistry.ItemCrystal.ItemEarthCrystalShard, 'Q', Item.netherQuartz, 'D', Block.dispenser, 'S', base, 'I', Item.ingotIron}));

        ItemStack blockBreaker = new ItemStack(BlockRegistry.BlockSender);
        UtilItemStack.setNBTTagInt(blockBreaker, "mode", TileSender.BLOCK_BREAK);
        GameRegistry.addRecipe(new ShapedOreRecipe(blockBreaker, new Object[]{"IPI", "CSC", "QIQ",

        'C', ItemRegistry.ItemCrystal.ItemGarnet, 'Q', Item.netherQuartz, 'P', Item.pickaxeDiamond, 'S', base, 'I', Item.ingotIron}));

        ItemStack smelter = new ItemStack(BlockRegistry.BlockSender);
        UtilItemStack.setNBTTagInt(smelter, "mode", TileSender.SMELTER);
        GameRegistry.addRecipe(new ShapedOreRecipe(smelter, new Object[]{"FCF", "QSQ", "CQC",

        'C', ItemRegistry.ItemCrystal.ItemFireCrystalShard, 'Q', Item.netherQuartz, 'F', Block.furnaceIdle, 'S', base}));

        GameRegistry.addShapelessRecipe(new ItemStack(BlockRegistry.BlockSender), base);

        initTempRecipesBlood();
    }

    public static void initTempRecipesBlood() {

        GameRegistry.addShapelessRecipe(FluidContainerRegistry.fillFluidContainer(new FluidStack(GU.FluidRegistry.Blood, 1000), new ItemStack(ItemRegistry.ItemStorageCrystal)), new ItemStack(ItemRegistry.ItemStorageCrystal), ItemRegistry.ItemCrystal.ItemPlantBloodCrystalShard, ItemRegistry.ItemCrystal.ItemPlantBloodCrystalShard, ItemRegistry.ItemCrystal.ItemPlantBloodCrystalShard, ItemRegistry.ItemCrystal.ItemPlantBloodCrystalShard, ItemRegistry.ItemCrystal.ItemPlantBloodCrystalShard, ItemRegistry.ItemCrystal.ItemPlantBloodCrystalShard, ItemRegistry.ItemCrystal.ItemPlantBloodCrystalShard, ItemRegistry.ItemCrystal.ItemPlantBloodCrystalShard);
        GameRegistry.addShapelessRecipe(FluidContainerRegistry.fillFluidContainer(new FluidStack(GU.FluidRegistry.Blood, 1000), new ItemStack(ItemRegistry.ItemStorageCrystal)), new ItemStack(ItemRegistry.ItemStorageCrystal), ItemRegistry.ItemCrystal.ItemBloodCrystalShard, ItemRegistry.ItemCrystal.ItemBloodCrystalShard, ItemRegistry.ItemCrystal.ItemBloodCrystalShard, ItemRegistry.ItemCrystal.ItemBloodCrystalShard);

        GameRegistry.addRecipe(new ShapedOreRecipe(FluidContainerRegistry.fillFluidContainer(new FluidStack(GU.FluidRegistry.MeatMash, 1000), new ItemStack(ItemRegistry.ItemStorageCrystal)), new Object[]{"BBB", "BSB", "BBB",

        'B', Variables.MISC_MEAT, 'S', ItemRegistry.ItemStorageCrystal}));

    }
}
