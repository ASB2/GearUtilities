package GU.utils;

import net.minecraft.util.EnumChatFormatting;

public class UtilMisc {

    /*
     * Side 0 == Down = Bottom
     * Side 1 == Top = Top
     * Side 2 == Side facing North = Front
     * Side 3 == Side facing South = Back
     * Side 4 == Side facing West = Right
     * Side 5 == Side facing East = Left
     */

    public static boolean isValueInArray(Object[] object, Object value) {

        for(int i = 0; i < object.length; i++) {

            Object temp = object[i];

            if(temp.equals(value)) {

                return true;
            }
        }
        return false;
    }

    public static int getNumberDivided(int startNumber, int divisionAmoun) {

        return startNumber / divisionAmoun;
    }
    
    public static double getNumberDivided(double startNumber, double divisionAmoun) {

        return startNumber / divisionAmoun;
    }

    public static int getAmountScaled(int scale, int amount, int max) {

        int internal = amount * scale / max;

        if(internal > scale) {

            internal = scale;
        }
        return internal;
    }

    public static double getAmountScaled(double scale, double amount, double max) {

        double internal = amount * scale / max;

        if(internal > scale) {

            internal = scale;
        }
        return internal;
    }

    public static String getColorCode(EnumChatFormatting color) {

        return "\u00A7 " + color;
    }
}
