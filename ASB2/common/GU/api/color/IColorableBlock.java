package GU.api.color;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import UC.color.Color4f;

public interface IColorableBlock {
    
    public Color4f getColor(World world, int x, int y, int z, ForgeDirection direction);
    
    public boolean setColor(World world, int x, int y, int z, Color4f color, ForgeDirection direction);
}
