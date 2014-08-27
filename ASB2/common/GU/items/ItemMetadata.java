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
import GU.info.Reference;

public class ItemMetadata extends ItemBase {
    
    public Map<Integer, ItemMetadataWrapper> wrappers;
    private int lastMetadata;
    
    public ItemMetadata() {
        
        wrappers = new HashMap<Integer, ItemMetadataWrapper>();
        
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }
    
    public ItemMetadata addWrapper(int metadata, ItemMetadataWrapper wrapper) {
        wrappers.put(metadata, wrapper);
        wrapper.setMetadata(metadata);
        wrapper.setItem(this);
        return this;
    }
    
    public ItemMetadata addWrapper(ItemMetadataWrapper wrapper) {
        
        addWrapper(lastMetadata, wrapper);
        lastMetadata++;
        return this;
    }
    
    @Override
    public void postInit() {
        
        for (Entry<Integer, ItemMetadataWrapper> entry : wrappers.entrySet()) {
            
            entry.getValue().postInit();
        }
    }
    
    public void postInitRender() {
        
        MinecraftForgeClient.registerItemRenderer(this, ItemMetadataRenderer.instance);
        
        for (Entry<Integer, ItemMetadataWrapper> entry : wrappers.entrySet()) {
            
            entry.getValue().postInitRender();
        }
    }
    
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        
        for (Entry<Integer, ItemMetadataWrapper> wrapperEntry : wrappers.entrySet()) {
            
            wrapperEntry.getValue().setIcon(iconRegister.registerIcon(Reference.MOD_ID.concat(":").concat(wrapperEntry.getValue().iconName)));
        }
        super.registerIcons(iconRegister);
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubItems(Item item, CreativeTabs tab, List subItems) {
        
        for (Entry<Integer, ItemMetadataWrapper> wrapperEntry : wrappers.entrySet()) {
            
            subItems.add(new ItemStack(this, 1, wrapperEntry.getKey()));
            wrapperEntry.getValue().getSubItems(item, tab, subItems);
        }
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        ItemMetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.getDisplayName();
        }
        return super.getItemStackDisplayName(itemStack);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.add("Made just for you: ".concat(par2EntityPlayer.getDisplayName()));
        
        ItemMetadataWrapper wrapper = wrappers.get(par1ItemStack.getItemDamage());
        
        if (wrapper != null) {
            
            wrapper.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        }
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
    }
    
    @Override
    public boolean canHarvestBlock(Block par1Block, ItemStack itemStack) {
        
        ItemMetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.canHarvestBlock(par1Block, itemStack);
        }
        return super.canHarvestBlock(par1Block, itemStack);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        
        ItemMetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.onItemRightClick(itemStack, world, player);
        }
        return super.onItemRightClick(itemStack, world, player);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        
        ItemMetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
        }
        return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
    
    @Override
    public boolean doesContainerItemLeaveCraftingGrid(ItemStack itemStack) {
        
        ItemMetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.doesContainerItemLeaveCraftingGrid(itemStack);
        }
        return super.doesContainerItemLeaveCraftingGrid(itemStack);
    }
    
    @Override
    public boolean doesSneakBypassUse(World world, int x, int y, int z, EntityPlayer player) {
        
        ItemMetadataWrapper wrapper = wrappers.get(player.getHeldItem().getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.doesSneakBypassUse(world, x, y, z, player);
        }
        return super.doesSneakBypassUse(world, x, y, z, player);
    }
    
    @Override
    public int getItemStackLimit(ItemStack stack) {
        
        ItemMetadataWrapper wrapper = wrappers.get(stack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.getItemStackLimit(stack);
        }
        return super.getItemStackLimit(stack);
    }
    
    @Override
    public IIcon getIconIndex(ItemStack itemStack) {
        
        ItemMetadataWrapper wrapper = wrappers.get(itemStack.getItemDamage());
        
        if (wrapper != null) {
            
            return wrapper.getIconIndex(itemStack);
        }
        return super.getIconIndex(itemStack);
    }
    
    public static class ItemMetadataWrapper {
        
        String iconName;
        IIcon icon;
        String ign;
        int metadata;
        IItemRenderer renderer;
        ItemMetadata item;
        
        public ItemMetadataWrapper(String ign) {
            this.ign = ign;
            iconName = "";
        }
        
        public ItemMetadataWrapper() {
            // TODO Auto-generated constructor stub
        }
        
        public void postInit() {
            
        }
        
        public void postInitRender() {
            
        }
        
        public ItemMetadataWrapper setItem(ItemMetadata item) {
            this.item = item;
            return this;
        }
        
        public ItemMetadata getItem() {
            return item;
        }
        
        public ItemMetadataWrapper setIcon(IIcon icon) {
            this.icon = icon;
            return this;
        }
        
        public IIcon getIcon() {
            
            return icon;
        }
        
        public ItemMetadataWrapper setDisplayName(String name) {
            
            ign = name;
            return this;
        }
        
        public String getDisplayName() {
            
            return ign;
        }
        
        public ItemMetadataWrapper setMetadata(int metadata) {
            
            this.metadata = metadata;
            return this;
        }
        
        public int getMetadata() {
            
            return metadata;
        }
        
        public ItemMetadataWrapper setRenderer(IItemRenderer renderer) {
            this.renderer = renderer;
            return this;
        }
        
        public IItemRenderer getRenderer() {
            return renderer;
        }
        
        public ItemMetadataWrapper setIconName(String iconName) {
            this.iconName = iconName;
            return this;
        }
        
        public String getIconName() {
            return iconName;
        }
        
        @SuppressWarnings({ "rawtypes" })
        public void getSubItems(Item item, CreativeTabs tab, List subItems) {
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
        
        public int getItemStackLimit(ItemStack stack) {
            
            return 64;
        }
        
        public IIcon getIconIndex(ItemStack itemStack) {
            
            return this.getIcon();
        }
    }
}
