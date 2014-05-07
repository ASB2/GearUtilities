package GU.blocks;

import cpw.mods.fml.common.registry.GameRegistry;
import GU.GearUtilities;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {
    
    public BlockBase(Material material) {
        super(material);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
    }
    
    public void registerBlock(String entry) {
        
        GameRegistry.registerBlock(this, entry);
    }
}
