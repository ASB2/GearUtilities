package GU;

import org.lwjgl.input.Keyboard;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import ASB2.utils.UtilMisc;
import GU.info.Reference;

public class GUItemBlock extends ItemBlock {

    public GUItemBlock(int id) {
        super(id);
        this.setCreativeTab(GearUtilities.tabGUBlocks);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
//        super.addInformation(itemStack, player, info, var1);
        
        info.add("From: " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + Reference.NAME);
        
        if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
            
            this.addInformationSneaking(itemStack, player, info, var1);
        }
        else {

            info.add("Press " + UtilMisc.getColorCode(EnumChatFormatting.GOLD) + "Shift "+ UtilMisc.getColorCode(EnumChatFormatting.GRAY) + "to show more info");
        }
    }
    
    @SuppressWarnings("rawtypes")
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        
    }
    
}
