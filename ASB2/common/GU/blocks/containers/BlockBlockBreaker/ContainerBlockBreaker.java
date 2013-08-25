package GU.blocks.containers.BlockBlockBreaker;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import GU.gui.ContainerBase;

public class ContainerBlockBreaker extends ContainerBase {

    public ContainerBlockBreaker(InventoryPlayer inventory, TileBlockBreaker tileEntity) {
        super(inventory);

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 0, 47 + 15, 14)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 1, 65 + 15, 14)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 2, 83 + 15, 14)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 3, 47 + 15, 32)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 4, 65 + 15, 32)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 5, 83 + 15, 32)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 6, 47 + 15, 50)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 7, 65 + 15, 50)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 8, 83 + 15, 50)));
    }
}
