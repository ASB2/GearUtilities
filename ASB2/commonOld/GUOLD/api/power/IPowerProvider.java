package GUOLD.api.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;


public interface IPowerProvider {

    float getPowerStored();
    float getPowerMax();
    
    float getMinInput();
    float getMinOutput();
    
    float getMaxInput();
    float getMaxOutput();
    
    boolean gainPower(float PowerGained, ForgeDirection direction, boolean doUse);
    boolean usePower(float PowerUsed, ForgeDirection direction, boolean doUse);
    
    void setPowerStored(float newPower);
    void setPowerMax(float newMaxPower);
    
    PowerClass getPowerClass();
    State getState();
    
    void readFromNBT(NBTTagCompound tagCompound);
    void writeToNBT(NBTTagCompound tagCompound);
    
    IPowerProvider copy();
}
