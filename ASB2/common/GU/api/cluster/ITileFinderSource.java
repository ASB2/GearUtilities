package GU.api.cluster;

import net.minecraft.world.World;
import ASB2.vector.Vector3;
import GU.entity.EntityTileFinder.EntityTileFinder;

public interface ITileFinderSource {

    /**
     * Called by the block that is sending a clustor.
     * 
     * @param source
     * @param side
     * @param position
     * @param clustor
     * @param id
     */

    void onCollision(World world, Vector3 position, EntityTileFinder tile, int id);
}
