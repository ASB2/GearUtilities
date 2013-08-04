package GU.api.power;


public interface IPowerMisc {
    
    /**
     * Name of the block
     */
    String getName();
    
    /**
     * Returns power provider class of the tile
     */
    PowerProvider getPowerProvider();
}
