package GU.api.conduit;

import java.util.ArrayList;

import net.minecraft.world.World;
import GU.api.conduit.packet.*;

public interface IConduitNetwork {

    /**
     * Easy way for things to update a network. The coords should be the coordinates of the tile calling the method.
     */
    void updateNetwork(World world);
    
    /**
     * Should add a packet to a list to get updated.
     */
    void addConduitPacketToQuene(IConduitPacket packet);
    
    /**
     * A list of all the conduits that are in this network
     * @return Packets that havent't found a place to place their cargo.
     */
    ArrayList<IConduitPacket> getQuenedPackets();
    
    /**
     * Adds a conductor to the network
     */
    boolean addConductor(World world, IConduitConductor conduit);
    
    /**
     * Should merge everything in the current network into the new network.
     */
    void mergeNetworks(World world, ArrayList<IConduitConductor> newNetwok);
    
    /**
     * A list of all the conduits that are in this network
     * @return Conduits in the network
     */
    ArrayList<IConduitConductor> getAvaliableConductors();
}
