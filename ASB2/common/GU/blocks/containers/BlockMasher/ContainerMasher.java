package GU.blocks.containers.BlockMasher;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import GU.gui.ContainerBase;

public class ContainerMasher extends ContainerBase {

    TileMasher tileEntity;

    public ContainerMasher(InventoryPlayer inventory, TileMasher tileEntity) {
        super(inventory, tileEntity);
        this.tileEntity = tileEntity;
        
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 0, 62, 14)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 1, 80, 14)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 2, 98, 14)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 3, 62, 32)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 4, 80, 32)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 5, 98, 32)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 6, 62, 50)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 7, 80, 50)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 8, 98, 50)));
    }

    @Override
    public void onButtonEvent(int buttonID) {

        tileEntity.onButtonEvent(buttonID);
    }
}
