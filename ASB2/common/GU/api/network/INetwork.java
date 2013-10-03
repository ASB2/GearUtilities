package GU.api.network;

import java.util.ArrayList;

import net.minecraft.world.World;
import ASB2.vector.Vector3;

public interface INetwork {
    
    /**
     * Adds a conductor to the network
     */
    boolean addConductor(World world, Vector3 conduit);
    
    /**
     * Adds a conductor to the network
     */
    boolean removeConductor(World world, Vector3 conduit);
    
    /**
     * Should merge everything in the network the method is called in into the provided network.
     */
    void mergeNetworks(World world, ArrayList<Vector3> newNetwok);
    
    /**
     * A list of all the conduits that are in this network
     * @return Conduits in the network
     */
    ArrayList<Vector3> getAvaliableConductors();
    
    
    boolean addPowerRequest(Vector3 requesting);
    boolean removePowerRequest(Vector3 requesting);    
    ArrayList<Vector3> getPowerRequests();
    
    boolean addAvaliableInventory(Vector3 requesting);
    boolean removeAvaliableInventory(Vector3 requesting);
    ArrayList<Vector3> getAvaliableInventorys();
    
    boolean addAvaliableTank(Vector3 requesting);
    boolean removeAvaliableTank(Vector3 requesting);    
    ArrayList<Vector3> getAvaliableTanks();
}
