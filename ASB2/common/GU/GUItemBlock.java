package GU;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import GU.info.Reference;

public class GUItemBlock extends ItemBlock {
    
    public GUItemBlock(int id) {
        super(id);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean unknown) {
        
        info.add("From: " + Reference.NAME);
    }
}
