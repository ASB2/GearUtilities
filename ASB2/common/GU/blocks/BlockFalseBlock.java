package GU.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.utils.UtilPlayers;

public class BlockFalseBlock extends BlockBase {

    public BlockFalseBlock(int par1, Material par3Material) {
        super(par1, par3Material);
        useStandardRendering = false;
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

        if (world.getBlockId(x, y + 1, z) == 0
                && world.getBlockId(x, y + 2, z) == 0
                || world.getBlockId(x, y + 1, z) == this.blockID
                || world.getBlockId(x, y + 2, z) == this.blockID) {

            entity.motionY += movementFactor;
        }

        if (entity instanceof EntityPlayer) {

            if ((UtilPlayers.isSpecialPlayer(((EntityPlayer) entity).username))) {

                entity.extinguish();
            }

            if (world.getBlockId(x, y + 1, z) == 0
                    && world.getBlockId(x, y + 2, z) == 0
                    || world.getBlockId(x, y + 1, z) == this.blockID
                    || world.getBlockId(x, y + 2, z) == this.blockID) {

                if (((EntityPlayer) entity).isSneaking()) {

                    entity.motionY -= movementFactor;
                }
            }
        }
    }
}