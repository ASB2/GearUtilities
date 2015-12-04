package GU.api;

import UC.color.Color4i;


public enum EnumSideState {
    
    INPUT(Color4i.BLUE), OUTPUT(Color4i.ORANGE), BOTH(Color4i.RED), NONE(Color4i.WHITE);
    
    Color4i color;
    
    private EnumSideState(Color4i color) {
        
        this.color = color;
    }
    
    public EnumSideState increment() {
        
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
    
    public Color4i getColor() {
        
        return color.clone();
    }
}
