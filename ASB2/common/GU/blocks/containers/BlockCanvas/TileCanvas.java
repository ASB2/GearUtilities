package GU.blocks.containers.BlockCanvas;

import net.minecraftforge.common.ForgeDirection;
import GU.blocks.containers.TileBase;
import GU.color.Color;
import GU.color.IColorable;

public class TileCanvas extends TileBase implements IColorable {

    Color currentColor = new Color();

    @Override
    public Color getColor(ForgeDirection direction) {

        return currentColor;
    }

    @Override
    public boolean setColor(Color color, ForgeDirection direction) {

        currentColor = color;
        worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
        return true;
    }
}
