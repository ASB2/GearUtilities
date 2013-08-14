package GU.blocks.containers.BlockLamp;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.blocks.containers.TileBase;
import GU.utils.UtilBlock;
import GU.utils.UtilDirection;

public class TileLamp extends TileBase {

    public TileLamp() {

    }

    @Override
    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
        }
    }

    @Override
    public void trigger(int id) {

    }

    @Override
    public void triggerBlock(World world, EntityLivingBase player, ItemStack itemStack, int x, int y, int z, int side) {

        if(player.isSneaking()) {

            if(player instanceof EntityPlayer) {
                UtilBlock.breakAndAddToInventory(((EntityPlayer)player).inventory, worldObj, x, y, z, 0, true);
            }
            else {
                
                UtilBlock.breakAndAddToInventory(null, worldObj, x, y, z, 0, true);
            }
        }

        switch(getOrientation()) {

            case DOWN : worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.UP), 3); return;
            case UP: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.SOUTH), 3); return;
            case SOUTH: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.WEST), 3); return;
            case WEST: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.NORTH), 3); return;
            case NORTH: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.EAST), 3); return;
            case EAST: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.DOWN), 3); return;

            default : worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3); return;
        }        
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        if(isSneaking) {

            UtilBlock.breakAndAddToInventory(null, worldObj, x, y, z, 0, true);
        }

        switch(getOrientation()) {

            case DOWN : worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.UP), 3); return;
            case UP: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.SOUTH), 3); return;
            case SOUTH: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.WEST), 3); return;
            case WEST: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.NORTH), 3); return;
            case NORTH: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.EAST), 3); return;
            case EAST: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, UtilDirection.translateDirectionToNumber(ForgeDirection.DOWN), 3); return;

            default : worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3); return;
        }        
    }
}
