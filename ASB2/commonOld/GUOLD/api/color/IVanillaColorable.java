package GUOLD.api.color;

public interface IVanillaColorable {

    /**
     * Used for markings. Does NOT refer to the texture color of the block
     */
    VanillaColor getColorEnum();

    /**
     * Sets the color of the block
     */
    void setColor(VanillaColor color);
}
