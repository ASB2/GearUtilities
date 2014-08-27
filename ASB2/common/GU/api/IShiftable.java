package GU.api;

import net.minecraft.world.World;

public interface IShiftable {
    
    public void shiftBlock(World world, int x, int y, int z, int side, Object... caller);
}
