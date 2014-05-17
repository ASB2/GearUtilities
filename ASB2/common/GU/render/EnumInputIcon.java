package GU.render;

import net.minecraft.util.IIcon;

public enum EnumInputIcon {
    
    INPUT, OUTPUT, BOTH, NONE;
    
    public EnumInputIcon increment() {
        
        switch (this) {
        
            case INPUT:
                return OUTPUT;
            case OUTPUT:
                return BOTH;
            case BOTH:
                return INPUT;
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
}
