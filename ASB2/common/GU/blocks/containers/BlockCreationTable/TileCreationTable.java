package GU.blocks.containers.BlockCreationTable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import ASB2.utils.UtilInventory;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.blocks.containers.*;

public class TileCreationTable extends TileBase implements IPowerMisc, IInventory {

    public TileCreationTable() {

        this.waitTimer = new Wait(10, this, 0);
        powerProvider = new PowerProvider(PowerClass.LOW, State.SINK);
        tileInventory = new Inventory(11, 64, "Creation", true);
    }

    @Override
    public void updateEntity() {

        waitTimer.update();
        if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.openContainer != null && Minecraft.getMinecraft().thePlayer.openContainer instanceof ContainerCreationTable)
            this.sendReqularPowerPackets(10);
    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
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

        return tileInventory.isItemValidForSlot(i,  itemstack);
    }

    @Override
    public String getInvName() {

        return tileInventory.getInvName();
    }
}
