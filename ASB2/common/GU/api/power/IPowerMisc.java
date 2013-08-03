package GU.api.power;

import net.minecraftforge.common.ForgeDirection;
import GU.api.*;

public interface IPowerMisc {

    /**
     * Get the orientation of the block
     * @return direction facing
     */
    ForgeDirection getOrientation();

    /**
     * Name of the block
     */
    String getName();

    /**
     * Used for markings. Does NOT refer to the texture color of the block
     */
    EnumColor getColorEnum();

    /**
     * Sets the color of the block
     */
    void setColor(EnumColor color);
    
    int[] getPosition();
    /**
     * Returns power provider class of the tile
     */
    PowerProvider getPowerProvider();
}
