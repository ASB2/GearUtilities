package GU.api.conduit;

public interface IConduitConductor {

    boolean setNetwork(IConduitNetwork network);
    IConduitNetwork getNetwork();
    
    int[] getCoords();
}
