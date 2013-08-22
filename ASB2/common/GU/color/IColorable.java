package GU.color;

import net.minecraftforge.common.ForgeDirection;

public interface IColorable {

    public Color getColor(ForgeDirection direction);

    public boolean setColor(Color color, ForgeDirection direction);
}
