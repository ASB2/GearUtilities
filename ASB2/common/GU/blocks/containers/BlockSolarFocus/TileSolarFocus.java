package GU.blocks.containers.BlockSolarFocus;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import ASB2.utils.UtilInventory;
import GU.api.power.IPowerMisc;
import GU.api.power.IPowerProvider;
import GU.api.power.PowerClass;
import GU.blocks.containers.TileBase;
import GU.power.GUPowerProvider;

public class TileSolarFocus extends TileBase implements IPowerMisc, IInventory{

    public TileSolarFocus() {

        tileItemStacks = new ItemStack[1];
        powerProvider = new GUPowerProvider(PowerClass.LOW);
    }

    public void updateEntity() {
        
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
