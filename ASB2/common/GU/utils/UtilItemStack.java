package GU.utils;

import java.util.ArrayList;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class UtilItemStack {
    
    public static boolean damageItem(EntityLivingBase entity, ItemStack item, int damage) {

        if (!item.isItemStackDamageable())
            return false;

        if (entity instanceof EntityPlayer && ((EntityPlayer) entity).capabilities.isCreativeMode)
            return true;

        if (item.getMaxDamage() - item.getItemDamage() >= damage) {

            item.setItemDamage(item.getItemDamage() + damage);
            return true;
        } else {

            if (item.getMaxDamage() - item.getItemDamage() == 0) {

                --item.stackSize;

                if (item.stackSize < 0) {

                    item.stackSize = 0;
                }
            }
        }
        return false;
    }

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

        if (nbtTagCompound != null) {

            return nbtTagCompound.getInteger(tag);
        }
        return 0;
    }

    public static void setNBTTagDouble(ItemStack itemStack, String tag,
            int value) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(itemStack);
        nbtTagCompound.setDouble(tag, value);
    }

    public static double getNBTTagDouble(ItemStack itemStack, String tag) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(itemStack);

        if (nbtTagCompound != null) {

            return nbtTagCompound.getDouble(tag);
        }
        return 0;
    }
    
    public static void setNBTTagBoolean(ItemStack itemStack, String tag, boolean value) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(itemStack);
        nbtTagCompound.setBoolean(tag, value);
    }

    public static boolean getNBTTagBoolean(ItemStack itemStack, String tag) {

        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(itemStack);

        if (nbtTagCompound != null) {

            return nbtTagCompound.getBoolean(tag);
        }
        return false;
    }
    
    public static void setNBTTagInventory(ItemStack itemStack, String tag, ArrayList<ItemStack> inventory) {

        NBTTagCompound compound = UtilItemStack.getTAGfromItemstack(itemStack);
        NBTTagList nbttaglist = new NBTTagList();
        
        for (int i = 0; i < inventory.size(); i++) {
            
            if (inventory.get(i) != null) {
                
                NBTTagCompound nbttagcompound = new NBTTagCompound();
                inventory.get(i).writeToNBT(nbttagcompound);
                nbttaglist.appendTag(nbttagcompound);
            }
        }
        compound.setTag(tag, nbttaglist);
    }

    public static ArrayList<ItemStack> getNBTTagInventory(ItemStack itemStack, String tag) {

        ArrayList<ItemStack> itemList = new ArrayList<ItemStack>();
        
        NBTTagCompound compound = UtilItemStack.getTAGfromItemstack(itemStack);

        if (compound != null) {

            NBTTagList nbttaglist = compound.getTagList(tag);
            
            for(int pos = 0; pos < nbttaglist.tagCount(); pos++) {
                
                NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.tagAt(pos);
                
                itemList.add(ItemStack.loadItemStackFromNBT(nbttagcompound));
            }
        }
        return itemList;
    }
}
