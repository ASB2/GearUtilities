package GU.blocks.containers.BlockElectisCrystal;

import java.awt.Color;

import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import GU.blocks.containers.TileBase;
import GU.api.color.*;

public class TileElectisCrystal extends TileBase implements IColorableBlock {
    
    public static Color[] COLORS = new Color[] { Color.WHITE, Color.BLUE, Color.BLACK };
    
    int crystalLevel = 0;
    
    public TileElectisCrystal() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public Color getColor(World world, int x, int y, int z, ForgeDirection direction) {
        
        return COLORS[crystalLevel];
    }
    
    @Override
    public boolean setColor(World world, int x, int y, int z, Color color, ForgeDirection direction) {
        
        COLORS[crystalLevel] = color;
        return true;
    }
    
}
