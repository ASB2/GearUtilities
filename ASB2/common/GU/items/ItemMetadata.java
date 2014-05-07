package GU.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import GU.info.Reference;

public class ItemMetadata extends ItemBase {
    
    public Map<Integer, MetadataWrapper> wrappers;
    
    public ItemMetadata() {
        
        wrappers = new HashMap<Integer, MetadataWrapper>();
        
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        
        for (Entry<Integer, MetadataWrapper> wrapperEntry : wrappers.entrySet()) {
            
            wrapperEntry.getValue().setIcon(iconRegister.registerIcon(Reference.MOD_ID.concat(":").concat(wrapperEntry.getValue().iconName)));
        }
        super.registerIcons(iconRegister);
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubItems(Item item, CreativeTabs tab, List subItems) {
        
        for (Entry<Integer, MetadataWrapper> wrapperEntry : wrappers.entrySet()) {
            
            subItems.add(new ItemStack(this, 1, wrapperEntry.getKey()));
        }
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        MetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.getDisplayName();
        }
        return super.getItemStackDisplayName(itemStack);
    }
    
    public static class MetadataWrapper {
        
        String iconName;
        IIcon icon;
        String ign;
        int metadata;
        IItemRenderer renderer;
        
        public MetadataWrapper(String ign) {
            this.ign = ign;
            iconName = "";
        }
        
        public MetadataWrapper setIcon(IIcon icon) {
            this.icon = icon;
            return this;
        }
        
        public IIcon getIcon() {
            
            return icon;
        }
        
        public MetadataWrapper setDisplayName(String name) {
            
            ign = name;
            return this;
        }
        
        public String getDisplayName() {
            
            return ign;
        }
        
        public MetadataWrapper setMetadata(int metadata) {
            
            this.metadata = metadata;
            return this;
        }
        
        public int getMetadata() {
            
            return metadata;
        }
        
        public MetadataWrapper setRenderer(IItemRenderer renderer) {
            this.renderer = renderer;
            return this;
        }
        
        public IItemRenderer getRenderer() {
            return renderer;
        }
        
        public MetadataWrapper setIconName(String iconName) {
            this.iconName = iconName;
            return this;
        }
        
        public String getIconName() {
            return iconName;
        }
    }
}
