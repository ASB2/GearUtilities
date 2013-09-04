package GU.blocks.containers.BlockCreationTable;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import ASB2.utils.UtilMisc;
import GU.gui.GuiBase;

public class GuiCreationTable extends GuiBase {

    private TileCreationTable tileEntity;

    public GuiCreationTable(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerCreationTable(inventory, (TileCreationTable) tile));

        tileEntity = (TileCreationTable) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
        this.renderGuage(8, 6);
        this.renderBigSlot(148, 32);
        this.renderRightArrow(118, 32);
        this.scalePower(8, 6, UtilMisc.getAmountScaled(75, (int) tileEntity.getPowerProvider().getPowerStored(), (int) tileEntity.getPowerProvider().getPowerMax()));
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks) {
        super.drawScreen(mouseX, mouseY, gameTicks);

        drawTooltips(tileEntity, mouseX, mouseY, 8, 6, 18, 71);
    }
}
