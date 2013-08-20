package GU.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class UtilInventory {

    public static boolean addItemStackToInventoryAndSpawnExcess(World world,
            IInventory destination, ItemStack itemStack, int x, int y, int z) {

        if (!UtilInventory.addItemStackToInventory(destination, itemStack)) {

            UtilBlock.spawnItemStackEntity(world, x, y, z, itemStack, 0);
        }
        return true;
    }

    public static boolean addItemStackToInventory(IInventory destination,
            ItemStack itemStack) {

        if (itemStack != null) {

            for (int i = 0; i < destination.getSizeInventory(); i++) {

                ItemStack stack = destination.getStackInSlot(i);

                if (stack == null) {

                    destination.setInventorySlotContents(i, itemStack);
                    return true;
                } else {

                    if (stack.isItemEqual(itemStack)) {

                        int stackSizeAdded = stack.stackSize
                                + itemStack.stackSize;

                        if (stackSizeAdded <= destination
                                .getInventoryStackLimit()) {

                            if (stackSizeAdded <= stack.getMaxStackSize()) {

                                stack.stackSize = stackSizeAdded;
                                return true;
                            }

                        }
                    }
                }
            }
        }
        return false;
    }

    public static boolean addItemStackToSlot(IInventory inventory,
            ItemStack stack, int slot) {

        if (stack != null && inventory != null) {

            if (inventory.getStackInSlot(slot) == null
                    && stack.isItemEqual(inventory.getStackInSlot(slot))) {

                ItemStack temp = stack.copy();

                temp.stackSize = +inventory.getStackInSlot(slot).stackSize;

                if (temp.stackSize <= inventory.getInventoryStackLimit()
                        && temp.stackSize <= temp.getMaxStackSize()) {

                    inventory.setInventorySlotContents(slot, temp);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean doesInventoryHasStack(IInventory inventory,
            ItemStack stack) {

        if (stack != null) {

            for (int i = 0; i < inventory.getInventoryStackLimit(); i++) {

                if (inventory.getStackInSlot(i) != null) {

                    if ((inventory.getStackInSlot(i).isItemEqual(stack))) {

                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean consumeItemStack(IInventory inventory,
            ItemStack itemStack, int amount) {

        if (itemStack != null) {

            for (int i = 0; i < inventory.getSizeInventory(); i++) {

                ItemStack slotStack = inventory.getStackInSlot(i);

                if (slotStack != null) {

                    if (slotStack.isItemEqual(itemStack)) {

                        return UtilInventory.decreaseSlotContents(inventory, i,
                                amount);
                    }
                }
            }
        }
        return false;
    }

    public static void moveAllInventorySlots(IInventory tileToTakeFrom,
            IInventory tileToMoveTo) {

        for (int i = 0; i < tileToTakeFrom.getSizeInventory(); i++) {

            if (tileToTakeFrom.getStackInSlot(i) != null) {

                for (int z = 0; z < tileToMoveTo.getSizeInventory(); z++) {

                    UtilInventory.moveIInventorySlot(tileToTakeFrom, i,
                            tileToMoveTo, z);
                }
            }
        }
    }

    public static void moveIInventorySlot(IInventory inventoryToTakeFrom,
            int slotToMoveFrom, IInventory inventoryToMoveTo, int slotToMoveTo) {

        if (inventoryToTakeFrom.getStackInSlot(slotToMoveFrom) != null) {

            if (inventoryToMoveTo.getStackInSlot(slotToMoveTo) == null) {

                if (inventoryToTakeFrom.getStackInSlot(slotToMoveFrom).stackSize <= inventoryToMoveTo
                        .getInventoryStackLimit()) {

                    inventoryToMoveTo.setInventorySlotContents(slotToMoveTo,
                            inventoryToTakeFrom.getStackInSlot(slotToMoveFrom));
                    inventoryToTakeFrom.setInventorySlotContents(
                            slotToMoveFrom, null);
                }

                else {

                    int stackAmount = inventoryToMoveTo
                            .getInventoryStackLimit()
                            - inventoryToTakeFrom
                                    .getStackInSlot(slotToMoveFrom).stackSize;

                    ItemStack tempSlotToMoveTo = inventoryToTakeFrom
                            .getStackInSlot(slotToMoveFrom).copy();
                    tempSlotToMoveTo.stackSize = inventoryToMoveTo
                            .getInventoryStackLimit();

                    inventoryToMoveTo.setInventorySlotContents(slotToMoveTo,
                            tempSlotToMoveTo);
                    //
                    ItemStack tempStack = inventoryToTakeFrom.getStackInSlot(
                            slotToMoveFrom).copy();
                    tempStack.stackSize = stackAmount;

                    inventoryToTakeFrom.setInventorySlotContents(
                            slotToMoveFrom, tempStack);
                }
            }

            else if (inventoryToMoveTo.getStackInSlot(slotToMoveTo)
                    .isItemEqual(
                            inventoryToTakeFrom.getStackInSlot(slotToMoveFrom))) {

                if (inventoryToMoveTo.getStackInSlot(slotToMoveTo).stackSize
                        + inventoryToTakeFrom.getStackInSlot(slotToMoveFrom).stackSize <= inventoryToMoveTo
                            .getInventoryStackLimit()) {

                    ItemStack tempStackMovelTo = inventoryToMoveTo
                            .getStackInSlot(slotToMoveTo);
                    tempStackMovelTo.stackSize = tempStackMovelTo.stackSize
                            + inventoryToTakeFrom
                                    .getStackInSlot(slotToMoveFrom).stackSize;

                    inventoryToMoveTo.setInventorySlotContents(slotToMoveTo,
                            tempStackMovelTo);

                    inventoryToTakeFrom.setInventorySlotContents(
                            slotToMoveFrom, null);
                }
            }
        }
    }

    public static void moveFromAllISidedSlots(
            ISidedInventory inventoryToTakeFrom, int side,
            IInventory inventoryToMoveTo) {

        for (int i = 0; i < inventoryToTakeFrom
                .getAccessibleSlotsFromSide(side).length; i++) {

            for (int z = 0; z < inventoryToMoveTo.getSizeInventory(); z++) {

                if (inventoryToTakeFrom.getStackInSlot(i) != null) {

                    if (inventoryToTakeFrom.canExtractItem(i,
                            inventoryToTakeFrom.getStackInSlot(i), side)) {

                        UtilInventory.moveIInventorySlot(inventoryToTakeFrom,
                                inventoryToTakeFrom
                                        .getAccessibleSlotsFromSide(side)[i],
                                inventoryToMoveTo, z);
                    }
                }
            }
        }
    }

    public static void moveToAllISidedSlots(IInventory inventoryToTakeFrom,
            int side, ISidedInventory inventoryToMoveTo) {

        for (int i = 0; i < inventoryToMoveTo.getAccessibleSlotsFromSide(side).length; i++) {

            for (int z = 0; z < inventoryToTakeFrom.getSizeInventory(); z++) {

                if (inventoryToMoveTo.getStackInSlot(i) != null) {

                    if (inventoryToMoveTo.canInsertItem(i,
                            inventoryToMoveTo.getStackInSlot(i), side)) {

                        UtilInventory.moveIInventorySlot(inventoryToMoveTo, z,
                                inventoryToMoveTo, inventoryToMoveTo
                                        .getAccessibleSlotsFromSide(side)[i]);
                    }
                }
            }
        }
    }

    public static boolean decreaseSlotContents(IInventory inventory,
            int slotToChange, int amount) {

        if (inventory.getStackInSlot(slotToChange) != null) {

            ItemStack itemStack = inventory.getStackInSlot(slotToChange).copy();

            if (itemStack != null) {

                if (itemStack.stackSize == 1 && amount == 1) {

                    inventory.setInventorySlotContents(slotToChange, null);
                    return true;
                }
                if (itemStack.stackSize >= amount) {

                    itemStack.stackSize = itemStack.stackSize - amount;

                    if (itemStack.stackSize > 0) {

                        inventory.setInventorySlotContents(slotToChange,
                                itemStack);
                        return true;
                    } else if (itemStack.stackSize == 0) {

                        inventory.setInventorySlotContents(slotToChange, null);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static ItemStack decreaseSlotContentsItemStack(IInventory inventory,
            int slot, int amt) {

        ItemStack stack = inventory.getStackInSlot(slot);

        if (stack != null) {

            if (stack.stackSize <= amt) {

                inventory.setInventorySlotContents(slot, null);
            } else {

                stack = stack.splitStack(amt);
                if (stack.stackSize == 0) {

                    inventory.setInventorySlotContents(slot, null);
                }
            }
        }
        return stack;
    }

    public static boolean increaseSlotContents(IInventory inventory,
            ItemStack itemStack, int slotToChange, int amount) {

        if (amount <= inventory.getInventoryStackLimit()
                && itemStack.stackSize <= inventory.getInventoryStackLimit()) {

            if (inventory.getStackInSlot(slotToChange) == null) {

                inventory.setInventorySlotContents(slotToChange, itemStack);
            }
        }
        return false;
    }
}
