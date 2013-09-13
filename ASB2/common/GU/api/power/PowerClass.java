package GU.api.power;

public enum PowerClass {

    LOW(0, 10, 1000), MID(1, 100, 10000), HIGH(2, 1000, 100000), UNKNOWN(3, 0, 0);

    private int id;
    private float powerValue = 0;
    private float suggestedMax = 0;
    
    PowerClass(int id, float power, float max) {

        powerValue = power;
        suggestedMax = max;
        this.id = id;
    }
    
    public float getPowerValue() {

        return powerValue;
    }
    
    public float getSuggestedMax() {

        return suggestedMax;
    }
    
    public int getID() {

        return id;
    }
    
    public static PowerClass getPowerClass(int id) {
        
        switch(id) {
            
            case 0: return LOW;
            case 1: return MID;
            case 2: return HIGH;
            default: return UNKNOWN;
        }
    }
}
