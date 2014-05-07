package GU.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import GU.GearUtilities;
import cpw.mods.fml.common.registry.GameRegistry;

public class BlockBase extends Block {
    
    public BlockBase(Material material) {
        super(material);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
    }
    
    public void registerBlock(String entry) {
        
        GameRegistry.registerBlock(this, entry);
    }
    
    public String getBlockDisplayName(ItemStack stack) {
        
        return null;
    }
}
