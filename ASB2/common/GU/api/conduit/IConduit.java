package GU.api.conduit;

import java.util.List;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * 
 * @author ASB2 Can be used on blocks and tiles
 */
public interface IConduit {
    
    /**
     * 
     * @param world
     *            of the block or tile
     * @param x
     *            of the block or tile
     * @param y
     *            of the block or tile
     * @param z
     *            of the block or tile
     * @return
     */
    List<TileEntity> getAvaliableTiles(World world, int x, int y, int z);
}
