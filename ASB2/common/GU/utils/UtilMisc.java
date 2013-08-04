package GU.utils;

public class UtilMisc {

    public static boolean isValueInArray(Object[] object, Object value) {

        for(int i = 0; i < object.length; i++) {

            Object temp = object[i];

            if(temp.equals(value)) {

                return true;
            }
        }
        return false;
    }

    public int getAmountScaled(int scale, int amount, int max) {

        int internal = amount * scale / max;

        if(internal > scale) {

            internal = scale;
        }
        return 0;
    }
}
