package GUOLD.api;

public enum EnumResourceType {

    POWER, FLUID, ITEM;

    public enum EnumPowerType {

        GEAR_UTILITIES(1), BUILD_CRAFT(2), INDUSTRIAL_CRAFT(1);

        int relativePowerCost;

        EnumPowerType(int relativity) {
            relativePowerCost = relativity;
        }
    }
}
