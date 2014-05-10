package GU.api;

public enum EnumSimulationType {
    
    SIMULATE, /** This is a test */
    LIGITIMATE, /** This is ligitimate */
    FORCED/** This ignores packet size */
    ;
    
    public boolean getBooleanValue() {
        
        return this == SIMULATE ? false : true;
    }
}
