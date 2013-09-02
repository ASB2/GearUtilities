package GU.api.power;

public enum PowerClass {

    LOW(10, 1000), MID(100, 10000), HIGH(1000, 100000);

    PowerClass(float power, float max) {

        powerValue = power;
        suggestedMax = max;
    }

    private float powerValue = 0;
    private float suggestedMax = 0;
    
    public float getPowerValue() {

        return powerValue;
    }
    
    public float getSuggestedMax() {

        return suggestedMax;
    }
}
