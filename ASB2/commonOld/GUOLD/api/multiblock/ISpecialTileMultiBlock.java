package GUOLD.api.multiblock;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface ISpecialTileMultiBlock {
    
    TileEntity getBlockTileEntity(World world, int x, int y, int z);
}
