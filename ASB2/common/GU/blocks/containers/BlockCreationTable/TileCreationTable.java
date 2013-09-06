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
import GU.power.GUPowerProvider;

public class TileCreationTable extends TileBase implements IPowerMisc, IInventory {

    public TileCreationTable() {

        this.waitTimer = new Wait(10, this, 0);
        powerProvider = new GUPowerProvider(PowerClass.LOW, State.SINK);
        tileItemStacks = new ItemStack[11]; 
    }

    @Override
    public void updateEntity() {

        waitTimer.update();
        if(Minecraft.getMinecraft().thePlayer != null && Minecraft.getMinecraft().thePlayer.openContainer != null && Minecraft.getMinecraft().thePlayer.openContainer instanceof ContainerCreationTable)
            this.sendReqularPowerPackets(10);
    }

    @Override
    public void trigger(int id) {

        ((GUPowerProvider)this.getPowerProvider()).movePower(worldObj, xCoord, yCoord, zCoord);
    }

    @Override
    public PowerProvider getPowerProvider() {

        return powerProvider;
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
        return "Creation Table";
    }
}
