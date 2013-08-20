package GU.blocks.containers.BlockCanvas;

import net.minecraftforge.common.ForgeDirection;
import GU.blocks.containers.TileBase;
import GU.color.Color;
import GU.color.IColorable;

public class TileCanvas extends TileBase implements IColorable {

    Color currentColor = new Color(1, 1, 1, 1);

    @Override
    public boolean setColor(Color color, ForgeDirection direction) {

        currentColor = color;
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return true;
    }

    @Override
    public Color getColor(ForgeDirection direction) {

        return currentColor;
    }

    @Override
    public boolean changeRed(float amount, ForgeDirection side) {

        currentColor.setRed(currentColor.getRed() + amount);

        return true;
    }

    @Override
    public boolean changeGreen(float amount, ForgeDirection side) {

        currentColor.setGreen(currentColor.getGreen() + amount);
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return false;
    }

    @Override
    public boolean changeBlue(float amount, ForgeDirection side) {

        currentColor.setBlue(currentColor.getBlue() + amount);
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return false;
    }

    @Override
    public boolean changeAlpha(float amount, ForgeDirection side) {

        currentColor.setAlpha(currentColor.getAlpha() + amount);
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return false;
    }
}
