package GU;

import net.minecraftforge.common.Configuration;
import GU.items.ItemBase;
import GU.items.ItemCrystal;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemRegistry {

    public static ItemBase ItemCrystal;
    
    private static int id = 5000;
    
    public static void init(Configuration config) {
        
        ItemCrystal = new ItemCrystal(config.getItem("Metadata_Crystals", ItemRegistry.getNextBaseID()).getInt());
        LanguageRegistry.addName(ItemCrystal, "ItemCrystal");
    }

    public static int getNextBaseID() {

        return id++;
    }
}
