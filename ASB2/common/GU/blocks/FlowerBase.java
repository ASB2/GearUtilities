package GU.blocks;

import net.minecraft.block.BlockFlower;
import GU.GearUtilities;

public class FlowerBase extends BlockFlower {
    
    String texture;
    
    public FlowerBase(int id) {
        super(id);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
    }
}
