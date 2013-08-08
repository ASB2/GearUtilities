package GU.utils;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class UtilBlock {

    public static boolean placeBlockInAir(World world, int x, int y, int z, int blockId, int metaData) {

        if(world.getBlockId( x, y , z) == 0) {


            return world.setBlock(x, y, z, blockId, metaData, 3);
        }
        return false;
    }

    public static boolean setBlock(World world, int x, int y, int z, int blockId, int metaData) {

        if(world.blockExists(x, y, z)) {

            if(blockId == 0) {

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

        if(world.getBlockId(x,y,z) != 0) {       

            world.playAuxSFX(2001, x, y, z, world.getBlockId(x,y,z) + (world.getBlockMetadata(x, y, z) << 12));
            Block.blocksList[world.getBlockId(x,y,z)].dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }

    public static void breakAndAddToInventory(IInventory inventory, World world, int x, int y, int z, int fortune, boolean dropExtra) {

        if(world.getBlockId(x,y,z) != 0) {

            Block block =  Block.blocksList[world.getBlockId(x,y,z)];

            ArrayList<ItemStack> items = block.getBlockDropped(world, x, y, z, world.getBlockMetadata(x, y, z), fortune);

            for (ItemStack item : items) {

                if (world.rand.nextFloat() <= fortune) {

                    if(!UtilInventory.addItemStackToInventory(inventory, item)) {

                        if(dropExtra) {

                            UtilBlock.spawnItemStackEntity(world, x, y, z, item, 0);
                        }
                    }
                }
            }

            world.playAuxSFX(2001, x, y, z, world.getBlockId(x,y,z) + (world.getBlockMetadata(x, y, z) << 12));
            world.setBlockToAir(x, y, z);
        }
    }

    public static void spawnItemStackEntity(World world, int x, int y, int z, ItemStack item, int delayforPickup) {

        if (world.getGameRules().getGameRuleBooleanValue("doTileDrops")) {

            float f = 0.7F;
            double d0 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d1 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d2 = (double)(world.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            EntityItem entityitem = new EntityItem(world, (double)x + d0, (double)y + d1, (double)z + d2, item);
            entityitem.delayBeforeCanPickup = delayforPickup;
            world.spawnEntityInWorld(entityitem);
        }
    }

    public static boolean cycle2DBlock(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int radius, IBlockCycle cycle, int id)
    {

        boolean isSuccessful = false;


        if(side.offsetX != 0) {

            for(int i = -radius; i <= radius; i++){

                for(int n = -radius; n <= radius; n++) {

                    if(cycle.execute(player, world, x , y + i, z + n, side, id))
                        isSuccessful = true;
                }
            }
        }

        if(side.offsetY != 0) {

            for(int i = -radius; i <= radius; i++){

                for(int n = -radius; n <= radius; n++) {

                    if(cycle.execute(player, world,  x + i, y, z + n, side, id))
                        isSuccessful = true;
                }
            }
        }

        if(side.offsetZ != 0) {

            for(int i = -radius; i <= radius; i++) {

                for(int n = -radius; n <= radius; n++) {

                    if(cycle.execute(player, world,  x + i, y + n, z, side, id))
                        isSuccessful = true;
                }
            }
        }

        return isSuccessful;
    }

    /*
     * Sends the coordinates of every block within a certain radius
     */

    public static boolean cycle3DBlock(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int radius, IBlockCycle cycle, int id)
    {
        boolean isSuccessful = false;

        if(side.offsetX != 0) {

            for(int i = -radius; i <= radius; i++) {

                if(UtilBlock.cycle2DBlock(player, world, x + i, y, z, side, radius, cycle, id))
                    isSuccessful = true;
            }
        }

        if(side.offsetY != 0) {

            for(int i = -radius; i <= radius; i++) {

                if(UtilBlock.cycle2DBlock(player, world, x , y + i, z, side, radius, cycle, id))
                    isSuccessful = true;
            }
        }

        if(side.offsetZ != 0) {

            for(int i = -radius; i <= radius; i++) {

                if(UtilBlock.cycle2DBlock(player, world, x , y, z + 1, side, radius, cycle, id))
                    isSuccessful = true;
            }
        }
        return isSuccessful;
    }

    public static boolean cycle3DBlock(EntityPlayer player, World world, int x, int y, int z, ForgeDirection side, int radius, int distance, IBlockCycle cycle, int id)
    {
        boolean isSuccessful = false;

        for(int i = 0; i < distance; i++) {

            if(side.offsetX != 0) {

                if(side.offsetX > 0) {

                    if(UtilBlock.cycle2DBlock(player, world, x + i, y, z, side, radius, cycle, id))
                        isSuccessful = true;
                }

                if(side.offsetX < 0) {

                    if(UtilBlock.cycle2DBlock(player, world, x - i, y, z, side, radius, cycle, id))
                        isSuccessful = true;
                }
            }

            if(side.offsetY != 0) {

                if(side.offsetY > 0) {

                    if(UtilBlock.cycle2DBlock(player, world, x, y - i, z, side, radius, cycle, id))
                        isSuccessful = true;
                }

                if(side.offsetY < 0) {

                    if(UtilBlock.cycle2DBlock(player, world, x, y + i, z, side, radius, cycle, id))
                        isSuccessful = true;
                }
            }

            if(side.offsetZ != 0) {

                if(side.offsetZ > 0) {

                    if(UtilBlock.cycle2DBlock(player, world, x, y, z - i, side, radius, cycle, id))
                        isSuccessful = true;
                }

                if(side.offsetZ < 0) {

                    if(UtilBlock.cycle2DBlock(player, world, x, y, z + i, side, radius, cycle, id))
                        isSuccessful = true;
                }
            }
        }
        return isSuccessful;
    }
}
