package GU.api.conduit;

import net.minecraft.world.World;

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
    
    /**
     * Used in conjunction with the getNearestConduit in IConduitNetwork to see if the conduit meets the requirements.
     * @prams worldObj, conduit you are checking, id passed when getNearestConduit was called for personal use
     */
    boolean isConditValid(World world, IConduitConductor conduit, int id);
}
