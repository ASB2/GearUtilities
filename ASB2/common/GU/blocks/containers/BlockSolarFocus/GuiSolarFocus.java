package GU.blocks.containers.BlockSolarFocus;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import ASB2.utils.UtilMisc;
import GU.gui.GuiBase;

public class GuiSolarFocus extends GuiBase {

    private TileSolarFocus tileEntity;

    public GuiSolarFocus(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerSolarFocus(inventory, (TileSolarFocus) tile));

        tileEntity = (TileSolarFocus) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
        this.renderGuage(8, 6);
        this.scalePower(8, 6, UtilMisc.getAmountScaled(75, (int) tileEntity.getPowerProvider().getPowerStored(), (int) tileEntity.getPowerProvider().getPowerMax()));
    }
    
    @Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks) {
        super.drawScreen(mouseX, mouseY, gameTicks);

        drawTooltips(tileEntity, mouseX, mouseY, 8, 6, 18, 71);
    }
}