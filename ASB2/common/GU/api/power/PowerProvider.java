package GU.api.power;

import net.minecraft.nbt.NBTTagCompound;

public abstract class PowerProvider {

    NBTTagCompound ntbTag = new NBTTagCompound();

    protected State currentState;
    protected PowerClass powerClass;

    protected int powerStored = 0;
    protected int powerMax;

    public PowerProvider(int maximumPower, PowerClass powerClass) {

        this.powerClass = powerClass;
        this.powerMax = maximumPower;
    }

    public void updateProvider() {

        
    }

    public int getPowerStored() {

        return powerStored;
    }

    public int getPowerMax() {

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

    public boolean gainPower(int PowerGained) {

        if(this.getPowerMax() - this.getPowerStored() >= PowerGained) {

            this.setPower(this.getPowerStored() + PowerGained);

            return true;
        }
        return false;
    }

    public boolean usePower(int PowerUsed) {

        if(this.getPowerStored() >= PowerUsed) {

            this.setPower(this.getPowerStored() - PowerUsed);

            return true;
        }
        return false;
    }

    public void setPower(int newPower) {        

        this.powerStored = newPower;
    }

    public void setMaxPower(int newMaxPower) {

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

    public boolean canGainPower(int power) {

        if(this.getPowerMax() - this.getPowerStored() >= power) {

            return true;
        }
        return false; 
    }

    public boolean canUsePower(int power) {

        if(this.getPowerStored() >= power) {

            return true;
        }
        return false;
    }

    public void readFromNBT(NBTTagCompound tagCompound) {

        powerStored = tagCompound.getInteger("powerStored");
        powerMax = tagCompound.getInteger("powerMax");
    }

    public void writeToNBT(NBTTagCompound tagCompound) {

        tagCompound.setInteger("powerStored", powerStored);
        tagCompound.setInteger("powerMax", powerMax);
    }
}
