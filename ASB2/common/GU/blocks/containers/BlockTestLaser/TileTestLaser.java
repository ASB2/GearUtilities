package GU.blocks.containers.BlockTestLaser;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import GU.api.IWrenchable;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.fx.TestEffect;
import GU.power.GUPowerProvider;
import GU.utils.UtilRender;
import GU.vector.Vector3;

public class TileTestLaser extends TileBase implements IWrenchable, IPowerMisc,
        IInventory {

    public TileTestLaser() {

        powerProvider = new GUPowerProvider(1000, PowerClass.LOW, State.SINK);
        orientation = ForgeDirection.SOUTH;
        waitTimer = new Wait(10, this, 0);
    }

    @Override
    public ForgeDirection getOrientation() {

        switch (this.getBlockMetadata()) {

            case 0:
                return ForgeDirection.SOUTH;
            case 1:
                return ForgeDirection.WEST;
            case 2:
                return ForgeDirection.NORTH;
            case 3:
                return ForgeDirection.EAST;
            default:
                return ForgeDirection.SOUTH;
        }
    }

    @Override
    public void updateEntity() {

        this.powerProvider.setPower(this.powerProvider.getPowerMax());
        if (!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            if (this.findLaserConnection(getOrientation(), 100) != null
                    && this.powerProvider.canUsePower(10,
                            ForgeDirection.UNKNOWN)) {

                TileEntity tile = this.findLaserConnection(getOrientation(),
                        100);

                this.powerProvider.usePower(10, ForgeDirection.UNKNOWN);
                TestEffect beam = new TestEffect(worldObj, new Vector3(
                        xCoord + .5, yCoord + .9, zCoord + .5), new Vector3(
                        tile.xCoord + .5, tile.yCoord + .5, tile.zCoord + .5));
                UtilRender.renderFX(beam);
            }
        }
    }

    @SuppressWarnings("unused")
    public TileEntity findLaserConnection(ForgeDirection direction, int distance) {

        for (int i = 0; i < distance; i++) {

            if (direction.offsetX != 0) {

                if (direction.offsetX > 0) {

                    TileEntity tile = worldObj.getBlockTileEntity(xCoord + i,
                            yCoord, zCoord);

                    // if(tile != null && tile instanceof ILaserReciever && tile
                    // != this) {
                    //
                    // return tile;
                    // }

                    if (worldObj.isBlockSolidOnSide(xCoord + i, yCoord, zCoord,
                            direction.getOpposite())) {

                        return null;
                    }
                }

                if (direction.offsetX < 0) {

                    TileEntity tile = worldObj.getBlockTileEntity(xCoord - i,
                            yCoord, zCoord);

                    // if(tile != null && tile instanceof ILaserReciever && tile
                    // != this) {
                    //
                    // return tile;
                    // }

                    if (worldObj.isBlockSolidOnSide(xCoord - i, yCoord, zCoord,
                            direction.getOpposite())) {

                        return null;
                    }
                }
            }

            if (direction.offsetY != 0) {

                if (direction.offsetY > 0) {

                    TileEntity tile = worldObj.getBlockTileEntity(xCoord,
                            yCoord + i, zCoord);

                    // if(tile != null && tile instanceof ILaserReciever && tile
                    // != this) {
                    //
                    // return tile;
                    // }

                    if (worldObj.isBlockSolidOnSide(xCoord, yCoord + i, zCoord,
                            direction.getOpposite())) {

                        return null;
                    }
                }

                if (direction.offsetY < 0) {

                    TileEntity tile = worldObj.getBlockTileEntity(xCoord,
                            yCoord - i, zCoord);

                    // if(tile != null && tile instanceof ILaserReciever && tile
                    // != this) {
                    //
                    // return tile;
                    // }

                    if (worldObj.isBlockSolidOnSide(xCoord, yCoord - i, zCoord,
                            direction.getOpposite())) {

                        return null;
                    }
                }
            }

            if (direction.offsetZ != 0) {

                if (direction.offsetZ > 0) {

                    TileEntity tile = worldObj.getBlockTileEntity(xCoord,
                            yCoord, zCoord + i);

                    // if(tile != null && tile instanceof ILaserReciever && tile
                    // != this) {
                    //
                    // return tile;
                    // }

                    if (worldObj.isBlockSolidOnSide(xCoord, yCoord, zCoord + i,
                            direction.getOpposite())) {

                        return null;
                    }
                }

                if (direction.offsetZ < 0) {

                    TileEntity tile = worldObj.getBlockTileEntity(xCoord,
                            yCoord, zCoord - i);

                    // if(tile != null && tile instanceof ILaserReciever && tile
                    // != this) {
                    //
                    // return tile;
                    // }

                    if (worldObj.isBlockSolidOnSide(xCoord, yCoord, zCoord - i,
                            direction.getOpposite())) {

                        return null;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void trigger(int id) {

    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
    }

    @Override
    public int getSizeInventory() {
        // TODO Auto-generated method stub
        return tileItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {

        if (slot <= tileItemStacks.length) {

            return tileItemStacks[slot];
        }
        return null;

    }

    @Override
    public ItemStack decrStackSize(int slot, int amt) {

        ItemStack stack = getStackInSlot(slot);

        if (stack != null) {

            if (stack.stackSize <= amt) {

                setInventorySlotContents(slot, null);
            } else {

                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {

                    setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {

        ItemStack stack = getStackInSlot(slot);

        if (stack != null) {

            setInventorySlotContents(slot, null);
        }
        return stack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {

        tileItemStacks[slot] = itemStack;
    }

    @Override
    public String getInvName() {

        return "Test Laser";
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
    public boolean isUseableByPlayer(EntityPlayer player) {

        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this
                && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5,
                        zCoord + 0.5) < 64;
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
    public boolean isItemValidForSlot(int slot, ItemStack itemstack) {

        return true;
    }
}
