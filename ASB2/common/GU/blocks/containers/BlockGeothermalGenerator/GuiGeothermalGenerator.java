package GU.blocks.containers.BlockGeothermalGenerator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.gui.GuiBase;
import GU.utils.UtilMisc;

public class GuiGeothermalGenerator extends GuiBase {

    private TileGeothermalGenerator tileEntity;

    public GuiGeothermalGenerator(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerGeothermalGenerator(inventory, (TileGeothermalGenerator)tile));

        tileEntity = (TileGeothermalGenerator) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
        this.addPowerTank(posX + 8, posY + 6);
        this.scalePower(posX + 7, posY + 5, UtilMisc.getAmountScaled(75, (int)tileEntity.getPowerProvider().getPowerStored(), (int)tileEntity.getPowerProvider().getPowerMax()));
    }
}