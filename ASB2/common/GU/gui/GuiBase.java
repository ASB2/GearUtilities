package GU.gui;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import GU.api.power.IPowerMisc;
import GU.info.Gui;
import GU.utils.UtilRender;

public abstract class GuiBase extends GuiContainer {

    ContainerBase container;

    public final int xSizeOfTexture = 176;
    public final int ySizeOfTexture = 166;
    public final int powerBarScale = 75;
    
    protected int posX = (width - xSizeOfTexture) / 2;
    protected int posY = (height - ySizeOfTexture) / 2;

    public GuiBase(ContainerBase container) {
        super(container);

        this.container = container;
        container.finishConstructing(this);
    }

    protected void drawGuiContainerForegroundLayer(int x, int y) {

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

            addSlot(posX + container.slotList.get(i).xDisplayPosition - 1, posY + container.slotList.get(i).yDisplayPosition - 1);
        }
    }
        
    public void addSlot(int x, int y) {

        drawTexturedModalRect(x, y, 176, 32, 18, 18);
    }

    public void renderBigSlot(int x, int y) {

        drawTexturedModalRect(x, y, 226, 4, 26, 26);
    }

    public void renderFlame(int x, int y) {

        drawTexturedModalRect(x, y, 191, 0, 14, 14);
    }

    public void renderBurningFlame(int x, int y) {

        drawTexturedModalRect(x, y, 176, 0, 14, 14);
    }

    public void scaleFlame(int x, int y,int scale) {

        drawTexturedModalRect(x, y + 14 - scale, 191, 0, 14, scale);
    }

    public void scaleBurningFlame(int x, int y,int scale) {

        drawTexturedModalRect(x, y + 14 - scale, 176, 0 , 14, scale);
    }

    public void addTank(int x, int y) {

        drawTexturedModalRect(x, y, 176, 123, 18, 71);
    }

    public void renderRightArrow(int x, int y) {

        drawTexturedModalRect(x, y, 200, 14, 24, 17);
    }

    public void renderLeftArrow(int x, int y) {

        drawTexturedModalRect(x, y, 195, 32, 24, 17);
    }

    public void addPowerTank(int x, int y) {

        drawTexturedModalRect(x, y, 176, 51, 18, 71);
    }

    public void scalePower(int x, int y, int scale) {

        drawTexturedModalRect(x, y + 71 - scale, 194, 51, 18, scale);
    }

    protected void drawTooltips(IPowerMisc tileEntity, int mouseX, int mouseY, int coordX, int coordY, int maxX, int maxY) {

        if(tileEntity != null && tileEntity.getPowerProvider() != null) {

            if(isPointInRegion(coordX, coordY, maxX, maxY, mouseX, mouseY)) {

                drawBarTooltip("Energy", "TCU", tileEntity.getPowerProvider().getPowerStored(), tileEntity.getPowerProvider().getPowerMax(), mouseX, mouseY);
            }
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

        for(int i = 0; i < stringList.size(); i++)
        {
            tempWidth = this.fontRenderer.getStringWidth(stringList.get(i));

            if(tempWidth > tooltipWidth)
            {
                tooltipWidth = tempWidth;
            }
        }

        xStart = x + 12;
        yStart = y - 12;
        int tooltipHeight = 8;

        if(stringList.size() > 1)
        {
            tooltipHeight += 2 + (stringList.size() - 1) * 10;
        }

        if(this.guiTop + yStart + tooltipHeight + 6 > this.height)
        {
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

        for(int stringIndex = 0; stringIndex < stringList.size(); ++stringIndex)
        {
            String line = stringList.get(stringIndex);

            if(stringIndex == 0)
            {
                line = "\u00a7" + Integer.toHexString(15) + line;
            }
            else
            {
                line = "\u00a77" + line;
            }

            this.fontRenderer.drawStringWithShadow(line, xStart, yStart, -1);

            if(stringIndex == 0)
            {
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
