package GU.color;

import net.minecraftforge.common.ForgeDirection;

public interface IColorable {
    
    public int getAnimationBrightness(ForgeDirection direction);

    public void setColor(Color color, ForgeDirection direction);
    
    public Color getColor(ForgeDirection direction);
}
