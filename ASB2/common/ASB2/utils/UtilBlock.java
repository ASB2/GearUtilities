package ASB2.utils;

import java.util.ArrayList;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.IFluidBlock;

/**
 * 
 * @author ASB2
 * 
 */
public class UtilBlock {
    
    public static boolean isWaterInfine(World world, int x, int y, int z) {
        
        int around = 0;
        
        if (world.getBlock(x, y, z) == Blocks.water) {
            
            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                
                if (world.getBlock(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ) == Blocks.water) {
                    
                    around++;
                }
            }
        }
        return around >= 2;
    }
    
    public static boolean isBreakable(World world, int x, int y, int z) {
        
        Block block = world.getBlock(x, y, z);
        
        if (block != null && !UtilBlock.isBlockAir(world, x, y, z)) {
            
            if (!(block instanceof IFluidBlock)) {
                
                if (block.getBlockHardness(world, x, y, z) != -1) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean rotateBlock(World world, int x, int y, int z, ForgeDirection face) {
        
        Block block = world.getBlock(x, y, z);
        
        if (block != null) {
            
            return block.rotateBlock(world, x, y, z, face);
        }
        return false;
    }
    
    public static boolean isBlockAir(IBlockAccess world, int x, int y, int z) {
        
        Block block = world.getBlock(x, y, z);
        
        return block == null || block.isAir(world, x, y, z);
    }
    
    public static boolean placeBlockInAir(World world, int x, int y, int z, Block block, int metaData) {
        
        if (UtilBlock.isBlockAir(world, x, y, z)) {
            
            return world.setBlock(x, y, z, block, metaData, 3);
        }
        return false;
    }
    
    public static boolean isBlockIndirectlyGettingPowered(IBlockAccess world, int x, int y, int z) {
        
        boolean itWorked = false;
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            if (world.isBlockProvidingPowerTo(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, direction.ordinal()) > 0) {
                
                itWorked = true;
            }
        }
        return itWorked;
    }
    
    public static int getHighestRedstoneStrength(IBlockAccess world, int x, int y, int z) {
        
        int highest = 0;
        
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            
            int temp = world.isBlockProvidingPowerTo(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ, direction.getOpposite().ordinal());
            
            if (temp > highest) {
                
                highest = temp;
            }
        }
        return highest * 15;
    }
    
    public static void breakBlock(World world, int x, int y, int z) {
        
        Block block = world.getBlock(x, y, z);
        
        if (block != null) {
            
            world.playAuxSFX(2001, x, y, z, 0);
            block.breakBlock(world, x, y, z, block, world.getBlockMetadata(x, y, z));
            block.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockToAir(x, y, z);
        }
    }
    
    public static void breakBlockNoDrop(World world, int x, int y, int z) {
        
        Block block = world.getBlock(x, y, z);
        
        if (block != null) {
            
            world.playAuxSFX(2001, x, y, z, 0);
            world.setBlockToAir(x, y, z);
        }
    }
    
    public static boolean breakAndAddToInventory(IInventory inventory, World world, int x, int y, int z, boolean doWork) {
        
        Block block = world.getBlock(x, y, z);
        
        if (block != null) {
            
            ArrayList<ItemStack> items = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 1);
            
            if (!items.isEmpty()) {
                
                if (inventory == null) {
                    
                    return false;
                }
                
                for (ItemStack item : items) {
                    
                    if (!UtilInventory.addItemStackToInventory(inventory, item, false)) {
                        
                        return false;
                    }
                }
                
                if (doWork) {
                    
                    for (ItemStack item : items) {
                        
                        UtilInventory.addItemStackToInventory(inventory, item, doWork);
                    }
                    
                    UtilBlock.breakBlockNoDrop(world, x, y, z);
                    return true;
                }
            } else {
                
                UtilBlock.breakBlockNoDrop(world, x, y, z);
                return true;
            }
        }
        return false;
    }
    
    public static boolean breakAndAddToISidedInventory(ISidedInventory inventory, ForgeDirection direction, World world, int x, int y, int z, boolean doWork) {
        
        Block block = world.getBlock(x, y, z);
        
        if (block != null) {
            
            ArrayList<ItemStack> items = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), 1);
            
            if (!items.isEmpty()) {
                
                if (inventory == null) {
                    
                    return false;
                }
                
                for (ItemStack item : items) {
                    
                    if (!UtilInventory.addItemStackToISidedInventory(inventory, direction, item, false)) {
                        
                        return false;
                    }
                }
                
                if (doWork) {
                    
                    for (ItemStack item : items) {
                        
                        UtilInventory.addItemStackToISidedInventory(inventory, direction, item, doWork);
                    }
                    
                    UtilBlock.breakBlockNoDrop(world, x, y, z);
                    return true;
                }
            } else {
                
                UtilBlock.breakBlockNoDrop(world, x, y, z);
                return true;
            }
        }
        return false;
    }
    
    public static boolean breakAndAddToInventorySpawnExcess(IInventory inventory, World world, int x, int y, int z, int fortune, boolean doWork) {
        
        Block block = world.getBlock(x, y, z);
        
        if (block != null) {
            
            ArrayList<ItemStack> items = block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), fortune);
            
            if (!items.isEmpty()) {
                
                if (inventory == null) {
                    
                    return false;
                }
                
                if (doWork) {
                    
                    for (ItemStack item : items) {
                        
                        if (!UtilInventory.addItemStackToInventory(inventory, item, true)) {
                            
                            UtilBlock.spawnItemStackEntity(world, x, y, z, item, 20);
                        }
                    }
                    
                    UtilBlock.breakBlock(world, x, y, z);
                    return true;
                }
            } else {
                
                UtilBlock.breakBlock(world, x, y, z);
                return true;
            }
        }
        return false;
    }
    
    public static ArrayList<ItemStack> getItemStackDropped(World world, int x, int y, int z, int fortune) {
        
        if (fortune <= 0)
            fortune = 1;
        
        Block block = world.getBlock(x, y, z);
        
        if (block != null) {
            
            return block.getDrops(world, x, y, z, world.getBlockMetadata(x, y, z), fortune);
        }
        
        return new ArrayList<ItemStack>();
    }
    
    public static void spawnItemStackEntity(World world, double x, double y, double z, ItemStack item, int delayforPickup) {
        
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
}
