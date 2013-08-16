package GU.api.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public abstract class PowerProvider {

    NBTTagCompound ntbTag = new NBTTagCompound();

    protected State currentState;
    protected PowerClass powerClass;

    protected float powerStored = 0;
    protected float powerMax;

    public PowerProvider(int maximumPower, PowerClass powerClass) {

        this.powerClass = powerClass;
        this.powerMax = maximumPower;
    }

    public void updateProvider() {

        
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

    public State getCurrentState() {

        if(currentState == null) {

            if(this.outputtingPower()) {

                currentState = State.SOURCE;
            }

            else if(this.requestingPower()) {

                currentState = State.SINK;
            }

            else {

                currentState = State.OTHER;
            }
        }

        if(this.outputtingPower()) {

            currentState = State.SOURCE;
        }

        else if(this.requestingPower()) {

            currentState = State.SINK;
        }

        else {

            currentState = State.OTHER;
        }
        return currentState;
    }

    public boolean gainPower(float PowerGained, ForgeDirection direction) {

        if(this.getPowerMax() - this.getPowerStored() >= PowerGained) {

            this.setPower(this.getPowerStored() + PowerGained);

            return true;
        }
        return false;
    }

    public boolean usePower(float PowerUsed, ForgeDirection direction) {

        if(this.getPowerStored() >= PowerUsed) {

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

    public boolean requestingPower() {

        if(getPowerStored() < getPowerMax())
            return true;

        return false;
    }

    public boolean outputtingPower() {

        if(getPowerStored() > 0)
            return true;

        return false;
    }

    public boolean canGainPower(float power, ForgeDirection direction) {

        if(this.getPowerMax() - this.getPowerStored() >= power) {

            return true;
        }
        return false; 
    }

    public boolean canUsePower(float power, ForgeDirection direction) {

        if(this.getPowerStored() >= power) {

            return true;
        }
        return false;
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
