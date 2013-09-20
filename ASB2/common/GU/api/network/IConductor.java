package GU.api.network;

import net.minecraft.tileentity.TileEntity;

public interface IConductor {

    /**
     * Sets the conduit network of the conductor
     */
    boolean setNetwork(INetwork network);
    
    /**
     * Gets the conduit of the network.
     * return can be null.
     */
    INetwork getNetwork();
    
    /**
     * The coordinates of the conduit
     */
    int[] getCoords();
    
    TileEntity[] getAvaliableTileEntities();
}
