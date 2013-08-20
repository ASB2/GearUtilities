package GU.api.power;

public enum PowerClass {

    LOW(1), MID(10), HIGH(100);

    PowerClass(int power) {

        powerValue = power;
    }

    int powerValue = 0;

    public int getPowerValue() {

        return powerValue;
    }
}
