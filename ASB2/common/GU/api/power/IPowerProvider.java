package GU.api.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;


public interface IPowerProvider {

    float getPowerStored();
    float getPowerMax();
    
    boolean gainPower(float PowerGained, ForgeDirection direction, boolean doUse);
    boolean usePower(float PowerUsed, ForgeDirection direction, boolean doUse);
    
    void setPowerStored(float newPower);
    void setPowerMax(float newMaxPower);
    
    PowerClass getPowerClass();
    
    void readFromNBT(NBTTagCompound tagCompound);
    void writeToNBT(NBTTagCompound tagCompound);
    
    IPowerProvider copy();
}
