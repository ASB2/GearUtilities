package GU.blocks.containers.BlockCreationTable;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.gui.GuiBase;
import GU.utils.UtilMisc;

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
        this.scalePower(posX + 7, posY + 5, UtilMisc.getAmountScaled(75, (int) tileEntity.getPowerProvider().getPowerStored(), (int) tileEntity.getPowerProvider().getPowerMax()));
    }
}
