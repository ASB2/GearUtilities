package GU.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class BlockEtherealConnectedGlass extends BlockConnectedGlass {

    public BlockEtherealConnectedGlass(int id, Material material) {
        super(id, material);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        if (!world.isBlockIndirectlyGettingPowered(x, y, z))
            return null;

        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }
}
