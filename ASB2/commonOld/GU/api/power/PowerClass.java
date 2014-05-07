package GU.api.power;

public enum PowerClass {
    
    LOW(16, 1000), MID(64, 10000), HIGH(128, 100000), OTHER(0, 0);
    
    private float powerValue = 0;
    private float suggestedMax = 0;
    
    PowerClass(float power, float max) {
        
        powerValue = power;
        suggestedMax = max;
    }
    
    public float getPowerValue() {
        
        return powerValue;
    }
    
    public float getSuggestedMax() {
        
        return suggestedMax;
    }
}
