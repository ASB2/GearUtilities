package GU.blocks.containers.BlockCamoBlock;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import GU.gui.ContainerBase;

public class ContainerCamoBlock extends ContainerBase {

    public ContainerCamoBlock(InventoryPlayer inventory, TileCamoBlock tileEntity) {
        super(inventory, tileEntity);

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 0, 47 + 15, 14)));
    }
}
