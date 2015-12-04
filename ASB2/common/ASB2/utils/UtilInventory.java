package ASB2.utils;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class UtilInventory {
    
    public static boolean addItemStackToInventoryAndSpawnExcess(World world, IInventory destination, ItemStack itemStack, double x, double y, double z) {
        
        if (!UtilInventory.addItemStackToInventory(destination, itemStack, true)) {
            
            UtilBlock.spawnItemStackEntity(world, x, y, z, itemStack, 1);
        }
        return true;
    }
    
    public static boolean addItemStackToISidedInventory(ISidedInventory destination, ForgeDirection direction, ItemStack itemStack, boolean doWork) {
        
        if (itemStack != null) {
            
            for (int i = 0; i < destination.getAccessibleSlotsFromSide(direction.ordinal()).length; i++) {
                
                if (destination.canInsertItem(destination.getAccessibleSlotsFromSide(direction.ordinal())[i], itemStack, direction.ordinal())) {
                    
                    if (UtilInventory.addItemStackToSlot(destination, itemStack, destination.getAccessibleSlotsFromSide(direction.ordinal())[i], doWork)) {
                        
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean addItemStackToInventory(IInventory destination, ItemStack itemStack, boolean doWork) {
        
        if (itemStack != null) {
            
            for (int i = 0; i < destination.getSizeInventory(); i++) {
                
                if (UtilInventory.addItemStackToSlot(destination, itemStack, i, doWork)) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean addItemStackToISidedSlot(ISidedInventory destination, ForgeDirection direction, ItemStack itemStack, int slot, boolean doWork) {
        
        if (destination != null) {
            
            int[] accessableSlots = destination.getAccessibleSlotsFromSide(direction.ordinal());
            
            for (int i = 0; i < accessableSlots.length; i++) {
                
                if (i == slot && destination.canInsertItem(accessableSlots[i], itemStack, direction.ordinal())) {
                    
                    return UtilInventory.addItemStackToSlot(destination, itemStack, slot, doWork);
                }
            }
        }
        return false;
    }
    
    public static boolean addItemStackToSlot(IInventory destination, ItemStack itemStack, int slot, boolean doWork) {
        
        if (destination != null && itemStack != null) {
            
            if (destination.isItemValidForSlot(slot, itemStack)) {
                
                ItemStack stack = destination.getStackInSlot(slot);
                
                if (stack != null) {
                    
                    stack = stack.copy();
                }
                
                if (stack == null) {
                    
                    if (doWork)
                        destination.setInventorySlotContents(slot, itemStack);
                    
                    return true;
                } else {
                    
                    if (stack.isItemEqual(itemStack)) {
                        
                        if (doWork) {
                            
                            return UtilInventory.increaseSlotContents(destination, slot, itemStack.stackSize);
                        } else {
                            
                            if (stack.stackSize + itemStack.stackSize <= destination.getInventoryStackLimit() && stack.stackSize + itemStack.stackSize <= stack.getMaxStackSize()) {
                                
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean removeItemStackFromISidedInventory(ISidedInventory destination, ForgeDirection direction, ItemStack itemStack, int amount, boolean doWork) {
        
        if (itemStack != null) {
            
            for (int i = 0; i < destination.getAccessibleSlotsFromSide(direction.ordinal()).length; i++) {
                
                if (destination.canExtractItem(destination.getAccessibleSlotsFromSide(direction.ordinal())[i], itemStack, direction.ordinal())) {
                    
                    if (UtilInventory.removeItemStackFromSlot(destination, itemStack, destination.getAccessibleSlotsFromSide(direction.ordinal())[i], amount, doWork)) {
                        
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean removeItemStackFromInventory(IInventory source, ItemStack itemStack, int amount, boolean doWork) {
        
        if (itemStack != null) {
            
            for (int i = 0; i < source.getSizeInventory(); i++) {
                
                if (UtilInventory.removeItemStackFromSlot(source, itemStack, i, amount, doWork)) {
                    
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean removeItemStackFromISidedSlot(ISidedInventory source, ForgeDirection direction, ItemStack itemStack, int slot, int amount, boolean doWork) {
        
        if (source != null) {
            
            if (source.canExtractItem(slot, itemStack, direction.ordinal())) {
                
                return UtilInventory.removeItemStackFromSlot(source, itemStack, slot, amount, doWork);
            }
        }
        return false;
    }
    
    public static boolean removeItemStackFromSlot(IInventory source, ItemStack itemStack, int slot, int amount, boolean doWork) {
        
        if (source != null && itemStack != null) {
            
            ItemStack stack = source.getStackInSlot(slot);
            
            if (stack != null) {
                
                stack = stack.copy();
                
                if (stack.isItemEqual(itemStack)) {
                    
                    if (doWork) {
                        
                        return UtilInventory.decreaseSlotContentsBoolean(source, slot, amount);
                    } else {
                        
                        if (stack.stackSize - amount >= 0) {
                            
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean hasItemStack(IInventory inventory, ItemStack stack) {
        
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
    
    public static boolean consumeItemStack(IInventory inventory, ItemStack itemStack, int amount) {
        
        if (inventory != null) {
            
            if (itemStack != null) {
                
                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                    
                    ItemStack slotStack = inventory.getStackInSlot(i);
                    
                    if (slotStack != null) {
                        
                        slotStack = slotStack.copy();
                        
                        if (slotStack.isItemEqual(itemStack)) {
                            
                            if (UtilInventory.decreaseSlotContentsBoolean(inventory, i, amount)) {
                                
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean moveEntireInventory(IInventory source, IInventory destination) {
        
        boolean itWorked = false;
        
        if (source != destination) {
            
            for (int sourceSlot = 0; sourceSlot < source.getSizeInventory(); sourceSlot++) {
                
                if (source.getStackInSlot(sourceSlot) != null) {
                    
                    ItemStack sourceStack = source.getStackInSlot(sourceSlot).copy();
                    
                    for (int destinationSlot = 0; destinationSlot < destination.getSizeInventory(); destinationSlot++) {
                        
                        if (UtilInventory.addItemStackToSlot(destination, sourceStack, destinationSlot, false) && UtilInventory.removeItemStackFromInventory(source, sourceStack, sourceStack.stackSize, false)) {
                            
                            itWorked = UtilInventory.addItemStackToSlot(destination, sourceStack, destinationSlot, true) && UtilInventory.removeItemStackFromInventory(source, sourceStack, sourceStack.stackSize, true);
                        }
                    }
                }
            }
        }
        return itWorked;
    }
    
    public static boolean moveEntireISidedInventory(ISidedInventory source, ForgeDirection directionSource, IInventory destination) {
        
        boolean itWorked = false;
        
        if (source != destination) {
            
            int[] avaliableSlots = source.getAccessibleSlotsFromSide(directionSource.ordinal());
            
            for (int sourceArrayPosition = 0; sourceArrayPosition < avaliableSlots.length; sourceArrayPosition++) {
                
                if (source.getStackInSlot(avaliableSlots[sourceArrayPosition]) != null) {
                    
                    ItemStack sourceStack = source.getStackInSlot(avaliableSlots[sourceArrayPosition]).copy();
                    
                    if (source.canExtractItem(avaliableSlots[sourceArrayPosition], sourceStack, directionSource.ordinal())) {
                        
                        for (int destinationSlot = 0; destinationSlot < destination.getSizeInventory(); destinationSlot++) {
                            
                            if (UtilInventory.addItemStackToSlot(destination, sourceStack, destinationSlot, false) && UtilInventory.removeItemStackFromISidedInventory(source, directionSource, sourceStack, sourceStack.stackSize, false)) {
                                
                                itWorked = UtilInventory.addItemStackToSlot(destination, sourceStack, destinationSlot, true) && UtilInventory.removeItemStackFromISidedInventory(source, directionSource, sourceStack, sourceStack.stackSize, true);
                            }
                        }
                    }
                }
            }
        }
        return itWorked;
    }
    
    public static boolean moveEntireISidedInventory(IInventory source, ForgeDirection direction, ISidedInventory destination) {
        
        boolean itWorked = false;
        
        if (source != destination) {
            
            for (int sourceSlot = 0; sourceSlot < source.getSizeInventory(); sourceSlot++) {
                
                if (source.getStackInSlot(sourceSlot) != null) {
                    
                    ItemStack sourceStack = source.getStackInSlot(sourceSlot).copy();
                    
                    int[] destinationAvaliableSlots = destination.getAccessibleSlotsFromSide(direction.getOpposite().ordinal());
                    
                    for (int destinationArrayPosition = 0; destinationArrayPosition < destinationAvaliableSlots.length; destinationArrayPosition++) {
                        
                        if (UtilInventory.addItemStackToISidedSlot(destination, direction.getOpposite(), sourceStack, destinationAvaliableSlots[destinationArrayPosition], false) && UtilInventory.removeItemStackFromInventory(source, sourceStack, sourceStack.stackSize, false)) {
                            
                            itWorked = UtilInventory.addItemStackToISidedSlot(destination, direction.getOpposite(), sourceStack, destinationAvaliableSlots[destinationArrayPosition], true) && UtilInventory.removeItemStackFromInventory(source, sourceStack, sourceStack.stackSize, true);
                        }
                    }
                }
            }
        }
        return itWorked;
    }
    
    public static boolean moveEntireISidedInventory(ISidedInventory source, ForgeDirection direction, ForgeDirection directionDestination, ISidedInventory destination) {
        
        boolean itWorked = false;
        
        if (source != destination) {
            
            int[] avaliableSlots = source.getAccessibleSlotsFromSide(direction.ordinal());
            
            for (int sourceArrayPosition = 0; sourceArrayPosition < avaliableSlots.length; sourceArrayPosition++) {
                
                if (source.getStackInSlot(avaliableSlots[sourceArrayPosition]) != null) {
                    
                    ItemStack sourceStack = source.getStackInSlot(avaliableSlots[sourceArrayPosition]).copy();
                    
                    int[] destinationAvaliableSlots = destination.getAccessibleSlotsFromSide(directionDestination.ordinal());
                    
                    for (int destinationArrayPosition = 0; destinationArrayPosition < destinationAvaliableSlots.length; destinationArrayPosition++) {
                        
                        if (UtilInventory.addItemStackToISidedSlot(destination, directionDestination, sourceStack, destinationAvaliableSlots[destinationArrayPosition], false) && UtilInventory.removeItemStackFromISidedInventory(source, direction, sourceStack, sourceStack.stackSize, false)) {
                            
                            itWorked = UtilInventory.addItemStackToISidedSlot(destination, directionDestination, sourceStack, destinationAvaliableSlots[destinationArrayPosition], true) && UtilInventory.removeItemStackFromISidedInventory(source, direction, sourceStack, sourceStack.stackSize, true);
                        }
                    }
                }
            }
        }
        return itWorked;
    }
    
    public static boolean moveItems(IInventory start, int numberOfItems, int maxStackSize, IInventory end) {
        
        int movedItems = 0;
        
        for (int index = 0; index < start.getSizeInventory(); index++) {
            
            if (movedItems < numberOfItems) {
                
                ItemStack stack = start.getStackInSlot(index);
                
                if (stack != null) {
                    
                    ItemStack cloned = stack.copy();
                    
                    if (cloned.stackSize > maxStackSize) {
                        
                        cloned.stackSize = maxStackSize;
                    }
                    
                    if (UtilInventory.removeItemStackFromInventory(start, cloned, cloned.stackSize, false)) {
                        
                        if (UtilInventory.addItemStackToInventory(end, cloned, false)) {
                            
                            movedItems++;
                            UtilInventory.removeItemStackFromInventory(start, cloned, cloned.stackSize, true);
                            UtilInventory.addItemStackToInventory(end, cloned, true);
                            continue;
                        }
                    }
                    continue;
                }
            }else {
                
                return true;
            }
        }
        return true;
    }
    
    public static boolean moveItems(ISidedInventory start, ForgeDirection startDirection, int numberOfItems, int maxStackSize, IInventory end) {
        
        int movedItems = 0;
        
        int[] accesibleSlots = start.getAccessibleSlotsFromSide(startDirection.ordinal());
        
        for (int index = 0; index < accesibleSlots.length; index++) {
            
            if (movedItems <= numberOfItems) {
                
                ItemStack stack = start.getStackInSlot(accesibleSlots[index]);
                
                if (stack != null) {
                    
                    ItemStack cloned = stack.copy();
                    
                    if (cloned.stackSize > maxStackSize) {
                        
                        cloned.stackSize = maxStackSize;
                    }
                    
                    if (UtilInventory.removeItemStackFromISidedInventory(start, startDirection, cloned, cloned.stackSize, false)) {
                        
                        if (UtilInventory.addItemStackToInventory(end, cloned, false)) {
                            
                            movedItems++;
                            UtilInventory.removeItemStackFromISidedInventory(start, startDirection, cloned, cloned.stackSize, true);
                            UtilInventory.addItemStackToInventory(end, cloned, true);
                            continue;
                        }
                    }
                    return false;
                } else {
                    
                    continue;
                }
            }
        }
        return true;
    }
    
    public static boolean moveItems(IInventory start, int numberOfItems, int maxStackSize, ISidedInventory end, ForgeDirection endDirection) {
        
        int movedItems = 0;
        
        for (int index = 0; index < start.getSizeInventory(); index++) {
            
            if (movedItems <= numberOfItems) {
                
                ItemStack stack = start.getStackInSlot(index);
                
                if (stack != null) {
                    
                    ItemStack cloned = stack.copy();
                    
                    if (cloned.stackSize > maxStackSize) {
                        
                        cloned.stackSize = maxStackSize;
                    }
                    
                    if (UtilInventory.removeItemStackFromInventory(start, cloned, cloned.stackSize, false)) {
                        
                        if (UtilInventory.addItemStackToISidedInventory(end, endDirection, cloned, false)) {
                            
                            movedItems++;
                            UtilInventory.removeItemStackFromInventory(start, cloned, cloned.stackSize, true);
                            UtilInventory.addItemStackToISidedInventory(end, endDirection, cloned, true);
                            continue;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean moveItems(ISidedInventory start, ForgeDirection startDirection, int numberOfItems, int maxStackSize, ISidedInventory end, ForgeDirection endDirection) {
        
        int movedItems = 0;
        
        int[] accesibleSlots = start.getAccessibleSlotsFromSide(startDirection.ordinal());
        
        for (int index = 0; index < accesibleSlots.length; index++) {
            
            if (movedItems <= numberOfItems) {
                
                ItemStack stack = start.getStackInSlot(accesibleSlots[index]);
                
                if (stack != null) {
                    
                    ItemStack cloned = stack.copy();
                    
                    if (cloned.stackSize > maxStackSize) {
                        
                        cloned.stackSize = maxStackSize;
                    }
                    
                    if (UtilInventory.removeItemStackFromISidedInventory(start, startDirection, cloned, cloned.stackSize, false)) {
                        
                        if (UtilInventory.addItemStackToISidedInventory(end, endDirection, cloned, false)) {
                            
                            movedItems++;
                            UtilInventory.removeItemStackFromISidedInventory(start, startDirection, cloned, cloned.stackSize, true);
                            UtilInventory.addItemStackToISidedInventory(end, endDirection, cloned, true);
                            continue;
                        }
                    }
                    return false;
                } else {
                    
                    continue;
                }
            }
        }
        return true;
    }
    
    public static boolean decreaseSlotContentsBoolean(IInventory inventory, int slot, int amount) {
        
        return UtilInventory.decreaseSlotContents(inventory, slot, amount) != null;
    }
    
    public static ItemStack decreaseSlotContents(IInventory inventory, int slot, int amount) {
        
        ItemStack stack = inventory.getStackInSlot(slot);
        
        if (stack != null) {
            
            int toLeave = stack.stackSize - amount;
            
            ItemStack temp = stack.copy();
            temp.stackSize = amount;
            
            ItemStack stackLeft = stack.copy();
            stackLeft.stackSize = toLeave;
            
            inventory.setInventorySlotContents(slot, stackLeft);
            
            if (stackLeft.stackSize <= 0)
                inventory.setInventorySlotContents(slot, null);
            
            return temp;
        }
        return null;
    }
    
    public static boolean increaseSlotContents(IInventory inventory, int slot, int amount) {
        
        ItemStack stack = inventory.getStackInSlot(slot);
        
        if (stack != null && stack.getItem() != null) {
            
            int newAmount = stack.stackSize + amount;
            
            if (newAmount <= inventory.getInventoryStackLimit() && newAmount <= stack.getMaxStackSize()) {
                
                ItemStack temp = stack.copy();
                temp.stackSize = newAmount;
                
                inventory.setInventorySlotContents(slot, temp);
                return true;
            }
        }
        return false;
    }
}
