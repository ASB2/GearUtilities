package GU.render;

import java.awt.Color;
import java.awt.color.ColorSpace;

@SuppressWarnings("serial")
public class Colour extends Color {
    
    public Colour(ColorSpace cspace, float[] components, float alpha) {
        super(cspace, components, alpha);
        // TODO Auto-generated constructor stub
    }
    
    public Colour(float r, float g, float b, float a) {
        super(r, g, b, a);
        // TODO Auto-generated constructor stub
    }
    
    public Colour(float r, float g, float b) {
        super(r, g, b);
        // TODO Auto-generated constructor stub
    }
    
    public Colour(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
        // TODO Auto-generated constructor stub
    }
    
    public Colour(int arg0, int arg1, int arg2, int arg3) {
        super(arg0, arg1, arg2, arg3);
        // TODO Auto-generated constructor stub
    }
    
    public Colour(int r, int g, int b) {
        super(r, g, b);
        // TODO Auto-generated constructor stub
    }
    
    public Colour(int rgb) {
        super(rgb);
        // TODO Auto-generated constructor stub
    }
}
