package GU.blocks.containers.BlockAdvancedPotionBrewery;

import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import GU.gui.GuiBase;
import GU.utils.UtilRender;
import GU.packets.*;

public class GuiAdvancedPotionBrewery extends GuiBase {

    private TileAdvancedPotionBrewery tileEntity;

    public GuiAdvancedPotionBrewery(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerAdvancedPotionBrewery(inventory, (TileAdvancedPotionBrewery) tile));

        tileEntity = (TileAdvancedPotionBrewery)tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
        this.renderBigSlot(83, 54);
        this.renderRightArrow(53, 53);
        this.renderGuage(151, 6);

        if(tileEntity != null) {

            if(tileEntity.fluidTank != null) {

                if(tileEntity.fluidTank.getFluidAmount() > 0) {

                    if(tileEntity.fluidTank.getFluid() != null) {

                        UtilRender.bindBlockTextures();
//                        this.scaleFluid(151, 6, UtilMisc.getAmountScaled(75, tileEntity.fluidTank.getFluidAmount(), tileEntity.fluidTank.getCapacity()), tileEntity.fluidTank.getFluid().getFluid().getStillIcon());
//                        this.renderGuageOverLay(151, 6);
                    this.renderIcon(151, 6,tileEntity.fluidTank.getFluid().getFluid().getStillIcon(), 16, 16);
                    }
                }
            }
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

    @Override
    protected void actionPerformed(GuiButton button) {

        PacketDispatcher.sendPacketToServer(new ButtonPressPacket(button.id).makePacket());
    }
}
