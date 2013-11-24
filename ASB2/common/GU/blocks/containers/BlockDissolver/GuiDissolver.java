package GU.blocks.containers.BlockDissolver;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import ASB2.utils.UtilMisc;
import GU.gui.GuiBase;

public class GuiDissolver extends GuiBase {

    private TileDissolver tileEntity;

    public GuiDissolver(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerDissolver(inventory, (TileDissolver) tile));

        tileEntity = (TileDissolver) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
        this.renderGuage(8, 6);
        this.scalePower(8, 6, UtilMisc.getAmountScaled(75, (int) tileEntity.getPowerProvider().getPowerStored(), (int) tileEntity.getPowerProvider().getPowerMax()));
        this.renderRightArrow(90, 32);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks) {
        super.drawScreen(mouseX, mouseY, gameTicks);

        drawTooltips(tileEntity, mouseX, mouseY, 8, 6, 18, 71);
    }

    @Override
    public void initGui() {
        super.initGui();
    }
}
