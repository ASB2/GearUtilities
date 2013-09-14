package GU.api.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public abstract class PowerProvider implements IPowerProvider {
    
    protected PowerClass powerClass;

    protected float powerStored;
    protected float powerMax;

    public PowerProvider(float maximumPower, PowerClass powerClass) {

        this.powerClass = powerClass;
        this.powerMax = maximumPower;
    }

    public float getPowerStored() {

        return powerStored;
    }

    public float getPowerMax() {

        return powerMax;
    }

    public PowerClass getPowerClass() {

        return this.powerClass;
    }

    public boolean gainPower(float PowerGained, ForgeDirection direction, boolean doUse) {

        if (this.getPowerMax() - this.getPowerStored() >= PowerGained) {

            if(doUse)
                this.setPowerStored(this.getPowerStored() + PowerGained);

            return true;
        }
        return false;
    }

    public boolean usePower(float PowerUsed, ForgeDirection direction, boolean doUse) {

        if (this.getPowerStored() >= PowerUsed) {

            if(doUse)
            this.setPowerStored(this.getPowerStored() - PowerUsed);

            return true;
        }
        return false;
    }

    public void setPowerStored(float newPower) {

        this.powerStored = newPower;
    }

    public void setPowerMax(float newMaxPower) {

        this.powerMax = newMaxPower;
    }

    public void readFromNBT(NBTTagCompound tagCompound) {

        powerStored = tagCompound.getFloat("powerStored");
        powerMax = tagCompound.getFloat("powerMax");
    }

    public void writeToNBT(NBTTagCompound tagCompound) {

        tagCompound.setFloat("powerStored", powerStored);
        tagCompound.setFloat("powerMax", powerMax);
    }
}
