package GU.blocks.containers.BlockSolarFocus;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import GU.gui.ContainerBase;

public class ContainerSolarFocus extends ContainerBase {

    public ContainerSolarFocus(InventoryPlayer inventory, TileSolarFocus tileEntity) {
        super(inventory, tileEntity);


        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 0, 80, 32)));
    }
}
