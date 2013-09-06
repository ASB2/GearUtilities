package GU.blocks.containers.BlockClusterSender;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.blocks.containers.ContainerBase;

public class BlockClusterSender extends ContainerBase {

    public BlockClusterSender(int id, Material material) {
        super(id, material);
        
        this.useStandardRendering = false;
        this.registerTile(TileClusterSender.class);
    }

    public ForgeDirection[] getValidRotations(World worldObj, int x, int y, int z) {

        return ForgeDirection.VALID_DIRECTIONS;
    }
    
    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z) {

        float minWidth = 0, minHeight = 0;

        float maxWidth = 1, maxHeight = .3F;

        switch (ForgeDirection.getOrientation(world.getBlockMetadata(x, y, z))) {

            case DOWN: {

                this.setBlockBounds(minWidth, maxWidth - maxHeight, minWidth, maxWidth, 1, maxWidth);
                return;
            }

            case UP: {

                this.setBlockBounds(minWidth, minHeight, minWidth, maxWidth, maxHeight, maxWidth);
                break;
            }

            case NORTH: {

                this.setBlockBounds(minWidth, minWidth, maxWidth - maxHeight, maxWidth, maxWidth, maxWidth);
                break;
            }

            case SOUTH: {

                this.setBlockBounds(minWidth, minWidth, minWidth, maxWidth, maxWidth, maxHeight);
                break;
            }

            case WEST: {

                this.setBlockBounds(1 - maxHeight, minWidth, minWidth, maxWidth, maxWidth, maxWidth);
                break;
            }

            case EAST: {

                this.setBlockBounds(minWidth, minWidth, minWidth, maxHeight, maxWidth, maxWidth);
                break;
            }

            default: {

                this.setBlockBounds(0, 0, 0, 1, 1, 1);
                break;
            }
        }
    }
    
    @Override
    public TileEntity createNewTileEntity(World world) {

        return new TileClusterSender();
    }
}
