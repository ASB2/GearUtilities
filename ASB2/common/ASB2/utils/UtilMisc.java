package ASB2.utils;

import java.awt.Color;

import net.minecraft.util.EnumChatFormatting;

public class UtilMisc {

    /*
     * Side 0 == Down = Bottom Side 1 == Top = Top Side 2 == Side facing North =
     * Front Side 3 == Side facing South = Back Side 4 == Side facing West =
     * Right Side 5 == Side facing East = Left
     */

    public static String capitilizeFirst(String string) {

        char[] characters = string.toCharArray();
        char first = characters[0];

        characters[0] = Character.toUpperCase(first);

        String temp = "";

        for(char character: characters) {

            temp = temp + Character.toString(character);
        }
        return temp;
    }

    public static boolean isValueInArray(Object[] object, Object value) {

        for (int i = 0; i < object.length; i++) {

            Object temp = object[i];

            if (temp.equals(value)) {

                return true;
            }
        }
        return false;
    }

    public static int getNumberDivided(int startNumber, int divisionAmoun) {

        return startNumber / divisionAmoun;
    }

    public static double getNumberDivided(double startNumber,
            double divisionAmoun) {

        return startNumber / divisionAmoun;
    }

    public static int getAmountScaled(int scale, int amount, int max) {

        if (max != 0) {

            int internal = amount * scale / max;

            if (internal > scale) {

                internal = scale;
            }
            return internal;
        }
        return 0;
    }

    public static double getAmountScaled(double scale, double amount, double max) {

        double internal = amount * scale / max;

        if (internal > scale) {

            internal = scale;
        }
        return internal;
    }

    public static String getColorCode(EnumChatFormatting color) {

        return "\u00A7 " + color;
    }

    public static Color changeRed(Color color, int amount) {

        if(color.getRed() + amount <= 255 && color.getRed() + amount >= 0) {

            return new Color(color.getRed() + amount, color.getGreen(), color.getBlue());
        }
        return color;
    }

    public static Color changeGreen(Color color, int amount) {

        if(color.getGreen() + amount <= 255 && color.getGreen() + amount >= 0) {

            return new Color(color.getRed(), color.getGreen()+ amount , color.getBlue());
        }
        return color;
    }
    
    public static Color changeBlue(Color color, int amount) {

        if(color.getBlue() + amount <= 255 && color.getBlue() + amount >= 0) {

            return new Color(color.getRed(), color.getGreen(), color.getBlue() + amount);
        }
        return color;
    }
}
