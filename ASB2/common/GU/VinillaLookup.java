package GU;

import ASB2.utils.UtilBlock;
import net.minecraft.block.Block;
import net.minecraft.world.World;

public class VinillaLookup {

    public static void shiftingBlockLookup(World world, int x, int y, int z) {

        int id = world.getBlockId(x, y, z);

        if(id == Block.glass.blockID) {

            UtilBlock.breakBlock(world, x, y, z);
            world.setBlock(x, y, z, BlockRegistry.BlockGlassPipe.blockID);
        }
    }
}
