package GU.api.conduit;


public interface IConduitConductor {

    /**
     * Sets the conduit network of the conductor
     */
    boolean setNetwork(IConduitNetwork network);
    
    /**
     * Gets the conduit of the network.
     * return can be null.
     */
    IConduitNetwork getNetwork();
    
    /**
     * The coordinates of the conduit
     */
    int[] getCoords();
}
