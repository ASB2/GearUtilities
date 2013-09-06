package GU.api.network;

import java.util.ArrayList;

import ASB2.vector.Vector3;

public interface IPowerNetwork extends INetwork {

    boolean postPowerRequest(Vector3 requesting);
    boolean removePowerRequest(Vector3 requesting);    
    
    ArrayList<Vector3> getPowerRequests();
}
