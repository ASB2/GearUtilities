package GU.blocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import GU.GUItemBlock;
import GU.info.Reference;

public class BlockMetadata extends BlockBase {
    
    protected Map<Integer, BlockMetadataWrapper> wrappers;
    private int lastMetadata;
    
    public BlockMetadata(Material material) {
        super(material);
        wrappers = new HashMap<Integer, BlockMetadataWrapper>();
    }
    
    public BlockMetadata addWrapper(int metadata, BlockMetadataWrapper wrapper) {
        wrappers.put(metadata, wrapper);
        wrapper.setMetadata(metadata);
        wrapper.setBlock(this);
        return this;
    }
    
    public BlockMetadata addWrapper(BlockMetadataWrapper wrapper) {
        
        addWrapper(lastMetadata, wrapper);
        lastMetadata++;
        return this;
    }
    
    public Map<Integer, BlockMetadataWrapper> getWrappers() {
        return wrappers;
    }
    
    @Override
    public void registerBlock(String entry) {
        
        GameRegistry.registerBlock(this, ItemBlockMetadataBlock.class, entry);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconRegister) {
        
        for (Entry<Integer, BlockMetadataWrapper> wrapperEntry : wrappers.entrySet()) {
            
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
        
        BlockMetadataWrapper wrapper = wrappers.get(p_149691_2_);
        
        if (wrapper != null) {
            
            return wrapper.getIcon(p_149691_1_);
        }
        return super.getIcon(p_149691_1_, p_149691_2_);
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        
        BlockMetadataWrapper wrapper = wrappers.get(metadata);
        
        if (wrapper != null) {
            
            return wrapper.getClonedDrops(world, x, y, z, metadata, fortune);
        }
        return super.getDrops(world, x, y, z, metadata, fortune);
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems) {
        
        for (Entry<Integer, BlockMetadataWrapper> wrapperEntry : wrappers.entrySet()) {
            
            subItems.add(new ItemStack(this, 1, wrapperEntry.getKey()));
            wrapperEntry.getValue().getSubBlocks(unknown, tab, subItems);
        }
    }
    
    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getHardness(world, x, y, z);
        }
        return super.getBlockHardness(world, x, y, z);
    }
    
    @Override
    public int getHarvestLevel(int metadata) {
        
        BlockMetadataWrapper wrapper = wrappers.get(metadata);
        
        if (wrapper != null) {
            
            return wrapper.getHarvestLevel();
        }
        return super.getHarvestLevel(metadata);
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getPickBlock(target, world, x, y, z);
        }
        return super.getPickBlock(target, world, x, y, z);
    }
    
    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.onBlockActivated(world, x, y, z, player, side, xHit, yHit, zHit);
        }
        return super.onBlockActivated(world, x, y, z, player, side, xHit, yHit, zHit);
    }
    
    @Override
    public void onBlockAdded(World world, int x, int y, int z) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            wrapper.onBlockAdded(world, x, y, z);
        } else {
            
            super.onBlockAdded(world, x, y, z);
        }
    }
    
    @Override
    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            wrapper.onBlockClicked(world, x, y, z, player);
        } else {
            
            super.onBlockClicked(world, x, y, z, player);
        }
    }
    
    @Override
    public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getLightOpacity(world, x, y, z);
        }
        return super.getLightOpacity(world, x, y, z);
    }
    
    @Override
    public int getLightValue(IBlockAccess world, int x, int y, int z) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getLightValue(world, x, y, z);
        }
        return super.getLightValue(world, x, y, z);
    }
    
    @Override
    public float getEnchantPowerBonus(World world, int x, int y, int z) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getEnchantPowerBonus(world, x, y, z);
        }
        return super.getEnchantPowerBonus(world, x, y, z);
    }
    
    @Override
    public boolean getBlocksMovement(IBlockAccess world, int x, int y, int z) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getBlocksMovement(world, x, y, z);
        }
        return super.getBlocksMovement(world, x, y, z);
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getCollisionBoundingBoxFromPool(world, x, y, z);
        }
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }
    
    @Override
    public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
        
        BlockMetadataWrapper wrapper = wrappers.get(metadata);
        
        if (wrapper != null) {
            
            return wrapper.getExpDrop(world, metadata, fortune);
        }
        return super.getExpDrop(world, metadata, fortune);
    }
    
    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
        }
        return super.getExplosionResistance(entity, world, x, y, z, explosionX, explosionY, explosionZ);
    }
    
    @Override
    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.canConnectRedstone(world, x, y, z, side);
        }
        return super.canConnectRedstone(world, x, y, z, side);
    }
    
    @Override
    public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.getComparatorInputOverride(world, x, y, z, side);
        }
        return super.getComparatorInputOverride(world, x, y, z, side);
    }
    
    @Override
    public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.isProvidingStrongPower(world, x, y, z, side);
        }
        return super.isProvidingStrongPower(world, x, y, z, side);
    }
    
    @Override
    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        
        BlockMetadataWrapper wrapper = wrappers.get(world.getBlockMetadata(x, y, z));
        
        if (wrapper != null) {
            
            return wrapper.isProvidingWeakPower(world, x, y, z, side);
        }
        return super.isProvidingWeakPower(world, x, y, z, side);
    }
    
    // @Override
    // public boolean shouldCheckWeakPower(IBlockAccess world, int x, int y, int
    // z, int side) {
    // // TODO Auto-generated method stub
    // return super.shouldCheckWeakPower(world, x, y, z, side);
    // }
    
    public static class BlockMetadataWrapper {
        
        protected String[] iconNames;
        protected IIcon[] icons;
        protected ArrayList<ItemStack> itemStacks;
        protected String ign;
        protected int metadata;
        protected float hardness = 3;
        protected int harvestLevel;
        protected String harvestTool;
        protected ItemStack pickBlock;
        BlockMetadata block;
        
        public BlockMetadataWrapper(String[] iconNames) {
            this();
            this.iconNames = iconNames;
            this.icons = new IIcon[iconNames.length];
        }
        
        public BlockMetadataWrapper() {
            
        }
        
        public BlockMetadataWrapper setBlock(BlockMetadata block) {
            this.block = block;
            return this;
        }
        
        public BlockMetadata getBlock() {
            return block;
        }
        
        public BlockMetadataWrapper setIconNames(String[] iconNames) {
            this.iconNames = iconNames;
            this.icons = new IIcon[iconNames.length];
            return this;
        }
        
        public String[] getIconNames() {
            return iconNames;
        }
        
        public BlockMetadataWrapper setIcons(IIcon icon, int side) {
            this.icons[side] = icon;
            return this;
        }
        
        public IIcon getIcon(int side) {
            
            if (icons.length <= side) {
                
                return icons[0];
            }
            return icons[side];
        }
        
        public BlockMetadataWrapper addDrop(ItemStack stack) {
            
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
            
            if (itemStacks != null && !itemStacks.isEmpty()) {
                
                for (ItemStack stack : itemStacks) {
                    
                    stacks.add(stack.copy());
                }
            }
            return stacks;
        }
        
        @SuppressWarnings({ "rawtypes" })
        public void getSubBlocks(Item unknown, CreativeTabs tab, List subItems) {
        }
        
        public BlockMetadataWrapper setDisplayName(String name) {
            
            ign = name;
            return this;
        }
        
        public String getDisplayName() {
            
            return ign;
        }
        
        public BlockMetadataWrapper setMetadata(int metadata) {
            
            this.metadata = metadata;
            return this;
        }
        
        public int getMetadata() {
            
            return metadata;
        }
        
        public BlockMetadataWrapper setHardness(float hardness) {
            this.hardness = hardness;
            return this;
        }
        
        public float getHardness(IBlockAccess world, int x, int y, int z) {
            
            return hardness;
        }
        
        public TileEntity createNewTileEntity(World var1, int metadata) {
            
            return null;
        }
        
        public int getHarvestLevel() {
            
            return harvestLevel;
        }
        
        public BlockMetadataWrapper setPickBlock(ItemStack pickBlock) {
            this.pickBlock = pickBlock;
            return this;
        }
        
        public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z) {
            
            if (pickBlock == null) {
                
                pickBlock = new ItemStack(world.getBlock(x, y, z), 1, world.getBlockMetadata(x, y, z));
            }
            return pickBlock;
        }
        
        public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float xHit, float yHit, float zHit) {
            
            return false;
        }
        
        public void onBlockAdded(World world, int x, int y, int z) {
            
        }
        
        public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
            
        }
        
        public int getLightOpacity(IBlockAccess world, int x, int y, int z) {
            
            return 0;
        }
        
        public int getLightValue(IBlockAccess world, int x, int y, int z) {
            
            return 0;
        }
        
        public float getEnchantPowerBonus(World world, int x, int y, int z) {
            
            return 0;
        }
        
        public boolean getBlocksMovement(IBlockAccess world, int x, int y, int z) {
            
            return world.getTileEntity(x, y, z) == null;
        }
        
        public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
            
            return AxisAlignedBB.getBoundingBox((double) x + this.getBlock().minX, (double) y + this.getBlock().minY, (double) z + this.getBlock().minZ, (double) x + this.getBlock().maxX, (double) y + this.getBlock().maxY, (double) z + this.getBlock().maxZ);
        }
        
        public int getExpDrop(IBlockAccess world, int metadata, int fortune) {
            
            return 0;
        }
        
        public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ) {
            
            return this.getBlock().blockHardness / 5f;
        }
        
        public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side) {
            
            return false;
        }
        
        public int getComparatorInputOverride(World world, int x, int y, int z, int side) {
            
            return 0;
        }
        
        public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
            
            return 0;
        }
        
        public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
            
            return 0;
        }
        
        // public boolean shouldCheckWeakPower(IBlockAccess world, int x, int y,
        // int z, int side) {
        //
        // return
        // }
    }
    
    public static class ItemBlockMetadataBlock extends GUItemBlock {
        
        BlockMetadata castedBlock;
        
        public ItemBlockMetadataBlock(Block block) {
            super(block);
            
            if (block instanceof BlockMetadata) {
                
                castedBlock = (BlockMetadata) block;
            } else {
                
                throw new IllegalStateException("ItemBlockMetadataBlock takes BlockMetadata as a paramater. Something else was passed");
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
            
            BlockMetadataWrapper wrapper = castedBlock.wrappers.get(new Integer(itemStack.getItemDamage()));
            
            return wrapper != null ? wrapper.getDisplayName() : "This metadata is broken contact ASB2";
        }
    }
}
