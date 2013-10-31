package GU.api.network;

import java.util.ArrayList;

import net.minecraft.world.World;
import ASB2.vector.Vector3;

public interface INetwork {
    
    /**
     * Adds a conductor to the network
     */
    boolean addConductor(Vector3 conduit);
    
    /**
     * Adds a conductor to the network
     */
    boolean removeConductor(Vector3 conduit);
    
    /**
     * A list of all the conduits that are in this network
     * @return Conduits in the network
     */
    ArrayList<Vector3> getConductors();

    boolean addFluidInterface(Vector3 vector);
    boolean removeFluidInterface(Vector3 vector);
    ArrayList<Vector3> getFluidInterfaces();
    
    boolean addItemInterface(Vector3 vector);
    boolean removeItemInterface(Vector3 vector);
    ArrayList<Vector3> getItemInterfaces();
    
    boolean addPowerInterface(Vector3 vector);
    boolean removePowerInterface(Vector3 vector);
    ArrayList<Vector3> getPowerInterfaces();
    /**
     * Should merge everything in the network the method is called in into the provided network.
     */
    void mergeNetworks(World world, ArrayList<Vector3> newNetwok);
    
    void onNetworkConductorBroken(World world, Vector3 tile);
}
