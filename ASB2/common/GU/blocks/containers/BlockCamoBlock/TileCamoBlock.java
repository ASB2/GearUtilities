package GU.blocks.containers.BlockCamoBlock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import ASB2.utils.UtilInventory;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.*;

public class TileCamoBlock extends TileBase implements IInventory {

    public TileCamoBlock() {

        tileInventory = new Inventory(1, 1, "Camo Block", true);
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
    public ItemStack decrStackSize(int slot, int amount) {


        return UtilInventory.decreaseSlotContents(this, slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return tileInventory.getStackInSlotOnClosing(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {

        tileInventory.setInventorySlotContents(i, itemStack);
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

        return tileInventory.getInvName();
    }
}
