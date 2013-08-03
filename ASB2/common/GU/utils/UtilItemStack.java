package GU.utils;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class UtilItemStack {

    public static NBTTagCompound getTAGfromItemstack(ItemStack itemStack) {

        if (itemStack != null) {

            NBTTagCompound tag = itemStack.getTagCompound();

            if (tag == null) {

                tag = new NBTTagCompound();
                itemStack.setTagCompound(tag);
            }
            return tag;
        }
        return null;
    }

    public static void setNBTTagInt(ItemStack itemStack, String tag, int value) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(itemStack);
        nbtTagCompound.setInteger(tag, value);
    }

    public static int getNBTTagInt(ItemStack itemStack, String tag) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(itemStack);

        if(nbtTagCompound != null) {
            
            return nbtTagCompound.getInteger(tag);
        }
        return 0;
    }
    
    public static void setNBTTagDouble(ItemStack itemStack, String tag, int value) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(itemStack);
        nbtTagCompound.setDouble(tag, value);
    }

    public static double getNBTTagDouble(ItemStack itemStack, String tag) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(itemStack);

        if(nbtTagCompound != null) {
            
            return nbtTagCompound.getDouble(tag);
        }
        return 0;
    }
}


