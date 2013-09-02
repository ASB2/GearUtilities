package GU.blocks.containers.BlockAdvancedPotionBrewery;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.gui.GuiBase;
import GU.utils.UtilMisc;
import GU.utils.UtilRender;

public class GuiAdvancedPotionBrewery extends GuiBase {

    private TileAdvancedPotionBrewery tileEntity;

    public GuiAdvancedPotionBrewery(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerAdvancedPotionBrewery(inventory, (TileAdvancedPotionBrewery) tile));

        tileEntity = (TileAdvancedPotionBrewery)tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();

        this.renderGuage(8, 6);
        this.scalePower(8, 6, UtilMisc.getAmountScaled(75, (int)tileEntity.getPowerProvider().getPowerStored(), (int)tileEntity.getPowerProvider().getPowerMax()));

        this.renderBigSlot(83, 54);
        this.renderRightArrow(53, 53);
        this.renderGuage(151, 6);

        if(tileEntity != null) {

            if(tileEntity.fluidTank != null) {

                if(tileEntity.fluidTank.getFluidAmount() > 0) {

                    if(tileEntity.fluidTank.getFluid() != null) {

                        UtilRender.bindBlockTextures();
                                                this.scaleFluid(151, 6, UtilMisc.getAmountScaled(75, tileEntity.fluidTank.getFluidAmount(), tileEntity.fluidTank.getCapacity()), tileEntity.fluidTank.getFluid().getFluid().getStillIcon());
                                                this.renderGuageOverLay(151, 6);
                        this.renderIcon(151, 6,tileEntity.fluidTank.getFluid().getFluid().getStillIcon(), 16, 16);
                    }
                }
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks) {
        super.drawScreen(mouseX, mouseY, gameTicks);

        drawTooltips(tileEntity, mouseX, mouseY, 8, 6, 18, 71);
        
        if(tileEntity.fluidTank.getFluid() != null) {

            drawTooltips(tileEntity.fluidTank.getCapacity(), tileEntity.fluidTank.getFluidAmount(), tileEntity.fluidTank.getFluid().getFluid().getLocalizedName(), mouseX, mouseY, 151, 6, 18, 71);
        }
        else {
            
            drawTooltips(tileEntity.fluidTank.getCapacity(), 0, "None", mouseX, mouseY, 151, 6, 18, 71);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        buttonList.add(new GuiButton(0, posX + 110, posY + 51, 32, 20, "Craft"));
    }
}
