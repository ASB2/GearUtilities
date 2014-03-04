package GU;

import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.Configuration;
import ASB2.items.AxeBase;
import ASB2.items.HoeBase;
import ASB2.items.PickaxeBase;
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
import GU.items.ItemShifter;
import GU.items.ItemTeleporter;
import GU.items.ItemTestItem;
import GU.items.ItemTradeStick;
import GU.items.ItemCrystalShards.ItemCrystal;
import GU.items.ItemFlameFocus.ItemFlameFocus;
import GU.items.ItemFlameShard.ItemFlameShard;
import GU.items.ItemStorageCrystal.ItemStorageCrystal;
import GU.items.potionIngredients.ItemPotionIngredients;
import cpw.mods.fml.common.registry.GameRegistry;
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
    public static Item ItemGarnetSword, ItemGarnetPickaxe, ItemGarnetShovel, ItemGarnetAxe, ItemGarnetHoe;
    public static ItemBase ItemHandheldTank;
    public static Item ItemSmoothStoneSword, ItemSmoothStonePickaxe, ItemSmoothStoneShovel, ItemSmoothStoneAxe, ItemSmoothStoneHoe;
    public static ItemBase ItemShifter;
    public static ItemBase ItemFlameFocus;
    public static ItemBase ItemFlameShard;

    private static int id = 5000;

    public static void init(Configuration config) {

        ItemCrystal = new ItemCrystal(config.getItem("ItemCrystal", ItemRegistry.getNextBaseID()).getInt());

        ItemPotionIngredients = new ItemPotionIngredients(config.getItem("ItemPotionIngredients", ItemRegistry.getNextBaseID()).getInt());

        ItemTestItem = new ItemTestItem(config.getItem("ItemTestItem", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemTestItem, "Test Item", "ItemTestItem");

        ItemBasicDestructionCatalyst = new ItemBasicDestructionCatalyst(config.getItem("ItemContritioSimplexCatalyst", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemBasicDestructionCatalyst, "Contritio Simplex Catalyst", "ItemContritioSimplexCatalyst");

        ItemEnhancedDestructionCatalyst = new ItemEnhancedDestructionCatalyst(config.getItem("ItemContritioConsecteturCatalyst", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemEnhancedDestructionCatalyst, "Contritio Consectetur Catalyst", "ItemContritioConsecteturCatalyst");

        ItemLifeStealingBludgeoningStick = new ItemLifeStealingBludgeoningStick(config.getItem("ItemLifeStealingBludgeoningStick", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemLifeStealingBludgeoningStick, "Life tealing Bludgeoning Stick", "ItemLifeStealingBludgeoningStick");

        ItemTeleporter = new ItemTeleporter(config.getItem("ItemTeleporter", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemTeleporter, "Teleporter", "ItemTeleporter");

        ItemTradeStick = new ItemTradeStick(config.getItem("ItemTradeStick", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemTradeStick, "Trade Stick", "ItemTradeStick");

        ItemGearReader = new ItemGearReader(config.getItem("ItemGearReader", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemGearReader, "Gear Reader", "ItemGearReader");

        ItemLinker = new ItemLinker(config.getItem("ItemLinker", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemLinker, "Linker", "ItemLinker");

        ItemAdvancedStick = new ItemAdvancedStick(config.getItem("ItemAdvancedStick", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemAdvancedStick, "Advanced Stick", "ItemAdvancedStick");

        ItemStorageCrystal = new ItemStorageCrystal(config.getItem("ItemStorageCrystal", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemStorageCrystal, "Storage Crystal", "ItemStorageCrystal");

        ItemPhantomPlacer = new ItemPhantomPlacer(config.getItem("ItemPhantomPlacer", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemPhantomPlacer, "Phantom Placer", "ItemPhantomPlacer");

        ItemBrewedPotion = new ItemBrewedPotion(config.getItem("ItemBrewedPotion", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemBrewedPotion, "Brewed Potion", "ItemBrewedPotion");

        ItemCharcoalSword = new SwordBase(config.getItem("ItemCharcoalSword", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalSword");
        addItem(ItemCharcoalSword, "Charcoal Sword", "ItemCharcoalSword");

        ItemCharcoalPickaxe = new PickaxeBase(config.getItem("ItemCharcoalPickaxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalPickaxe");
        addItem(ItemCharcoalPickaxe, "Charcoal Pickaxe", "ItemCharcoalPickaxe");

        ItemCharcoalShovel = new ShovelBase(config.getItem("ItemCharcoalShovel", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalShovel");
        addItem(ItemCharcoalShovel, "Charcoal Shovel", "ItemCharcoalShovel");

        ItemCharcoalAxe = new AxeBase(config.getItem("ItemCharcoalAxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalAxe");
        addItem(ItemCharcoalAxe, "Charcoal Axe", "ItemCharcoalAxe");

        ItemGarnetSword = new SwordBase(config.getItem("ItemGarnetSword", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetSword");
        addItem(ItemGarnetSword, "Garnet Sword", "ItemGarnetSword");

        ItemGarnetPickaxe = new PickaxeBase(config.getItem("ItemGarnetPickaxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetPickaxe");
        addItem(ItemGarnetPickaxe, "Garnet Pickaxe", "ItemGarnetPickaxe");

        ItemGarnetShovel = new ShovelBase(config.getItem("ItemGarnetShovel", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetShovel");
        addItem(ItemGarnetShovel, "Garnet Shovel", "ItemGarnetShovel");

        ItemGarnetAxe = new AxeBase(config.getItem("ItemGarnetAxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetAxe");
        addItem(ItemGarnetAxe, "Garnet Axe", "ItemGarnetAxe");

        ItemGarnetHoe = new HoeBase(config.getItem("ItemGarnetHoe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemGarnetHoe");
        addItem(ItemGarnetHoe, "Garnet Hoe", "ItemGarnetHoe");

        ItemSmoothStoneSword = new SwordBase(config.getItem("ItemSmoothStoneSword", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneSword");
        addItem(ItemSmoothStoneSword, "Smooth Stone Sword", "ItemSmoothStoneSword");

        ItemSmoothStonePickaxe = new PickaxeBase(config.getItem("ItemSmoothStonePickaxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStonePickaxe");
        addItem(ItemSmoothStonePickaxe, "Smooth Stone Pickaxe", "ItemSmoothStonePickaxe");

        ItemSmoothStoneShovel = new ShovelBase(config.getItem("ItemSmoothStoneShovel", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneShovel");
        addItem(ItemSmoothStoneShovel, "Smooth Stone Shovel", "ItemSmoothStoneShovel");

        ItemSmoothStoneAxe = new AxeBase(config.getItem("ItemSmoothStoneAxe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneAxe");
        addItem(ItemSmoothStoneAxe, "Smooth Stone Axe", "ItemSmoothStoneAxe");

        ItemSmoothStoneHoe = new HoeBase(config.getItem("ItemSmoothStoneHoe", ItemRegistry.getNextBaseID()).getInt(), EnumToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneHoe");
        addItem(ItemSmoothStoneHoe, "Smooth Stone Hoe", "ItemSmoothStoneHoe");

        ItemShifter = new ItemShifter(config.getItem("ItemShifter", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemShifter, "Shifter", "ItemShifter");

        ItemFlameFocus = new ItemFlameFocus(config.getItem("ItemFlameFocus", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemFlameFocus, "Flame Focus", "ItemFlameFocus");
    
        ItemFlameShard = new ItemFlameShard(config.getItem("ItemFlameShard", ItemRegistry.getNextBaseID()).getInt());
        addItem(ItemFlameShard, "Flame Shard", "ItemFlameShard");
    }

    public static ItemBase addItem(ItemBase item, String ign, String unlocalized) {

        item.setItemName(unlocalized);
        item.setTextureName(unlocalized);
        GameRegistry.registerItem(item, item.getUnlocalizedName());
        LanguageRegistry.addName(item, ign);
        return item;
    }

    public static Item addItem(Item item, String ign, String unlocalized) {

        item.setCreativeTab(GearUtilities.tabGUItems);
        item.setUnlocalizedName(Reference.UNIQUE_ID + unlocalized);
        item.setTextureName(unlocalized);
        GameRegistry.registerItem(item, item.getUnlocalizedName());
        LanguageRegistry.addName(item, ign);
        return item;
    }

    public static int getNextBaseID() {

        return id++;
    }
}
