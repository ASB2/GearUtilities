package GU.blocks.containers.BlockGeothermalGenerator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import GU.gui.ContainerBase;

public class ContainerGeothermalGenerator extends ContainerBase {

    public ContainerGeothermalGenerator(InventoryPlayer inventory, TileGeothermalGenerator tileEntity) {
        super(inventory);

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 0, 28, 64)));

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 1, 47 + 15, 14)));
    }

}
