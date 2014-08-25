package GUOLD.api.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class PowerProvider implements IPowerProvider {
    
    protected PowerClass powerClass;
    protected State currentState;
    
    protected float powerStored;
    protected float powerMax;
    
    public PowerProvider(PowerClass powerClass, State state) {
        this(powerClass.getSuggestedMax(), powerClass, state);
    }
    
    public PowerProvider(float maximumPower, PowerClass powerClass, State state) {
        
        this.powerClass = powerClass;
        this.powerMax = maximumPower;
        this.currentState = state;
    }
    
    @Override
    public float getPowerStored() {
        
        return powerStored;
    }
    
    @Override
    public float getPowerMax() {
        
        return powerMax;
    }
    
    @Override
    public PowerClass getPowerClass() {
        
        return this.powerClass;
    }
    
    @Override
    public boolean usePower(float power, ForgeDirection direction, boolean doUse) {
        
        if (this.getMaxOutput() != -1) {
            
            if (power > this.getMaxOutput()) {
                
                return false;
            }
        }
        
        if (this.getMinOutput() != -1) {
            
            if (power < this.getMinOutput()) {
                
                return false;
            }
        }
        
        if (this.getPowerStored() >= power) {
            
            if (doUse)
                this.setPowerStored(this.getPowerStored() - power);
            
            return true;
        }
        return false;
    }
    
    @Override
    public boolean gainPower(float power, ForgeDirection direction, boolean doUse) {
        
        if (this.getMaxInput() != -1) {
            
            if (power > this.getMaxInput()) {
                
                return false;
            }
        }
        
        if (this.getMinInput() != -1) {
            
            if (power < this.getMinInput()) {
                
                return false;
            }
        }
        
        if (this.getPowerMax() - this.getPowerStored() >= power) {
            
            if (doUse)
                this.setPowerStored(this.getPowerStored() + power);
            
            return true;
        }
        return false;
    }
    
    @Override
    public void setPowerStored(float newPower) {
        
        this.powerStored = newPower;
    }
    
    @Override
    public void setPowerMax(float newMaxPower) {
        
        this.powerMax = newMaxPower;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tagCompound) {
        
        powerStored = tagCompound.getFloat("powerStored");
        powerMax = tagCompound.getFloat("powerMax");
    }
    
    @Override
    public void writeToNBT(NBTTagCompound tagCompound) {
        
        tagCompound.setFloat("powerStored", powerStored);
        tagCompound.setFloat("powerMax", powerMax);
    }
    
    @Override
    public IPowerProvider copy() {
        PowerProvider provider = new PowerProvider(this.getPowerMax(), this.getPowerClass(), getState());
        provider.setPowerStored(getPowerStored());
        return provider;
    }
    
    @Override
    public float getMinInput() {
        
        return -1;
    }
    
    @Override
    public float getMinOutput() {
        
        return -1;
    }
    
    @Override
    public float getMaxInput() {
        
        return -1;
    }
    
    @Override
    public float getMaxOutput() {
        
        return -1;
    }
    
    @Override
    public State getState() {
        
        return currentState;
    }
}
