package GU.api.power;

public enum PowerClass {

    LOW(10, 1000), MID(100, 10000), HIGH(1000, 100000);

    PowerClass(int power, int max) {

        powerValue = power;
        suggestedMax = max;
    }

    int powerValue = 0;
    int suggestedMax = 0;
    
    public int getPowerValue() {

        return powerValue;
    }
    
    public int getSuggestedMax() {

        return suggestedMax;
    }
}
