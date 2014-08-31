package GU;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import GU.info.Reference;
import GU.items.AdvancedStickWrapper;
import GU.items.DestructorWrapper;
import GU.items.ElectisShardWrapper;
import GU.items.GearReaderWrapper;
import GU.items.ItemBase;
import GU.items.ItemFluidCrystalArray;
import GU.items.ItemMetadata;
import GU.items.ItemMetadata.ItemMetadataWrapper;
import GU.items.ItemRenderers.GarnetRenderer;
import GU.items.TeleporterWrapper;
import GU.items.UtilityTabletWrapper;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ItemRegistry {
    
    public static final Map<String, Item> customItemMap = new HashMap<String, Item>();
    
    public static final ItemMetadata METADATA_ITEM = new ItemMetadata();
    public static final ItemFluidCrystalArray ITEM_FLUID = new ItemFluidCrystalArray();
    public static final ItemStack ELECTIS_CRYSTAL_SHARD = new ItemStack(METADATA_ITEM, 1, 0);
    public static final ItemStack GARNET = new ItemStack(METADATA_ITEM, 1, 1);
    
    static {
        
        customItemMap.put(Reference.MOD_ID.concat(":ItemMetadata"), METADATA_ITEM);
        customItemMap.put(Reference.MOD_ID.concat(":ItemMetadataFluid"), ITEM_FLUID);
        
        METADATA_ITEM.addWrapper(new ElectisShardWrapper("Electis Crystal Shard"));
        METADATA_ITEM.addWrapper(new ItemMetadataWrapper("Garnet").setRenderer(GarnetRenderer.instance));
        METADATA_ITEM.addWrapper(new ItemMetadataWrapper("Electis Controler").setRenderer(GarnetRenderer.instance));
        METADATA_ITEM.addWrapper(new GearReaderWrapper("Gear Reader"));
        METADATA_ITEM.addWrapper(new TeleporterWrapper("Teleporter"));
        METADATA_ITEM.addWrapper(new AdvancedStickWrapper("Advanced Stick"));
        METADATA_ITEM.addWrapper(new DestructorWrapper("Destructor"));
        METADATA_ITEM.addWrapper(new UtilityTabletWrapper("Utility Tablet"));
        // METADATA_ITEM.addWrapper(new
        // ColorModifierWrapper("Color Modifier").setRenderer(ColorModifierRenderer.instance));
    }
    
    private ItemRegistry() {
        // TODO Auto-generated constructor stub
    }
    
    public static void init(Configuration config) {
        
        for (Entry<String, Item> entry : customItemMap.entrySet()) {
            
            GameRegistry.registerItem(entry.getValue(), entry.getKey());
            
            if (entry.getValue() instanceof ItemBase) {
                
                ((ItemBase) entry.getValue()).postInit();
            }
        }
    }
}
