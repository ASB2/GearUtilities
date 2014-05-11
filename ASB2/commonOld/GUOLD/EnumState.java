package GUOLD;

import net.minecraft.util.IIcon;

public enum EnumState {
    
    INPUT, OUTPUT, BOTH, NONE;
    
    public EnumState increment() {
        
        switch (this) {
        
            case INPUT:
                return OUTPUT;
            case OUTPUT:
                return BOTH;
            case NONE:
                return INPUT;
            default:
                return NONE;
        }
    }
    
    public static IIcon[] SIDES = new IIcon[4];
    
    public IIcon getStateIcon() {
        
        return SIDES[this.ordinal()];
    }    
}
