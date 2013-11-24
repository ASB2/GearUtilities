package GU.blocks.containers.BlockSolarFocus;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;
import ASB2.utils.UtilInventory;
import GU.api.ISolarFocus;
import GU.api.power.IPowerMisc;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.*;

public class TileSolarFocus extends TileBase implements IPowerMisc, IInventory{

    public TileSolarFocus() {

        waitTimer = new Wait(20, this, 0);
        tileInventory = new Inventory(1, 64, "Solar Focus", true);
        powerProvider = new PowerProvider(PowerClass.LOW, State.SOURCE);
    }

    @Override
    public void updateEntity() {

        if((worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord) && worldObj.isDaytime()) || worldObj.getBlockLightOpacity(xCoord, yCoord, zCoord) >= 10) {

            waitTimer.update();
        }
    }

    @Override
    public void trigger(int id) {

        if(tileInventory.getItemArray() != null && tileInventory.getItemArray().length > 0) {

            if(tileInventory.getItemArray()[0] != null && tileInventory.getItemArray()[0].getItem() instanceof ISolarFocus) {

                if(((ISolarFocus)tileInventory.getItemArray()[0].getItem()).canFocus(tileInventory.getItemArray()[0], worldObj, xCoord, yCoord, zCoord, this.getPowerProvider().copy())) {

                    if(this.getPowerProvider().gainPower(((ISolarFocus)tileInventory.getItemArray()[0].getItem()).getPowerForTick(tileInventory.getItemArray()[0], worldObj, xCoord, yCoord, zCoord, this.getPowerProvider().copy()), ForgeDirection.UP, true)) {

                        ((ISolarFocus)tileInventory.getItemArray()[0].getItem()).damageFocus(tileInventory.getItemArray()[0], worldObj, xCoord, yCoord, zCoord, this.getPowerProvider().copy());
                    }
                }
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
        // TODO Auto-generated method stub
        return false;
    }

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

        return itemstack.getItem() instanceof ISolarFocus && itemstack.stackSize == 1;
    }

    @Override
    public String getInvName() {

        return "Solar Focus";
    }

    @Override
    public IPowerProvider getPowerProvider() {

        return powerProvider;
    }
}
