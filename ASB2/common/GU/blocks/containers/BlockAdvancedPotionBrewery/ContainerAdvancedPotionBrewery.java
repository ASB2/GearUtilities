package GU.blocks.containers.BlockAdvancedPotionBrewery;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import GU.gui.ContainerBase;

public class ContainerAdvancedPotionBrewery extends ContainerBase {

    public ContainerAdvancedPotionBrewery(InventoryPlayer inventory, TileAdvancedPotionBrewery tileEntity) {
        super(inventory);

        this.addSlotToContainer(addSlotToList(new Slot(tileEntity, 0, 47 + 15, 14)));
    }
}
