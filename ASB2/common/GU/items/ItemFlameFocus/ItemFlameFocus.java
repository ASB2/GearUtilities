package GU.items.ItemFlameFocus;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import GU.api.flame.EnumFlameType;
import GU.items.ItemBase;

public class ItemFlameFocus extends ItemBase {

    public ItemFlameFocus(int id) {
        super(id);
        this.setMaxStackSize(1);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List list) {

        ItemStack stack = new ItemStack(this);

        UtilItemStack.setNBTTagInt(stack, "Flame", EnumFlameType.STORM.ordinal());
        list.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "Flame", EnumFlameType.SUN.ordinal());
        list.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "Flame", EnumFlameType.SKY.ordinal());
        list.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "Flame", EnumFlameType.LIGHTNING.ordinal());
        list.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "Flame", EnumFlameType.RAIN.ordinal());
        list.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "Flame", EnumFlameType.CLOUD.ordinal());
        list.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "Flame", EnumFlameType.MIST.ordinal());
        list.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "Flame", EnumFlameType.DARK.ordinal());
        list.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "Flame", EnumFlameType.LIGHT.ordinal());
        list.add(stack.copy());

        UtilItemStack.setNBTTagInt(stack, "Flame", EnumFlameType.UNTYPED.ordinal());
        list.add(stack.copy());
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        super.addInformationSneaking(itemStack, player, info, var1);

        info.add("Flame Type: " + EnumFlameType.values()[UtilItemStack.getNBTTagInt(itemStack, "Flame")]);
    }

    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitx, float hity, float hitz) {

        // if (player.isSneaking()) {
        // if (player.capabilities.isCreativeMode) {
        //
        // this.getPowerProvider(itemStack).setPowerStored(this.getPowerProvider(itemStack).getPowerMax());
        // return true;
        // }
        // }
        return false;
    }
}
