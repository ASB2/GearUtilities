package GU.color;

public class Color {

    private int red = 255;
    private int green = 255;
    private int blue = 255;
    private int alpha = 255;

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

    public Color copy() {

        return new Color(this);
    }

    public void changeRed(int change) {

        this.red += change;
    }

    public void changeGreen(int change) {

        this.green += change;
    }

    public void changeBlue(int change) {

        this.blue += change;
    }

    public void changeAlpha(int change) {

        this.alpha += change;
    }
}
