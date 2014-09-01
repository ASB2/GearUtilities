package GU.api.color;

import net.minecraft.init.Items;
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
    
    public ItemStack getVinillaDye() {
        
        switch (this) {
        
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
    
    public static VanillaColor getItemColorValue(ItemStack item) {
        
        if (item != null) {
            
            int[] thingsRegisteredTo = OreDictionary.getOreIDs(item);
            
            for (int index = 0; index < thingsRegisteredTo.length; index++) {
                
                String regesteredTo = OreDictionary.getOreName(thingsRegisteredTo[index]);
                
                for (int i = 0; i < dyesOreDictionary.length; i++) {
                    
                    if (regesteredTo.equalsIgnoreCase(dyesOreDictionary[i])) {
                        
                        return VanillaColor.values()[i];
                    }
                }
            }
        }
        return VanillaColor.NONE;
    }
    
    public static boolean isItemDye(ItemStack item) {
        
        return VanillaColor.getItemColorValue(item) != VanillaColor.NONE;
    }
    
    public Color4i getRGBValue() {
        
        switch (this) {
        
            case WHITE:
                return new Color4i(255, 255, 255);
            case ORANGE:
                return new Color4i(255, 165, 0);
            case MAGENTA:
                return new Color4i(220, 0, 190);
            case LIGHT_BLUE:
                return new Color4i(173, 216, 250);
            case YELLOW:
                return new Color4i(255, 255, 0);
            case LIME:
                return new Color4i(191, 255, 0);
            case PINK:
                return new Color4i(255, 192, 203);
            case GREY:
                return new Color4i(128, 128, 128);
            case LIGHT_GREY:
                return new Color4i(190, 190, 190);
            case CYAN:
                return new Color4i(0, 255, 255);
            case PURPLE:
                return new Color4i(110, 0, 110);
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
