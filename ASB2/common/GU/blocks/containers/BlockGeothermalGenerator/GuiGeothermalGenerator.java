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
    public void drawScreen(int mouseX, int mouseY, float gameTicks) {
        super.drawScreen(mouseX, mouseY, gameTicks);

        drawTooltips(tileEntity, mouseX, mouseY, 9, 6, 18, 71);
        drawTooltips(tileEntity.fluidTank.getCapacity(), tileEntity.fluidTank.getFluidAmount(), tileEntity.fluidTank.getFluid().getFluid().getName(), mouseX, mouseY, 150, 7, 18, 71);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();

        this.addTank(posX + 150, posY + 7);

        if(tileEntity.fluidTank.getFluid() != null && tileEntity.fluidTank.getFluid().getFluid().getIcon() != null) {

            this.scaleLiquid(posX + 150, posY + 7, UtilMisc.getAmountScaled(75, tileEntity.fluidTank.getFluidAmount(), tileEntity.fluidTank.getCapacity()), tileEntity.fluidTank.getFluid().getFluid().getIcon());
        }
        this.addPowerTank(posX + 8, posY + 6);
        this.scalePower(posX + 8, posY + 6, UtilMisc.getAmountScaled(75, (int)tileEntity.getPowerProvider().getPowerStored(), (int)tileEntity.getPowerProvider().getPowerMax()));
    }


}