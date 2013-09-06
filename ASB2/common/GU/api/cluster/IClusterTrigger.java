package GU.api.cluster;

import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;

public interface IClusterTrigger {

    void onSentClustorCollosion(IClusterTrigger source, ForgeDirection side, Vector3 position, IClustor clustor, int id);
    
    void onClustorCollosion(IClusterTrigger source, ForgeDirection side, Vector3 position, IClustor clustor);
}
