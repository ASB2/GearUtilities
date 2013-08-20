package GU.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

public class BlockPlantUpdater extends BlockBase {

    public BlockPlantUpdater(int id, Material material) {
        super(id, material);
    }

    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);

        Block plant = Block.blocksList[world.getBlockId(x, y + 1, z)];

        if ((plant != null) && (plant.getTickRandomly())) {

            if ((plant == this) && (world.getBlockMetadata(x, y + 1, z) == 1)) {

                plant.updateTick(world, x, y + 1, z, random);
            } else if ((plant instanceof IPlantable)) {

                int topIncrement = 1;
                int currentID = world.getBlockId(x, y + topIncrement, z);

                while (true) {

                    if (plant.blockID == currentID) {

                        topIncrement++;
                    } else {

                        topIncrement--;
                        currentID = world.getBlockId(x, y + topIncrement, z);
                        break;
                    }
                    currentID = world.getBlockId(x, y + topIncrement, z);
                }
                plant = Block.blocksList[currentID];
                plant.updateTick(world, x, y + topIncrement, z, random);
            }
        }
    }
}