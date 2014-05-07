package GU;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import GU.blocks.BlockBase;

public class GUItemBlock extends ItemBlock {
    
    public GUItemBlock(Block block) {
        super(block);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String getItemStackDisplayName(ItemStack itemStack) {
        
        if (field_150939_a instanceof BlockBase) {
            
            BlockBase block = (BlockBase) field_150939_a;
            
            return block.getBlockDisplayName(itemStack) == null ? super.getItemStackDisplayName(itemStack) : block.getBlockDisplayName(itemStack);
        }
        return super.getItemStackDisplayName(itemStack);
    }
}
