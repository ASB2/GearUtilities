package GU.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.utils.UtilBlock;

public class VinillaLookup {
    
    public static boolean shiftVinillaBlock(World world, int x, int y, int z, int side) {
        
        if (world.getBlockId(x, y, z) == Block.brick.blockID) {
           
            world.destroyBlock(x, y, z, false);
            UtilBlock.spawnItemStackEntity(world, x, y, z, new ItemStack(Item.brick, 4), 0);
        }
        return false;
    }
}
