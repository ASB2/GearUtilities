package GU.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import GU.GUItemBlock;
import GU.GearUtilities;
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
    
    public boolean getPlaceItemStackMetadata() {
        
        return placeItemStackMetadata;
    }
    
    public void registerTile(Class<? extends TileEntity> tile) {
        
        GameRegistry.registerTileEntity(tile, tile.toString());
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
        // TODO Auto-generated method stub
        return NoiseManager.instance.blockNoiseIcon;
    }
}
