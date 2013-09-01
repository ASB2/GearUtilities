package GU.blocks.containers.BlockCamoBlock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import GU.blocks.containers.TileBase;
import GU.utils.*;

public class TileCamoBlock extends TileBase implements IInventory {

    public TileCamoBlock() {

        tileItemStacks = new ItemStack[1];
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


        return UtilInventory.decreaseSlotContents(this, slot, amount);
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
    public void onInventoryChanged() {
        super.onInventoryChanged();

        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);    }
    
    @Override
    public int getInventoryStackLimit() {

        return 1;
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

        return "Camo Block";
    }
}
