package GU.api;

public enum EnumSimulationType {
    
    SIMULATE, LIGITIMATE, FORCED;
    
    public boolean getBooleanValue() {
        
        return this == SIMULATE ? false : true;
    }
}
