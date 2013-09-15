package GU.api.power;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilItemStack;

public class ItemPowerProvider implements IPowerProvider {

    ItemStack item;

    public ItemPowerProvider(ItemStack stack, float powerMax)  {

        this.item = stack;
        this.setPowerMax(powerMax);
    }

    @Override
    public float getPowerStored() {

        return UtilItemStack.getNBTTagFloat(item, "powerStored");
    }

    @Override
    public float getPowerMax() {

        return UtilItemStack.getNBTTagFloat(item, "powerMax");
    }

    public boolean usePower(float power, ForgeDirection direction, boolean doUse) {

        if(this.getPowerStored() >= power) {

            if(doUse)
                this.setPowerStored(this.getPowerStored() - power);

            return true;
        }
        return false;
    }

    public boolean gainPower(float power, ForgeDirection direction, boolean doUse) {

        if(this.getPowerMax() - this.getPowerStored() >= power) {

            if(doUse)
                this.setPowerStored(this.getPowerStored() + power);

            return true;
        }
        return false;
    }

    @Override
    public void setPowerStored(float newPower) {

        UtilItemStack.setNBTTagFloat(item, "powerStored", newPower);
    }

    @Override
    public void setPowerMax(float newMaxPower) {

        UtilItemStack.setNBTTagFloat(item, "powerMax", newMaxPower);
    }

    @Override
    public PowerClass getPowerClass() {

        return PowerClass.OTHER;
    }

    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        // TODO Auto-generated method stub
        
    }
}
