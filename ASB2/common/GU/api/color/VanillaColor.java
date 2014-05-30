package GU.api.color;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import UC.color.Color4i;

public enum VanillaColor {
    
    BLACK, RED, GREEN, BROWN, BLUE, PURPLE, CYAN, LIGHT_GREY, GREY, PINK, LIME, YELLOW, LIGHT_BLUE, MAGENTA, ORANGE, WHITE, NONE;
    public static final String[] dyesOreDictionary = { "dyeBlack", "dyeRed", "dyeGreen", "dyeBrown", "dyeBlue", "dyePurple", "dyeCyan", "dyeLightGray", "dyeGray", "dyePink", "dyeLime", "dyeYellow", "dyeLightBlue", "dyeMagenta", "dyeOrange", "dyeWhite" };
    
    public static ItemStack getVinillaDye(VanillaColor Color4i) {
        
        switch (Color4i) {
        
            case BLACK:
                return new ItemStack(Items.dye, 1, 0);
            case RED:
                return new ItemStack(Items.dye, 1, 1);
            case GREEN:
                return new ItemStack(Items.dye, 2, 2);
            case BROWN:
                return new ItemStack(Items.dye, 1, 3);
            case BLUE:
                return new ItemStack(Items.dye, 2, 4);
            case PURPLE:
                return new ItemStack(Items.dye, 1, 5);
            case CYAN:
                return new ItemStack(Items.dye, 2, 6);
            case LIGHT_GREY:
                return new ItemStack(Items.dye, 1, 7);
            case GREY:
                return new ItemStack(Items.dye, 2, 8);
            case PINK:
                return new ItemStack(Items.dye, 1, 9);
            case LIME:
                return new ItemStack(Items.dye, 2, 10);
            case YELLOW:
                return new ItemStack(Items.dye, 1, 11);
            case LIGHT_BLUE:
                return new ItemStack(Items.dye, 2, 12);
            case MAGENTA:
                return new ItemStack(Items.dye, 1, 13);
            case ORANGE:
                return new ItemStack(Items.dye, 2, 14);
            case WHITE:
                return new ItemStack(Items.dye, 1, 15);
                
            default:
                return null;
        }
    }
    
    public static VanillaColor getItemColor4iValue(ItemStack item) {
        
        if (item != null) {
            
            Item itemI = item.getItem();
            
            if (itemI == Items.dye) {
                
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
            
            if (itemI == Items.dye) {
                
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
    
    public static Color4i getRGBValue(VanillaColor color) {
        
        switch (color) {
        
            case WHITE:
                return new Color4i(255, 255, 255);
            case ORANGE:
                return new Color4i(255, 165, 0);
            case MAGENTA:
                return new Color4i(139, 0, 139);
            case LIGHT_BLUE:
                return new Color4i(173, 216, 230);
            case YELLOW:
                return new Color4i(255, 255, 0);
            case LIME:
                return new Color4i(191, 255, 0);
            case PINK:
                return new Color4i(255, 192, 203);
            case GREY:
                return new Color4i(128, 128, 128);
            case LIGHT_GREY:
                return new Color4i(100 + 90, 100 + 90, 100 + 90);
            case CYAN:
                return new Color4i(0, 255, 255);
            case PURPLE:
                return new Color4i(128, 0, 128);
            case BLUE:
                return new Color4i(0, 0, 255);
            case BROWN:
                return new Color4i(75, 42, 42);
            case GREEN:
                return new Color4i(0, 255, 0);
            case RED:
                return new Color4i(255, 0, 0);
            case BLACK:
                return new Color4i(30, 30, 30);
            case NONE:
                return new Color4i(255, 255, 255);
            default:
                return new Color4i(255, 255, 255);
        }
    }
}
