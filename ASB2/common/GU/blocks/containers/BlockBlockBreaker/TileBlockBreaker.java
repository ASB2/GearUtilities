package GU.blocks.containers.BlockBlockBreaker;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import ASB2.utils.UtilBlock;
import ASB2.utils.UtilDirection;
import ASB2.utils.UtilInventory;
import GU.api.BlackLists;
import GU.blocks.containers.TileBase;

public class TileBlockBreaker extends TileBase implements IInventory {

    public TileBlockBreaker() {

        tileItemStacks = new ItemStack[9];
    }

    public void updateEntity( ){

        int[] coords = UtilDirection.translateDirectionToCoords(getOrientation(), this);

        if(worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            if(canContainBlock() && !BlackLists.getInstance().isOnBlockBreakerBlackList(Block.blocksList[UtilDirection.translateDirectionToBlockId(worldObj, getOrientation(), this)], worldObj.getBlockMetadata(coords[0], coords[1], coords[2]))) {

                UtilBlock.breakAndAddToInventory(this, worldObj, coords[0], coords[1], coords[2], true);
            }
        }
    }

    public boolean canContainBlock() {

        int[] coords = UtilDirection.translateDirectionToCoords(getOrientation(), this);

        ArrayList<ItemStack> items = UtilBlock.getItemStackDropped(worldObj, coords[0], coords[1], coords[2], 1);

        boolean itWorked = false;

        if(!items.isEmpty()) {

            for(ItemStack stack: items) {

                itWorked = UtilInventory.addItemStackToInventory(this, stack, false);
            }
        }
        else {
            
            return true;
        }
        return itWorked;
    }

    @Override
    public int getSizeInventory() {

        return tileItemStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return tileItemStacks[i];
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

        ItemStack itemStack = getStackInSlot(slot);
        if (itemStack != null) {

            if (itemStack.stackSize <= amount) {

                setInventorySlotContents(slot, null);
            }

            else {

                itemStack = itemStack.splitStack(amount);

                if (itemStack.stackSize == 0) {

                    setInventorySlotContents(slot, null);
                }
            }
        }

        return itemStack;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return tileItemStacks[i];
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

        tileItemStacks[i] = itemStack;
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
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public String getInvName() {

        return "Block Breaker";
    }
}
