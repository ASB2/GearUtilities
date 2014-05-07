package GU.items;

import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import GU.ItemRegistry;
import GU.items.ItemMetadata.MetadataWrapper;

public class ItemMetadataRenderer implements IItemRenderer {
    
    public static final ItemMetadataRenderer instance = new ItemMetadataRenderer();
    
    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        
        MetadataWrapper wrapper = ItemRegistry.METADATA_ITEM.wrappers.get(item.getItemDamage());
        
        if (wrapper != null) {
            
            IItemRenderer renderer = wrapper.getRenderer();
            
            if (renderer != null) {
                
                return renderer.handleRenderType(item, type);
            }
        }
        return false;
    }
    
    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        
        MetadataWrapper wrapper = ItemRegistry.METADATA_ITEM.wrappers.get(item.getItemDamage());
        
        if (wrapper != null) {
            
            IItemRenderer renderer = wrapper.getRenderer();
            
            if (renderer != null) {
                
                return renderer.shouldUseRenderHelper(type, item, helper);
            }
        }
        return false;
    }
    
    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        
        MetadataWrapper wrapper = ItemRegistry.METADATA_ITEM.wrappers.get(item.getItemDamage());
        
        if (wrapper != null) {
            
            IItemRenderer renderer = wrapper.getRenderer();
            
            if (renderer != null) {
                
                renderer.renderItem(type, item, data);
            }
        }
    }
}
