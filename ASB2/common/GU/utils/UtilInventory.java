package GU.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;

public class UtilInventory {

    @SuppressWarnings("unused")
    public static boolean addItemStackToInventory(IInventory destination, ItemStack itemStack) {

        if(itemStack != null) {

            for(int i = 0; i < destination.getSizeInventory(); i++) {

                return UtilInventory.increaseSlotContents(destination, itemStack, i, itemStack.stackSize);
            }
        }
        return false;
    }

    public static boolean inventoryHasStack(IInventory inventory, ItemStack stack) {

        if(stack != null) {

            for(int i =0; i < inventory.getInventoryStackLimit(); i++) {

                if(inventory.getStackInSlot(i) != null){

                    if((inventory.getStackInSlot(i).isItemEqual(stack))) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean removeItemStackToInventory(IInventory destination, ItemStack itemStack) {

        if(itemStack != null) {

            for(int i = 0; i < destination.getSizeInventory(); i++) {

                ItemStack slotStack = destination.getStackInSlot(i);

                if(slotStack == null) {

                    destination.setInventorySlotContents(i, itemStack);
                    return true;
                }
                else {

                    if(slotStack.isItemEqual(itemStack)) {
                        ItemStack temp = slotStack.copy();
                        temp.stackSize += itemStack.stackSize;

                        if(temp.stackSize <= destination.getInventoryStackLimit()) {

                            destination.setInventorySlotContents(i, temp);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static void moveAllInventorySlots(IInventory tileToTakeFrom, IInventory tileToMoveTo) {

        for(int i = 0; i < tileToTakeFrom.getSizeInventory(); i++) {

            if(tileToTakeFrom.getStackInSlot(i) != null) {

                for(int z = 0; z < tileToMoveTo.getSizeInventory(); z++) {

                    UtilInventory.moveIInventorySlot(tileToTakeFrom, i, tileToMoveTo, z);
                }
            }
        }
    }

    public static void moveIInventorySlot(IInventory inventoryToTakeFrom, int slotToMoveFrom, IInventory inventoryToMoveTo, int slotToMoveTo) {

        if(inventoryToTakeFrom.getStackInSlot(slotToMoveFrom) != null) {

            if(inventoryToMoveTo.getStackInSlot(slotToMoveTo) == null) {

                if(inventoryToTakeFrom.getStackInSlot(slotToMoveFrom).stackSize <= inventoryToMoveTo.getInventoryStackLimit()) {

                    inventoryToMoveTo.setInventorySlotContents(slotToMoveTo, inventoryToTakeFrom.getStackInSlot(slotToMoveFrom));
                    inventoryToTakeFrom.setInventorySlotContents(slotToMoveFrom, null);
                }

                else {

                    int stackAmount =  inventoryToMoveTo.getInventoryStackLimit() - inventoryToTakeFrom.getStackInSlot(slotToMoveFrom).stackSize;

                    ItemStack tempSlotToMoveTo = inventoryToTakeFrom.getStackInSlot(slotToMoveFrom).copy();            
                    tempSlotToMoveTo.stackSize = inventoryToMoveTo.getInventoryStackLimit();

                    inventoryToMoveTo.setInventorySlotContents(slotToMoveTo, tempSlotToMoveTo);
                    //
                    ItemStack tempStack = inventoryToTakeFrom.getStackInSlot(slotToMoveFrom).copy();
                    tempStack.stackSize = stackAmount;

                    inventoryToTakeFrom.setInventorySlotContents(slotToMoveFrom, tempStack);
                }
            }

            else if(inventoryToMoveTo.getStackInSlot(slotToMoveTo).isItemEqual(inventoryToTakeFrom.getStackInSlot(slotToMoveFrom))) {

                if(inventoryToMoveTo.getStackInSlot(slotToMoveTo).stackSize + inventoryToTakeFrom.getStackInSlot(slotToMoveFrom).stackSize <= inventoryToMoveTo.getInventoryStackLimit()) {

                    ItemStack tempStackMovelTo = inventoryToMoveTo.getStackInSlot(slotToMoveTo);
                    tempStackMovelTo.stackSize = tempStackMovelTo.stackSize + inventoryToTakeFrom.getStackInSlot(slotToMoveFrom).stackSize;

                    inventoryToMoveTo.setInventorySlotContents(slotToMoveTo, tempStackMovelTo);

                    inventoryToTakeFrom.setInventorySlotContents(slotToMoveFrom, null);
                }
            }
        }
    }

    public static void moveFromAllISidedSlots(ISidedInventory inventoryToTakeFrom, int side, IInventory inventoryToMoveTo) {

        for(int i = 0; i < inventoryToTakeFrom.getAccessibleSlotsFromSide(side).length; i++) {

            for(int z = 0; z < inventoryToMoveTo.getSizeInventory(); z++) {

                if(inventoryToTakeFrom.getStackInSlot(i) != null) {

                    if(inventoryToTakeFrom.canExtractItem(i, inventoryToTakeFrom.getStackInSlot(i), side)) {

                        UtilInventory.moveIInventorySlot(inventoryToTakeFrom, inventoryToTakeFrom.getAccessibleSlotsFromSide(side)[i], inventoryToMoveTo, z);
                    }
                }
            }
        }
    }

    public static void moveToAllISidedSlots(IInventory inventoryToTakeFrom, int side, ISidedInventory inventoryToMoveTo) {

        for(int i = 0; i < inventoryToMoveTo.getAccessibleSlotsFromSide(side).length; i++) {

            for(int z = 0; z < inventoryToTakeFrom.getSizeInventory(); z++) {

                if(inventoryToMoveTo.getStackInSlot(i) != null) {

                    if(inventoryToMoveTo.canInsertItem(i, inventoryToMoveTo.getStackInSlot(i), side)) {

                        UtilInventory.moveIInventorySlot(inventoryToMoveTo, z, inventoryToMoveTo, inventoryToMoveTo.getAccessibleSlotsFromSide(side)[i]);
                    }
                }
            }
        }
    }

    public static boolean decreaseSlotContents(IInventory inventory, int slotToChange, int amount) {

        ItemStack itemStack = inventory.getStackInSlot(slotToChange);

        if (itemStack != null) {

            if(itemStack.stackSize == 1 && amount == 1) {

                inventory.setInventorySlotContents(slotToChange, null);
                return true;
            }
            if (itemStack.stackSize >= amount) {

                itemStack.stackSize = itemStack.stackSize - amount;

                if(!(itemStack.stackSize < 0)) {

                    inventory.setInventorySlotContents(slotToChange, itemStack);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean increaseSlotContents(IInventory inventory, ItemStack itemStack, int slotToChange, int amount) {

        if(amount <= inventory.getInventoryStackLimit() && itemStack.stackSize <= inventory.getInventoryStackLimit()) {

            if (inventory.getStackInSlot(slotToChange) == null) {

                inventory.setInventorySlotContents(slotToChange, itemStack);
            }
        }
        return false;
    }
}
