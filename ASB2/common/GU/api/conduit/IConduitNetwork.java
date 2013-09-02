package GU.api.conduit;

import java.util.ArrayList;

import net.minecraft.world.World;

public interface IConduitNetwork {

    /*
     * Easy way for things to update a network. The coords should be the coordinates of the tile calling the method.
     */
    void updateNetwork(World world, int x, int y, int z);
    
    /*
     * Adds a conductor to the network
     */
    boolean addConductor(World world, IConduitConductor conduit);
    
    /*
     * Should merge everything in the current network into the new network.
     */
    void mergeNetworks(World world, ArrayList<IConduitConductor> newNetwok);
    
    /*
     * Traces a path from the provided source conduit to the destination conduit
     * @return if the path can be traced.
     */
    boolean tracePath(World world, IConduitConductor source, IConduitConductor destination);
    
    /*
     * A list of all the conduits that are in this network
     * @return Conduits in the network
     */
    ArrayList<IConduitConductor> getAvaliableConductors();
}
