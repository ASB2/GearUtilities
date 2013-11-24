package GU.blocks.containers.BlockDissolver;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import GU.gui.ContainerBase;

public class ContainerDissolver extends ContainerBase {

    TileDissolver tileEntity;

    public ContainerDissolver(InventoryPlayer inventory, TileDissolver tileEntity) {
        super(inventory, tileEntity);

        this.tileEntity = tileEntity;

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 0, 62 - 30, 14)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 1, 80 - 30, 14)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 2, 98 - 30, 14)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 3, 62 - 30, 32)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 4, 80 - 30, 32)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 5, 98 - 30, 32)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 6, 62 - 30, 50)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 7, 80 - 30, 50)));
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 8, 98 - 30, 50)));
        
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 9, 130, 32)));
    }

    @Override
    public void onButtonEvent(int buttonID) {

        tileEntity.onButtonEvent(buttonID);
    }
}
