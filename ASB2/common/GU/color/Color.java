package GU.color;

public class Color {

    int red = 255;
    int green = 255;
    int blue = 255;
    int alpha = 255;

    int redAdjustment = 0;
    int greenAdjustment = 0;
    int blueAdjustment = 0;
    int alphaAdjustment = 0;
    
    public Color() {

    }

    public Color(int red, int green, int blue) {

        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color(int red, int green, int blue, int alpha) {

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    public Color(Color color) {

        this.red = color.getRed();
        this.green = color.getGreen();
        this.blue = color.getBlue();
        this.alpha = color.getAlpha();
    }

    public Color(EnumVinillaColor color) {
    
        Color vinillaColor = EnumVinillaColor.getRGBValue(color);
        
        this.red = vinillaColor.getRed();
        this.green = vinillaColor.getGreen();
        this.blue = vinillaColor.getBlue();
        this.alpha = vinillaColor.getAlpha();
    }
    
    public void setRed(int red) {

        this.red = red;
    }

    public void setGreen(int green) {

        this.green = green;
    }

    public void setBlue(int blue) {

        this.blue = blue;
    }

    public void setAlpha(int alpha) {

        this.alpha = alpha;
    }

    
    public int getRed() {

        return red;
    }

    public int getGreen() {

        return green;
    }

    public int getBlue() {

        return blue;
    }

    public int getAlpha() {

        return alpha;
    }

    public void setRedAdjustment(int red) {

        this.redAdjustment = red;
    }

    public void setGreenAdjustment(int green) {

        this.greenAdjustment = green;
    }

    public void setBlueAdjustment(int blue) {

        this.blueAdjustment = blue;
    }

    public void setAlphaAdjustment(int alpha) {

        this.alphaAdjustment = alpha;
    }

    public int getRedAdjustment() {

        return redAdjustment;
    }

    public int getGreenAdjustment() {

        return greenAdjustment;
    }

    public int getBlueAdjustment() {

        return blueAdjustment;
    }

    public int getAlphaAdjustment() {

        return alphaAdjustment;
    }
    
    public Color getColorAdjusted() {

        return new Color(this.getRed() - this.getRedAdjustment(), this.getGreen() - this.getGreenAdjustment(), this.getBlue() - this.getBlueAdjustment(), this.getAlpha() - this.getAlphaAdjustment());
    }

    public Color copy() {

        return new Color(this);
    }
}
