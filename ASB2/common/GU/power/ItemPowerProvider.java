package GU.power;

import net.minecraft.item.ItemStack;
import ASB2.utils.UtilItemStack;
import GU.api.power.IItemPowerProvider;

public class ItemPowerProvider implements IItemPowerProvider {
    
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

    public boolean usePower(float power, boolean doUse) {

        if(item != null) {

            if(this.getPowerStored() >= power) {

                if(doUse)
                    this.setPowerStored(this.getPowerStored() - power);
                
                return true;
            }                    
        }
        return false;
    }

    public boolean gainPower(float power, boolean doUse) {

        if(item != null) {

            if(this.getPowerMax() - this.getPowerStored() >= power) {

                if(doUse)
                    this.setPowerStored(this.getPowerStored() + power);
                
                return true;
            }                    
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
}
