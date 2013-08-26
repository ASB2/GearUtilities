package GU.blocks.containers.BlockAdvancedPotionBrewery;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.gui.GuiBase;

public class GuiAdvancedPotionBrewery extends GuiBase {

    @SuppressWarnings("unused")
    private TileAdvancedPotionBrewery tileEntity;

    public GuiAdvancedPotionBrewery(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerAdvancedPotionBrewery(inventory, (TileAdvancedPotionBrewery) tile));

        tileEntity = (TileAdvancedPotionBrewery) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
    }
}
