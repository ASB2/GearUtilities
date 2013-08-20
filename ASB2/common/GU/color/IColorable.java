package GU.color;

import net.minecraftforge.common.ForgeDirection;

public interface IColorable {

    public Color getColor(ForgeDirection direction);

    public boolean setColor(Color color, ForgeDirection direction);

    public boolean changeRed(float amount, ForgeDirection side);

    public boolean changeGreen(float amount, ForgeDirection side);

    public boolean changeBlue(float amount, ForgeDirection side);

    public boolean changeAlpha(float amount, ForgeDirection side);
}
