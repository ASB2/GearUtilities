package GU.color;

public class Color {

    float red;
    float green;
    float blue;
    float alpha;

    public Color() {
        red = 1;
        green = 1;
        blue = 1;
        alpha = 1;
    }

    public Color(float red, float green, float blue) {

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 1;
    }

    public Color(float red, float green, float blue, float alpha) {

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

    public void setRed(float red) {

        this.red = red / 10;
    }

    public void setGreen(float green) {

        this.green = green / 10;
    }

    public void setBlue(float blue) {

        this.blue = blue / 10;
    }

    public void setAlpha(float alpha) {

        this.alpha = alpha / 10;
    }

    public float getRed() {

        return red;
    }

    public float getGreen() {

        return green;
    }

    public float getBlue() {

        return blue;
    }

    public float getAlpha() {

        return alpha;
    }

    public Color copy() {

        Color color = new Color(this);
        return color;
    }
}
