package GU.api.power;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public abstract class ItemPowerProvider {

    public int getPowerStored(ItemStack item) {

        return this.getTAGfromItemstack(item).getInteger("powerStored");
    }

    public void setPowerStored(ItemStack item, int newPower) {

        this.getTAGfromItemstack(item).setInteger("powerStored", newPower);
    }

    public int getPowerMax(ItemStack item) {

        return this.getTAGfromItemstack(item).getInteger("powerMax");
    }

    public boolean usePower(ItemStack item, int power, boolean doUse) {

        if(item != null) {

            if(this.getPowerStored(item) >= power) {

                if(doUse)
                    this.setPowerStored(item, this.getPowerStored(item) - power);
                
                return true;
            }                    
        }
        return false;
    }

    public boolean gainPower(ItemStack item, int power, boolean doUse) {

        if(item != null) {

            if(this.getPowerMax(item) - this.getPowerStored(item) >= power) {

                if(doUse)
                    this.setPowerStored(item, this.getPowerStored(item) + power);
                
                return true;
            }                    
        }
        return false;
    }

    public NBTTagCompound getTAGfromItemstack(ItemStack itemStack) {

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
}
