package GU;

import net.minecraftforge.common.Configuration;
import GU.items.ItemAirCrystalShard;
import GU.items.ItemBase;
import GU.items.ItemBasicDestructionCatalyst;
import GU.items.ItemCrystalCasing;
import GU.items.ItemEarthCrystalShard;
import GU.items.ItemEnergyCrystalShard;
import GU.items.ItemEnhancedDestructionCatalyst;
import GU.items.ItemFireCrystalShard;
import GU.items.ItemGarnet;
import GU.items.ItemGearReader;
import GU.items.ItemGearTinkerer;
import GU.items.ItemLifeStealingBludgeoningStick;
import GU.items.ItemLinker;
import GU.items.ItemRainBGone;
import GU.items.ItemTeleporter;
import GU.items.ItemTradeStick;
import GU.items.ItemWaterCrystalShard;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemRegistry {

    public static ItemBase ItemAirCrystalShard;
    public static ItemBase ItemEarthCrystalShard;
    public static ItemBase ItemFireCrystalShard;
    public static ItemBase ItemWaterCrystalShard;
    public static ItemBase ItemEnergyCrystalShard;
    public static ItemBase ItemGarnet;
    public static ItemBase ItemCrystalCasing;
    public static ItemBase ItemBasicDestructionCatalyst;
    public static ItemBase ItemEnhancedDestructionCatalyst;
    public static ItemBase ItemLifeStealingBludgeoningStick;
    public static ItemBase ItemRainBGone;
    public static ItemBase ItemTeleporter;
    public static ItemBase ItemTradeStick;
    public static ItemBase ItemGearReader;
    public static ItemBase ItemLinker;
    public static ItemBase ItemGearTinkerer;
    
    private static int id = 5000;

    public static void init(Configuration config) {

        ItemAirCrystalShard = new ItemAirCrystalShard(config.getItem("ItemAirCrystalShard", ItemRegistry.getNextBaseID()).getInt());
        ItemAirCrystalShard.setItemName("ItemAirCrystalShard");
        LanguageRegistry.addName(ItemAirCrystalShard, "Air Crystal Shard");

        ItemEarthCrystalShard = new ItemEarthCrystalShard(config.getItem("ItemEarthCrystalShard", ItemRegistry.getNextBaseID()).getInt());
        ItemEarthCrystalShard.setItemName("ItemEarthCrystalShard");
        LanguageRegistry.addName(ItemEarthCrystalShard, "Earth Crystal Shard");

        ItemFireCrystalShard = new ItemFireCrystalShard(config.getItem("ItemFireCrystalShard", ItemRegistry.getNextBaseID()).getInt());
        ItemFireCrystalShard.setItemName("ItemFireCrystalShard");
        LanguageRegistry.addName(ItemFireCrystalShard, "Fire Crystal Shard");

        ItemWaterCrystalShard = new ItemWaterCrystalShard(config.getItem("ItemWaterCrystalShard", ItemRegistry.getNextBaseID()).getInt());
        ItemWaterCrystalShard.setItemName("ItemWaterCrystalShard");
        LanguageRegistry.addName(ItemWaterCrystalShard, "Water Crystal Shard");

        ItemEnergyCrystalShard = new ItemEnergyCrystalShard(config.getItem("ItemEnergyCrystalShard", ItemRegistry.getNextBaseID()).getInt());
        ItemEnergyCrystalShard.setItemName("ItemEnergyCrystalShard");
        LanguageRegistry.addName(ItemEnergyCrystalShard, "Energy Crystal Shard");

        ItemGarnet = new ItemGarnet(config.getItem("ItemGarnet", ItemRegistry.getNextBaseID()).getInt());
        ItemGarnet.setItemName("ItemGarnet");
        LanguageRegistry.addName(ItemGarnet, "Garnet");
        
        ItemCrystalCasing = new ItemCrystalCasing(config.getItem("ItemCrystalCasing", ItemRegistry.getNextBaseID()).getInt());
        ItemCrystalCasing.setItemName("ItemCrystalCasing");
        LanguageRegistry.addName(ItemCrystalCasing, "Crystal Casing");
        
        ItemBasicDestructionCatalyst = new ItemBasicDestructionCatalyst(config.getItem("ItemContritioSimplexCatalyst", ItemRegistry.getNextBaseID()).getInt());
        ItemBasicDestructionCatalyst.setItemName("ItemContritioSimplexCatalyst");
        LanguageRegistry.addName(ItemBasicDestructionCatalyst, "Contritio Simplex Catalyst");
        
        ItemEnhancedDestructionCatalyst = new ItemEnhancedDestructionCatalyst(config.getItem("ItemContritioConsecteturCatalyst", ItemRegistry.getNextBaseID()).getInt());
        ItemEnhancedDestructionCatalyst.setItemName("ItemContritioConsecteturCatalyst");
        LanguageRegistry.addName(ItemEnhancedDestructionCatalyst, "Contritio Consectetur Catalyst");
        
        ItemLifeStealingBludgeoningStick = new ItemLifeStealingBludgeoningStick(config.getItem("ItemLifeStealingBludgeoningStick", ItemRegistry.getNextBaseID()).getInt());
        ItemLifeStealingBludgeoningStick.setItemName("ItemLifeStealingBludgeoningStick");
        LanguageRegistry.addName(ItemLifeStealingBludgeoningStick, "Life Stealing Bludgeoning Stick");
        
        ItemRainBGone = new ItemRainBGone(config.getItem("ItemRainBGone", ItemRegistry.getNextBaseID()).getInt());
        ItemRainBGone.setItemName("ItemRainBGone");
        LanguageRegistry.addName(ItemRainBGone, "Rain B Gone");
        
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
        
        ItemGearTinkerer = new ItemGearTinkerer(config.getItem("ItemGearTinkerer", ItemRegistry.getNextBaseID()).getInt());
        ItemGearTinkerer.setItemName("ItemGearTinkerer");
        LanguageRegistry.addName(ItemGearTinkerer, "Gear Tinkerer");
    }
    
    public static int getNextBaseID() {

        return id++;
    }
}
