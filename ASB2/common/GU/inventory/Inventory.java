package GU.inventory;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import ASB2.utils.UtilInventory;

public class Inventory implements ISidedInventory {

    public final static int STANDARD_STACKSIZE = 64;

    int inventorySize = 0;
    int maxStackSize = 64;
    ArrayList<MegaObjectHolder<ItemStack>> storedStacks;
    String inventoryName;
    boolean playerCanUse;
    boolean localized = true;

    public Inventory(int inventorySize, String inventoryName) {
        this(inventorySize, inventoryName, true);
    }

    public Inventory(int inventorySize, String inventoryName, boolean useableByPlayer) {
        this(inventorySize, STANDARD_STACKSIZE, inventoryName, useableByPlayer);
    }

    public Inventory(int inventorySize, int maxStackSize, String inventoryName, boolean useableByPlayer) {

        this.inventorySize = inventorySize;
        this.maxStackSize = maxStackSize;
        storedStacks = new ArrayList<MegaObjectHolder<ItemStack>>((inventorySize / MegaObjectHolder.USUAL_MAX_OBJECTS) + 1);
        this.inventoryName = inventoryName;
        playerCanUse = useableByPlayer;

        for (int i = 0; i < storedStacks.size(); i++) {

            storedStacks.set(i, new MegaObjectHolder<ItemStack>());
        }
    }

    public ArrayList<MegaObjectHolder<ItemStack>> getItemArray() {

        return storedStacks;
    }

    public void setSizeInventory(int newSize) {

        if (newSize > 0) {

            this.inventorySize = newSize;
            storedStacks.ensureCapacity(newSize);
        }
    }

    @Override
    public int getSizeInventory() {

        return inventorySize;
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        return this.getObjectHolderForSlot(i).getObject(i / MegaObjectHolder.USUAL_MAX_OBJECTS);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount) {

        return UtilInventory.decreaseSlotContents(this, slot, amount);
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        return getStackInSlot(i);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {

        this.getObjectHolderForSlot(i).setObject(i / MegaObjectHolder.USUAL_MAX_OBJECTS, itemstack);
    }

    public void setInventoryName(String newName) {

        this.inventoryName = newName;
    }

    @Override
    public String getInvName() {

        return inventoryName;
    }

    public void setIsInventoryNameLocalized(boolean newBoolean) {

        this.localized = newBoolean;
    }

    @Override
    public boolean isInvNameLocalized() {

        return localized;
    }

    public void setInventoryStackLimit(int newLimit) {

        this.maxStackSize = newLimit;
    }

    @Override
    public int getInventoryStackLimit() {

        return maxStackSize;
    }

    @Override
    public void onInventoryChanged() {

        // TODO Auto-generated method stub
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer) {

        return playerCanUse;
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

        return true;
    }

    public NBTTagCompound save(NBTTagCompound tag) {

        int numberStoredStacks = 0;

        for (int megaObjectsHolderIterator = 0; megaObjectsHolderIterator < storedStacks.size(); megaObjectsHolderIterator++) {

            MegaObjectHolder<ItemStack> objects = storedStacks.get(megaObjectsHolderIterator);

            ArrayList<ItemStack> items = objects.getObjects();

            for (int objectsIterator = 0; objectsIterator < items.size(); objectsIterator++) {

                numberStoredStacks++;

                ItemStack stack = items.get(objectsIterator);

                if (stack != null) {

                    tag.setCompoundTag("Slot" + numberStoredStacks, stack.writeToNBT(new NBTTagCompound()));
                }
            }
        }
        tag.setInteger("StoredMegaObjects", storedStacks.size());
        return tag;
    }

    public void load(NBTTagCompound tag) {

        int processedStacks = 0;
        int storedMegaObjects = tag.getInteger("StoredMegaObjects");

        storedStacks = new ArrayList<MegaObjectHolder<ItemStack>>(storedMegaObjects);

        for (int currentMegaObject = 0; currentMegaObject < storedStacks.size(); currentMegaObject++) {

            MegaObjectHolder<ItemStack> currentObjects = storedStacks.get(currentMegaObject);
            ArrayList<ItemStack> items = currentObjects.getObjects();

            for (int currentItem = 0; currentItem < items.size(); currentItem++) {

                processedStacks++;

                ItemStack stack = ItemStack.loadItemStackFromNBT(tag.getCompoundTag("Slot" + processedStacks));

                if (stack != null) {

                    currentObjects.setObject(processedStacks, stack);
                }
            }
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int var1) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemstack, int j) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemstack, int j) {
        // TODO Auto-generated method stub
        return false;
    }

    public MegaObjectHolder<ItemStack> getObjectHolderForSlot(int slot) {

        int divided = slot / MegaObjectHolder.USUAL_MAX_OBJECTS;

        return storedStacks.get(divided);
    }
}
