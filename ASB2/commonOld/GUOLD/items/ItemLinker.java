package GUOLD.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilEntity;

public class ItemLinker extends ItemBase {
    
    public ItemLinker() {
        setMaxStackSize(1);
    }
    
    @Override
    public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int hitX, float hitY, float hitZ, float par10) {
        
        if (!this.isCoodsSet(itemStack)) {
            
            this.setXCoord(itemStack, x);
            this.setYCoord(itemStack, y);
            this.setZCoord(itemStack, z);
            this.setDimentionIDCoord(itemStack, player.dimension);
            this.setCoodsSet(itemStack, true);
            
            UtilEntity.sendChatToPlayer(player, "Coordinates set to X: " + x + " Y: " + y + " Z: " + z);
        }
        
        return !this.isCoodsSet(itemStack);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformationSneaking(ItemStack itemStack, EntityPlayer player, java.util.List info, boolean var1) {
        
        if (this.isCoodsSet(itemStack)) {
            
            info.add("X: " + this.getXCoord(itemStack) + " Y: " + this.getYCoord(itemStack) + " Z: " + this.getXCoord(itemStack));
            info.add("Dimention " + this.getDimentionIDCoord(itemStack));
            
        }
        else {
            info.add("Coordinates not set.");
        }
    }
    
    public int getDimentionIDCoord(ItemStack itemStack) {
        
        return UtilItemStack.getNBTTagInt(itemStack, "dimentionID");
    }
    
    public void setDimentionIDCoord(ItemStack item, int x) {
        
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setInteger("dimentionID", x);
    }
    
    public boolean isCoodsSet(ItemStack item) {
        
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        if (nbtTagCompound != null) {
            return nbtTagCompound.getBoolean("coodsSet");
        }
        return false;
    }
    
    public void setCoodsSet(ItemStack item, boolean coodsSet) {
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setBoolean("coodsSet", coodsSet);
    }
    
    public double getXCoord(ItemStack item) {
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        if (nbtTagCompound != null) {
            return nbtTagCompound.getDouble("X");
        }
        return 0;
    }
    
    public void setXCoord(ItemStack item, double x) {
        
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setDouble("X", x);
    }
    
    public double getYCoord(ItemStack item) {
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        if (nbtTagCompound != null) {
            return nbtTagCompound.getDouble("Y");
        }
        return 0;
    }
    
    public void setYCoord(ItemStack item, double y) {
        
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setDouble("Y", y);
    }
    
    public double getZCoord(ItemStack item) {
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        if (nbtTagCompound != null) {
            return nbtTagCompound.getDouble("Z");
        }
        return 0;
    }
    
    public void setZCoord(ItemStack item, double z) {
        
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        nbtTagCompound.setDouble("Z", z);
    }
    
}
