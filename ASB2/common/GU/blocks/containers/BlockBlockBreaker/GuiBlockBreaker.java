package GU.blocks.containers.BlockBlockBreaker;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.gui.GuiBase;

public class GuiBlockBreaker extends GuiBase {

    @SuppressWarnings("unused")
    private TileBlockBreaker tileEntity;

    public GuiBlockBreaker(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerBlockBreaker(inventory, (TileBlockBreaker) tile));

        tileEntity = (TileBlockBreaker) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
    }
}
