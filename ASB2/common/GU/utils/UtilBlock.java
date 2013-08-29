package GU.utils;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class UtilBlock {

    public static boolean placeBlockInAir(World world, int x, int y, int z, int blockId, int metaData) {

        if (world.getBlockId(x, y, z) == 0) {

            return world.setBlock(x, y, z, blockId, metaData, 3);
        }
        return false;
    }

    public static boolean isBlockIndirectlyGettingPowered(IBlockAccess world, int x, int y, int z) {

        boolean itWorked = false;

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            if(world.isBlockProvidingPowerTo(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, direction.ordinal()) > 0) {

                itWorked = true;
            }
        }
        return itWorked;
    }

    public static int getHighestRedstoneStrength(IBlockAccess world, int x, int y, int z) {

        int highest = 0;

        for(ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {

            int temp = world.isBlockProvidingPowerTo(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, direction.getOpposite().ordinal());
         
            if(temp > highest) {
                
                highest = temp;
            }
        }
        return highest * 15;
    }

    public static boolean setBlock(World world, int x, int y, int z, int blockId, int metaData) {

        if (world.blockExists(x, y, z)) {

            if (blockId == 0) {

                world.setBlockToAir(x, y, z);
                return true;
            }

            else {

                return world.setBlockMetadataWithNotify(x, y, z, blockId, metaData);
            }
        }
        return false;
    }

    public static void breakBlock(World world, int x, int y, int z) {

        if (world.getBlockId(x, y, z) != 0) {

            world.playAuxSFX(2001, x, y, z, world.getBlockId(x, y, z) + (world.getBlockMetadata(x, y, z) << 12));
            Block.blocksList[world.getBlockId(x, y, z)].dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    public static void breakAndAddToInventory(IInventory inventory, World world, int x, int y, int z, int fortune, boolean dropExtra) {

        if(fortune <= 0)
            fortune = 1;

        if (world.getBlockId(x, y, z) != 0) {

            Block block = Block.blocksList[world.getBlockId(x, y, z)];

            ArrayList<ItemStack> items = block.getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), fortune);

            for (ItemStack item : items) {

                if (world.rand.nextFloat() <= fortune) {

                    if (inventory != null) {

                        if (!UtilInventory.addItemStackToInventory(inventory, item, true)) {

                            if (dropExtra) {

                                UtilBlock.spawnItemStackEntity(world, x, y, z, item, 1);
                            }
                        }
                    } else if(dropExtra) {

                        UtilBlock.spawnItemStackEntity(world, x, y, z, item, 1);
                    }
                }
            }

            world.playAuxSFX( 2001, x, y, z, world.getBlockId(x, y, z) + (world.getBlockMetadata(x, y, z) << 12));
            world.setBlockToAir(x, y, z);
        }
    }

    public static ArrayList<ItemStack> getItemStackDropped(World world, int x, int y, int z, int fortune) {

        if(fortune <= 0)
            fortune = 1;

        if (world.getBlockId(x, y, z) != 0) {

            Block block = Block.blocksList[world.getBlockId(x, y, z)];

            return block.getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), fortune);
        }

        return new ArrayList<ItemStack>();
    }

    public static void spawnItemStackEntity(World world, int x, int y, int z, ItemStack item, int delayforPickup) {

        if (world.getGameRules().getGameRuleBooleanValue("doTileDrops") && !world.isRemote) {

            float f = 0.7F;
            double d0 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
            double d1 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
            double d2 = world.rand.nextFloat() * f + (1.0F - f) * 0.5D;
            EntityItem entityitem = new EntityItem(world, x + d0, y + d1, z + d2, item);
            entityitem.delayBeforeCanPickup = delayforPickup;
            world.spawnEntityInWorld(entityitem);
        }
    }

    public static boolean cycle2DBlock(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int radius, IBlockCycle cycle, int id) {

        boolean isSuccessful = false;

        if (side.offsetX != 0) {

            for (int i = -radius; i <= radius; i++) {

                for (int n = -radius; n <= radius; n++) {

                    if (cycle.execute(player, world, x, y + i, z + n, side, id))
                        isSuccessful = true;
                }
            }
        }

        if (side.offsetY != 0) {

            for (int i = -radius; i <= radius; i++) {

                for (int n = -radius; n <= radius; n++) {

                    if (cycle.execute(player, world, x + i, y, z + n, side, id))
                        isSuccessful = true;
                }
            }
        }

        if (side.offsetZ != 0) {

            for (int i = -radius; i <= radius; i++) {

                for (int n = -radius; n <= radius; n++) {

                    if (cycle.execute(player, world, x + i, y + n, z, side, id))
                        isSuccessful = true;
                }
            }
        }

        return isSuccessful;
    }

    /*
     * Sends the coordinates of every block within a certain radius
     */

    public static boolean cycle3DBlock(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int radius, IBlockCycle cycle, int id) {
        boolean isSuccessful = false;

        if (side.offsetX != 0) {

            for (int i = -radius; i <= radius; i++) {

                if (UtilBlock.cycle2DBlock(player, world, x + i, y, z, side,
                        radius, cycle, id))
                    isSuccessful = true;
            }
        }

        if (side.offsetY != 0) {

            for (int i = -radius; i <= radius; i++) {

                if (UtilBlock.cycle2DBlock(player, world, x, y + i, z, side,
                        radius, cycle, id))
                    isSuccessful = true;
            }
        }

        if (side.offsetZ != 0) {

            for (int i = -radius; i <= radius; i++) {

                if (UtilBlock.cycle2DBlock(player, world, x, y, z + 1, side,
                        radius, cycle, id))
                    isSuccessful = true;
            }
        }
        return isSuccessful;
    }

    public static boolean cycle3DBlock(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int radius, int distance, IBlockCycle cycle, int id) {
        boolean isSuccessful = false;

        for (int i = 0; i < distance; i++) {

            if (side.offsetX != 0) {

                if (side.offsetX > 0) {

                    if (UtilBlock.cycle2DBlock(player, world, x - i, y, z, side, radius, cycle, id))
                        isSuccessful = true;
                }

                if (side.offsetX < 0) {

                    if (UtilBlock.cycle2DBlock(player, world, x + i, y, z, side, radius, cycle, id))
                        isSuccessful = true;
                }
            }

            if (side.offsetY != 0) {

                if (side.offsetY > 0) {

                    if (UtilBlock.cycle2DBlock(player, world, x, y - i, z, side, radius, cycle, id))
                        isSuccessful = true;
                }

                if (side.offsetY < 0) {

                    if (UtilBlock.cycle2DBlock(player, world, x, y + i, z, side, radius, cycle, id))
                        isSuccessful = true;
                }
            }

            if (side.offsetZ != 0) {

                if (side.offsetZ > 0) {

                    if (UtilBlock.cycle2DBlock(player, world, x, y, z - i, side, radius, cycle, id))
                        isSuccessful = true;
                }

                if (side.offsetZ < 0) {

                    if (UtilBlock.cycle2DBlock(player, world, x, y, z + i, side, radius, cycle, id))
                        isSuccessful = true;
                }
            }
        }
        return isSuccessful;
    }
}
