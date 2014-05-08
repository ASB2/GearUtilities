package GU.api.color;

import java.awt.Color;

import net.minecraft.item.ItemStack;

public interface IColorableItem {
    
    public Color getColor(ItemStack stack);
    
    public boolean setColor(ItemStack stack, Color color);
    
}
