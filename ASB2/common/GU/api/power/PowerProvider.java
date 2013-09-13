package GU.api.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public abstract class PowerProvider implements IPowerProvider {

    protected State currentState;
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

    public State getCurrentState(ForgeDirection direction) {

        if (currentState == null) {

            return State.OTHER;
        }
        return currentState;
    }

    public boolean gainPower(float PowerGained, ForgeDirection direction, boolean doUse) {

        if (this.getPowerMax() - this.getPowerStored() >= PowerGained) {

            if(doUse)
                this.setPower(this.getPowerStored() + PowerGained);

            return true;
        }
        return false;
    }

    public boolean usePower(float PowerUsed, ForgeDirection direction, boolean doUse) {

        if (this.getPowerStored() >= PowerUsed) {

            if(doUse)
            this.setPower(this.getPowerStored() - PowerUsed);

            return true;
        }
        return false;
    }

    public void setPower(float newPower) {

        this.powerStored = newPower;
    }

    public void setMaxPower(float newMaxPower) {

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
