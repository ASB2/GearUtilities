package GU.blocks.containers.BlockItemSender;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.gui.GuiBase;

public class GuiItemSender extends GuiBase {

    @SuppressWarnings("unused")
    private TileItemSender tileEntity;

    public GuiItemSender(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerItemSender(inventory, (TileItemSender) tile));

        tileEntity = (TileItemSender)tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks) {
        super.drawScreen(mouseX, mouseY, gameTicks);
    }
    
    @Override
    public void initGui() {
        super.initGui();
    }
}
