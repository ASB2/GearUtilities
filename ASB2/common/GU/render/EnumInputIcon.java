package GU.render;

import net.minecraft.util.IIcon;
import UC.color.Color4i;

public enum EnumInputIcon {
    
    INPUT(Color4i.BLUE), OUTPUT(Color4i.ORANGE), BOTH(Color4i.RED), NONE(Color4i.WHITE);
    
    Color4i color;
    
    private EnumInputIcon(Color4i color) {
        
        this.color = color;
    }
    
    public EnumInputIcon increment() {
        
        switch (this) {
        
            case INPUT:
                return OUTPUT;
            case OUTPUT:
                return BOTH;
            case BOTH:
                return NONE;
            case NONE:
                return INPUT;
            default:
                return NONE;
        }
    }
    
    private static IIcon[] SIDES = new IIcon[4];
    
    public EnumInputIcon setStateIcon(IIcon icon) {
        
        SIDES[this.ordinal()] = icon;
        return this;
    }
    
    public IIcon getStateIcon() {
        
        return SIDES[this.ordinal()];
    }
    
    public Color4i getColor() {
        
        return color.clone();
    }
}
