package GUOLD;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.config.Configuration;
import ASB2.items.AxeBase;
import ASB2.items.HoeBase;
import ASB2.items.PickaxeBase;
import ASB2.items.ShovelBase;
import ASB2.items.SwordBase;
import GUOLD.info.Reference;
import GUOLD.items.ItemAdvancedStick;
import GUOLD.items.ItemBase;
import GUOLD.items.ItemBrewedPotion;
import GUOLD.items.ItemGearReader;
import GUOLD.items.ItemLinker;
import GUOLD.items.ItemPhantomPlacer;
import GUOLD.items.ItemShifter;
import GUOLD.items.ItemTeleporter;
import GUOLD.items.ItemTestItem;
import GUOLD.items.ItemCrystalShards.ItemCrystal;
import GUOLD.items.ItemFlameFocus.ItemFlameFocus;
import GUOLD.items.ItemFlameShard.ItemFlameShard;
import GUOLD.items.ItemStorageCrystal.ItemStorageCrystal;
import GUOLD.items.potionIngredients.ItemPotionIngredients;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemRegistry {
    
    public static ItemCrystal ItemCrystal;
    public static ItemBase ItemPotionIngredients;
    public static ItemBase ItemTeleporter;
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
    
    public static void init(Configuration config) {
        
        ItemCrystal = new ItemCrystal();
        
        ItemPotionIngredients = new ItemPotionIngredients();
        
        ItemTestItem = new ItemTestItem();
        addItem(ItemTestItem, "Test Item", "ItemTestItem");
        
        ItemTeleporter = new ItemTeleporter();
        addItem(ItemTeleporter, "Teleporter", "ItemTeleporter");
        
        ItemGearReader = new ItemGearReader();
        addItem(ItemGearReader, "Gear Reader", "ItemGearReader");
        
        ItemLinker = new ItemLinker();
        addItem(ItemLinker, "Linker", "ItemLinker");
        
        ItemAdvancedStick = new ItemAdvancedStick();
        addItem(ItemAdvancedStick, "Advanced Stick", "ItemAdvancedStick");
        
        ItemStorageCrystal = new ItemStorageCrystal();
        addItem(ItemStorageCrystal, "Storage Crystal", "ItemStorageCrystal");
        
        ItemPhantomPlacer = new ItemPhantomPlacer();
        addItem(ItemPhantomPlacer, "Phantom Placer", "ItemPhantomPlacer");
        
        ItemBrewedPotion = new ItemBrewedPotion();
        addItem(ItemBrewedPotion, "Brewed Potion", "ItemBrewedPotion");
        
        ItemCharcoalSword = new SwordBase(ToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalSword");
        addItem(ItemCharcoalSword, "Charcoal Sword", "ItemCharcoalSword");
        
        ItemCharcoalPickaxe = new PickaxeBase(ToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalPickaxe");
        addItem(ItemCharcoalPickaxe, "Charcoal Pickaxe", "ItemCharcoalPickaxe");
        
        ItemCharcoalShovel = new ShovelBase(ToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalShovel");
        addItem(ItemCharcoalShovel, "Charcoal Shovel", "ItemCharcoalShovel");
        
        ItemCharcoalAxe = new AxeBase(ToolMaterial.STONE, Reference.MODDID + ":ItemCharcoalAxe");
        addItem(ItemCharcoalAxe, "Charcoal Axe", "ItemCharcoalAxe");
        
        ItemGarnetSword = new SwordBase(ToolMaterial.IRON, Reference.MODDID + ":ItemGarnetSword");
        addItem(ItemGarnetSword, "Garnet Sword", "ItemGarnetSword");
        
        ItemGarnetPickaxe = new PickaxeBase(ToolMaterial.IRON, Reference.MODDID + ":ItemGarnetPickaxe");
        addItem(ItemGarnetPickaxe, "Garnet Pickaxe", "ItemGarnetPickaxe");
        
        ItemGarnetShovel = new ShovelBase(ToolMaterial.IRON, Reference.MODDID + ":ItemGarnetShovel");
        addItem(ItemGarnetShovel, "Garnet Shovel", "ItemGarnetShovel");
        
        ItemGarnetAxe = new AxeBase(ToolMaterial.IRON, Reference.MODDID + ":ItemGarnetAxe");
        addItem(ItemGarnetAxe, "Garnet Axe", "ItemGarnetAxe");
        
        ItemGarnetHoe = new HoeBase(ToolMaterial.IRON, Reference.MODDID + ":ItemGarnetHoe");
        addItem(ItemGarnetHoe, "Garnet Hoe", "ItemGarnetHoe");
        
        ItemSmoothStoneSword = new SwordBase(ToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneSword");
        addItem(ItemSmoothStoneSword, "Smooth Stone Sword", "ItemSmoothStoneSword");
        
        ItemSmoothStonePickaxe = new PickaxeBase(ToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStonePickaxe");
        addItem(ItemSmoothStonePickaxe, "Smooth Stone Pickaxe", "ItemSmoothStonePickaxe");
        
        ItemSmoothStoneShovel = new ShovelBase(ToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneShovel");
        addItem(ItemSmoothStoneShovel, "Smooth Stone Shovel", "ItemSmoothStoneShovel");
        
        ItemSmoothStoneAxe = new AxeBase(ToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneAxe");
        addItem(ItemSmoothStoneAxe, "Smooth Stone Axe", "ItemSmoothStoneAxe");
        
        ItemSmoothStoneHoe = new HoeBase(ToolMaterial.IRON, Reference.MODDID + ":ItemSmoothStoneHoe");
        addItem(ItemSmoothStoneHoe, "Smooth Stone Hoe", "ItemSmoothStoneHoe");
        
        ItemShifter = new ItemShifter();
        addItem(ItemShifter, "Shifter", "ItemShifter");
        
        ItemFlameFocus = new ItemFlameFocus();
        addItem(ItemFlameFocus, "Flame Focus", "ItemFlameFocus");
        
        ItemFlameShard = new ItemFlameShard();
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
}
