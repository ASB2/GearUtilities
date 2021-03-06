package GU;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.security.auth.login.Configuration;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import GU.info.Reference;
import GU.items.AdvancedStickWrapper;
import GU.items.DestructorWrapper;
import GU.items.ElectisShardWrapper;
import GU.items.GearReaderWrapper;
import GU.items.HandheldTeleporterWrapper;
import GU.items.ItemBase;
import GU.items.ItemFluidCrystalArray;
import GU.items.ItemMetadata;
import GU.items.ItemMetadata.ItemMetadataWrapper;
import GU.items.ItemRenderers.GarnetRenderer;
import GU.items.ItemRenderers.PlaceHolderRenderer;
import GU.items.TwoWayHandheldTeleporter;
import GU.items.UtilityTabletWrapper;

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
        METADATA_ITEM.addWrapper(new ItemMetadataWrapper("Electis Infued Garnet").setRenderer(GarnetRenderer.instance));
        METADATA_ITEM.addWrapper(new ItemMetadataWrapper("Electis Controler").setRenderer(PlaceHolderRenderer.instance));
        METADATA_ITEM.addWrapper(new GearReaderWrapper("Gear Reader"));
        METADATA_ITEM.addWrapper(new HandheldTeleporterWrapper("Handheld Teleporter"));
        METADATA_ITEM.addWrapper(new AdvancedStickWrapper("Advanced Stick"));
        METADATA_ITEM.addWrapper(new DestructorWrapper("Destructor"));
        METADATA_ITEM.addWrapper(new UtilityTabletWrapper("Utility Tablet"));
        METADATA_ITEM.addWrapper(new TwoWayHandheldTeleporter("Two Way Handheld Teleporter"));
        
        
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
