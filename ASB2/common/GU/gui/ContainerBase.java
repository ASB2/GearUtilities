package GU.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import GU.api.IItemInventory;

public abstract class ContainerBase extends Container {

    protected GuiBase gui;

    InventoryPlayer inventory;
    List<Slot> slotList = new ArrayList<Slot>();
    int slotCount = 0;

    public ContainerBase(InventoryPlayer inventory, Object tile) {

        this.inventory = inventory;

        if(tile instanceof IInventory) {

            slotCount = ((IInventory)tile).getSizeInventory();
        }
        else if(tile instanceof ItemStack) {

            if(((ItemStack)tile).getItem() instanceof IItemInventory) {

                slotCount = ((IItemInventory)tile).getInventory(((ItemStack)tile)).getSizeInventory();
            }
        }

        this.addInventorySlots();
    }

    public void finishConstructing(GuiBase gui) {

        this.gui = gui;
    }

    public void addInventorySlots() {

        for (int i = 0; i < 3; i++) {

            for (int k = 0; k < 9; k++) {

                addSlotToContainer(new Slot(inventory, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
            }
        }

        for (int j = 0; j < 9; j++) {

            addSlotToContainer(new Slot(inventory, j, 8 + j * 18, 142));
        }
    }

    public Slot addSlotToList(Slot slot) {

        slotList.add(slot);
        return slot;
    }

    public void onButtonEvent(int buttonID) {

    }

    @Override
    public boolean canInteractWith(EntityPlayer entityplayer) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slotNumber) {

        Slot slot = getSlot(slotNumber);

        if(slot != null && slot.getHasStack()) {

            ItemStack stack = slot.getStack();
            ItemStack result = stack.copy();

            if(slotNumber >= 36) {

                if(!mergeItemStack(stack, 0, 36, false)) {

                    return null;
                }
            }

            else if(!mergeItemStack(stack, 36, 36 + slotCount, false)) {

                return null;   
            }

            if(stack.stackSize == 0) {

                slot.putStack(null);
            }
            else {

                slot.onSlotChanged();
            }

            slot.onPickupFromSlot(player, stack);
            return result;
        }

        return null;
    }
}
