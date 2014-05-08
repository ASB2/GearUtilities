package GU;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import GU.info.Reference;
import GU.items.ItemBase;
import GU.items.ItemMetadata;
import GU.items.ItemMetadata.MetadataWrapper;
import GU.items.ItemRenderers.GarnetRenderer;
import GU.items.ItemElectisCrystalShard.ElectisCrystalShardRenderer;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ItemRegistry {
    
    private static final Map<String, Item> customItemMap = new HashMap<String, Item>();
    
    public static final ItemMetadata METADATA_ITEM = new ItemMetadata();
    public static final ItemStack ELECTIS_CRYSTAL_SHARD = new ItemStack(METADATA_ITEM, 1, 0);
    public static final ItemStack GARNET = new ItemStack(METADATA_ITEM, 1, 1);
    
    static {
        
        customItemMap.put(Reference.MOD_ID.concat(":ItemMetadata"), METADATA_ITEM);
        METADATA_ITEM.wrappers.put(0, new MetadataWrapper("Electis Crystal Shard").setRenderer(ElectisCrystalShardRenderer.instance));
        METADATA_ITEM.wrappers.put(1, new MetadataWrapper("Garnet").setRenderer(GarnetRenderer.instance));
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
