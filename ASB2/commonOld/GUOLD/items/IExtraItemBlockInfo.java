package GUOLD.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IExtraItemBlockInfo {
    
    @SuppressWarnings("rawtypes")
    void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1);
    
    String getUnlocalizedName(ItemStack itemStack);
    
    String getItemStackDisplayName(ItemStack itemStack);
    
    int getPlacedMetadata(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata);
}
