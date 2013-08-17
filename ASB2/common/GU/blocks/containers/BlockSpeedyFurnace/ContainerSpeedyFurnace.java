package GU.blocks.containers.BlockSpeedyFurnace;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import GU.gui.ContainerBase;

public class ContainerSpeedyFurnace extends ContainerBase {

    public ContainerSpeedyFurnace(InventoryPlayer inventory, TileSpeedyFurnace tileEntity) {
        super(inventory);

        this.addSlotToContainer(this.addSlotToList(new Slot(tileEntity, 0, 56, 32)));        
        this.addSlotToContainer(this.addSlotToList(new Slot(tileEntity, 1, 116, 32))); 
        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 2, 65 + 15, 14)));
    }
}
