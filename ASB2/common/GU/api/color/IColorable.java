package GU.api.color;

public interface IColorable {

    /**
     * Used for markings. Does NOT refer to the texture color of the block
     */
    EnumColor getColorEnum();

    /**
     * Sets the color of the block
     */
    void setColor(EnumColor color);
}
