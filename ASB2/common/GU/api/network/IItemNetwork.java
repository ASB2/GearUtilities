package GU.api.network;

import java.util.ArrayList;

import ASB2.vector.Vector3;

public interface IItemNetwork extends INetwork {

    boolean addAvaliableInventory(Vector3 requesting);
    boolean removeAvaliableInventory(Vector3 requesting);    
    
    ArrayList<Vector3> getAvaliableInventorys();
}
