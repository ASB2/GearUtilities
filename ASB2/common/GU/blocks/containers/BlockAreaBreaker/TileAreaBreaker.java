package GU.blocks.containers.BlockAreaBreaker;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.IBlockCycle;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilInventory;
import GU.api.wait.Wait;
import GU.blocks.containers.Inventory;
import GU.blocks.containers.TileBase;

public class TileAreaBreaker extends TileBase implements IInventory, IBlockCycle {

    int radius = 1;
    int debth = 3;

    public TileAreaBreaker() {

        waitTimer = new Wait(10, this, 0);
        tileInventory = new Inventory(10, 64, "Area Breaker", true);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();
    }

    @Override
    public void trigger(int id) {

        radius = 10;
        debth= 10;
        
        if(!worldObj.isRemote) {

            if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

                UtilBlock.cycle3DBlock(null, worldObj, xCoord + this.getOrientation().getOpposite().offsetX, yCoord + this.getOrientation().getOpposite().offsetY, zCoord + this.getOrientation().getOpposite().offsetZ, this.getOrientation(), radius, debth, this, 0);
            }
        }
    }

    @Override
    public int getSizeInventory() {

        return tileInventory.getSizeInventory();
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return tileInventory.getStackInSlot(i);
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {

        return UtilInventory.decreaseSlotContents(this, i, j);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return tileInventory.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {

        tileInventory.setInventorySlotContents(i, itemstack);
    }

    @Override
    public String getInvName() {

        return tileInventory.getInvName();
    }

    @Override
    public boolean isInvNameLocalized() {

        return true;
    }

    @Override
    public int getInventoryStackLimit() {

        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {

        return true;
    }

    @Override
    public void openChest() {
        // TODO Auto-generated method stub

    }

    @Override
    public void closeChest() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack) {

        return true;
    }

    @Override
    public boolean execute(EntityLivingBase player, World world, int x, int y, int z, ForgeDirection side, int id) {

        if(world.blockExists(x, y, z)) {
            
            TileEntity tile = UtilDirection.translateDirectionToTile(this, worldObj, this.getOrientation());

            if(tile != null && tile instanceof IInventory) {

                if(world.rand.nextInt(debth * radius * 10) == 0) {

                    if(UtilBlock.isBreakable(world, x, y, z)) {

                        UtilBlock.breakAndAddToInventory((IInventory)tile, world, x, y, z, true);
                    }
                }
            }
        }
        return false;
    }
}
