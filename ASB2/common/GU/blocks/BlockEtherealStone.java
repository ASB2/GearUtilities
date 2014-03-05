package GU.blocks;

import GU.render.BlockSimpleRenderer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockEtherealStone extends BlockBase {

    public BlockEtherealStone(int id, Material material) {
        super(id, material);
        useStandardRendering = false;
    }

    @Override
    public int getRenderType() {

        return BlockSimpleRenderer.renderID;
    }

    @Override
    public Icon getIcon(int side, int metadata) {

        return Block.stone.getIcon(side, metadata);
    }

    @Override
    public void registerIcons(IconRegister iconRegister) {

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
            } else {

                entity.motionY += movementFactor;
            }
        } else {

            entity.motionY += movementFactor;
        }
    }
}