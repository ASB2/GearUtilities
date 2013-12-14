package GU.api.color;

import java.awt.Color;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public enum VanillaColor {
    
    BLACK, RED, GREEN, BROWN, BLUE, PURPLE, CYAN, LIGHT_GREY, GREY, PINK, LIME, YELLOW, LIGHT_BLUE, MAGENTA, ORANGE, WHITE, NONE;
    public static final String[] dyesOreDictionary = { "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };
    
    public static ItemStack getVinillaDye(VanillaColor color) {
        
        switch (color) {
        
            case BLACK:
                return new ItemStack(Item.dyePowder.itemID, 1, 0);
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
            case GREY:
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
    
    public static VanillaColor getItemColorValue(ItemStack item) {
        
        if (item != null) {
            
            Item itemI = item.getItem();
            
            if (itemI == Item.dyePowder) {
                
                switch (item.getItemDamage()) {
                
                    case 0:
                        return VanillaColor.BLACK;
                    case 1:
                        return VanillaColor.RED;
                    case 2:
                        return VanillaColor.GREEN;
                    case 3:
                        return VanillaColor.BROWN;
                    case 4:
                        return VanillaColor.BLUE;
                    case 5:
                        return VanillaColor.PURPLE;
                    case 6:
                        return VanillaColor.CYAN;
                    case 7:
                        return VanillaColor.LIGHT_GREY;
                    case 8:
                        return VanillaColor.GREY;
                    case 9:
                        return VanillaColor.PINK;
                    case 10:
                        return VanillaColor.LIME;
                    case 11:
                        return VanillaColor.YELLOW;
                    case 12:
                        return VanillaColor.LIGHT_BLUE;
                    case 13:
                        return VanillaColor.MAGENTA;
                    case 14:
                        return VanillaColor.ORANGE;
                    case 15:
                        return VanillaColor.WHITE;
                }
            }
            
            for (int i = 0; i < dyesOreDictionary.length; i++) {
                
                if (OreDictionary.getOres(dyesOreDictionary[i]).contains(item)) {
                    
                    return VanillaColor.values()[i];
                }
            }
        }
        return VanillaColor.NONE;
    }
    
    public static boolean isItemDye(ItemStack item) {
        
        if (item != null) {
            
            Item itemI = item.getItem();
            
            if (itemI == Item.dyePowder) {
                
                if (item.getItemDamage() <= 16) {
                    
                    return true;
                }
            }
            for (int i = 0; i < dyesOreDictionary.length; i++) {
                
                if (OreDictionary.getOres(dyesOreDictionary[i]).contains(item)) {
                    
                    return VanillaColor.values()[i] != NONE;
                }
            }
        }
        return false;
    }
    
    public static Color getRGBValue(VanillaColor color) {
        
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
            case GREY:
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
                return new Color(75, 42, 42);
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
