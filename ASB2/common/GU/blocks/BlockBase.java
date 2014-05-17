package GU.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import GU.GUItemBlock;
import GU.GearUtilities;
import GU.render.BlockSimpleRenderer;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBase extends Block {
    
    boolean placeItemStackMetadata = false;
    
    public BlockBase(Material material) {
        super(material);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
    }
    
    public void postInit() {
        
    }
    
    public void registerBlock(String entry) {
        
        GameRegistry.registerBlock(this, GUItemBlock.class, entry);
    }
    
    @SuppressWarnings("rawtypes")
    public void addInformation(ItemStack stack, EntityPlayer player, List par3List, boolean par4) {
        
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
}
