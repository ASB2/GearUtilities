package GU.api.cluster;

import net.minecraftforge.common.ForgeDirection;
import ASB2.vector.Vector3;

public interface IClusterTrigger {

    void onClustorCollosion(ForgeDirection side, Vector3 position, IClustor clustor);
}
