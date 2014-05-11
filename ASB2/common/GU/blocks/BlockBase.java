package GU.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import GU.GUItemBlock;
import GU.GearUtilities;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBase extends Block {
    
    public BlockBase(Material material) {
        super(material);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
    }
    
    public void postInit() {
        
    }
    
    public void registerBlock(String entry) {
        
        GameRegistry.registerBlock(this, GUItemBlock.class, entry);
    }
    
    public String getBlockDisplayName(ItemStack stack) {
        
        return null;
    }
    
    public void registerTile(Class<? extends TileEntity> tile) {
        
        GameRegistry.registerTileEntity(tile, tile.toString());
    }
}
