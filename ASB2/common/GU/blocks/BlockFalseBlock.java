package GU.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class BlockFalseBlock extends BlockBase {

    public BlockFalseBlock(int par1, Material material) {
        super(par1, material);
        useStandardRendering = false;
        this.setHardness(Block.stone.blockHardness);
    }

    @Override
    public int getRenderType() {

        return 0;
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        return Block.stone.getIcon(side, metadata);
    }

    @Override
    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side) {

        return true;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {

        if (!world.isBlockIndirectlyGettingPowered(x, y, z))
            return null;

        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {

        float movementFactor = .3F;
        entity.fallDistance = 0;

        if (entity instanceof EntityLivingBase) {

            entity.extinguish();

            if (((EntityLivingBase) entity).isSneaking()) {

                entity.motionY -= movementFactor;
            }
            else {

                entity.motionY += movementFactor;
            }
        }
        else {
            
            entity.motionY += movementFactor;
        }
    }
}