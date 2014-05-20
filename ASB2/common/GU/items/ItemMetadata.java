package GU.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import GU.ItemRegistry;
import GU.info.Reference;

public class ItemMetadata extends ItemBase {
    
    public Map<Integer, MetadataWrapper> wrappers;
    private int lastMetadata;
    
    public ItemMetadata() {
        
        wrappers = new HashMap<Integer, MetadataWrapper>();
        
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }
    
    public ItemMetadata addWrapper(int metadata, MetadataWrapper wrapper) {
        wrappers.put(metadata, wrapper);
        wrapper.setMetadata(metadata);
        wrapper.setItem(this);
        return this;
    }
    
    public ItemMetadata addWrapper(MetadataWrapper wrapper) {
        
        addWrapper(lastMetadata, wrapper);
        lastMetadata++;
        return this;
    }
    
    @Override
    public void postInit() {
        
        MinecraftForgeClient.registerItemRenderer(ItemRegistry.METADATA_ITEM, ItemMetadataRenderer.instance);
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
    
    @SuppressWarnings("rawtypes")
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        
        MetadataWrapper wrapper = wrappers.get(par1ItemStack.getItemDamage());
        
        if (wrapper != null) {
            
            wrapper.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        }
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
    }
    
    @Override
    public boolean canHarvestBlock(Block par1Block, ItemStack itemStack) {
        
        MetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.canHarvestBlock(par1Block, itemStack);
        }
        return super.canHarvestBlock(par1Block, itemStack);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        
        MetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.onItemRightClick(itemStack, world, player);
        }
        return super.onItemRightClick(itemStack, world, player);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        
        MetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }
        return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
    
    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
        
        MetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.doesContainerItemLeaveCraftingGrid(itemStack);
        }
        return super.doesContainerItemLeaveCraftingGrid(itemStack);
    }
    
    @Override
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        
        MetadataWrapper wrapper = wrappers.get(player.getHeldItem().getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.doesSneakBypassUse(world, x, y, z, player);
        }
        return super.doesSneakBypassUse(world, x, y, z, player);
    }
    
    public static class MetadataWrapper {
        
        String iconName;
        IIcon icon;
        String ign;
        int metadata;
        IItemRenderer renderer;
        ItemMetadata item;
        
        public MetadataWrapper(String ign) {
            this.ign = ign;
            iconName = "";
        }
        
        public MetadataWrapper() {
            // TODO Auto-generated constructor stub
        }
        
        public MetadataWrapper setItem(ItemMetadata item) {
            this.item = item;
            return this;
        }
        
        public ItemMetadata getItem() {
            return item;
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
        
        @SuppressWarnings("rawtypes")
        public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
            // TODO Auto-generated method stub
        }
        
        public boolean canHarvestBlock(Block par1Block, ItemStack itemStack) {
            
            return true;
        }
        
        public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
            
            return itemStack;
        }
        
        public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
            
            return false;
        }
        
        public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
            
            return true;
        }
        
        public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
            
            return false;
        }
    }
}
