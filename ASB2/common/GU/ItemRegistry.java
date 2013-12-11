package GU;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import ASB2.items.AxeBase;
import ASB2.items.HoeBase;
import ASB2.items.PickaxeBase;
import ASB2.items.ScytheBase;
import ASB2.items.ShovelBase;
import ASB2.items.SwordBase;
import GU.info.Reference;
import GU.items.ItemAdvancedStick;
import GU.items.ItemBase;
import GU.items.ItemBasicDestructionCatalyst;
import GU.items.ItemBrewedPotion;
import GU.items.ItemEnhancedDestructionCatalyst;
import GU.items.ItemGearReader;
import GU.items.ItemLifeStealingBludgeoningStick;
import GU.items.ItemLinker;
import GU.items.ItemPhantomPlacer;
import GU.items.ItemTeleporter;
import GU.items.ItemTestItem;
import GU.items.ItemTradeStick;
import GU.items.ItemCrystalShards.ItemCrystal;
import GU.items.ItemStorageCrystal.ItemStorageCrystal;
import GU.items.potionIngredients.ItemPotionIngredients;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemRegistry {

    public static ItemCrystal ItemCrystal;
    public static ItemBase ItemPotionIngredients;
    public static ItemBase ItemBasicDestructionCatalyst;
    public static ItemBase ItemEnhancedDestructionCatalyst;
    public static ItemBase ItemLifeStealingBludgeoningStick;
    public static ItemBase ItemTeleporter;
    public static ItemBase ItemTradeStick;
    public static ItemBase ItemGearReader;
    public static ItemBase ItemLinker;
    public static ItemBase ItemAdvancedStick;
    public static ItemBase ItemTestItem;
    public static ItemBase ItemStorageCrystal;
    public static ItemBase ItemPhantomPlacer;
    public static ItemBase ItemBrewedPotion;
    public static Item ItemCharcoalSword, ItemCharcoalPickaxe, ItemCharcoalShovel, ItemCharcoalAxe;
    public static Item ItemGarnetSword, ItemGarnetPickaxe, ItemGarnetShovel, ItemGarnetAxe, ItemGarnetHoe, ItemGarnetScythe;
    public static ItemBase ItemHandheldTank;
    public static Item ItemSmoothStoneSword, ItemSmoothStonePickaxe, ItemSmoothStoneShovel, ItemSmoothStoneAxe, ItemSmoothStoneHoe;
    
    private static int id = 5000;

    public static void init(Configuration config) {

        ItemCrystal = new ItemCrystal(config.getItem("ItemCrystal", ItemRegistry.getNextBaseID()).getInt());

        ItemPotionIngredients = new ItemPotionIngredients(config.getItem("ItemPotionIngredients", ItemRegistry.getNextBaseID()).getInt());

        ItemTestItem = new ItemTestItem(config.getItem("ItemTestItem", ItemRegistry.getNextBaseID()).getInt());
        ItemTestItem.setItemName("ItemTestItem");
        LanguageRegistry.addName(ItemTestItem, "Test Item");

        ItemBasicDestructionCatalyst = new ItemBasicDestructionCatalyst(config.getItem("ItemContritioSimplexCatalyst", ItemRegistry.getNextBaseID()).getInt());
        ItemBasicDestructionCatalyst.setItemName("ItemContritioSimplexCatalyst");
        LanguageRegistry.addName(ItemBasicDestructionCatalyst, "Contritio Simplex Catalyst");

        ItemEnhancedDestructionCatalyst = new ItemEnhancedDestructionCatalyst(config.getItem("ItemContritioConsecteturCatalyst", ItemRegistry.getNextBaseID()).getInt());
        ItemEnhancedDestructionCatalyst.setItemName("ItemContritioConsecteturCatalyst");
        LanguageRegistry.addName(ItemEnhancedDestructionCatalyst, "Contritio Consectetur Catalyst");

        ItemLifeStealingBludgeoningStick = new ItemLifeStealingBludgeoningStick(config.getItem("ItemLifeStealingBludgeoningStick", ItemRegistry.getNextBaseID()).getInt());
        ItemLifeStealingBludgeoningStick.setItemName("ItemLifeStealingBludgeoningStick");
        LanguageRegistry.addName(ItemLifeStealingBludgeoningStick, "Life Stealing Bludgeoning Stick");

        ItemTeleporter = new ItemTeleporter(config.getItem("ItemTeleporter", ItemRegistry.getNextBaseID()).getInt());
        ItemTeleporter.setItemName("ItemTeleporter");
        LanguageRegistry.addName(ItemTeleporter, "Teleporter");

        ItemTradeStick = new ItemTradeStick(config.getItem("ItemTradeStick", ItemRegistry.getNextBaseID()).getInt());
        ItemTradeStick.setItemName("ItemTradeStick");
        LanguageRegistry.addName(ItemTradeStick, "Trade Stick");

        ItemGearReader = new ItemGearReader(config.getItem("ItemGearReader", ItemRegistry.getNextBaseID()).getInt());
        ItemGearReader.setItemName("ItemGearReader");
        LanguageRegistry.addName(ItemGearReader, "Gear Reader");

        ItemLinker = new ItemLinker(config.getItem("ItemLinker", ItemRegistry.getNextBaseID()).getInt());
        ItemLinker.setItemName("ItemLinker");
        LanguageRegistry.addName(ItemLinker, "Linker");

        ItemAdvancedStick = new ItemAdvancedStick(config.getItem("ItemAdvancedStick", ItemRegistry.getNextBaseID()).getInt());
        ItemAdvancedStick.setItemName("ItemAdvancedStick");
        LanguageRegistry.addName(ItemAdvancedStick, "Advanced Stick");

        ItemStorageCrystal = new ItemStorageCrystal(config.getItem("ItemStorageCrystal", ItemRegistry.getNextBaseID()).getInt());
        ItemStorageCrystal.setItemName("ItemStorageCrystal");
        LanguageRegistry.addName(ItemStorageCrystal, "Storage Crystal");

        ItemPhantomPlacer = new ItemPhantomPlacer(config.getItem("ItemPhantomPlacer", ItemRegistry.getNextBaseID()).getInt());
        ItemPhantomPlacer.setItemName("ItemPhantomPlacer");
        LanguageRegistry.addName(ItemPhantomPlacer, "Phantom Placer");

        ItemBrewedPotion = new ItemBrewedPotion(config.getItem("ItemBrewedPotion", ItemRegistry.getNextBaseID()).getInt());
        ItemBrewedPotion.setItemName("ItemBrewedPotion");
        LanguageRegistry.addName(ItemBrewedPotion, "Brewed Potion");

        ItemCharcoalSword = new SwordBase(config.getItem("ItemCharcoalSword", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalSword");
        LanguageRegistry.addName(ItemCharcoalSword, "Charcoal Sword");
        ItemCharcoalSword.setCreativeTab(GearUtilities.tabGUItems);

        ItemCharcoalPickaxe = new PickaxeBase(config.getItem("ItemCharcoalPickaxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalPickaxe");
        LanguageRegistry.addName(ItemCharcoalPickaxe, "Charcoal Pickaxe");
        ItemCharcoalPickaxe.setCreativeTab(GearUtilities.tabGUItems);

        ItemCharcoalShovel = new ShovelBase(config.getItem("ItemCharcoalShovel", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalShovel");
        LanguageRegistry.addName(ItemCharcoalShovel, "Charcoal Shovel");
        ItemCharcoalShovel.setCreativeTab(GearUtilities.tabGUItems);

        ItemCharcoalAxe = new AxeBase(config.getItem("ItemCharcoalAxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalAxe");
        LanguageRegistry.addName(ItemCharcoalAxe, "Charcoal Axe");
        ItemCharcoalAxe.setCreativeTab(GearUtilities.tabGUItems);

        ItemGarnetSword = new SwordBase(config.getItem("ItemGarnetSword", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetSword");
        LanguageRegistry.addName(ItemGarnetSword, "Garnet Sword");
        ItemGarnetSword.setCreativeTab(GearUtilities.tabGUItems);

        ItemGarnetPickaxe = new PickaxeBase(config.getItem("ItemGarnetPickaxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetPickaxe");
        LanguageRegistry.addName(ItemGarnetPickaxe, "Garnet Pickaxe");
        ItemGarnetPickaxe.setCreativeTab(GearUtilities.tabGUItems);

        ItemGarnetShovel = new ShovelBase(config.getItem("ItemGarnetShovel", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetShovel");
        LanguageRegistry.addName(ItemGarnetShovel, "Garnet Shovel");
        ItemGarnetShovel.setCreativeTab(GearUtilities.tabGUItems);

        ItemGarnetAxe = new AxeBase(config.getItem("ItemGarnetAxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetAxe");
        LanguageRegistry.addName(ItemGarnetAxe, "Garnet Axe");
        ItemGarnetAxe.setCreativeTab(GearUtilities.tabGUItems);

        ItemGarnetHoe = new HoeBase(config.getItem("ItemGarnetHoe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetHoe");
        LanguageRegistry.addName(ItemGarnetHoe, "Garnet Hoe");
        ItemGarnetHoe.setCreativeTab(GearUtilities.tabGUItems);

        ItemGarnetScythe = new ScytheBase(config.getItem("ItemGarnetScythe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetScythe");
        LanguageRegistry.addName(ItemGarnetScythe, "Garnet Scythe");
        ItemGarnetScythe.setCreativeTab(GearUtilities.tabGUItems);

        ItemSmoothStoneSword = new SwordBase(config.getItem("ItemSmoothStoneSword", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneSword");
        LanguageRegistry.addName(ItemSmoothStoneSword, "Smooth Stone Sword");
        ItemSmoothStoneSword.setCreativeTab(GearUtilities.tabGUItems);

        ItemSmoothStonePickaxe = new PickaxeBase(config.getItem("ItemSmoothStonePickaxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStonePickaxe");
        LanguageRegistry.addName(ItemSmoothStonePickaxe, "Smooth Stone Pickaxe");
        ItemSmoothStonePickaxe.setCreativeTab(GearUtilities.tabGUItems);

        ItemSmoothStoneShovel = new ShovelBase(config.getItem("ItemSmoothStoneShovel", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneShovel");
        LanguageRegistry.addName(ItemSmoothStoneShovel, "Smooth Stone Shovel");
        ItemSmoothStoneShovel.setCreativeTab(GearUtilities.tabGUItems);

        ItemSmoothStoneAxe = new AxeBase(config.getItem("ItemSmoothStoneAxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneAxe");
        LanguageRegistry.addName(ItemSmoothStoneAxe, "Smooth Stone Axe");
        ItemSmoothStoneAxe.setCreativeTab(GearUtilities.tabGUItems);

        ItemSmoothStoneHoe = new HoeBase(config.getItem("ItemSmoothStoneHoe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneHoe");
        LanguageRegistry.addName(ItemSmoothStoneHoe, "Smooth Stone Hoe");
        ItemSmoothStoneHoe.setCreativeTab(GearUtilities.tabGUItems);
    }

    public static int getNextBaseID() {

        return id++;
    }
}
