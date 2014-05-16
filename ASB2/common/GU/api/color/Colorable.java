package GU.api.color;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import UC.color.Color4f;

public final class Colorable {
    
    public static interface IColorableTile {
        
        public Color4f getColor(ForgeDirection direction);
        
        public boolean setColor(Color4f color, ForgeDirection direction);
    }
    
    public static interface IColorableBlock {
        
        public Color4f getColor(World world, int x, int y, int z, ForgeDirection direction);
        
        public boolean setColor(World world, int x, int y, int z, Color4f color, ForgeDirection direction);
    }
    
    public static interface IColorableItem {
        
        public Color4f getColor(ItemStack stack);
        
        public boolean setColor(ItemStack stack, Color4f color);
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
