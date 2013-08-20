package GU.color;

public class Color {

    float red;
    float green;
    float blue;
    float alpha;

    public Color() {
        red = 0;
        green = 0;
        blue = 0;
        alpha = 0;
    }

    public Color(float red, float green, float blue) {

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = 0;
    }

    public Color(float red, float green, float blue, float alpha) {

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
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
}
