package GU.api.module;


public abstract interface IModuleProvider {
    
    ModuleType getModuleType();
    float getMaxPowerChange();
    String getName();
}
