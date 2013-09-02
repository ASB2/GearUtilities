package GU.blocks.containers.BlockAdvancedPotionBrewery;

import net.minecraft.entity.player.InventoryPlayer;
import GU.gui.ContainerBase;

public class ContainerAdvancedPotionBrewery extends ContainerBase {

    TileAdvancedPotionBrewery tileEntity;

    public ContainerAdvancedPotionBrewery(InventoryPlayer inventory, TileAdvancedPotionBrewery tileEntity) {
        super(inventory, tileEntity);
        this.tileEntity = tileEntity;
        
        this.addSlotToContainer(addSlotToList(new PotionIngredientSlot(tileEntity, 0, 33, 19)));
        this.addSlotToContainer(addSlotToList(new PotionIngredientSlot(tileEntity, 1, 52, 19)));
        this.addSlotToContainer(addSlotToList(new PotionIngredientSlot(tileEntity, 2, 71, 19)));
        this.addSlotToContainer(addSlotToList(new PotionIngredientSlot(tileEntity, 3, 90, 19)));
        this.addSlotToContainer(addSlotToList(new PotionIngredientSlot(tileEntity, 4, 109, 19)));
        this.addSlotToContainer(addSlotToList(new PotionIngredientSlot(tileEntity, 5, 128, 19)));
        this.addSlotToContainer(addSlotToList(new PotionIngredientSlot(tileEntity, 6, 35, 54)));
        this.addSlotToContainer(addSlotToList(new PotionIngredientSlot(tileEntity, 7, 83, 54)));

        tileEntity.trigger(0);
    }

    @Override
    public void onButtonEvent(int buttonID) {

        tileEntity.onButtonEvent(buttonID);
    }
}
