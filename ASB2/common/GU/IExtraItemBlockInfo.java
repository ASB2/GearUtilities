package GU;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IExtraItemBlockInfo {
    
    @SuppressWarnings("rawtypes")
    void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1);
    String getUnlocalizedName(ItemStack itemStack);
    String getItemStackDisplayName(ItemStack itemStack);
}
