package GUOLD.api;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import ASB2.utils.UtilBlock;

public class VinillaLookup {
    
    public static boolean shiftVinillaBlock(World world, int x, int y, int z, int side) {
        
        if (world.getBlock(x, y, z) == Blocks.brick_block) {
            
            UtilBlock.breakBlock(world, x, y, z);
            UtilBlock.spawnItemStackEntity(world, x, y, z, new ItemStack(Items.brick, 4), 0);
        }
        return false;
    }
}
