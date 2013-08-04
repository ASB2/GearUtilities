package GU.api;

import net.minecraftforge.common.ForgeDirection;

public interface IDirectionSpecific {

    /**
     * Get the orientation of the block
     * @return direction facing
     */
    ForgeDirection getOrientation();

}
