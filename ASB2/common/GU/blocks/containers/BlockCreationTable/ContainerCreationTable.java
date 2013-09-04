package GU.blocks.containers.BlockCreationTable;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import GU.gui.ContainerBase;

public class ContainerCreationTable extends ContainerBase {

    public ContainerCreationTable(InventoryPlayer inventory, TileCreationTable tileEntity) {
        super(inventory, tileEntity);

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 0, 28, 64)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 1, 62, 14)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 2, 80, 14)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 3, 98, 14)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 4, 62, 32)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 5, 80, 32)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 6, 98, 32)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 7, 62, 65)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 8, 80, 65)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 9, 98, 65)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 10, 148, 32)));
    }
}
