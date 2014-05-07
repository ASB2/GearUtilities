package GU.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemBase extends Item {
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        // TODO Auto-generated method stub
        return super.onItemRightClick(itemStack, world, player);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        // TODO Auto-generated method stub
        return super.onItemUse(itemStack, player, world, x, y, z, side, hitX, hitY, hitZ);
    }
}
