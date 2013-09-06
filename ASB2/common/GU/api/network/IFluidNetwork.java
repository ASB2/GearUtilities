package GU.api.network;

import java.util.ArrayList;

import ASB2.vector.Vector3;

public interface IFluidNetwork extends INetwork {

    boolean addAvaliableTank(Vector3 requesting);
    boolean removeAvaliableTank(Vector3 requesting);    
    
    ArrayList<Vector3> getAvaliableTanks();
}
