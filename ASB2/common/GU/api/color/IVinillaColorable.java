package GU.api.color;

public interface IVinillaColorable {

    /**
     * Used for markings. Does NOT refer to the texture color of the block
     */
    EnumVinillaColor getColorEnum();

    /**
     * Sets the color of the block
     */
    void setColor(EnumVinillaColor color);
}
