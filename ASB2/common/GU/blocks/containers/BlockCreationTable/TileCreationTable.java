package GU.blocks.containers.BlockCreationTable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import GU.api.power.IPowerMisc;
import GU.api.power.PowerClass;
import GU.api.power.PowerProvider;
import GU.api.power.State;
import GU.api.wait.Wait;
import GU.blocks.containers.TileBase;
import GU.power.GUPowerProvider;
import GU.utils.*;

public class TileCreationTable extends TileBase implements IPowerMisc, IInventory {

    public TileCreationTable() {

        this.waitTimer = new Wait(10, this, 0);
        powerProvider = new GUPowerProvider(0, PowerClass.LOW, State.SINK);
        tileItemStacks = new ItemStack[11];
    }

    @Override
    public void updateEntity() {

        if(!worldObj.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {

            waitTimer.update();
        }
    }

    @Override
    public void trigger(int id) {

        ((GUPowerProvider)this.getPowerProvider()).movePower(worldObj, xCoord, yCoord, zCoord);
    }

    @Override
    public void triggerBlock(World world, EntityLivingBase player, ItemStack itemStack, int x, int y, int z, int side) {

        super.triggerBlock(world, player, itemStack, side, side, side, side);     
    }

    @Override
    public void triggerBlock(World world, boolean isSneaking, ItemStack itemStack, int x, int y, int z, int side) {

        super.triggerBlock(world, isSneaking, itemStack, side, side, side, side);            
    }

    @Override
    public String getName() {

        return "Creation Table";
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

        if(UtilInventory.decreaseSlotContents(this, i, j)) {

            return this.getStackInSlot(i);
        }
        return null;
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
    public String getInvName() {
        // TODO Auto-generated method stub
        return getName();
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
}
