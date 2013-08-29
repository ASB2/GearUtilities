package GU.blocks.containers.BlockRunicCube;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import GU.api.runes.IRuneBlock;
import GU.api.runes.IRuneItem;
import GU.blocks.containers.TileBase;
import GU.utils.UtilInventory;

public class TileRunicCube extends TileBase implements IInventory, IRuneBlock {

    int inventorySize = 1;

    public TileRunicCube(int inventorySize) {

        this.inventorySize = inventorySize;
        tileItemStacks = new ItemStack[inventorySize];
    }

    public void updateEntity() {

        for(ItemStack stack : tileItemStacks) {

            if(stack != null) {
                if(stack.getItem() instanceof IRuneItem) {

                    IRuneItem rune = (IRuneItem)stack.getItem();

                    if(rune.shouldUpdate(this, stack, xCoord, yCoord, zCoord)) {

                        rune.onUpdate(this, stack, xCoord, yCoord, zCoord);
                    }
                }
            }
        }
    }

    @Override
    public ArrayList<ItemStack> getRunes() {

        ArrayList<ItemStack> array = new ArrayList<ItemStack>();

        for(ItemStack stack : tileItemStacks) {

            if(stack != null && stack.getItem() instanceof IRuneItem) {

                array.add(stack);
            }
        }
        return array;
    }

    @Override
    public ItemStack removeRune() {

        if(!getRunes().isEmpty()) {

            for(ItemStack stack : getRunes()) {

                if(stack != null) {

                    ((IRuneItem)stack.getItem()).onRemoval(this, stack, xCoord, yCoord, zCoord);
                    UtilInventory.consumeItemStack(this, stack, stack.stackSize);
                    return stack;
                }
            }
        }
        return null;
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

        return "Runic Cube";
    }
}
