package GU.color;

import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;

/**
 * An enum to standardize the coloring of my mod's colors
 */
public enum EnumVinillaColor {

    WHITE, ORANGE, MAGENTA, LIGHT_BLUE, YELLOW, LIME, PINK, GRAY, LIGHT_GREY, CYAN, PURPLE, BLUE, BROWN, GREEN, RED, BLACK, NONE;

    public static ItemStack getVinillaDye(EnumVinillaColor color) {

        switch (color) {

            case RED:
                return new ItemStack(Item.dyePowder.itemID, 1, 1);
            case GREEN:
                return new ItemStack(Item.dyePowder.itemID, 2, 2);
            case BROWN:
                return new ItemStack(Item.dyePowder.itemID, 1, 3);
            case BLUE:
                return new ItemStack(Item.dyePowder.itemID, 2, 4);
            case PURPLE:
                return new ItemStack(Item.dyePowder.itemID, 1, 5);
            case CYAN:
                return new ItemStack(Item.dyePowder.itemID, 2, 6);
            case LIGHT_GREY:
                return new ItemStack(Item.dyePowder.itemID, 1, 7);
            case GRAY:
                return new ItemStack(Item.dyePowder.itemID, 2, 8);
            case PINK:
                return new ItemStack(Item.dyePowder.itemID, 1, 9);
            case LIME:
                return new ItemStack(Item.dyePowder.itemID, 2, 10);
            case YELLOW:
                return new ItemStack(Item.dyePowder.itemID, 1, 11);
            case LIGHT_BLUE:
                return new ItemStack(Item.dyePowder.itemID, 2, 12);
            case MAGENTA:
                return new ItemStack(Item.dyePowder.itemID, 1, 13);
            case ORANGE:
                return new ItemStack(Item.dyePowder.itemID, 2, 14);
            case WHITE:
                return new ItemStack(Item.dyePowder.itemID, 1, 15);

            default:
                return null;
        }
    }

    public static EnumVinillaColor getItemColorValue(ItemStack item) {

        if (item != null) {

            if (item.getItem() != null) {

                Item itemI = item.getItem();

                if (itemI instanceof ItemDye) {

                    switch (item.getItemDamage()) {

                        case 1:
                            return EnumVinillaColor.RED;
                        case 2:
                            return EnumVinillaColor.GREEN;
                        case 3:
                            return EnumVinillaColor.BROWN;
                        case 4:
                            return EnumVinillaColor.BLUE;
                        case 5:
                            return EnumVinillaColor.PURPLE;
                        case 6:
                            return EnumVinillaColor.CYAN;
                        case 7:
                            return EnumVinillaColor.LIGHT_GREY;
                        case 8:
                            return EnumVinillaColor.GRAY;
                        case 9:
                            return EnumVinillaColor.PINK;
                        case 10:
                            return EnumVinillaColor.LIME;
                        case 11:
                            return EnumVinillaColor.YELLOW;
                        case 12:
                            return EnumVinillaColor.LIGHT_BLUE;
                        case 13:
                            return EnumVinillaColor.MAGENTA;
                        case 14:
                            return EnumVinillaColor.ORANGE;
                        case 15:
                            return EnumVinillaColor.WHITE;
                    }
                }
            }
        }
        return EnumVinillaColor.NONE;
    }

    public static EnumVinillaColor translateNumberToColor(int numb) {

        switch (numb) {

            case 0:
                return EnumVinillaColor.WHITE;
            case 1:
                return EnumVinillaColor.ORANGE;
            case 2:
                return EnumVinillaColor.MAGENTA;
            case 3:
                return EnumVinillaColor.LIGHT_BLUE;
            case 4:
                return EnumVinillaColor.YELLOW;
            case 5:
                return EnumVinillaColor.LIME;
            case 6:
                return EnumVinillaColor.PINK;
            case 7:
                return EnumVinillaColor.GRAY;
            case 8:
                return EnumVinillaColor.LIGHT_GREY;
            case 9:
                return EnumVinillaColor.CYAN;
            case 10:
                return EnumVinillaColor.PURPLE;
            case 11:
                return EnumVinillaColor.BLUE;
            case 12:
                return EnumVinillaColor.BROWN;
            case 13:
                return EnumVinillaColor.GREEN;
            case 14:
                return EnumVinillaColor.RED;
            case 15:
                return EnumVinillaColor.BLACK;
            default:
                return EnumVinillaColor.NONE;
        }
    }

    public static int translateColorToNumber(EnumVinillaColor color) {

        switch (color) {

            case WHITE:
                return 0;
            case ORANGE:
                return 1;
            case MAGENTA:
                return 2;
            case LIGHT_BLUE:
                return 3;
            case YELLOW:
                return 4;
            case LIME:
                return 5;
            case PINK:
                return 6;
            case GRAY:
                return 7;
            case LIGHT_GREY:
                return 8;
            case CYAN:
                return 9;
            case PURPLE:
                return 10;
            case BLUE:
                return 11;
            case BROWN:
                return 12;
            case GREEN:
                return 13;
            case RED:
                return 14;
            case BLACK:
                return 15;

            case NONE:
                return -1;
            default:
                return -1;
        }
    }
    
    public static Color getRGBValue(EnumVinillaColor color) {
     
        switch (color) {

            case WHITE:
                return new Color(255, 255, 255);
            case ORANGE:
                return new Color(255, 165, 0);
            case MAGENTA:
                return new Color(139, 0, 139);
            case LIGHT_BLUE:
                return new Color(173, 216, 230);
            case YELLOW:
                return new Color(255, 255, 0);
            case LIME:
                return new Color(0, 255, 0);
            case PINK:
                return new Color(255, 192, 203);
            case GRAY:
                return new Color(128, 128, 128);
            case LIGHT_GREY:
                return new Color(211, 211, 211);
            case CYAN:
                return new Color(0, 255, 255);
            case PURPLE:
                return new Color(128, 0, 128);
            case BLUE:
                return new Color(0, 0, 255);
            case BROWN:
                return new Color(165, 42, 42);
            case GREEN:
                return new Color(0, 255, 0);
            case RED:
                return new Color(255, 0, 0);
            case BLACK:
                return new Color(0, 0, 0);
            case NONE:
                return new Color(255, 255, 255);
            default:
                return new Color(255, 255, 255);
        }
    }
}
