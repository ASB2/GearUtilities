package GU.blocks.containers.BlockMultiInterface;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import ASB2.vector.Vector3;
import GU.api.multiblock.IMultiBlock;
import GU.blocks.containers.TileMultiBase;

public class TileItemMultiInterface extends TileMultiBase implements IInventory {

    Map<SlotHolder, IMultiBlock> inventorise = new HashMap<SlotHolder, IMultiBlock>();
    int maxSizeInventory = 0;

    public TileItemMultiInterface() {

        this.destoryTileWithNotMultiBlock = true;
        this.useSidesRendering = true;
    }

    private class SlotHolder {

        public int minSlot, maxSlot;

        public SlotHolder(int minSlot, int maxSlot) {
            this.minSlot = minSlot;
            this.maxSlot = maxSlot;
        }

        public int getMinSlot() {

            return minSlot;
        }

        public int getMaxSlot() {

            return maxSlot;
        }
    }

    @Override
    public boolean addMultiBlock(IMultiBlock multiBlock) {

        if (multiBlock.getSize().getEdges().contains(new Vector3(this))) {

            return false;
        }

        if (super.addMultiBlock(multiBlock)) {

            recalculate();
        }
        return false;
    }

    @Override
    public void removeMultiBlock(IMultiBlock multiBlock) {
        super.removeMultiBlock(multiBlock);
        recalculate();
    }

    public void recalculate() {

        int lastSlotMin = 0;

        for (IMultiBlock multi : this.getComprisedMultiBlocks()) {

            if (multi instanceof IInventory) {

                int size = ((IInventory) multi).getSizeInventory();

                if (size > 0) {

                    lastSlotMin++;
                    this.inventorise.put(new SlotHolder(lastSlotMin, ((IInventory) multi).getSizeInventory() + lastSlotMin), multi);
                    lastSlotMin += ((IInventory) multi).getSizeInventory();
                }
            }
            maxSizeInventory = lastSlotMin;
        }
    }

    public IInventory redirectSlot(int slot) {

        for (SlotHolder slots : inventorise.keySet()) {

            if (slots.getMinSlot() <= slot && slots.getMaxSlot() >= slot) {

                return (IInventory) inventorise.get(slots);
            }
        }
        return null;
    }

    @Override
    public int getSizeInventory() {

        return maxSizeInventory;
    }

    @Override
    public ItemStack getStackInSlot(int i) {

        IInventory inventory = redirectSlot(i);

        if (inventory != null) {

            return inventory.getStackInSlot(i);
        }
        return null;
    }

    @Override
    public ItemStack decrStackSize(int i, int j) {

        IInventory inventory = redirectSlot(i);

        if (inventory != null) {

            return inventory.decrStackSize(i, j);
        }
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {

        IInventory inventory = redirectSlot(i);

        if (inventory != null) {

            return inventory.getStackInSlotOnClosing(i);
        }
        return null;
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemstack) {

        IInventory inventory = redirectSlot(i);

        if (inventory != null) {

            inventory.setInventorySlotContents(i, itemstack);
        }
    }

    @Override
    public String getInvName() {

        return "Item Multi Interface";
    }

    @Override
    public boolean isInvNameLocalized() {

        return true;
    }

    @Override
    public int getInventoryStackLimit() {

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

        IInventory inventory = redirectSlot(i);

        if (inventory != null) {

            return inventory.isItemValidForSlot(i, itemstack);
        }
        return false;
    }
}
