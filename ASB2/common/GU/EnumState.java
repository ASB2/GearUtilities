package GU;

import net.minecraft.util.Icon;

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
    
    public static Icon[] SIDES = new Icon[4];
    
    public Icon getStateIcon() {
        
        return SIDES[this.ordinal()];
    }    
}
