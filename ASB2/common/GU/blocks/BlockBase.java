package GU.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving.SpawnPlacementType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import GU.GUItemBlock;
import GU.GearUtilities;
import GU.ItemRegistry;
import GU.blocks.containers.TileBase;
import GU.render.BlockSimpleRenderer;
import GU.render.noise.NoiseManager;

public class BlockBase extends Block {
    
    boolean placeItemStackMetadata = false;
    
    public BlockBase(Material material) {
        super(material);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
    }
    
    public void postInit() {
        
    }
    
    @SideOnly(Side.CLIENT)
    public void postInitRender() {
        
    }
    
    public void registerBlock(String entry) {
        
        GameRegistry.registerBlock(this, GUItemBlock.class, entry);
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
        
        par3List.add("Made just for you: ".concat(player.getDisplayName()));
    }
    
    @Override
    public String getBlockDisplayName(ItemStack stack) {
        
        return null;
    }
    
    @Override
    public boolean canCreatureSpawn(IBlockAccess world, BlockPos pos, SpawnPlacementType type) {
        
        return false;
    }
    
    public boolean getPlaceItemStackMetadata() {
        
        return placeItemStackMetadata;
    }
    
    public void registerTile(Class<? extends TileEntity> tile) {
        
        GameRegistry.registerTileEntity(tile, tile.toString());
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        
        return triggerBlock(world, x, y, z, player, side, p_149727_7_, p_149727_8_, p_149727_9_);
    }
    
    public boolean triggerBlock(World world, int x, int y, int z, EntityPlayer player, int side, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
        
        TileBase tile = (TileBase) world.getTileEntity(x, y, z);
        
        if (tile != null && clickedByWrench(player.inventory.getCurrentItem())) {
            
            return tile.triggerBlock(world, player, x, y, z, ForgeDirection.getOrientation(side));
        }
        return false;
    }
    
    public static boolean clickedByWrench(ItemStack stack) {
        
        if (stack == null) {
            
            return true;
        }
        
        if (stack.getItem() == ItemRegistry.METADATA_ITEM) {
            
            if (stack.getItemDamage() == 5) {
                
                return true;
            }
        }
        
        if (stack.getItem() == Items.stick) {
            
            return true;
        }
        return false;
    }
    
    @Override
    public boolean rotateBlock(World world, BlockPos pos, EnumFacing axis) {
        
        TileBase tile = (TileBase) world.getTileEntity(pos);
        
        if (tile != null) {
            
            return tile.rotateBlock(world, pos, axis);
        }
        return false;
    }
    
    @Override
    public int getRenderType() {
        
        return BlockSimpleRenderer.renderID;
    }
    
    @SideOnly(Side.CLIENT)
    public int getRenderBlockPass() {
        
        return 0;
    }
    
    @Override
    public boolean renderAsNormalBlock() {
        
        return this.getRenderType() != -1;
    }
    
    @Override
    public boolean isOpaqueCube() {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public int getHarvestLevel(IBlockState state) {
        
        return 3;
    }
    
    @Override
    public float getBlockHardness(World worldIn, BlockPos pos) {
        // TODO Auto-generated method stub
        return 1;
    }
    
    @Override
    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }
    
    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        
        return NoiseManager.instance.blockNoiseIcon;
    }
}
