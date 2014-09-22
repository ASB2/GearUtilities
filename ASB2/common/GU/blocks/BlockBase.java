package GU.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import GU.GUItemBlock;
import GU.GearUtilities;
import GU.ItemRegistry;
import GU.blocks.containers.TileBase;
import GU.render.BlockSimpleRenderer;
import GU.render.noise.NoiseManager;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

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
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
        
        par3List.add("Made just for you: ".concat(player.getDisplayName()));
    }
    
    public String getBlockDisplayName(ItemStack stack) {
        
        return null;
    }
    
    @Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z) {
        // TODO Auto-generated method stub
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
    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis) {
        
        TileBase tile = (TileBase) world.getTileEntity(x, y, z);
        
        if (tile != null) {
            
            return tile.rotateBlock(world, x, y, z, axis);
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
    public int getHarvestLevel(int metadata) {
        
        return 3;
    }
    
    @Override
    public float getBlockHardness(World world, int x, int y, int z) {
        
        return 1;
    }
    
    @Override
    public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int metadata, int fortune) {
        
        ArrayList<ItemStack> ret = new ArrayList<ItemStack>();
        
        ret.add(new ItemStack(this, 1, 0));
        return ret;
    }
    
    @Override
    public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
        
        return NoiseManager.instance.blockNoiseIcon;
    }
}
