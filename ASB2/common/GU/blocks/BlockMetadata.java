package GU.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import GU.GUItemBlock;
import GU.info.Reference;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockMetadata extends BlockBase {
    
    public Map<Integer, MetadataWrapper> wrappers;
    
    public BlockMetadata(Material material) {
        super(material);
        wrappers = new HashMap<Integer, MetadataWrapper>();
        postInit();
    }
    
    public void postInit() {
        
    }
    
    @Override
    public void registerBlock(String entry) {
        
        GameRegistry.registerBlock(this, ItemBlockMetadataBlock.class, entry);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        
        for (Entry<Integer, MetadataWrapper> wrapperEntry : wrappers.entrySet()) {
            
            int currentSide = 0;
            
            for (String icon : wrapperEntry.getValue().iconNames) {
                
                wrapperEntry.getValue().setIcons(iconRegister.registerIcon(Reference.MOD_ID.concat(":").concat(icon)), currentSide);
                currentSide++;
            }
        }
        super.registerBlockIcons(iconRegister);
    }
    
    @Override
    public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side) {
        
        MetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getIcon(side);
        }
        return super.getIcon(world, x, y, z, side);
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        
        MetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return (ArrayList<ItemStack>) wrapper.getDrops();
        }
        return super.getDrops(world, x, y, z, metadata, fortune);
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems) {
        
        for (Entry<Integer, MetadataWrapper> wrapperEntry : wrappers.entrySet()) {
            
            subItems.add(new ItemStack(this, 1, wrapperEntry.getKey()));
        }
    }
    
    public static class MetadataWrapper {
        
        String[] iconNames;
        IIcon[] icons;
        List<ItemStack> itemStacks;
        String ign;
        
        public MetadataWrapper(String[] icons) {
            
            iconNames = icons;
            this.icons = new IIcon[icons.length];
        }
        
        public MetadataWrapper setIcons(IIcon icon, int side) {
            this.icons[side] = icon;
            return this;
        }
        
        public IIcon getIcon(int side) {
            
            if (icons.length >= side) {
                
                return icons[0];
            }
            return icons[side];
        }
        
        public MetadataWrapper addDrop(ItemStack stack) {
            
            if (itemStacks == null) {
                
                itemStacks = new ArrayList<ItemStack>();
            }
            
            itemStacks.add(stack);
            return this;
        }
        
        public List<ItemStack> getDrops() {
            
            return itemStacks;
        }
        
        public MetadataWrapper setDisplayName(String name) {
            
            ign = name;
            return this;
        }
        
        public String getDisplayName() {
            
            return ign;
        }
    }
    
    public class ItemBlockMetadataBlock extends GUItemBlock {
        
        BlockMetadata castedBlock;
        
        public ItemBlockMetadataBlock(Block block) {
            super(block);
            
            if (block instanceof BlockMetadata) {
                
                castedBlock = (BlockMetadata) block;
            }
            else {
                
                throw new IllegalStateException("ItemBlockMetadataBlock on takes BlockMetadata as a paramater. Something else was passed");
            }
            this.setHasSubtypes(true);
            this.setMaxDamage(0);
        }
        
        @Override
        public int getMetadata(int damageValue) {
            
            return damageValue;
        }
        
        @Override
        public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
            
            boolean itWorked = super.placeBlockAt(stack, player, world, x, y, z, side, hitX, hitY, hitZ, metadata);
            
            world.setBlockMetadataWithNotify(x, y, z, this.getDamage(stack), 3);
            return itWorked;
        }
        
        @Override
        public String getItemStackDisplayName(ItemStack itemStack) {
            
            MetadataWrapper wrapper = castedBlock.wrappers.get(new Integer(itemStack.getItemDamage()));
            
            return wrapper != null ? wrapper.getDisplayName() : "This metadata is broken contact ASB2";
        }
    }
}
