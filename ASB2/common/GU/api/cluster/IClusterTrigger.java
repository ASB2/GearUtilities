package GU.api.cluster;

import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;

public interface IClusterTrigger {

    /**
     * Called by the block that is sending a clustor.
     * @param source
     * @param side
     * @param position
     * @param clustor
     * @param id
     */
    
    void onSentClustorCollosion(IClusterTrigger source, ForgeDirection side, Vector3 position, IClustor clustor, int id);
    
    /**
     * Called on the block hit by a clustor
     * @param source
     * @param side
     * @param position
     * @param clustor
     */
    void onClustorCollosion(IClusterTrigger source, ForgeDirection side, Vector3 position, IClustor clustor);
}
