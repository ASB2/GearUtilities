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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
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
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        
        MetadataWrapper wrapper = wrappers.get(p_149691_2_);
        
        if (wrapper != null) {
            
            return wrapper.getIcon(p_149691_1_);
        }
        return super.getIcon(p_149691_1_, p_149691_2_);
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        
        MetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getClonedDrops(world, x, y, z, metadata, fortune);
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
    
    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        
        MetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getHardness(world, x, y, z);
        }
        return super.getBlockHardness(world, x, y, z);
    }
    
    @Override
    public int getHarvestLevel(int metadata) {
        // TODO Auto-generated method stub
        return super.getHarvestLevel(metadata);
    }
    
    @Override
    public String getHarvestTool(int metadata) {
        // TODO Auto-generated method stub
        return super.getHarvestTool(metadata);
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        // TODO Auto-generated method stub
        return super.getPickBlock(target, world, x, y, z);
    }
    
    public static class MetadataWrapper {
        
        protected String[] iconNames;
        protected IIcon[] icons;
        protected ArrayList<ItemStack> itemStacks;
        protected String ign;
        protected int metadata;
        protected float hardness = 3;
        
        public MetadataWrapper(String[] iconNames) {
            
            this.iconNames = iconNames;
            this.icons = new IIcon[iconNames.length];
        }
        
        public MetadataWrapper() {
            
        }
        
        public MetadataWrapper setIconNames(String[] iconNames) {
            this.iconNames = iconNames;
            this.icons = new IIcon[iconNames.length];
            return this;
        }
        
        public String[] getIconNames() {
            return iconNames;
        }
        
        public MetadataWrapper setIcons(IIcon icon, int side) {
            this.icons[side] = icon;
            return this;
        }
        
        public IIcon getIcon(int side) {
            
            if (icons.length <= side) {
                
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
        
        public ArrayList<ItemStack> getDrops(IBlockAccess world, int x, int y, int z, int metadata, int fortune) {
            
            return itemStacks;
        }
        
        public ArrayList<ItemStack> getClonedDrops(IBlockAccess world, int x, int y, int z, int metadata, int fortune) {
            
            ArrayList<ItemStack> stacks = new ArrayList<ItemStack>();
            
            for (ItemStack stack : itemStacks) {
                
                stacks.add(stack.copy());
            }
            return stacks;
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
        
        public MetadataWrapper setHardness(float hardness) {
            this.hardness = hardness;
            return this;
        }
        
        public float getHardness(IBlockAccess world, int x, int y, int z) {
            
            return hardness;
        }
        
        public TileEntity createNewTileEntity(World var1, int metadata) {
            
            return null;
        }
    }
    
    public static class ItemBlockMetadataBlock extends GUItemBlock {
        
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
