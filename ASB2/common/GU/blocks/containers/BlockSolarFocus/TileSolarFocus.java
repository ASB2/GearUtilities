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
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;

public class TileSolarFocus extends TileBase implements IPowerMisc, IInventory{

    public TileSolarFocus() {

        waitTimer = new Wait(10, this, 0);
        tileItemStacks = new ItemStack[1];
        powerProvider = new PowerProvider(PowerClass.LOW);
    }

    public void updateEntity() {

        waitTimer.update();
    }

    public void trigger(int id) {

        if(tileItemStacks[0] != null && tileItemStacks[0].getItem() instanceof ISolarFocus) {

            if(this.getPowerProvider().gainPower(((ISolarFocus)tileItemStacks[0].getItem()).getPowerForTick(tileItemStacks[0], worldObj, xCoord, yCoord, zCoord, this.getPowerProvider()), ForgeDirection.UP, true)) {

                ((ISolarFocus)tileItemStacks[0].getItem()).damageFocus(tileItemStacks[0], worldObj, xCoord, yCoord, zCoord, this.getPowerProvider());
            }
        }                
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
    public ItemStack decrStackSize(int i, int j) {

        return UtilInventory.decreaseSlotContents(this, i, j);
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
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
        return "Solar Focus";
    }

    @Override
    public IPowerProvider getPowerProvider() {

        return powerProvider;
    }
}
