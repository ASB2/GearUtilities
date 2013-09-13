package GU.api.power;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;

public interface IPowerProvider {

    float getPowerStored();
    float getPowerMax();
    
    PowerClass getPowerClass();    
    State getCurrentState();
    
    boolean gainPower(float PowerGained, ForgeDirection direction, boolean doUse);
    boolean usePower(float PowerUsed, ForgeDirection direction, boolean doUse);
    
    void setPower(float newPower);
    void setMaxPower(float newMaxPower);
    
    void readFromNBT(NBTTagCompound tagCompound);
    void writeToNBT(NBTTagCompound tagCompound);
}
