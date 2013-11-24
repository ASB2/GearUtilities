package GU.blocks.containers.BlockSender;

import java.awt.Color;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;

import ASB2.utils.UtilMisc;
import ASB2.utils.UtilRender;
import GU.gui.GuiBase;

public class GuiSender extends GuiBase {

    private TileSender tileEntity;

    public GuiSender(InventoryPlayer inventory, TileEntity tile) {
        super(new ContainerSender(inventory, (TileSender) tile));

        tileEntity = (TileSender) tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {

        this.renderDefaultGui();
        this.renderGuage(8, 6);
        this.scalePower(8, 6, UtilMisc.getAmountScaled(75, (int) tileEntity.getPowerProvider().getPowerStored(), (int) tileEntity.getPowerProvider().getPowerMax()));
        this.renderGuageOverLay(38, 6);

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_LIGHTING);
        
        UtilRender.bindBlockTextures();
        
        if(tileEntity.fluidTank.getFluid() != null) {

            Color color = new Color(tileEntity.fluidTank.getFluid().getFluid().getColor());
            
            GL11.glColor3f(color.getRed(), color.getGreen(), color.getBlue());
            this.scaleFluid(38, 6, UtilMisc.getAmountScaled(75, tileEntity.fluidTank.getFluidAmount(), tileEntity.fluidTank.getCapacity()), tileEntity.fluidTank.getFluid().getFluid().getStillIcon());
        }
        
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float gameTicks) {
        super.drawScreen(mouseX, mouseY, gameTicks);

        drawTooltips(tileEntity, mouseX, mouseY, 8, 6, 18, 71);

        if(tileEntity.fluidTank.getFluid() != null) {

            drawTooltips(tileEntity.fluidTank.getCapacity(), tileEntity.fluidTank.getFluidAmount(), tileEntity.fluidTank.getFluid().getFluid().getName(), mouseX, mouseY, 38, 6, 18, 71);
        }
        else {

            drawTooltips(tileEntity.fluidTank.getCapacity(), tileEntity.fluidTank.getFluidAmount(), "None", mouseX, mouseY, 38, 6, 18, 71);
        }
    }

    @Override
    public void initGui() {
        super.initGui();
    }
}
