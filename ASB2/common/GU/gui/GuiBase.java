package GU.gui;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.Icon;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import ASB2.utils.UtilRender;
import GU.api.power.IPowerMisc;
import GU.info.Gui;
import GU.info.Reference;
import GU.packets.ButtonPressPacket;
import cpw.mods.fml.common.network.PacketDispatcher;

public abstract class GuiBase extends GuiContainer {

    ContainerBase container;

    public final int xSizeOfTexture = 176;
    public final int ySizeOfTexture = 166;
    public final int powerBarScale = 75;

    protected int posX;
    protected int posY;

    public GuiBase(ContainerBase container) {
        super(container);

        this.container = container;
        container.finishConstructing(this);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {

    }

    @Override
    protected void actionPerformed(GuiButton button) {

        PacketDispatcher.sendPacketToServer(new ButtonPressPacket(button.id).makePacket());
    }

    public void renderDefaultGui() {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX, posY, 0, 0, xSizeOfTexture, ySizeOfTexture);

        this.renderSlotsInContainer();
    }

    public void renderSlotsInContainer() {

        for(int i = 0; i < container.slotList.size(); i++) {

            renderSlot(posX + container.slotList.get(i).xDisplayPosition - 1, posY + container.slotList.get(i).yDisplayPosition - 1);
        }
    }

    public void renderSlot(int x, int y) {

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(x, y, 176, 32, 18, 18);
    }

    public void renderBigSlot(int x, int y) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX + x - 5, posY + y - 5, 226, 4, 26, 26);
    }

    public void renderFlame(int x, int y) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX + x, posY + y, 191, 0, 14, 14);
    }

    public void renderBurningFlame(int x, int y) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX + x, posY + y, 176, 0, 14, 14);
    }

    public void scaleFlame(int x, int y, int scale) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX + x, posY + y + 14 - scale, 191, 0, 14, scale);
    }

    public void scaleBurningFlame(int x, int y, int scale) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX + x, posY + y + 14 - scale, 176, 0, 14, scale);
    }

    public void renderTank(int x, int y) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX + x, posY + y, 176, 123, 18, 71);
    }

    public void scaleLiquid(int x, int y, int scale, Icon icon) {

        // Tessellator tessellator = Tessellator.instance;
        // tessellator.startDrawingQuads();
        // tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 +
        // par5), (double) this.zLevel, (double) par3Icon.getMinU(), (double)
        // par3Icon.getMaxV());
        // tessellator.addVertexWithUV((double) (par1 + par4), (double) (par2 +
        // par5), (double) this.zLevel, (double) par3Icon.getMaxU(), (double)
        // par3Icon.getMaxV());
        // tessellator.addVertexWithUV((double) (par1 + par4), (double) (par2 +
        // 0), (double) this.zLevel, (double) par3Icon.getMaxU(), (double)
        // par3Icon.getMinV());
        // tessellator.addVertexWithUV((double) (par1 + 0), (double) (par2 + 0),
        // (double) this.zLevel, (double) par3Icon.getMinU(), (double)
        // par3Icon.getMinV());
        // tessellator.draw();
    }

    public void renderRightArrow(int x, int y) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX + x, posY + y, 200, 14, 24, 17);
    }

    public void renderLeftArrow(int x, int y) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX + x, posY + y, 195, 32, 24, 17);
    }

    public void renderGuage(int x, int y) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX + x, posY + y, 176, 51, 18, 71);
    }

    public void scalePower(int x, int y, int scale) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);

        if(75 == scale) {

            drawTexturedModalRect(posX + x + 2, posY + y + 71 - scale + 6, 197, 53, 18, scale);
        }
        else {

            drawTexturedModalRect(posX + x + 2, posY + y + 71 - scale + 6, 197, 53, 18, scale);
        }
    }

    public void scaleFluid(int x, int y, int scale, Icon icon) {

        if(icon != null) {

            posX = (width - xSizeOfTexture) / 2;
            posY = (height - ySizeOfTexture) / 2;

            if(75 == scale) {

                drawTexturedModelRectFromIcon(posX + x + 2, posY + y + 75 - scale + 2, icon, 18 - 4, scale - 8);
            }
            else {

                drawTexturedModelRectFromIcon(posX + x + 2, posY + y + 71 - scale + 2, icon, 18 - 4, scale - 8);
            }
        }
    }

    public void renderGuageOverLay(int x, int y) {

        posX = (width - xSizeOfTexture) / 2;
        posY = (height - ySizeOfTexture) / 2;

        UtilRender.renderTexture(Gui.GUI_DEFAULT);
        drawTexturedModalRect(posX + x, posY + y, 176, 123, 18, 71);
    }

    public void renderIcon(int x, int y, Icon icon, int width, int height) {

        if(icon != null) {

            posX = (width - xSizeOfTexture) / 2;
            posY = (height - ySizeOfTexture) / 2;

            drawTexturedModelRectFromIcon(posX + x, posY + y, icon, width, height);
        }
    }

    protected void drawTooltips(IPowerMisc tileEntity, int mouseX, int mouseY, int coordX, int coordY, int maxX, int maxY) {

        if(tileEntity != null && tileEntity.getPowerProvider() != null) {

            if(isPointInRegion(coordX, coordY, maxX, maxY, mouseX, mouseY)) {

                drawBarTooltip("Energy", Reference.POWER_NAME, (int) tileEntity.getPowerProvider().getPowerStored(), (int) tileEntity.getPowerProvider().getPowerMax(), mouseX, mouseY);
            }
        }
    }

    protected void drawTooltips(int fluidMax, int fluidAmount, String fluidName, int mouseX, int mouseY, int coordX, int coordY, int maxX, int maxY) {

        if(isPointInRegion(coordX, coordY, maxX, maxY, mouseX, mouseY)) {

            drawBarTooltip("Fluid: " + fluidName, "mB", fluidAmount, fluidMax, mouseX, mouseY);
        }
    }

    protected void drawBarTooltip(String name, String unit, int value, int max, int x, int y) {

        GL11.glPushMatrix();
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glDisable(GL11.GL_LIGHTING);

        List<String> stringList = new LinkedList<String>();
        stringList.add(name);
        stringList.add(value + " / " + max + " " + unit);

        int tooltipWidth = 0;
        int tempWidth;
        int xStart;
        int yStart;

        for(int i = 0; i < stringList.size(); i++) {

            tempWidth = this.fontRenderer.getStringWidth(stringList.get(i));

            if(tempWidth > tooltipWidth) {

                tooltipWidth = tempWidth;
            }
        }

        xStart = x + 12;
        yStart = y - 12;
        int tooltipHeight = 8;

        if(stringList.size() > 1) {

            tooltipHeight += 2 + (stringList.size() - 1) * 10;
        }

        if(this.guiTop + yStart + tooltipHeight + 6 > this.height) {

            yStart = this.height - tooltipHeight - this.guiTop - 6;
        }

        this.zLevel = 300.0F;
        itemRenderer.zLevel = 300.0F;
        int color1 = -267386864;
        this.drawGradientRect(xStart - 3, yStart - 4, xStart + tooltipWidth + 3, yStart - 3, color1, color1);
        this.drawGradientRect(xStart - 3, yStart + tooltipHeight + 3, xStart + tooltipWidth + 3, yStart + tooltipHeight + 4, color1, color1);
        this.drawGradientRect(xStart - 3, yStart - 3, xStart + tooltipWidth + 3, yStart + tooltipHeight + 3, color1, color1);
        this.drawGradientRect(xStart - 4, yStart - 3, xStart - 3, yStart + tooltipHeight + 3, color1, color1);
        this.drawGradientRect(xStart + tooltipWidth + 3, yStart - 3, xStart + tooltipWidth + 4, yStart + tooltipHeight + 3, color1, color1);
        int color2 = 1347420415;
        int color3 = (color2 & 16711422) >> 1 | color2 & -16777216;
        this.drawGradientRect(xStart - 3, yStart - 3 + 1, xStart - 3 + 1, yStart + tooltipHeight + 3 - 1, color2, color3);
        this.drawGradientRect(xStart + tooltipWidth + 2, yStart - 3 + 1, xStart + tooltipWidth + 3, yStart + tooltipHeight + 3 - 1, color2, color3);
        this.drawGradientRect(xStart - 3, yStart - 3, xStart + tooltipWidth + 3, yStart - 3 + 1, color2, color2);
        this.drawGradientRect(xStart - 3, yStart + tooltipHeight + 2, xStart + tooltipWidth + 3, yStart + tooltipHeight + 3, color3, color3);

        for(int stringIndex = 0; stringIndex < stringList.size(); ++stringIndex) {
            String line = stringList.get(stringIndex);

            if(stringIndex == 0) {
                line = "\u00a7" + Integer.toHexString(15) + line;
            }
            else {
                line = "\u00a77" + line;
            }

            this.fontRenderer.drawStringWithShadow(line, xStart, yStart, -1);

            if(stringIndex == 0) {
                yStart += 2;
            }

            yStart += 10;
        }

        GL11.glPopMatrix();
        GL11.glEnable(GL11.GL_DEPTH_TEST);

        this.zLevel = 0.0F;
        itemRenderer.zLevel = 0.0F;
    }
}
