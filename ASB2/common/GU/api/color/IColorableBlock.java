package GU.api.color;

import java.awt.Color;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public interface IColorableBlock {
    
    public Color getColor(World world, int x, int y, int z, ForgeDirection direction);
    
    public boolean setColor(World world, int x, int y, int z, Color color, ForgeDirection direction);
}
