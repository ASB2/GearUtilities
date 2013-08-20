package GU.blocks.containers.BlockLamp;

import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.blocks.containers.ContainerBase;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockLamp extends ContainerBase {

    public BlockLamp(int id, Material material) {
        super(id, material);

        setLightValue(1.0F);
        this.registerTile(TileLamp.class);
        useStandardRendering = false;
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x,
            int y, int z) {

        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getCollisionBoundingBoxFromPool(world, x, y, z);
    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x,
            int y, int z) {

        this.setBlockBoundsBasedOnState(world, x, y, z);
        return super.getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y,
            int z) {

        float minWidth = 0, minHeight = 0;

        float maxWidth = 1, maxHeight = .25F;

        switch (ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z))) {

            case DOWN: {

                this.setBlockBounds(minWidth, 1 - maxHeight, minWidth,
                        maxWidth, 1, maxWidth);
                return;
            }

            case UP: {

                this.setBlockBounds(minWidth, minHeight, minWidth, maxWidth,
                        maxHeight, maxWidth);
                break;
            }

            case NORTH: {

                this.setBlockBounds(minWidth, minWidth, 1 - maxHeight,
                        maxWidth, maxWidth, maxWidth);
                break;
            }

            case SOUTH: {

                this.setBlockBounds(minWidth, minWidth, minWidth, maxWidth,
                        maxWidth, maxHeight);
                break;
            }

            case WEST: {

                this.setBlockBounds(1 - maxHeight, minWidth, minWidth,
                        maxWidth, maxWidth, maxWidth);
                break;
            }

            case EAST: {

                this.setBlockBounds(minWidth, minWidth, minWidth, maxHeight,
                        maxWidth, maxWidth);
                break;
            }

            default: {

                this.setBlockBounds(0, 0, 0, 1, 1, 1);
                break;
            }
        }
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z,
            Entity entity) {
        super.onEntityCollidedWithBlock(world, x, y, z, entity);
    }

    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileLamp();
    }
}
