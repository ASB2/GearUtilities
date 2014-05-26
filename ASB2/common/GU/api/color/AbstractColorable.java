package GU.api.color;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import UC.color.Color4i;

public final class AbstractColorable {
    
    private AbstractColorable() {
        // TODO Auto-generated constructor stub
    }
    
    public static interface IColorableTile {
        
        public Color4i getColor(ForgeDirection direction);
        
        public boolean setColor(Color4i color, ForgeDirection direction);
    }
    
    public static interface IColorableBlock {
        
        public Color4i getColor(World world, int x, int y, int z, ForgeDirection direction);
        
        public boolean setColor(World world, int x, int y, int z, Color4i color, ForgeDirection direction);
    }
    
    public static interface IColorableItem {
        
        public Color4i getColor(ItemStack stack);
        
        public boolean setColor(ItemStack stack, Color4i color);
    }
    
    public static interface IVanillaColorable {
        
        /**
         * Used for markings. Does NOT refer to the texture color of the block
         */
        VanillaColor getColorEnum();
        
        /**
         * Sets the color of the block
         */
        void setColor(VanillaColor color);
    }
    
}
