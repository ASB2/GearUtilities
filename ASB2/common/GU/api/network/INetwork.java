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
    
    boolean addNetworkInterface(Vector3 interfaces);
    boolean removeNetworkInterface(Vector3 interfaces);
    ArrayList<Vector3> getNetworkInterfaces();
}
