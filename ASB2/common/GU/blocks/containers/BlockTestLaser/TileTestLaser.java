package GU.blocks.containers.BlockTestLaser;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import GU.api.IWrenchable;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileTestLaser extends TileBase implements IWrenchable {

    public TileTestLaser() {

        orientation = ForgeDirection.SOUTH;
        waitTimer = new Wait(10, this, 0);
    }

    public ForgeDirection getOrientation() {

        switch(this.getBlockMetadata()) {

            case 0: return ForgeDirection.SOUTH;
            case 1: return ForgeDirection.WEST;
            case 2: return ForgeDirection.NORTH;
            case 3: return ForgeDirection.EAST;
            default: return ForgeDirection.SOUTH;
        }
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

        switch(getOrientation()) {

            case SOUTH: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3); return;
            case WEST: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 2, 3); return;
            case NORTH: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 3, 3); return;       
            case EAST: worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3); return;    

            default : worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3); return;
        }
    }
}
