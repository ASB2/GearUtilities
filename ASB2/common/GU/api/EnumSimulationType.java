package GU.api;

public enum EnumSimulationType {
    
    SIMULATE, /** This is a test */
    FORCED_SIMULATE/** This is a test */
    , LIGITIMATE, /** This is ligitimate */
    FORCED_LIGITIMATE/** This ignores packet size */
    ;
    
    public boolean isForced() {
        
        return this == FORCED_LIGITIMATE || this == FORCED_SIMULATE;
    }
    
    public boolean isLigitimate() {
        
        return this == LIGITIMATE || this == FORCED_LIGITIMATE;
    }
}
