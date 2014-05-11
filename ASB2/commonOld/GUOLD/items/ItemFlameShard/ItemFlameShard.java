package GUOLD.items.ItemFlameShard;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import GUOLD.api.flame.EnumFlameType;
import GUOLD.items.ItemBase;

public class ItemFlameShard extends ItemBase {
    
    public ItemFlameShard() {
        // TODO Auto-generated constructor stub
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List list) {
        
        ItemStack stack = new ItemStack(this);
        
        for (EnumFlameType type : EnumFlameType.values()) {
            
            UtilItemStack.setNBTTagInt(stack, "Flame", type.ordinal());
            list.add(stack.copy());
        }
    }
    
    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        super.addInformationSneaking(itemStack, player, info, var1);
        
        info.add("Flame Type: " + EnumFlameType.values()[UtilItemStack.getNBTTagInt(itemStack, "Flame")].getIGN());
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz) {
        
        int flame = UtilItemStack.getNBTTagInt(itemStack, "Flame");
        
        if (flame < EnumFlameType.values().length - 1) {
            
            UtilItemStack.setNBTTagInt(itemStack, "Flame", flame + 1);
        }
        else {
            
            UtilItemStack.setNBTTagInt(itemStack, "Flame", EnumFlameType.STORM.ordinal());
        }
        return true;
    }
}
