package GUOLD.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import ASB2.utils.UtilItemStack;
import ASB2.utils.UtilEntity;

public class ItemTeleporter extends ItemBase {
    
    public ItemTeleporter() {
        
        setMaxStackSize(1);
        setMaxDamage(10);
    }
    
    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer player) {
        
        this.setXCoord(par1ItemStack, player.posX);
        this.setYCoord(par1ItemStack, player.posY);
        this.setZCoord(par1ItemStack, player.posZ);
        this.setDimentionIDCoord(par1ItemStack, player.dimension);
        setCoodsSet(par1ItemStack, true);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
        
        if (isCoodsSet(itemStack)) {
            
            if (player instanceof EntityPlayerMP) {
                
                for (int i = 0; i < 2; i++) {
                    
                    if (player.dimension != this.getDimentionIDCoord(itemStack)) {
                        
                        itemStack.damageItem(1, player);
                        player.travelToDimension(this.getDimentionIDCoord(itemStack));
                    }
                    
                    else {
                        
                        itemStack.damageItem(1, player);
                        player.setPositionAndUpdate(this.getXCoord(itemStack), this.getYCoord(itemStack), this.getZCoord(itemStack));
                    }
                }
            }
        }
        
        else {
            
            this.setXCoord(itemStack, player.posX);
            this.setYCoord(itemStack, player.posY);
            this.setZCoord(itemStack, player.posZ);
            this.setDimentionIDCoord(itemStack, player.dimension);
            setCoodsSet(itemStack, true);
            
            UtilEntity.sendChatToPlayer(player, "Link Set");
        }
        
        return itemStack;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformationSneaking(ItemStack par1ItemStack, EntityPlayer player, List info, boolean b) {
        
        if (isCoodsSet(par1ItemStack)) {
            
            info.add("Destination X: " + (int) this.getXCoord(par1ItemStack) + " Y: " + (int) this.getYCoord(par1ItemStack) + " Z: " + (int) this.getZCoord(par1ItemStack));
            info.add("Dimention ID: " + this.getDimentionIDCoord(par1ItemStack));
        }
        
        if (!isCoodsSet(par1ItemStack)) {
            
            info.add("Coords Not Set.");
        }
    }
    
    public int getDimentionIDCoord(ItemStack item) {
        NBTTagCompound nbtTagCompound = UtilItemStack.getTAGfromItemstack(item);
        if (nbtTagCompound != null) {
            return nbtTagCompound.getInteger("dimentionID");
        }
        return 0;
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
