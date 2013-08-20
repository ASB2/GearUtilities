package GU.blocks.containers.BlockTestLaser;

import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import GU.blocks.containers.ContainerBase;

public class BlockTestLaser extends ContainerBase {

    public BlockTestLaser(int id, Material material) {
        super(id, material);

        this.registerTile(TileTestLaser.class);
        useStandardRendering = false;
    }

    @Override
    public void onBlockPlacedBy(World world, int x, int y, int z,
            EntityLivingBase entity, ItemStack itemStack) {

        int roatation = MathHelper
                .floor_double(entity.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        // South 0 = rotation
        // West 1 = rotation
        // North 2 = rotation
        // East 3 = rotation

        if (roatation == 0) {

            world.setBlockMetadataWithNotify(x, y, z, 0, 2);
        }

        if (roatation == 1) {

            world.setBlockMetadataWithNotify(x, y, z, 1, 2);
        }

        if (roatation == 2) {

            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (roatation == 3) {

            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }
    }

    @Override
    public int onBlockPlaced(World world, int x, int y, int z, int sideHit,
            float hitX, float hitY, float hitZ, int metaData) {

        return 0;
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileTestLaser();
    }
}
