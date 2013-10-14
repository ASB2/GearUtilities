package GU;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class VinillaLookup {

    public static void shiftingBlockLookup(World world, int x, int y, int z) {

        int id = world.getBlockId(x, y, z);

        if(id == Block.glass.blockID) {

            world.setBlock(x, y, z, BlockRegistry.BlockGlassPipe.blockID);
        }
    }
}
