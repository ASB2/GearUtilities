package GU.blocks.containers.BlockCreationTable;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import GU.gui.ContainerBase;

public class ContainerCreationTable extends ContainerBase {

    public ContainerCreationTable(InventoryPlayer inventory, TileCreationTable tileEntity) {
        super(inventory, tileEntity);

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 0, 28, 64)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 1, 47 + 15, 14)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 2, 65 + 15, 14)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 3, 83 + 15, 14)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 4, 47 + 15, 32)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 5, 65 + 15, 32)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 6, 83 + 15, 32)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 7, 47 + 15, 50)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 8, 65 + 15, 50)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 9, 83 + 15, 50)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 10, 83 + 60, 32)));
    }
}
