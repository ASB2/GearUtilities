package GU.items.ItemFocusGem;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.items.ItemBase;

public class ItemFocusGem extends ItemBase {
    
    public ItemFocusGem(int id) {
        super(id);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        // TODO Auto-generated method stub
        return super.onItemRightClick(par1ItemStack, par2World, par3EntityPlayer);
    }
    
    @Override
    public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10) {
        // TODO Auto-generated method stub
        return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10);
    }
}
