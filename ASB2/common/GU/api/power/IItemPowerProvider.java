package GU.api.power;


public interface IItemPowerProvider {

    float getPowerStored();
    float getPowerMax();
    
    boolean gainPower(float powerToGain, boolean doUse);
    boolean usePower(float powerToUse, boolean doUse);
    
    void setPowerStored(float newPower);
    void setPowerMax(float newMaxPower);
}
