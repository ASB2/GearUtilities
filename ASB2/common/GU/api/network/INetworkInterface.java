package GU.api.network;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public interface INetworkInterface {

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
    
    TileEntity[] getAvaliableTileEntities(ForgeDirection direction);
}
